package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/9.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 民宿&酒店list VO
 *
 * @author
 * @create 2017-10-09 16:23
 **/
public class HotelListVO extends PageVO {

    /**
     * jo.put("id", hotel.getId());
     * <p>
     * //图片
     * jo.put("picUrl", hotel.getPicUrl());
     * <p>
     * //图片标题
     * jo.put("title", hotel.getCnName());
     * <p>
     * //副标题支持html
     * jo.put("subTitle", hotel.getSubTitle());
     * <p>
     * //地址
     * jo.put("address", hotel.getAddress());
     */
    private List<JSONObject> hotelList;

    public List<JSONObject> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<JSONObject> hotelList) {
        this.hotelList = hotelList;
    }
}
