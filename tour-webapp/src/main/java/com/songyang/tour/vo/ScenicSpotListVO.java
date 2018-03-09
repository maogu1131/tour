package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/14.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 景区信息列表
 *
 * @author
 * @create 2017-10-14 15:25
 **/
public class ScenicSpotListVO extends PageVO {

    //bannerList 不分页取全部 hot标示List
    private List<JSONObject> hotList;

    //取非hot标示分页   景区list
    private List<JSONObject> scenicSpotList;

    public List<JSONObject> getHotList() {
        return hotList;
    }

    public void setHotList(List<JSONObject> hotList) {
        this.hotList = hotList;
    }

    public List<JSONObject> getScenicSpotList() {
        return scenicSpotList;
    }

    public void setScenicSpotList(List<JSONObject> scenicSpotList) {
        this.scenicSpotList = scenicSpotList;
    }
}
