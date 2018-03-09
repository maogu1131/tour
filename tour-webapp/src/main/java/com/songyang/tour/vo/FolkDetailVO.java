package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/11/4.
 */

import com.songyang.tour.model.BaseLabel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 民俗活动详情
 *
 * @author
 * @create 2017-11-04 14:27
 **/
public class FolkDetailVO implements Serializable {
    //民俗活动标题
    private String title;

    //bannerList
    private List<String> bannerUrlList;
    /**
     * 地址、开始时间、结束时间
     * 活动费用、注意事项、
     * 地图导航、咨询电话
     */
    private List<BaseLabel> labelList;

    //活动详情标题
    private String folkActDescTitle;
    //活动详情描述
    private String folkActDesc;
    //活动详情图片List
    private List<String> folkActDescPicUrlList;
    //特色活动标题
    private String specialDescTitle;
    //特色活动详情
    private String specialDesc;
    //特色活动图片List
    private List<String> specialDescPicUrlList;


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


    /**
     * 是否预约状态1：预约过， 2：未预约  3:已结束
     */
    private int reserveStatus;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getBannerUrlList() {
        return bannerUrlList;
    }

    public void setBannerUrlList(List<String> bannerUrlList) {
        this.bannerUrlList = bannerUrlList;
    }

    public List<BaseLabel> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<BaseLabel> labelList) {
        this.labelList = labelList;
    }

    public String getFolkActDescTitle() {
        return folkActDescTitle;
    }

    public void setFolkActDescTitle(String folkActDescTitle) {
        this.folkActDescTitle = folkActDescTitle;
    }

    public String getFolkActDesc() {
        return folkActDesc;
    }

    public void setFolkActDesc(String folkActDesc) {
        this.folkActDesc = folkActDesc;
    }

    public List<String> getFolkActDescPicUrlList() {
        return folkActDescPicUrlList;
    }

    public void setFolkActDescPicUrlList(List<String> folkActDescPicUrlList) {
        this.folkActDescPicUrlList = folkActDescPicUrlList;
    }

    public String getSpecialDescTitle() {
        return specialDescTitle;
    }

    public void setSpecialDescTitle(String specialDescTitle) {
        this.specialDescTitle = specialDescTitle;
    }

    public String getSpecialDesc() {
        return specialDesc;
    }

    public void setSpecialDesc(String specialDesc) {
        this.specialDesc = specialDesc;
    }

    public List<String> getSpecialDescPicUrlList() {
        return specialDescPicUrlList;
    }

    public void setSpecialDescPicUrlList(List<String> specialDescPicUrlList) {
        this.specialDescPicUrlList = specialDescPicUrlList;
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

    public int getReserveStatus() {
        return reserveStatus;
    }

    public void setReserveStatus(int reserveStatus) {
        this.reserveStatus = reserveStatus;
    }
}
