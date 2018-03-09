package com.songyang.tour.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* sy_custom_route 实体类 
* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
*/ 
public class SyCustomRoute implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:主键
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Long id;

//	/**
//	* 数据库字段长度:4
//	* 字段备注:1-私人定制，2-智能旅游
//	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate
//	*/
//	private Integer moduleType;

	/**
	* 数据库字段长度:4
	* 字段备注:业务大类型:1-一日游,2-二日游，3-三日游，4-四日游，5-五日游
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String type;

	/**
	* 数据库字段长度:4
	* 字段备注:业务子类型：1-纯酒店，2-民宿，3-酒店/民宿
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String subType;

	/**
	* 数据库字段长度:128
	* 字段备注:关键词：休闲，观光....，多个以逗号分隔
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String keyWord;

	/**
	* 数据库字段长度:64
	* 字段备注:描述
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String desc;

	/**
	 * 状态:1-有效，-1-删除
	 */
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

//	/**
//	 * 临时字段 不配置XML 只做页面展示用
//	 */
//	private List<String> picUrlList;

	/**
	 * N天的旅游
	 */
	List<SyCustomRouteDays> customRouteDaysList;

	/**
	 * N天的旅游 编辑、查看用
	 */
	private String customRouteDaysStr;

	public SyCustomRoute(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSubType(String subType){
		this.subType = subType;
	}

	public String getSubType(){
		return subType;
	}

	public void setKeyWord(String keyWord){
		this.keyWord = keyWord;
	}

	public String getKeyWord(){
		return keyWord;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
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

//	public List<String> getPicUrlList() {
//		return picUrlList;
//	}
//
//	public void setPicUrlList(List<String> picUrlList) {
//		this.picUrlList = picUrlList;
//	}


	public List<SyCustomRouteDays> getCustomRouteDaysList() {
		return customRouteDaysList;
	}

	public void setCustomRouteDaysList(List<SyCustomRouteDays> customRouteDaysList) {
		this.customRouteDaysList = customRouteDaysList;
	}

	public String getCustomRouteDaysStr() {
		return customRouteDaysStr;
	}

	public void setCustomRouteDaysStr(String customRouteDaysStr) {
		this.customRouteDaysStr = customRouteDaysStr;
	}
}

