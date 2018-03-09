package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/12/5.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.component.SessionServiceComponent;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.query.SyUserQuery;
import com.songyang.tour.service.SyUserService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.JSessionIdUtils;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.utils.SessionUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户注册controller
 *
 * @author
 * @create 2017-12-05 18:53
 **/
@Controller
@RequestMapping("/mobile/api")
public class UserRegisterController {
    private Logger logger = LoggerFactory.getLogger(UserRegisterController.class);

    public static final String PATTERN = "0?(13|14|15|18)[0-9]{9}";

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private SyUserService syUserService;

    @Resource
    private SessionServiceComponent sessionServiceComponent;


    @ResponseBody
    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<JSONObject> register(final HttpServletRequest request, final HttpServletResponse response) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                JSONObject jsonObject = new JSONObject();
                String userName = param.getString("userName");
                String phone = param.getString("phone");
                String password = param.getString("password");
                //参数验证
                if (StringUtils.isBlank(userName)) {
                    throw new TourBizException("用户名不能为空");
                }
                if (StringUtils.isBlank(phone)) {
                    throw new TourBizException("手机号码不能为空");
                }
                if (StringUtils.isBlank(password)) {
                    throw new TourBizException("密码不能为空");
                }
                if (password.length() < 6) {
                    throw new TourBizException("密码需大于6位");
                }
                if (password.length() > 30) {
                    throw new TourBizException("密码需小于30位");
                }

                Pattern r = Pattern.compile(PATTERN);
                Matcher m = r.matcher(phone);
                if (!m.matches()) {
                    throw new TourBizException("手机格式不正确");
                }


                SyUserQuery userQuery = new SyUserQuery();
                userQuery.setPhone(phone.trim());
                userQuery.setOffset(0);
                userQuery.setRows(1);
                List<SyUser> syUserList = syUserService.queryListByParam(userQuery);
                if (CollectionUtils.isNotEmpty(syUserList)) {
                    throw new TourBizException("该手机号码已被注册");
                }

                SyUser user = new SyUser();
                user.setPhone(phone);
                //BASE64加密 前缀随机六位
                String prefix = "";
                for (int i = 0; i < 6; i++) {
                    int ranNum = RandomUtils.nextInt(24);
                    prefix += TourConstants.RANDOM_CHAR[ranNum];
                }

                String newPassword = prefix + Base64.encodeBase64String(password.getBytes()).trim();
                user.setPassword(newPassword);
                user.setName(userName);
                user.setUserImg(UserLoginLogoutController.DEFAULT_HEAD);
                //先保存，获取自增id，进行更新为uid
                syUserService.insert(user);


                SyUser updateUser = new SyUser();
                updateUser.setId(user.getId());
                updateUser.setUserId(CommonUtil.buildUserId(user.getId()));
                //更新用户id
                syUserService.updateById(updateUser);

                String sessionId = JSessionIdUtils.generateJSessionId(JSessionIdUtils.JSESSIONID_PREFIX_MOBILE);
                //写cookie；
                /**Cookie loginCookie = new Cookie(SessionUtil.MOBILE_SESSIONID_COOKIE_NAME, sessionId);
                 loginCookie.setDomain(SessionUtil.COOKIE_DOMAIN);
                 loginCookie.setPath(SessionUtil.COOKIE_PATH);
                 loginCookie.setMaxAge(SessionUtil.COOKIE_MAX_AGE);
                 response.addCookie(loginCookie);**/

                jsonObject.put(SessionUtil.MOBILE_SESSIONID_COOKIE_NAME, sessionId);
                jsonObject.put("userId", user.getUserId());
                jsonObject.put("name", user.getName());
                jsonObject.put("phone", user.getPhone());
                String userImg = user.getUserImg();
                if(StringUtils.isNotBlank(userImg) && userImg.startsWith("http")){
                    jsonObject.put("userImg", userImg);
                }else{
                    jsonObject.put("userImg", CommonUtil.analyzeOnePicUrl(userImg));
                }

                //写redis；
                sessionServiceComponent.storeSession(updateUser.getUserId(), sessionId, SessionUtil.COOKIE_MAX_AGE);
                return Model.buidSucc(jsonObject);
            }
        }, logger, "用户注册异常");
    }

    public static void main(String[] args) {
        Pattern r = Pattern.compile(PATTERN);
        Matcher m = r.matcher("15258889143");
        if (!m.matches()) {
            System.out.println("不匹配");
        }
    }
}
