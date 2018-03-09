package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/30.
 */

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * 美食详情VO
 *
 * @author
 * @create 2017-10-30 16:18
 **/
public class FoodDetailVO implements Serializable {
    //美食标题
    private String title;
    //美食图片
    private List<String> picList;
    //美食简介
    private String desc;
    //推荐餐馆list
    /**
     * jo.put("id",restaurant.getId());
     * <p>
     * //图片
     * jo.put("picUrl", restaurant.getPicUrl());
     * <p>
     * //图片标题
     * jo.put("title", restaurant.getCnName());
     * <p>
     * //人均消费
     * jo.put("perPayDesc", restaurant.getPerPayDesc());
     * <p>
     * //餐馆类型:1-中餐馆、2-西餐馆、3-日韩料理、4-土家菜,99-其他',
     * //@see RestaurantTypeEnum
     * jo.put("type", restaurant.getType());
     * <p>
     * //地址
     * jo.put("address", restaurant.getAddress());
     * <p>
     * //标签
     * jo.put("label", restaurant.getLabel());
     */
    private List<JSONObject> restaurantList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<JSONObject> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<JSONObject> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
