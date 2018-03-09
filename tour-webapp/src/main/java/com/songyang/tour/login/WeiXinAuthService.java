package com.songyang.tour.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.model.SyWeiXinUser;
import com.songyang.tour.pojo.SyUser;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;





public class WeiXinAuthService
{

    private static final Logger LOG = LoggerFactory.getLogger(WeiXinAuthService.class);

    public static final String WX_AUTH_LOGIN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String WX_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    //appid和appSecret 是在公众平台上申请的
    //AppId
    public static final String WX_APP_ID = "wxb6411cbea5c*****";
    //AppSecret
    public static final String WX_APP_KEY = "86b91b295d23f34337b76cacd*******";




    public SyUser checkLogin(String code)
    {
        //获取授权 access_token
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append(WX_AUTH_LOGIN_URL).append("?appid=")
                .append(WX_APP_ID).append("&secret=")
                .append(WX_APP_KEY).append("&code=").append(code)
                .append("&grant_type=authorization_code");
        String loginRet = WeiXinAuthService.get(loginUrl.toString());
       JSONObject grantObj = JSON.parseObject(loginRet);
        String errcode = grantObj.getString("errcode");
        if (!StringUtils.isEmpty(errcode))
        {
            LOG.error("login weixin error"+loginRet);
            return null;
        }
        String openId = grantObj.getString("openid");
        if (StringUtils.isEmpty(openId))
        {
            LOG.error("login weixin getOpenId error"+loginRet);
            return null;
        }

        String accessToken = grantObj.getString("access_token");
        String expiresIn = grantObj.getString("expires_in");
        String refreshToken = grantObj.getString("refresh_token");
        String scope = grantObj.getString("scope");

        //获取用户信息
        StringBuffer userUrl = new StringBuffer();
        userUrl.append(WX_USERINFO_URL).append("?access_token=").append(accessToken).append("&openid=").append(openId);
        String userRet = WeiXinAuthService.get(userUrl.toString());
        JSONObject userObj =  JSON.parseObject(userRet);
        SyWeiXinUser userInfo = new SyWeiXinUser();
        userInfo.setOpenId(openId);
        userInfo.setAccessToken(accessToken);
        userInfo.setRefreshToken(refreshToken);
        userInfo.setScope(scope);
        userInfo.setExpiresIn(expiresIn);
        String nickname = userObj.getString("nickname");
        String sex = userObj.getString("sex");
        String userImg = userObj.getString("headimgurl");
        String unionId = userObj.getString("unionid");
        userInfo.setName(nickname);
        userInfo.setUserImg(userImg); //icon
        userInfo.setGender(Integer.valueOf(sex));
        // 联合ID
        userInfo.setWxLoginId(unionId); //loginId
        return userInfo;
    }


    public static String get(String url) {
        String body = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            LOG.info("create httppost:" + url);
            HttpGet get = new HttpGet(url);
            get.addHeader("Accept-Charset","utf-8");
            HttpResponse response = sendRequest(httpClient, get);
            body = parseResponse(response);
        } catch (IOException e) {
            LOG.error("send post request failed: {}", e.getMessage());
        }

        return body;
    }

    private static String paramsToString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        try{
            for (String key : params.keySet()) {
                sb.append(String.format("&%s=%s", key, URLEncoder.encode(params.get(key),StandardCharsets.UTF_8.toString())));
            }
        }catch(UnsupportedEncodingException e){
            LOG.warn("{}: encode url parameters failed", e.getMessage());
        }
        return sb.length() > 0 ? "?".concat(sb.substring(1)) : "";
    }

    private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost)
            throws ClientProtocolException, IOException {
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        return response;
    }

    private static String parseResponse(HttpResponse response) {
        LOG.info("get response from http server..");
        HttpEntity entity = response.getEntity();

        LOG.info("response status: " + response.getStatusLine());
        Charset charset = ContentType.getOrDefault(entity).getCharset();
        if (charset != null) {
            LOG.info(charset.name());
        }

        String body = null;
        try {
            body = EntityUtils.toString(entity, "utf-8");
            LOG.info("body " + body);
        } catch (IOException e) {
            LOG.warn("{}: cannot parse the entity", e.getMessage());
        }

        return body;
    }
}

