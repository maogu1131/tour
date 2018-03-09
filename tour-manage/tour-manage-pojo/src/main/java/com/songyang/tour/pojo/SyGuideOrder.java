package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_guide_order 实体类 
* Thu Nov 23 20:54:54 CST 2017 AutoGenerate 
*/ 
public class SyGuideOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:22
	* 字段备注:主键
	* Thu Nov 23 20:54:54 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:32
	* 字段备注:用户uid
	* Thu Nov 23 20:54:54 CST 2017 AutoGenerate 
	*/
	private String userId;

	/**
	* 数据库字段长度:16
	* 字段备注:客户联系电话
	* Thu Nov 23 20:54:54 CST 2017 AutoGenerate 
	*/
	private String phone;

	/**
	* 数据库字段长度:22
	* 字段备注:导游id
	* Thu Nov 23 20:54:54 CST 2017 AutoGenerate 
	*/
	private Long guideId;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Thu Nov 23 20:54:54 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:19
	* 字段备注:更新时间
	* Thu Nov 23 20:54:54 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;

	/**
	 * 导游姓名  不在xml里配置
	 */
	private String guideName;



	public SyGuideOrder(){
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

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setGuideId(Long guideId){
		this.guideId = guideId;
	}

	public Long getGuideId(){
		return guideId;
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

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
}

