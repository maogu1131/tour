package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/3.
 */

import java.util.Date;

/**
 * 民俗活动query
 *
 * @author
 * @create 2017-09-03 10:22
 **/
public class SyFolkQuery extends BaseQuery {

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     */
    private Long id;
    // 名称
    String title;
    // 地址
    String address;
    // 状态：1-正常,-1-删除
    Integer status;
    // 大于等于开始时间
    Date egtTime;
    // 小于结束时间
    Date ltTime;

    /**
     * 热度  1：热   2：不热
     *
     * @see com.songyang.tour.constants.TourConstants.HOT
     */
    private Integer hot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getEgtTime() {
        return egtTime;
    }

    public void setEgtTime(Date egtTime) {
        this.egtTime = egtTime;
    }

    public Date getLtTime() {
        return ltTime;
    }

    public void setLtTime(Date ltTime) {
        this.ltTime = ltTime;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }
}
