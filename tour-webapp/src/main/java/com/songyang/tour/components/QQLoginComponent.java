package com.songyang.tour.components;/**
 * Created by lenovo on 2017/11/1.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.service.SyUserService;
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

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 评价组件
 *
 * @author
 * @create 2017-11-01 17:29
 **/
@Component
public class QQLoginComponent {

    private static final Logger LOG = LoggerFactory.getLogger(QQLoginComponent.class);


    public static final String QQ_USERINFO_URL = "https://graph.qq.com/user/get_user_info";


    @Autowired
    private SyUserService syUserService;

    public SyUser login(String openId,String appId,String accessToken) {


        //获取用户信息
        StringBuffer userUrl = new StringBuffer();
        userUrl.append(QQ_USERINFO_URL).append("?access_token=").append(accessToken).
                append("&oauth_consumer_key=").append(appId).append("&openid=").append(openId);
        String userRet = this.get(userUrl.toString());
        JSONObject userObj = JSON.parseObject(userRet);


        String ret = userObj.getString("ret");

//        if("100014".equals(ret)){
//            return null;
//        }


        SyUser user = new SyUser();
        String nickname = userObj.getString("nickname");
        String sex = userObj.getString("gender");
        if (StringUtils.isNotBlank(sex) && "男".equals(sex) ){
            sex = "1";
        }else{
            sex = "2";
        }
        String userImg = userObj.getString("figureurl_qq_2");
        user.setName(nickname);
        user.setUserImg(userImg); //icon
        user.setGender(Integer.valueOf(sex));
        //  登录唯一标示
        user.setQqLoginId(openId); //loginId

        try {
            // 保存user/更新user
            syUserService.save(user);
            return user;
        } catch (Throwable e) {
            LOG.error("QQLoginComponent syUserService.save throwable", e);
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
