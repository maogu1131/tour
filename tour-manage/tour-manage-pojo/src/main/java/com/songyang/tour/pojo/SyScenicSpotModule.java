package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_scenic_spot_module 实体类 
* Sun Sep 24 23:33:24 CST 2017 AutoGenerate 
*/ 
public class SyScenicSpotModule implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:8
	* 字段备注:景区模块id
	* Sun Sep 24 23:33:24 CST 2017 AutoGenerate 
	*/
	private Integer id;

	/**
	* 数据库字段长度:64
	* 字段备注:景区模块标题
	* Sun Sep 24 23:33:24 CST 2017 AutoGenerate 
	*/
	private String title;

	/**
	* 数据库字段长度:128
	* 字段备注:图片地址
	* Sun Sep 24 23:33:24 CST 2017 AutoGenerate 
	*/
	private String picUrl;

	/**
	 * 展现优先级 数字越大越靠前展示
	 */
	private Integer priority;

	// 编辑图片时使用 不用配置XML
	private String picUrlListStr;


	/**
	 * 模块类型：1.景区类型   2.古村落类型
	 */
	private Integer type;

	/**
	* 数据库字段长度:4
	* 字段备注:记录状态 1:有效  2:删除
	* Sun Sep 24 23:33:24 CST 2017 AutoGenerate 
	*/
	private Integer status;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Sun Sep 24 23:33:24 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:19
	* 字段备注:更新时间
	* Sun Sep 24 23:33:24 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;

	/**
	* 数据库字段长度:64
	* 字段备注:创建者
	* Sun Sep 24 23:33:24 CST 2017 AutoGenerate 
	*/
	private String creator;

	/**
	* 数据库字段长度:64
	* 字段备注:修改者
	* Sun Sep 24 23:33:24 CST 2017 AutoGenerate 
	*/
	private String modifier;



	public SyScenicSpotModule(){
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}

	public String getPicUrl(){
		return picUrl;
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

	public String getPicUrlListStr() {
		return picUrlListStr;
	}

	public void setPicUrlListStr(String picUrlListStr) {
		this.picUrlListStr = picUrlListStr;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}

