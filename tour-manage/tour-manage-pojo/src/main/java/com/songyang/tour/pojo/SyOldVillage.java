package com.songyang.tour.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * sy_old_village 实体类
 * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
 */
public class SyOldVillage implements Serializable {

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

    /**
     * 数据库字段长度:32
     * 字段备注:古村落称号
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String shortName;


    /**
     * 1.不热  2.热
     */
    private Integer hot;

//	/**
//	* 数据库字段长度:4
//	* 字段备注:古村落类型:1-依山式、2-傍水式、99-其他
//	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate
//	*/
//	private Integer type;

    /**
     * 数据库字段长度:128
     * 字段备注:其他描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String otherDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:行政划区（归属地）
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String attribution;

    /**
     * 数据库字段长度:128
     * 字段备注:地址
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String address;

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
     * 字段备注:海拔:单位默认m
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String altitude;

    /**
     * 村落面积
     */
    private String area;

    /**
     * 数据库字段长度:128
     * 字段备注:人口描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String populationDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:历史文化描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String historyDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:古村落简介
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String villageDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:图片地址
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String picUrl;
    /**
     * 临时字段 不配置XML 只做页面展示用
     */
    private List<String> picUrlList;

    // 图片地址str url 列表  编辑图片是使用 不用配置XML
    private String picUrlListStr;


    /**
     * 数据库字段长度:256
     * 字段备注:历史文化图片地址，以逗号分隔
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String historyPicUrl;
    /**
     * 临时字段 不配置XML  只做页面展示用
     */
    private List<String> historyPicUrlList;

    // 历史文化图片地址str  url 列表  编辑图片是使用 不用配置XML
    private String historyPicUrlListStr;

    /**
     * 数据库字段长度:128
     * 字段备注:特色文化图片地址，以逗号分隔
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String specialPicUrl;

    // 特色图片地址str url 列表  编辑图片是使用 不用配置XML
    private String specialPicUrlListStr;
    /**
     * 临时字段 不配置XML 只做页面展示用
     */
    private List<String> specialPicUrlList;

    // 民俗活动图片
    private String folkActPicUrl;

    // 民俗活动图片地址str
    private String folkActPicUrlListStr;
    /**
     * 临时字段 不配置XML 只做页面展示用
     */
    private List<String> folkActPicUrlList;

    /**
     * 数据库字段长度:128
     * 字段备注:服务设施:多个以逗号分隔
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String services;

    /**
     * 数据库字段长度:128
     * 字段备注:特色描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String specialDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:民俗活动描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String folkActDesc;

    /**
     * 数据库字段长度:128
     * 字段备注:交通描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String trafficDesc;

    /**
     * 状态:1-有效，-1-删除
     */
    private Integer status;

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


    public SyOldVillage() {
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

//	public void setType(Integer type){
//		this.type = type;
//	}
//
//	public Integer getType(){
//		return type;
//	}

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
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

    public void setPopulationDesc(String populationDesc) {
        this.populationDesc = populationDesc;
    }

    public String getPopulationDesc() {
        return populationDesc;
    }

    public void setHistoryDesc(String historyDesc) {
        this.historyDesc = historyDesc;
    }

    public String getHistoryDesc() {
        return historyDesc;
    }

    public void setHistoryPicUrl(String historyPicUrl) {
        this.historyPicUrl = historyPicUrl;
    }

    public String getHistoryPicUrl() {
        return historyPicUrl;
    }

    public void setVillageDesc(String villageDesc) {
        this.villageDesc = villageDesc;
    }

    public String getVillageDesc() {
        return villageDesc;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getServices() {
        return services;
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

    public void setFolkActDesc(String folkActDesc) {
        this.folkActDesc = folkActDesc;
    }

    public String getFolkActDesc() {
        return folkActDesc;
    }

    public void setTrafficDesc(String trafficDesc) {
        this.trafficDesc = trafficDesc;
    }

    public String getTrafficDesc() {
        return trafficDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public List<String> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(List<String> picUrlList) {
        this.picUrlList = picUrlList;
    }

    public List<String> getHistoryPicUrlList() {
        return historyPicUrlList;
    }

    public void setHistoryPicUrlList(List<String> historyPicUrlList) {
        this.historyPicUrlList = historyPicUrlList;
    }

    public List<String> getSpecialPicUrlList() {
        return specialPicUrlList;
    }

    public void setSpecialPicUrlList(List<String> specialPicUrlList) {
        this.specialPicUrlList = specialPicUrlList;
    }


    public String getHistoryPicUrlListStr() {
        return historyPicUrlListStr;
    }

    public void setHistoryPicUrlListStr(String historyPicUrlListStr) {
        this.historyPicUrlListStr = historyPicUrlListStr;
    }

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFolkActPicUrl() {
        return folkActPicUrl;
    }

    public void setFolkActPicUrl(String folkActPicUrl) {
        this.folkActPicUrl = folkActPicUrl;
    }

    public String getFolkActPicUrlListStr() {
        return folkActPicUrlListStr;
    }

    public void setFolkActPicUrlListStr(String folkActPicUrlListStr) {
        this.folkActPicUrlListStr = folkActPicUrlListStr;
    }


    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public List<String> getFolkActPicUrlList() {
        return folkActPicUrlList;
    }

    public void setFolkActPicUrlList(List<String> folkActPicUrlList) {
        this.folkActPicUrlList = folkActPicUrlList;
    }
}

