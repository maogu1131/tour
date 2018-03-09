package com.songyang.tour.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* sy_public_place 实体类 
* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
*/ 
public class SyPublicPlace implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:主键
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:64
	* 字段备注:名称
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String name;

	/**
	 * 1：正常  2：删除
	 *@see com.songyang.tour.constants.TourConstants.STATUS
	 */
	private  Integer status;

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
	* 数据库字段长度:64
	* 字段备注:地址描述
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String address;

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



	public SyPublicPlace(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

