package com.songyang.tour.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @desc:
 * @date 2017/11/11
 */
public class SyUser implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     * Sun Nov 19 19:21:06 CST 2017 AutoGenerate
     */
    private Long id;


    /**
     * 用户id
     */
    private String userId;

    /**
     * 数据库字段长度:64
     * 字段备注:微信登录登录唯一标示
     * Sun Nov 19 19:21:06 CST 2017 AutoGenerate
     */
    private String wxLoginId;



    /**
     * 数据库字段长度:64
     * 字段备注:QQ登录登录唯一标示
     * Sun Nov 19 19:21:06 CST 2017 AutoGenerate
     */
    private String qqLoginId;


    /**
     * 数据库字段长度:11
     * 字段备注:手机号
     * Sun Nov 19 19:21:06 CST 2017 AutoGenerate
     */
    private String phone;

    /**
     * 数据库字段长度:128
     * 字段备注:密码
     * Sun Nov 19 19:21:06 CST 2017 AutoGenerate
     */
    private String password;

    /**
     * 数据库字段长度:64
     * 字段备注:名称
     * Sun Nov 19 19:21:06 CST 2017 AutoGenerate
     */
    private String name;

    /**
     * 数据库字段长度:4
     * 字段备注:性别:1-男,2-女
     * Sun Nov 19 19:21:06 CST 2017 AutoGenerate
     */
    private Integer gender;

    /**
     * 数据库字段长度:128
     * 字段备注:用户头像
     * Sun Nov 19 19:21:06 CST 2017 AutoGenerate
     */
    private String userImg;

    /**
     * 数据库字段长度:19
     * 字段备注:创建时间
     * Sun Nov 19 19:21:06 CST 2017 AutoGenerate
     */
    private Date createTime;

    /**
     * 数据库字段长度:19
     * 字段备注:修改时间
     * Sun Nov 19 19:21:06 CST 2017 AutoGenerate
     */
    private Date modifyTime;

    public SyUser(){
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWxLoginId() {
        return wxLoginId;
    }

    public void setWxLoginId(String wxLoginId) {
        this.wxLoginId = wxLoginId;
    }

    public String getQqLoginId() {
        return qqLoginId;
    }

    public void setQqLoginId(String qqLoginId) {
        this.qqLoginId = qqLoginId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
