package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_folk_reserve_order 实体类 
* Fri Dec 22 22:10:11 CST 2017 AutoGenerate 
*/ 
public class SyFolkReserveOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:22
	* 字段备注:
	* Fri Dec 22 22:10:11 CST 2017 AutoGenerate 
	*/
	private Long id;

	private Long folkId;

	/**
	* 数据库字段长度:64
	* 字段备注:用户id
	* Fri Dec 22 22:10:11 CST 2017 AutoGenerate 
	*/
	private String userId;

	/**
	* 数据库字段长度:32
	* 字段备注:预约手机号
	* Fri Dec 22 22:10:11 CST 2017 AutoGenerate 
	*/
	private String rentPhone;

	/**
	* 数据库字段长度:255
	* 字段备注:
	* Fri Dec 22 22:10:11 CST 2017 AutoGenerate 
	*/
	private String rentUserName;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Fri Dec 22 22:10:11 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:19
	* 字段备注:更新时间
	* Fri Dec 22 22:10:11 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;

	//后台展示用，不需要配置xml
	private String folkName;



	public SyFolkReserveOrder(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public Long getFolkId() {
		return folkId;
	}

	public void setFolkId(Long folkId) {
		this.folkId = folkId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setRentPhone(String rentPhone){
		this.rentPhone = rentPhone;
	}

	public String getRentPhone(){
		return rentPhone;
	}

	public void setRentUserName(String rentUserName){
		this.rentUserName = rentUserName;
	}

	public String getRentUserName(){
		return rentUserName;
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

	public String getFolkName() {
		return folkName;
	}

	public void setFolkName(String folkName) {
		this.folkName = folkName;
	}
}

