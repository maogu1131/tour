package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/31.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 旅游攻略详情VO
 *
 * @author
 * @create 2017-10-31 17:50
 **/
public class TravelStrategyDetailVO implements Serializable {
    //旅游攻略标题
    private String title;
    //图片List
    private List<String> picUrlList;
    //旅游攻略简介
    private String desc;
    //链接文章
    private String jumpUrl;
    //type 1-地址 2-图片
    private Integer type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(List<String> picUrlList) {
        this.picUrlList = picUrlList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
