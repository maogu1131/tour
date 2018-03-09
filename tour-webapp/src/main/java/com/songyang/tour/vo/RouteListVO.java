package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/6.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 定制路线列表list
 *
 * @author
 * @create 2017-10-06 23:01
 **/
public class RouteListVO extends PageVO {
    /**
     * jo.put("id",route.getId());
     * jo.put("type",route.getType());
     * jo.put("subType",route.getSubType());
     * jo.put("keyWord",route.getKeyWord());
     * jo.put("desc",route.getDesc());
     */
    private List<JSONObject> routeList;


    public List<JSONObject> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<JSONObject> routeList) {
        this.routeList = routeList;
    }
}
