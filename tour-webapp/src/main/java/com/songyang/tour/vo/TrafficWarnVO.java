package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/9/27.
 */

import java.io.Serializable;

/**
 * 交通预警VO
 *
 * @author
 * @create 2017-09-27 1:02
 **/
public class TrafficWarnVO implements Serializable {
    /**
     * 交通预警状态
     *
     * @see com.songyang.tour.enums.TrafficWarnStatusEnum
     */
    private int trafficStatus;
    /**
     * 预警内容
     */
    private String content;

    /**
     * 跳转内容
     */
    private String jumpUrl;


    /**
     * 创建时间
     */
    private String createTime;

    public int getTrafficStatus() {
        return trafficStatus;
    }

    public void setTrafficStatus(int trafficStatus) {
        this.trafficStatus = trafficStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
