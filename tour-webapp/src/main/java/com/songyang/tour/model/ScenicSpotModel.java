package com.songyang.tour.model;/**
 * Created by lenovo on 2017/10/29.
 */

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 景区model
 *
 * @author
 * @create 2017-10-29 13:52
 **/
public class ScenicSpotModel implements Serializable {
    //景区id
    private Integer id;
    //标题
    private String title;
    //图片url
    private String picUrl;
    //景区简介
    private String desc;
    //价格
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
