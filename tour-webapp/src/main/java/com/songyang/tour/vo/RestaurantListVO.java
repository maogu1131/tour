package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/6.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 餐馆列表VO
 *
 * @author
 * @create 2017-10-06 23:25
 **/
public class RestaurantListVO extends PageVO {

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

    public List<JSONObject> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<JSONObject> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
