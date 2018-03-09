package com.songyang.tour.components;/**
 * Created by lenovo on 2017/11/1.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.SyWeiXinUser;
import com.songyang.tour.pojo.SyEvaluate;
import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.query.SyEvaluateQuery;
import com.songyang.tour.service.SyEvaluateService;
import com.songyang.tour.service.SyUserService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.SessionUtil;
import com.songyang.tour.vo.EvaluateListVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 评价组件
 *
 * @author
 * @create 2017-11-01 17:29
 **/
@Component
public class WeiXinLoginComponent {

    private static final Logger LOG = LoggerFactory.getLogger(WeiXinLoginComponent.class);


    public static final String WX_AUTH_LOGIN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String WX_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";

    //appid和appSecret 是在公众平台上申请的
    //AppId
    public static final String WX_APP_ID = "wx94f97409a4cb6883";
    //AppSecret
    public static final String WX_APP_KEY = "2831c3aa840f102841782298fc39f46a";

    @Autowired
    private SyUserService syUserService;

    public SyUser login(String code) {

        // 获取授权 access_token
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append(WX_AUTH_LOGIN_URL).append("?appid=")
                .append(WX_APP_ID).append("&secret=")
                .append(WX_APP_KEY).append("&code=").append(code)
                .append("&grant_type=authorization_code");
        String loginRet = this.get(loginUrl.toString());
        JSONObject grantObj = JSON.parseObject(loginRet);
        String errCode = grantObj.getString("errcode");
        if (!org.apache.commons.lang.StringUtils.isEmpty(errCode)) {
            LOG.error("login weiXin error" + loginRet);
            return null;
        }
        String openId = grantObj.getString("openid");
        if (org.apache.commons.lang.StringUtils.isEmpty(openId)) {
            LOG.error("login weiXin getOpenId error" + loginRet);
            return null;
        }

        String accessToken = grantObj.getString("access_token");
        String expiresIn = grantObj.getString("expires_in");
        String refreshToken = grantObj.getString("refresh_token");
        String scope = grantObj.getString("scope");

        //获取用户信息
        StringBuffer userUrl = new StringBuffer();
        userUrl.append(WX_USERINFO_URL).append("?access_token=").append(accessToken).append("&openid=").append(openId);
        String userRet = this.get(userUrl.toString());
        JSONObject userObj = JSON.parseObject(userRet);
        SyWeiXinUser user = new SyWeiXinUser();
        user.setOpenId(openId);
        user.setAccessToken(accessToken);
        user.setRefreshToken(refreshToken);
        user.setScope(scope);
        user.setExpiresIn(expiresIn);
        String nickname = userObj.getString("nickname");
        String sex = userObj.getString("sex");
        String userImg = userObj.getString("headimgurl");
        String unionId = userObj.getString("unionid");
        user.setName(nickname);
        user.setUserImg(userImg); //icon
        user.setGender(Integer.valueOf(sex));
        //  登录唯一标示
        user.setWxLoginId(unionId); //loginId

        try {
            // 保存user/更新user
            syUserService.save(user);
            return user;
        } catch (Throwable e) {
            LOG.error("syUserService.save throwable", e);
            return null;
        }

    }

    public static String get(String url) {
        String body = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            LOG.info("send httpClient url:" + url);
            HttpGet get = new HttpGet(url);
            get.addHeader("Accept-Charset", "utf-8");
            HttpResponse response = sendRequest(httpClient, get);
            body = parseResponse(response);
        } catch (Throwable e) {
            LOG.error("send httpClient url:"+url+" throwable", e);
        }

        return body;
    }

    private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost)
            throws Throwable{
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        return response;
    }

    private static String parseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        LOG.info("parseResponse status:" + response.getStatusLine());
        Charset charset = ContentType.getOrDefault(entity).getCharset();
        if (charset != null) {
            LOG.info(charset.name());
        }

        String body = null;
        try {
            body = EntityUtils.toString(entity, "utf-8");
            LOG.info("response entity to String:" + body);
        } catch (IOException e) {
            LOG.warn("response cannot parse the entity", e);
        }

        return body;
    }


}
