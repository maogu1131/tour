package com.songyang.tour.controller.mobile.api;

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.component.SessionServiceComponent;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.QQLoginComponent;
import com.songyang.tour.components.WeiXinLoginComponent;
import com.songyang.tour.constants.ModelStatusCodeConstants;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.JSessionIdUtils;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.utils.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;


@Controller
@RequestMapping("/mobile/api")
public class WeiXinAuthController {

    private static final Logger LOG = LoggerFactory.getLogger(WeiXinAuthController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private WeiXinLoginComponent weiXinLoginComponent;

    @Resource
    private QQLoginComponent qqLoginComponent;

    @Resource
    private SessionServiceComponent sessionServiceComponent;

    @ResponseBody
    @RequestMapping(value = "/weiXin/login")
    public Model<JSONObject>  login(final HttpServletRequest request) {


        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                String code = param.getString("code");
                JSONObject jsonObject = new JSONObject();
                SyUser syUser = weiXinLoginComponent.login(code);

                if(syUser != null){
                    String sessionId = JSessionIdUtils.generateJSessionId(JSessionIdUtils.JSESSIONID_PREFIX_MOBILE);
                    jsonObject.put(SessionUtil.MOBILE_SESSIONID_COOKIE_NAME, sessionId);
                    jsonObject.put("userId", syUser.getUserId());
                    jsonObject.put("name", syUser.getName());
                    jsonObject.put("phone", syUser.getPhone());
                    String userImg = syUser.getUserImg();
                    if(StringUtils.isNotBlank(userImg) && userImg.startsWith("http")){
                        jsonObject.put("userImg", userImg);
                    }else {
                        jsonObject.put("userImg", userImg);
                    }
                    //写redis；
                    sessionServiceComponent.storeSession(syUser.getUserId(), sessionId, SessionUtil.COOKIE_MAX_AGE);
                    return Model.buidSucc(jsonObject);
                }else{
                    return Model.buidFail(ModelStatusCodeConstants.BUSINESS_ERROR,"微信授权登录失败");
                }
            }
        }, LOG, "微信登录失败");
    }

    @ResponseBody
    @RequestMapping(value = "/qq/login")
    public Model<JSONObject>  qqlogin(final HttpServletRequest request) {


        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                String accessToken = param.getString("accessToken");
                String openId = param.getString("openId");
                String appId = param.getString("appId");
                JSONObject jsonObject = new JSONObject();
                SyUser syUser = qqLoginComponent.login(openId,appId,accessToken);
//                SyUser syUser = qqLoginComponent.login("26D9AE28B49A86C9E00D86EEC34612D0",
//                        "1106618574","B09543CF0F138E9388CA8B8A5D40A986");

                if(syUser != null){
                    String sessionId = JSessionIdUtils.generateJSessionId(JSessionIdUtils.JSESSIONID_PREFIX_MOBILE);
                    jsonObject.put(SessionUtil.MOBILE_SESSIONID_COOKIE_NAME, sessionId);
                    jsonObject.put("userId", syUser.getUserId());
                    jsonObject.put("name", syUser.getName());
                    jsonObject.put("phone", syUser.getPhone());
                    String userImg = syUser.getUserImg();
                    if(StringUtils.isNotBlank(userImg) && userImg.startsWith("http")){
                        jsonObject.put("userImg", userImg);
                    }else {
                        jsonObject.put("userImg", userImg);
                    }
                    //写redis；
                    sessionServiceComponent.storeSession(syUser.getUserId(), sessionId, SessionUtil.COOKIE_MAX_AGE);
                    return Model.buidSucc(jsonObject);
                }else{
                    return Model.buidFail(ModelStatusCodeConstants.BUSINESS_ERROR,"qq授权登录失败，可能授权过期");
                }
            }
        }, LOG, "qq登录失败,可能授权过期");
    }


}

