package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/11/26.
 */

import java.io.Serializable;

/**
 * 智能推荐详情VO
 *
 * @author
 * @create 2017-11-26 17:11
 **/
public class CustomRouteDetailVO implements Serializable {
    //天描述
    private String dayDesc;

    //住 富文本
    private String liveContent;

    //游 富文本
   private String tourContent;



    public String getDayDesc() {
        return dayDesc;
    }

    public void setDayDesc(String dayDesc) {
        this.dayDesc = dayDesc;
    }

    public String getTourContent() {
        return tourContent;
    }

    public void setTourContent(String tourContent) {
        this.tourContent = tourContent;
    }

    public String getLiveContent() {
        return liveContent;
    }

    public void setLiveContent(String liveContent) {
        this.liveContent = liveContent;
    }
}
