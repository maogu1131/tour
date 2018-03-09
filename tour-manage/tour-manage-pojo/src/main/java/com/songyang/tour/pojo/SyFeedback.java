package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_feedback 实体类 
* Fri Dec 22 21:56:18 CST 2017 AutoGenerate 
*/ 
public class SyFeedback implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:22
	* 字段备注:
	* Fri Dec 22 21:56:18 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:512
	* 字段备注:联系方式
	* Fri Dec 22 21:56:18 CST 2017 AutoGenerate 
	*/
	private String contactInfo;

	/**
	 * 意见反馈内容
	 */
	private  String context;

	/**
	* 数据库字段长度:64
	* 字段备注:用户id
	* Fri Dec 22 21:56:18 CST 2017 AutoGenerate 
	*/
	private String userId;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Fri Dec 22 21:56:18 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:19
	* 字段备注:
	* Fri Dec 22 21:56:18 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;



	public SyFeedback(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setContactInfo(String contactInfo){
		this.contactInfo = contactInfo;
	}

	public String getContactInfo(){
		return contactInfo;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
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

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}

