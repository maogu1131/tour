package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/9/27.
 */

/**
 * 民俗活动首页参数VO
 *
 * @author
 * @create 2017-09-27 1:13
 **/
public class FolkParamVO extends IndexBaseParamVO {
    /**
     * 民俗活动开始和结束时间描述
     */
    private String timeDesc;

    /**
     * 地址
     */
    private String address;

    public String getTimeDesc() {
        return timeDesc;
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
