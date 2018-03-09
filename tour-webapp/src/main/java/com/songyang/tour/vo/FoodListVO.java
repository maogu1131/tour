package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/6.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 美食列表list
 *
 * @author
 * @create 2017-10-06 23:01
 **/
public class FoodListVO extends PageVO {
    /**
     * jo.put("picUrl",food.getPicUrl());
     * jo.put("id",food.getId());
     * jo.put("title",food.getName());
     * jo.put("desc",food.getDesc());
     */
    private List<JSONObject> foodList;

    public List<JSONObject> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<JSONObject> foodList) {
        this.foodList = foodList;
    }
}
