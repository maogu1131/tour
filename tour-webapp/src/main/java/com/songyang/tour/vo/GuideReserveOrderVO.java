package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/12/9.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 导游预约订单VO
 *
 * @author
 * @create 2017-12-09 23:39
 **/
public class GuideReserveOrderVO extends PageVO {
    private List<JSONObject> reserveOrderList;

    public List<JSONObject> getReserveOrderList() {
        return reserveOrderList;
    }

    public void setReserveOrderList(List<JSONObject> reserveOrderList) {
        this.reserveOrderList = reserveOrderList;
    }
}
