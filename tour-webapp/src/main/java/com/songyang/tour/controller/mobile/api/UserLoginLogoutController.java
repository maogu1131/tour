package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/12/5.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.component.SessionServiceComponent;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
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

/**
 * 用户登录登出controller
 *
 * @author
 * @create 2017-12-05 18:40
 **/
@Controller
@RequestMapping("/mobile/api")
public class UserLoginLogoutController {

    private Logger logger = LoggerFactory.getLogger(UserLoginLogoutController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private SyUserService syUserService;

    @Resource
    private SessionServiceComponent sessionServiceComponent;

    public static final String DEFAULT_HEAD="/other/default_head_portrait.jpg";


    @ResponseBody
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<JSONObject> login(final HttpServletRequest request, final HttpServletResponse response) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                String phone = param.getString("phone");
                String password = param.getString("password");
                //参数验证
                if (StringUtils.isBlank(phone)) {
                    throw new TourBizException("手机号码不能为空");
                }
                if (StringUtils.isBlank(password)) {
                    throw new TourBizException("密码不能为空");
                }

                JSONObject jsonObject = new JSONObject();
                SyUserQuery query = new SyUserQuery();
                query.setPhone(phone);
                query.setOffset(0);
                query.setRows(1);
                List<SyUser> syUserList = syUserService.queryListByParam(query);
                if (CollectionUtils.isEmpty(syUserList)) {
                    logger.warn("UserLoginLogoutController#login_user_is_null>>>phone:" + phone);
                    throw new TourBizException("手机号码或密码不正确");
                }
                SyUser user = syUserList.get(0);
                String dbPass = user.getPassword();
                String dbPassSub = StringUtils.substring(dbPass, 6, dbPass.length());
                String requestPass = Base64.encodeBase64String(password.getBytes());
                if (!StringUtils.equals(dbPassSub, requestPass)) {
                    logger.warn("UserLoginLogoutController#login_password_is_wrong>>>phone:" + phone);
                    throw new TourBizException("手机号码或密码不正确");
                }

                String sessionId = JSessionIdUtils.generateJSessionId(JSessionIdUtils.JSESSIONID_PREFIX_MOBILE);
                //写cookie；
                /**Cookie loginCookie = new Cookie(SessionUtil.MOBILE_SESSIONID_COOKIE_NAME, sessionId);
                loginCookie.setDomain(SessionUtil.COOKIE_DOMAIN);
                loginCookie.setPath(SessionUtil.COOKIE_PATH);
                loginCookie.setMaxAge(SessionUtil.COOKIE_MAX_AGE);
                response.addCookie(loginCookie);**/

                jsonObject.put(SessionUtil.MOBILE_SESSIONID_COOKIE_NAME, sessionId);
                jsonObject.put("name", user.getName());
                jsonObject.put("phone", user.getPhone());
                String userImg = user.getUserImg();
                if(StringUtils.isNotBlank(userImg) && userImg.startsWith("http")){
                    jsonObject.put("userImg", userImg);
                }else{
                    jsonObject.put("userImg", CommonUtil.analyzeOnePicUrl(userImg));
                }
                //写redis；
                sessionServiceComponent.storeSession(user.getUserId(), sessionId, SessionUtil.COOKIE_MAX_AGE);
                return Model.buidSucc(jsonObject);
            }
        }, logger, "用户登录异常");
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<Void> logoutForWeb(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<Void>() {
            @Override
            public Model<Void> doExecute() throws IOException {
                String jSessionId = SessionUtil.getSessionId(request);
                sessionServiceComponent.deleteSessionId(jSessionId);
                return Model.buidSucc();
            }
        }, logger, "用户退出异常");

    }

}
