package com.songyang.tour.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* sy_hotel 实体类 
* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
*/ 
public class SyHotel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:主键
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:64
	* 字段备注:中文名字
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String cnName;

	/**
	* 数据库字段长度:64
	* 字段备注:英文名字
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String subTitle;

	// 标签
	private String label;

	/**
	* 数据库字段长度:4
	* 字段备注:类型：1-酒店，2-民宿
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Integer type;

	/**
	* 数据库字段长度:4
	* 字段备注:酒店类型:1-经济型，2-商务型，3-豪华型，4-国际型；99-其他，参照其他描述
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String subType;

	/**
	* 数据库字段长度:64
	* 字段备注:其他类型描述
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String subTypeDesc;

	/**
	* 数据库字段长度:4
	* 字段备注:星级：1-一星，2-二星，3-三星，4-四星，5-五星，6-六星，7-七星
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Integer level;

	/**
	* 数据库字段长度:128
	* 字段备注:地址描述
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String address;

	/**
	* 数据库字段长度:22
	* 字段备注:经度
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private BigDecimal longitude;

	/**
	* 数据库字段长度:22
	* 字段备注:纬度
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private BigDecimal latitude;

	/**
	* 数据库字段长度:128
	* 字段备注:酒店跳转地址
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String jumpUrl;

	/**
	* 数据库字段长度:11
	* 字段备注:预定电话
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String phone;

	/**
	* 数据库字段长度:256
	* 字段备注:酒店简介
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String desc;

	// 滑动图片地址
	private String bannerPicUrl;


	// url 列表  编辑图片是使用 不用配置XML
	private String bannerPicUrlListStr;

	/**
	* 数据库字段长度:256
	* 字段备注:图片地址，以逗号分隔
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String picUrl;


	// url 列表  编辑图片是使用 不用配置XML
	private String picUrlListStr;

	/**
	* 数据库字段长度:256
	* 字段备注:营业时间描述
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String openTimeDesc;

	/**
	* 数据库字段长度:256
	* 字段备注:环境描述
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String environmentDesc;

	/**
	* 数据库字段长度:128
	* 字段备注:交通描述
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String trafficDesc;

	// 1-有效、-1-删除
	private Integer status;

	/**
	* 数据库字段长度:64
	* 字段备注:创建者
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String creator;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:64
	* 字段备注:修改者
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String modifier;

	/**
	* 数据库字段长度:19
	* 字段备注:修改时间
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;

	/**
	 * 临时字段 不配置XML 只做页面展示用
	 */
	private List<String> bannerPicUrlList;

	/**
	 * 临时字段 不配置XML 只做页面展示用
	 */
	private List<String> picUrlList;


	public SyHotel(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setCnName(String cnName){
		this.cnName = cnName;
	}

	public String getCnName(){
		return cnName;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public void setSubTypeDesc(String subTypeDesc){
		this.subTypeDesc = subTypeDesc;
	}

	public String getSubTypeDesc(){
		return subTypeDesc;
	}

	public void setLevel(Integer level){
		this.level = level;
	}

	public Integer getLevel(){
		return level;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setLongitude(BigDecimal longitude){
		this.longitude = longitude;
	}

	public BigDecimal getLongitude(){
		return longitude;
	}

	public void setLatitude(BigDecimal latitude){
		this.latitude = latitude;
	}

	public BigDecimal getLatitude(){
		return latitude;
	}

	public void setJumpUrl(String jumpUrl){
		this.jumpUrl = jumpUrl;
	}

	public String getJumpUrl(){
		return jumpUrl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	public String getBannerPicUrl() {
		return bannerPicUrl;
	}

	public void setBannerPicUrl(String bannerPicUrl) {
		this.bannerPicUrl = bannerPicUrl;
	}

	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}

	public String getPicUrl(){
		return picUrl;
	}

	public void setOpenTimeDesc(String openTimeDesc){
		this.openTimeDesc = openTimeDesc;
	}

	public String getOpenTimeDesc(){
		return openTimeDesc;
	}

	public void setEnvironmentDesc(String environmentDesc){
		this.environmentDesc = environmentDesc;
	}

	public String getEnvironmentDesc(){
		return environmentDesc;
	}

	public void setTrafficDesc(String trafficDesc){
		this.trafficDesc = trafficDesc;
	}

	public String getTrafficDesc(){
		return trafficDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreator(String creator){
		this.creator = creator;
	}

	public String getCreator(){
		return creator;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setModifier(String modifier){
		this.modifier = modifier;
	}

	public String getModifier(){
		return modifier;
	}

	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime(){
		return modifyTime;
	}

	public List<String> getBannerPicUrlList() {
		return bannerPicUrlList;
	}

	public void setBannerPicUrlList(List<String> bannerPicUrlList) {
		this.bannerPicUrlList = bannerPicUrlList;
	}

	public List<String> getPicUrlList() {
		return picUrlList;
	}

	public void setPicUrlList(List<String> picUrlList) {
		this.picUrlList = picUrlList;
	}

	public String getBannerPicUrlListStr() {
		return bannerPicUrlListStr;
	}

	public void setBannerPicUrlListStr(String bannerPicUrlListStr) {
		this.bannerPicUrlListStr = bannerPicUrlListStr;
	}

	public String getPicUrlListStr() {
		return picUrlListStr;
	}

	public void setPicUrlListStr(String picUrlListStr) {
		this.picUrlListStr = picUrlListStr;
	}
}

