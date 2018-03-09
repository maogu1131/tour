package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/14.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 古村落ListVO
 *
 * @author
 * @create 2017-10-14 17:08
 **/
public class OldVillageListVO extends PageVO {

    //bannerList 不分页取全部 hot标示List
    private List<JSONObject> hotList;

    //取非hot标示分页   古村落list
    private List<JSONObject> oldVillageList;

    public List<JSONObject> getHotList() {
        return hotList;
    }

    public void setHotList(List<JSONObject> hotList) {
        this.hotList = hotList;
    }

    public List<JSONObject> getOldVillageList() {
        return oldVillageList;
    }

    public void setOldVillageList(List<JSONObject> oldVillageList) {
        this.oldVillageList = oldVillageList;
    }
}
