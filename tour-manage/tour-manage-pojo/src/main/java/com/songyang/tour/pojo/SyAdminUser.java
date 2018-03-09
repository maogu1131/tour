package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_admin_user 实体类 
* Sat Dec 09 20:51:33 CST 2017 AutoGenerate 
*/ 
public class SyAdminUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:22
	* 字段备注:主键id
	* Sat Dec 09 20:51:33 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:64
	* 字段备注:用户名
	* Sat Dec 09 20:51:33 CST 2017 AutoGenerate 
	*/
	private String userName;

	/**
	* 数据库字段长度:128
	* 字段备注:登录密码
	* Sat Dec 09 20:51:33 CST 2017 AutoGenerate 
	*/
	private String password;

	/**
	* 数据库字段长度:4
	* 字段备注:角色类型：1.超级管理员 2:普通管理员
	* Sat Dec 09 20:51:33 CST 2017 AutoGenerate 
	*/
	private Integer roleType;

	/**
	* 数据库字段长度:4
	* 字段备注:用户状态：1:可用  2:不可用
	* Sat Dec 09 20:51:33 CST 2017 AutoGenerate 
	*/
	private Integer status;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Sat Dec 09 20:51:33 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:19
	* 字段备注:更新时间
	* Sat Dec 09 20:51:33 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;



	public SyAdminUser(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setRoleType(Integer roleType){
		this.roleType = roleType;
	}

	public Integer getRoleType(){
		return roleType;
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

}

