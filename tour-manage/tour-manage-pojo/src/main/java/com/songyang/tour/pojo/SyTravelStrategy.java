package com.songyang.tour.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* sy_travel_strategy 实体类 
* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
*/ 
public class SyTravelStrategy implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据库字段长度:20
	 * 字段备注:
	 * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
	 */
	private Long id;

	/**
	 * 数据库字段长度:64
	 * 字段备注:标题
	 * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
	 */
	private String title;

	/**
	 * 数据库字段长度:512
	 * 字段备注:旅游攻略简介
	 * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
	 */
	private String desc;

	//type 1-地址 2-图片
	private Integer type;

	//跳转url地址
	private String jumpUrl;

	/**
	 * 数据库字段长度:128
	 * 字段备注:图片url
	 * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
	 */
	private String picUrl;

	// 编辑图片时使用 不用配置XML
	private String picUrlListStr;

	/**
	 * 数据库字段长度:4
	 * 字段备注:热度 1:不热  2:热
	 * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
	 */
	private Integer hot;

	/**
	 * 数据库字段长度:4
	 * 字段备注:记录状态 1:有效  2:删除
	 * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
	 */
	private Integer status;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:19
	* 字段备注:更新时间
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;

	/**
	* 数据库字段长度:64
	* 字段备注:创建者
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private String creator;

	/**
	* 数据库字段长度:64
	* 字段备注:修改者
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private String modifier;

	/**
	 * 临时字段 不配置XML 只做页面展示用
	 */
	private List<String> picUrlList;


	public SyTravelStrategy(){
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

	public void setHot(Integer hot){
		this.hot = hot;
	}

	public Integer getHot(){
		return hot;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime(){
		return modifyTime;
	}

	public void setCreator(String creator){
		this.creator = creator;
	}

	public String getCreator(){
		return creator;
	}

	public void setModifier(String modifier){
		this.modifier = modifier;
	}

	public String getModifier(){
		return modifier;
	}

	public List<String> getPicUrlList() {
		return picUrlList;
	}

	public void setPicUrlList(List<String> picUrlList) {
		this.picUrlList = picUrlList;
	}

	public String getPicUrlListStr() {
		return picUrlListStr;
	}

	public void setPicUrlListStr(String picUrlListStr) {
		this.picUrlListStr = picUrlListStr;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}
}

