package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/31.
 */

import com.songyang.tour.model.BaseLabel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 民宿&酒店模型VO
 *
 * @author
 * @create 2017-10-31 11:00
 **/
public class HotelDetailVO implements Serializable {

    //标题
    private String title;

    //@see HotelType
    //HOTEL(1, "纯酒店"), HOMESTAY(2, "民宿"), MIX(3, "酒店/民宿");
    private int type;

    //banner图片
    private List<String> bannerPicUrlList;

    /**
     * 标签list
     * 类型、地址、评星级、地图导航、咨询电话
     */
    private List<BaseLabel> labelList;

    //简介标题
    private String descTitle;

    //简介内容
    private String desc;

    //简介图片集合
    private List<String> descPicUrlList;

    //标签
    private String label;

    //电话
    private String tel;

    /**
     * 数据库字段长度:22
     * 字段备注:经度
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private BigDecimal longitude;

    /**
     * 数据库字段长度:22
     * 字段备注:纬度
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private BigDecimal latitude;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BaseLabel> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<BaseLabel> labelList) {
        this.labelList = labelList;
    }

    public String getDescTitle() {
        return descTitle;
    }

    public void setDescTitle(String descTitle) {
        this.descTitle = descTitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getDescPicUrlList() {
        return descPicUrlList;
    }

    public void setDescPicUrlList(List<String> descPicUrlList) {
        this.descPicUrlList = descPicUrlList;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getBannerPicUrlList() {
        return bannerPicUrlList;
    }

    public void setBannerPicUrlList(List<String> bannerPicUrlList) {
        this.bannerPicUrlList = bannerPicUrlList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
