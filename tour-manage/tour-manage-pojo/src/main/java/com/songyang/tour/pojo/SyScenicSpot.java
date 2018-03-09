package com.songyang.tour.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * sy_scenic_spot 实体类
 * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
 */
public class SyScenicSpot implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:64
     * 字段备注:中文名字
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String cnName;

    /**
     * 数据库字段长度:64
     * 字段备注:英文名字
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String enName;

    //状态  状态 1正常  -1删除
    private Integer status;
    /**
     * 数据库字段长度:32
     * 字段备注:景区称号
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String shortName;

    /**
     * 热度：1-不热，2-热
     * @see com.songyang.tour.constants.TourConstants.HOT
     */
    private Integer hot;

    /**
     * 景区价格
     */
    private BigDecimal price;

    /**
     * 数据库字段长度:4
     * 字段备注:景区类型：1-自然风光、2-历史人物、3-名胜古迹、99-其他
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Integer type;

    /**
     * 数据库字段长度:128
     * 字段备注:其他描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String otherDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:地址描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String address;

    /**
     * 数据库字段长度:32
     * 字段备注:景区等级：几个A
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String level;

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
     * 数据库字段长度:32
     * 字段备注:海拔
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String altitude;

    /**
     * 数据库字段长度:11
     * 字段备注:预定电话
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String phone;

    /**
     * 数据库字段长度:128
     * 字段备注:微信二维码图片地址
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String wechatPicUrl;


    //编辑图片时使用 不用配置XML
    private String wechatPicUrlListStr;

    /**
     * 数据库字段长度:64
     * 字段备注:微信公众号
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String wechatPublicSignal;

    /**
     * 数据库字段长度:256
     * 字段备注:景区简介
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String desc;

    /**
     * 数据库字段长度:128
     * 字段备注:图片地址，以逗号分隔
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String picUrl;


    // 编辑图片时使用 不用配置XML
    private String picUrlListStr;

    /**
     * 数据库字段长度:128
     * 字段备注:视频地址
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String videoUrl;

    /**
     * 数据库字段长度:128
     * 字段备注:开放时间描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String openTimeDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:特色描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String specialDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:特色文化图片地址，以逗号分隔
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String specialPicUrl;

    // 编辑图片时使用 不用配置XML
    private String specialPicUrlListStr;

    /**
     * 数据库字段长度:128
     * 字段备注:线路描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String routeDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:票务描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String ticketDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:导览音频地址
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String tourAudioUrl;

    /**
     * 数据库字段长度:64
     * 字段备注:游玩提示
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String playTips;

    /**
     * 数据库字段长度:128
     * 字段备注:交通描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String trafficDesc;

    // 票总量
    private Integer ticketTotalNum;
    // 票发放量
    private Integer ticketSendNum;
    // 票剩余量
    private Integer ticketRemainNum;

    /**
     * 数据库字段长度:64
     * 字段备注:创建者
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String creator;

    /**
     * 数据库字段长度:19
     * 字段备注:创建时间
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Date createTime;

    /**
     * 数据库字段长度:64
     * 字段备注:修改者
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String modifier;

    /**
     * 数据库字段长度:19
     * 字段备注:修改时间
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Date modifyTime;

    /**
     * 临时字段 不配置XML 只做页面展示用
     */
    private List<String> picUrlList;
    /**
     * 临时字段 不配置XML 只做页面展示用
     */
    private List<String> specialPicUrlList;

    /**
     * 临时字段 不配置XML 只做页面展示用
     */
    private List<String> wechatPicUrlList;


    public String getPicUrlListStr() {
        return picUrlListStr;
    }

    public void setPicUrlListStr(String picUrlListStr) {
        this.picUrlListStr = picUrlListStr;
    }

    public String getSpecialPicUrlListStr() {
        return specialPicUrlListStr;
    }

    public void setSpecialPicUrlListStr(String specialPicUrlListStr) {
        this.specialPicUrlListStr = specialPicUrlListStr;
    }

    public SyScenicSpot() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getEnName() {
        return enName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWechatPicUrl(String wechatPicUrl) {
        this.wechatPicUrl = wechatPicUrl;
    }

    public String getWechatPicUrl() {
        return wechatPicUrl;
    }

    public void setWechatPublicSignal(String wechatPublicSignal) {
        this.wechatPublicSignal = wechatPublicSignal;
    }

    public String getWechatPublicSignal() {
        return wechatPublicSignal;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setOpenTimeDesc(String openTimeDesc) {
        this.openTimeDesc = openTimeDesc;
    }

    public String getOpenTimeDesc() {
        return openTimeDesc;
    }

    public void setSpecialDesc(String specialDesc) {
        this.specialDesc = specialDesc;
    }

    public String getSpecialDesc() {
        return specialDesc;
    }

    public void setSpecialPicUrl(String specialPicUrl) {
        this.specialPicUrl = specialPicUrl;
    }

    public String getSpecialPicUrl() {
        return specialPicUrl;
    }

    public void setRouteDesc(String routeDesc) {
        this.routeDesc = routeDesc;
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public void setTicketDesc(String ticketDesc) {
        this.ticketDesc = ticketDesc;
    }

    public String getTicketDesc() {
        return ticketDesc;
    }

    public void setTourAudioUrl(String tourAudioUrl) {
        this.tourAudioUrl = tourAudioUrl;
    }

    public String getTourAudioUrl() {
        return tourAudioUrl;
    }

    public void setPlayTips(String playTips) {
        this.playTips = playTips;
    }

    public String getPlayTips() {
        return playTips;
    }

    public void setTrafficDesc(String trafficDesc) {
        this.trafficDesc = trafficDesc;
    }

    public String getTrafficDesc() {
        return trafficDesc;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(List<String> picUrlList) {
        this.picUrlList = picUrlList;
    }

    public List<String> getSpecialPicUrlList() {
        return specialPicUrlList;
    }

    public void setSpecialPicUrlList(List<String> specialPicUrlList) {
        this.specialPicUrlList = specialPicUrlList;
    }

    public String getWechatPicUrlListStr() {
        return wechatPicUrlListStr;
    }

    public void setWechatPicUrlListStr(String wechatPicUrlListStr) {
        this.wechatPicUrlListStr = wechatPicUrlListStr;
    }

    public List<String> getWechatPicUrlList() {
        return wechatPicUrlList;
    }

    public void setWechatPicUrlList(List<String> wechatPicUrlList) {
        this.wechatPicUrlList = wechatPicUrlList;
    }

    public Integer getTicketTotalNum() {
        return ticketTotalNum;
    }

    public void setTicketTotalNum(Integer ticketTotalNum) {
        this.ticketTotalNum = ticketTotalNum;
    }

    public Integer getTicketSendNum() {
        return ticketSendNum;
    }

    public void setTicketSendNum(Integer ticketSendNum) {
        this.ticketSendNum = ticketSendNum;
    }

    public Integer getTicketRemainNum() {
        return ticketRemainNum;
    }

    public void setTicketRemainNum(Integer ticketRemainNum) {
        this.ticketRemainNum = ticketRemainNum;
    }
}

