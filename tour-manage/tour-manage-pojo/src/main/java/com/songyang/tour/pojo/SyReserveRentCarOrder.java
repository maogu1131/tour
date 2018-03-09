package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_reserve_rent_car_order 实体类 
* Sun Nov 19 19:21:06 CST 2017 AutoGenerate 
*/ 
public class SyReserveRentCarOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:22
	* 字段备注:主键id
	* Sun Nov 19 19:21:06 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:64
	* 字段备注:租车类型
	* Sun Nov 19 19:21:06 CST 2017 AutoGenerate 
	*/
	private String type;

	/**
	* 数据库字段长度:32
	* 字段备注:用户id
	* Sun Nov 19 19:21:06 CST 2017 AutoGenerate 
	*/
	private String userId;

	/**
	* 数据库字段长度:64
	* 字段备注:租车人数
	* Sun Nov 19 19:21:06 CST 2017 AutoGenerate 
	*/
	private String rentNum;

	/**
	* 数据库字段长度:19
	* 字段备注:租车时间
	* Sun Nov 19 19:21:06 CST 2017 AutoGenerate 
	*/
	private Date rentTime;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Sun Nov 19 19:21:06 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:19
	* 字段备注:更新时间
	* Sun Nov 19 19:21:06 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;



	public SyReserveRentCarOrder(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setRentNum(String rentNum){
		this.rentNum = rentNum;
	}

	public String getRentNum(){
		return rentNum;
	}

	public void setRentTime(Date rentTime){
		this.rentTime = rentTime;
	}

	public Date getRentTime(){
		return rentTime;
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

