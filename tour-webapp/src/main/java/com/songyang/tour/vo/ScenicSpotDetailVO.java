package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/11/3.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.model.BaseLabel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 景区详情VO
 *
 * @author
 * @create 2017-11-03 17:40
 **/
public class ScenicSpotDetailVO implements Serializable {

    //景区中文名称
    private String cnName;
    //景区英文名称
    private String enName;
    //景区称号
    private String shortName;
    //bannerList
    private List<String> bannerUrlList;
    /**
     * 地址、景区类型、开放时间描述、游玩提示、停车场、
     * 地理位置、语音讲解、咨询电话
     */
    private List<BaseLabel> labelList;

    //景区简介标题
    private String scenicSpotTitle;

    //景区描述
    private String scenicSpotDesc;

    //景区图片List
    private List<String> scenicSpotPicUrlList;

    //特色描述标题
    private String specialDescTitle;

    //特色项目描述
    private String specialDesc;

    //特色描述图片List
    private List<String> specialDescPicUrlList;

    //推荐路线标题
    private String routeDescTitle;

    //推荐路线描述
    private String routeDesc;

    /**
     * 周边推荐标题
     */
    private String nearbyTitle;

    /**
     * 周边推荐List  一个菜，一个古村落，一个旅馆
     * id  picUrl  title
     */
    private List<JSONObject> nearbyList;


    //价格
    private BigDecimal price;

    //电话
    private String tel;

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

    //旅游录音url
    private String tourAudioUrl;

    //TODO门票购买

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

    public String getScenicSpotTitle() {
        return scenicSpotTitle;
    }

    public void setScenicSpotTitle(String scenicSpotTitle) {
        this.scenicSpotTitle = scenicSpotTitle;
    }

    public String getScenicSpotDesc() {
        return scenicSpotDesc;
    }

    public void setScenicSpotDesc(String scenicSpotDesc) {
        this.scenicSpotDesc = scenicSpotDesc;
    }

    public List<String> getScenicSpotPicUrlList() {
        return scenicSpotPicUrlList;
    }

    public void setScenicSpotPicUrlList(List<String> scenicSpotPicUrlList) {
        this.scenicSpotPicUrlList = scenicSpotPicUrlList;
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

    public String getRouteDescTitle() {
        return routeDescTitle;
    }

    public void setRouteDescTitle(String routeDescTitle) {
        this.routeDescTitle = routeDescTitle;
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public void setRouteDesc(String routeDesc) {
        this.routeDesc = routeDesc;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getBannerUrlList() {
        return bannerUrlList;
    }

    public void setBannerUrlList(List<String> bannerUrlList) {
        this.bannerUrlList = bannerUrlList;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getTourAudioUrl() {
        return tourAudioUrl;
    }

    public void setTourAudioUrl(String tourAudioUrl) {
        this.tourAudioUrl = tourAudioUrl;
    }
}
