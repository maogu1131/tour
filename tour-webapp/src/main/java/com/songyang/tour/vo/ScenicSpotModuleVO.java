package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/13.
 */

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * 景区模块VO
 *
 * @author
 * @create 2017-10-13 10:56
 **/
public class ScenicSpotModuleVO implements Serializable {
    /**
     *id、title、picUrl
     */
    private List<JSONObject> spotModuleList;

    public List<JSONObject> getSpotModuleList() {
        return spotModuleList;
    }

    public void setSpotModuleList(List<JSONObject> spotModuleList) {
        this.spotModuleList = spotModuleList;
    }
}
