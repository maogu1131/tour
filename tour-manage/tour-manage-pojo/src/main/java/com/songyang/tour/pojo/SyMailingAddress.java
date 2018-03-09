package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_mailing_address 实体类 
* Sat Nov 04 16:11:18 CST 2017 AutoGenerate 
*/ 
public class SyMailingAddress implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:22
	* 字段备注:唯一主键
	* Sat Nov 04 16:11:18 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:22
	* 字段备注:用户uid
	* Sat Nov 04 16:11:18 CST 2017 AutoGenerate 
	*/
	private String userId;

	/**
	* 数据库字段长度:64
	* 字段备注:收货人
	* Sat Nov 04 16:11:18 CST 2017 AutoGenerate 
	*/
	private String userName;

	/**
	* 数据库字段长度:15
	* 字段备注:联系电话
	* Sat Nov 04 16:11:18 CST 2017 AutoGenerate 
	*/
	private String phone;

	/**
	* 数据库字段长度:4
	* 字段备注:状态 1:默认邮箱 2:备用邮箱
	* Sat Nov 04 16:11:18 CST 2017 AutoGenerate 
	*/
	private Integer type;

	/**
	* 数据库字段长度:512
	* 字段备注:地址
	* Sat Nov 04 16:11:18 CST 2017 AutoGenerate 
	*/
	private String address;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Sat Nov 04 16:11:18 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:19
	* 字段备注:更新时间
	* Sat Nov 04 16:11:18 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;



	public SyMailingAddress(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
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

}

