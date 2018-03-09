package com.songyang.tour.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
* sy_evaluate 实体类 
* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
*/ 
public class SyEvaluate implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:主键
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:4
	* 字段备注:待评价方的纬度类型
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Integer aEffectType;

	/**
	* 数据库字段长度:32
	* 字段备注:待评价方的id
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String aEffectId;

	/**
	* 数据库字段长度:32
	* 字段备注:待评价方的名称
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String aEffectName;

	/**
	* 数据库字段长度:11
	* 字段备注:评价等级,星星的数量
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private BigDecimal effectLevel;

	/**
	* 数据库字段长度:128
	* 字段备注:评价内容
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String effectContent;

	// 图片地址
	private String effectPicUrl;

	// 图片地址-前端展现用
	private List<String> picUrlList;

	/**
	* 数据库字段长度:32
	* 字段备注:评价方的ID
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String bEffectId;

	/**
	* 数据库字段长度:32
	* 字段备注:评价方的名称
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String bEffectName;


	/**
	 * 用户头像
	 */
	private String bEffectImg;


	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Date effectTime;

	/**
	 * 状态
	 */
	private Integer status;

	public SyEvaluate(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAEffectType() {
		return aEffectType;
	}

	public void setaEffectType(Integer aEffectType) {
		this.aEffectType = aEffectType;
	}

	public String getAEffectId() {
		return aEffectId;
	}

	public void setaEffectId(String aEffectId) {
		this.aEffectId = aEffectId;
	}

	public String getAEffectName() {
		return aEffectName;
	}

	public void setaEffectName(String aEffectName) {
		this.aEffectName = aEffectName;
	}

	public BigDecimal getEffectLevel() {
		return effectLevel;
	}

	public void setEffectLevel(BigDecimal effectLevel) {
		this.effectLevel = effectLevel;
	}

	public String getEffectContent() {
		return effectContent;
	}

	public void setEffectContent(String effectContent) {
		this.effectContent = effectContent;
	}

	public String getEffectPicUrl() {
		return effectPicUrl;
	}

	public void setEffectPicUrl(String effectPicUrl) {
		this.effectPicUrl = effectPicUrl;
	}

	public List<String> getPicUrlList() {
		return picUrlList;
	}

	public void setPicUrlList(List<String> picUrlList) {
		this.picUrlList = picUrlList;
	}

	public String getbEffectId() {
		return bEffectId;
	}

	public void setbEffectId(String bEffectId) {
		this.bEffectId = bEffectId;
	}

	public String getBEffectName() {
		return bEffectName;
	}

	public void setbEffectName(String bEffectName) {
		this.bEffectName = bEffectName;
	}

	public Date getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	public String getBEffectImg() {
		return bEffectImg;
	}

	public void setbEffectImg(String bEffectImg) {
		this.bEffectImg = bEffectImg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

