package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_traffic_warn 实体类 
* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
*/ 
public class SyTrafficWarn implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:22
	* 字段备注:
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:4
	* 字段备注:记录状态 1:有效  2:删除
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private Integer status;

	/**
	* 数据库字段长度:4
	* 字段备注:交通状态：1.缓行 2.畅通 3.拥堵
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private Integer trafficStatus;

	/**
	* 数据库字段长度:128
	* 字段备注:跳转url
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private String jumpUrl;

	/**
	* 数据库字段长度:256
	* 字段备注:预警内容
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private String content;

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



	public SyTrafficWarn(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setTrafficStatus(Integer trafficStatus){
		this.trafficStatus = trafficStatus;
	}

	public Integer getTrafficStatus(){
		return trafficStatus;
	}

	public void setJumpUrl(String jumpUrl){
		this.jumpUrl = jumpUrl;
	}

	public String getJumpUrl(){
		return jumpUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

}

