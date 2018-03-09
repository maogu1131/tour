package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/30.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.model.BaseLabel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 餐馆详情VO
 *
 * @author
 * @create 2017-10-30 20:26
 **/
public class RestaurantDetailVO implements Serializable {
    //餐馆名称
    private String title;
    //bannerList
    private List<String> bannerUrlList;
    //基础标签List
    private List<BaseLabel> labelList;


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


    //餐馆简介标题
    private String restaurantDescTitle;
    //餐馆简介
    private String restaurantDesc;
    //餐馆简介图片List
    private List<String> restaurantDescPicUrlList;


    //美食推荐标题
    private String  foodTitle;

    /**
     * 美食推荐list
     * 一个图片url
     * 名称
     * id
     */
    private List<JSONObject> foodList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getBannerUrlList() {
        return bannerUrlList;
    }

    public void setBannerUrlList(List<String> bannerUrlList) {
        this.bannerUrlList = bannerUrlList;
    }

    public List<BaseLabel> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<BaseLabel> labelList) {
        this.labelList = labelList;
    }

    public String getRestaurantDescTitle() {
        return restaurantDescTitle;
    }

    public void setRestaurantDescTitle(String restaurantDescTitle) {
        this.restaurantDescTitle = restaurantDescTitle;
    }

    public String getRestaurantDesc() {
        return restaurantDesc;
    }

    public void setRestaurantDesc(String restaurantDesc) {
        this.restaurantDesc = restaurantDesc;
    }

    public List<String> getRestaurantDescPicUrlList() {
        return restaurantDescPicUrlList;
    }

    public void setRestaurantDescPicUrlList(List<String> restaurantDescPicUrlList) {
        this.restaurantDescPicUrlList = restaurantDescPicUrlList;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    public List<JSONObject> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<JSONObject> foodList) {
        this.foodList = foodList;
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
}
