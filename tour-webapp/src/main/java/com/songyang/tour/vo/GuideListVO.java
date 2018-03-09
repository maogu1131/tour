package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/11/23.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 导游列表
 *
 * @author
 * @create 2017-11-23 20:22
 **/
public class GuideListVO extends PageVO {

    /**
     * guideId
     * guideHeadImgUrl
     * guideName
     * guideDesc
     * guideVoiceUrl
     */
    private List<JSONObject> guideList;

    public List<JSONObject> getGuideList() {
        return guideList;
    }

    public void setGuideList(List<JSONObject> guideList) {
        this.guideList = guideList;
    }
}
