package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/9.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 旅游攻略ListVO
 *
 * @author
 * @create 2017-10-09 20:07
 **/
public class TravelStrategyListVO extends PageVO {

    //bannerList 不分页取全部 hot标示List
    private List<JSONObject> hotList;

    //取非hot标示分页   旅游攻略list
    private List<JSONObject> travelStrategyList;

    public List<JSONObject> getHotList() {
        return hotList;
    }

    public void setHotList(List<JSONObject> hotList) {
        this.hotList = hotList;
    }

    public List<JSONObject> getTravelStrategyList() {
        return travelStrategyList;
    }

    public void setTravelStrategyList(List<JSONObject> travelStrategyList) {
        this.travelStrategyList = travelStrategyList;
    }
}
