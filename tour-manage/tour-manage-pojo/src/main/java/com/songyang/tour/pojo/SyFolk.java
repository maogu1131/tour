package com.songyang.tour.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* sy_folk 实体类 
* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
*/ 
public class SyFolk implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:主键
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:64
	* 字段备注:名称
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String title;


	/**
	 * 热度：1-不热，2-热
	 * @see com.songyang.tour.constants.TourConstants.HOT
	 */
	private Integer hot;

	/**
	* 数据库字段长度:64
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
	* 数据库字段长度:19
	* 字段备注:开始时间
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Date startTime;

	/**
	* 数据库字段长度:19
	* 字段备注:结束时间
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Date endTime;

	/**
	* 数据库字段长度:1
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate
	*/
	private Integer status;

	/**
	* 数据库字段长度:11
	* 字段备注:预定电话
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String phone;

	/**
	* 数据库字段长度:64
	* 字段备注:活动描述
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String desc;

	/**
	 * 字段备注:活动图片地址,以逗号分隔
	 */
	private String bannerUrl;

	/**
	 * 临时字段 不配置XML 只做页面展示用
	 */
	private List<String> bannerUrlList;


	// url 列表  编辑图片是使用 不用配置XML
	private String bannerUrlListStr;


	/**
	* 数据库字段长度:256
	* 字段备注:活动详情图片地址,以逗号分隔
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String picUrl;
	/**
	 * 临时字段 不配置XML 只做页面展示用
	 */
	private List<String> picUrlList;


	// url 列表  编辑图片是使用 不用配置XML
	private String picUrlListStr;

	/**
	* 数据库字段长度:256
	* 字段备注:收费描述
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String chargeDesc;

	/**
	* 数据库字段长度:256
	* 字段备注:特色描述
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String specialDesc;

	/**
	* 数据库字段长度:256
	* 字段备注:特色文化图片地址，以逗号分隔
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String specialPicUrl;
	/**
	 * 临时字段 不配置XML 只做页面展示用
	 */
	private List<String> specialPicUrlList;


	// url 列表  编辑图片是使用 不用配置XML
	private String specialPicUrlListStr;

	/**
	* 数据库字段长度:128
	* 字段备注:活动游玩提示
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String playTips;

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


	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public List<String> getBannerUrlList() {
		return bannerUrlList;
	}

	public void setBannerUrlList(List<String> bannerUrlList) {
		this.bannerUrlList = bannerUrlList;
	}

	public String getBannerUrlListStr() {
		return bannerUrlListStr;
	}

	public void setBannerUrlListStr(String bannerUrlListStr) {
		this.bannerUrlListStr = bannerUrlListStr;
	}

	public void setPicUrlListStr(String picUrlListStr) {
		this.picUrlListStr = picUrlListStr;
	}

	public void setSpecialPicUrlListStr(String specialPicUrlListStr) {
		this.specialPicUrlListStr = specialPicUrlListStr;
	}

	public SyFolk(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
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

	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}

	public Date getStartTime(){
		return startTime;
	}

	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}

	public Date getEndTime(){
		return endTime;
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

	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}

	public String getPicUrl(){
		return picUrl;
	}

	public void setChargeDesc(String chargeDesc){
		this.chargeDesc = chargeDesc;
	}

	public String getChargeDesc(){
		return chargeDesc;
	}

	public void setSpecialDesc(String specialDesc){
		this.specialDesc = specialDesc;
	}

	public String getSpecialDesc(){
		return specialDesc;
	}

	public void setSpecialPicUrl(String specialPicUrl){
		this.specialPicUrl = specialPicUrl;
	}

	public String getSpecialPicUrl(){
		return specialPicUrl;
	}

	public void setPlayTips(String playTips){
		this.playTips = playTips;
	}

	public String getPlayTips(){
		return playTips;
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

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}
	
	public String getPicUrlListStr() {
		return picUrlListStr;
	}

	public String getSpecialPicUrlListStr() {
		return specialPicUrlListStr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

