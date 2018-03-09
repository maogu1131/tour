package com.songyang.tour.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * sy_restaurant 实体类
 * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
 */
public class SyRestaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:64
     * 字段备注:中文名字
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String cnName;

    /**
     * 数据库字段长度:64
     * 字段备注:英文名字
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String enName;

    /**
     * 数据库字段长度:4
     * 字段备注:记录状态  1:有效  2:删除
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private Integer status;

    /**
     * 数据库字段长度:4
     * 字段备注:餐馆类型:1-中餐馆、2-西餐馆、3-日韩料理、99-其他
     *
     * @see com.songyang.tour.enums.RestaurantTypeEnum
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private Integer type;

    /**
     * 数据库字段长度:6
     * 字段备注:标签：文本 “|”分隔
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String label;

    /**
     * 数据库字段长度:128
     * 字段备注:人均消费
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String perPayDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:地址描述
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String address;

    /**
     * 数据库字段长度:32
     * 字段备注:经度
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private BigDecimal longitude;

    /**
     * 数据库字段长度:32
     * 字段备注:纬度
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private BigDecimal latitude;

    /**
     * 数据库字段长度:11
     * 字段备注:预定电话
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String phone;

    /**
     * 数据库字段长度:256
     * 字段备注:餐馆简介
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String desc;

    /**
     * 数据库字段长度:256
     * 字段备注:大图地址
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String bannerPicUrl;


    // url 列表  编辑图片时使用 不用配置XML
    private String bannerPicUrlListStr;

    /**
     * 数据库字段长度:128
     * 字段备注:图片地址，以逗号分隔
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String picUrl;


    // url 列表  编辑图片时使用 不用配置XML
    private String picUrlListStr;

    /**
     * 数据库字段长度:128
     * 字段备注:营业时间描述
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String openTimeDesc;

    /**
     * 数据库字段长度:64
     * 字段备注:交通描述
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String trafficDesc;

    /**
     * 数据库字段长度:64
     * 字段备注:创建者
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String creator;

    /**
     * 数据库字段长度:19
     * 字段备注:创建时间
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private Date createTime;

    /**
     * 数据库字段长度:64
     * 字段备注:修改者
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private String modifier;

    /**
     * 数据库字段长度:19
     * 字段备注:修改时间
     * Sat Sep 23 13:35:45 CST 2017 AutoGenerate
     */
    private Date modifyTime;


    /**
     * 临时字段 不配置XML 只做页面展示用
     */
    private List<String> bannerPicUrlList;

    /**
     * 临时字段 不配置XML 只做页面展示用
     */
    private List<String> picUrlList;


    public String getPicUrlListStr() {
        return picUrlListStr;
    }

    public void setPicUrlListStr(String picUrlListStr) {
        this.picUrlListStr = picUrlListStr;
    }

    public SyRestaurant() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getEnName() {
        return enName;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setPerPayDesc(String perPayDesc) {
        this.perPayDesc = perPayDesc;
    }

    public String getPerPayDesc() {
        return perPayDesc;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }


    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setBannerPicUrl(String bannerPicUrl) {
        this.bannerPicUrl = bannerPicUrl;
    }

    public String getBannerPicUrl() {
        return bannerPicUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setOpenTimeDesc(String openTimeDesc) {
        this.openTimeDesc = openTimeDesc;
    }

    public String getOpenTimeDesc() {
        return openTimeDesc;
    }

    public void setTrafficDesc(String trafficDesc) {
        this.trafficDesc = trafficDesc;
    }

    public String getTrafficDesc() {
        return trafficDesc;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public List<String> getBannerPicUrlList() {
        return bannerPicUrlList;
    }

    public void setBannerPicUrlList(List<String> bannerPicUrlList) {
        this.bannerPicUrlList = bannerPicUrlList;
    }

    public List<String> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(List<String> picUrlList) {
        this.picUrlList = picUrlList;
    }

    public String getBannerPicUrlListStr() {
        return bannerPicUrlListStr;
    }

    public void setBannerPicUrlListStr(String bannerPicUrlListStr) {
        this.bannerPicUrlListStr = bannerPicUrlListStr;
    }
}

