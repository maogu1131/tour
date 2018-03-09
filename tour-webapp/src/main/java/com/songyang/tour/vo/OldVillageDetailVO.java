package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/14.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.model.BaseLabel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 古洛村详情VO
 *
 * @author
 * @create 2017-10-14 17:50
 **/
public class OldVillageDetailVO implements Serializable {

    /**
     * 古村落中文名称
     */
    private String cnName;

    /**
     * 古村落英文名称
     */
    private String enName;

    /**
     * 古村落称号
     */
    private String shortName;

    /**
     * 数据库字段长度:22
     * 字段备注:经度
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private BigDecimal longitude;

    /**
     * 数据库字段长度:22
     * 字段备注:纬度
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private BigDecimal latitude;


    /**
     * bannerlist
     */
    private List<String> bannerList;


    //古村落简介标题
    private String villageDescTitle;

    /**
     * 古村落简介
     */
    private String villageDesc;


    /**
     * 古村落简介list
     */
    private List<String> villageDescPicUrlList;

    /**
     * 标签list
     * 地址、村落面积、常住人口、服务设施
     */
    private List<BaseLabel> labelList;

    /**
     * 历史文化title
     */
    private String historyDescTitle;

    /**
     * 历史文化描述
     */
    private String historyDesc;

    /**
     * 历史文化图片集合
     */
    private List<String> historyDescPicUrlList;

    /**
     * 特色描述title
     */
    private String specialDescTitle;

    /**
     * 特色描述
     */
    private String specialDesc;

    /**
     * 特色图片集合
     */
    private List<String> specialDescPicUrlList;


    /**
     * 民俗活动title
     */
    private String folkDescTitle;

    /**
     * 民俗活动描述
     */
    private String folkActDesc;

    /**
     * 民俗活动图片集合
     */
    private List<String> folkActDescPicUrlList;

    /**
     * 周边推荐标题
     */
    private String nearbyTitle;

    /**
     * 周边推荐List
     * id  picUrl  title
     */
    private List<JSONObject> nearbyList;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<BaseLabel> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<BaseLabel> labelList) {
        this.labelList = labelList;
    }

    public String getHistoryDescTitle() {
        return historyDescTitle;
    }

    public void setHistoryDescTitle(String historyDescTitle) {
        this.historyDescTitle = historyDescTitle;
    }

    public String getHistoryDesc() {
        return historyDesc;
    }

    public void setHistoryDesc(String historyDesc) {
        this.historyDesc = historyDesc;
    }

    public List<String> getHistoryDescPicUrlList() {
        return historyDescPicUrlList;
    }

    public void setHistoryDescPicUrlList(List<String> historyDescPicUrlList) {
        this.historyDescPicUrlList = historyDescPicUrlList;
    }

    public String getSpecialDescTitle() {
        return specialDescTitle;
    }

    public void setSpecialDescTitle(String specialDescTitle) {
        this.specialDescTitle = specialDescTitle;
    }

    public String getSpecialDesc() {
        return specialDesc;
    }

    public void setSpecialDesc(String specialDesc) {
        this.specialDesc = specialDesc;
    }

    public List<String> getSpecialDescPicUrlList() {
        return specialDescPicUrlList;
    }

    public void setSpecialDescPicUrlList(List<String> specialDescPicUrlList) {
        this.specialDescPicUrlList = specialDescPicUrlList;
    }

    public String getFolkDescTitle() {
        return folkDescTitle;
    }

    public void setFolkDescTitle(String folkDescTitle) {
        this.folkDescTitle = folkDescTitle;
    }

    public List<String> getVillageDescPicUrlList() {
        return villageDescPicUrlList;
    }

    public void setVillageDescPicUrlList(List<String> villageDescPicUrlList) {
        this.villageDescPicUrlList = villageDescPicUrlList;
    }

    public String getFolkActDesc() {
        return folkActDesc;
    }

    public void setFolkActDesc(String folkActDesc) {
        this.folkActDesc = folkActDesc;
    }

    public List<String> getFolkActDescPicUrlList() {
        return folkActDescPicUrlList;
    }

    public void setFolkActDescPicUrlList(List<String> folkActDescPicUrlList) {
        this.folkActDescPicUrlList = folkActDescPicUrlList;
    }

    public String getNearbyTitle() {
        return nearbyTitle;
    }

    public void setNearbyTitle(String nearbyTitle) {
        this.nearbyTitle = nearbyTitle;
    }

    public List<JSONObject> getNearbyList() {
        return nearbyList;
    }

    public void setNearbyList(List<JSONObject> nearbyList) {
        this.nearbyList = nearbyList;
    }

    public String getVillageDesc() {
        return villageDesc;
    }

    public void setVillageDesc(String villageDesc) {
        this.villageDesc = villageDesc;
    }

    public String getVillageDescTitle() {
        return villageDescTitle;
    }

    public void setVillageDescTitle(String villageDescTitle) {
        this.villageDescTitle = villageDescTitle;
    }

    public List<String> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<String> bannerList) {
        this.bannerList = bannerList;
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
}
