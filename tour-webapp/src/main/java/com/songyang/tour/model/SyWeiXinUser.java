package com.songyang.tour.model;/**
 * Created by lenovo on 2017/12/2.
 */

import com.songyang.tour.pojo.SyUser;

/**
 * 微信user
 *
 * @author
 * @create 2017-12-02 12:24
 **/
public class SyWeiXinUser extends SyUser {


    // 访问微信的标示
    private String openId;
    //
    private String accessToken;
    private String refreshToken;

    // 范围
    private String scope;
    // 过期时间
    private String expiresIn;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}
