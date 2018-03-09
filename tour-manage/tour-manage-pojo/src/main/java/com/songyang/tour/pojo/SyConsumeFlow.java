package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_consume_flow 实体类 
* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
*/ 
public class SyConsumeFlow implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:主键
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:32
	* 字段备注:唯一标示：order_id+'#'+order_status
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private String reqId;

	/**
	* 数据库字段长度:32
	* 字段备注:订单order_id
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private String orderId;

	/**
	* 数据库字段长度:32
	* 字段备注:用户id
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private String userId;

	/**
	* 数据库字段长度:4
	* 字段备注:1-景区，2-旅游商品
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private Integer src;

	/**
	* 数据库字段长度:32
	* 字段备注:景区id/旅游商品id
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private String prodId;

	/**
	* 数据库字段长度:128
	* 字段备注:商品名称
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private String prodName;

	/**
	* 数据库字段长度:11
	* 字段备注:数量
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private Integer num;

	/**
	* 数据库字段长度:1
	* 字段备注:1-扣减成功,-1-扣减失败
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private Integer status;

	/**
	* 数据库字段长度:8
	* 字段备注:业务发生日期整数，如20171202
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private Integer occurDate;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:19
	* 字段备注:修改时间
	* Sat Dec 02 21:26:54 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;



	public SyConsumeFlow(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setReqId(String reqId){
		this.reqId = reqId;
	}

	public String getReqId(){
		return reqId;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setSrc(Integer src){
		this.src = src;
	}

	public Integer getSrc(){
		return src;
	}

	public void setProdId(String prodId){
		this.prodId = prodId;
	}

	public String getProdId(){
		return prodId;
	}

	public void setProdName(String prodName){
		this.prodName = prodName;
	}

	public String getProdName(){
		return prodName;
	}

	public void setNum(Integer num){
		this.num = num;
	}

	public Integer getNum(){
		return num;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setOccurDate(Integer occurDate){
		this.occurDate = occurDate;
	}

	public Integer getOccurDate(){
		return occurDate;
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

