package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_public_service 实体类 
* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
*/ 
public class SyPublicService implements Serializable {

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
	 * @see com.songyang.tour.constants.TourConstants.STATUS
	 */
	private Integer status;

	/**
	* 数据库字段长度:64
	* 字段备注:描述
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String desc;

	/**
	* 数据库字段长度:128
	* 字段备注:地址描述
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String address;

	/**
	* 数据库字段长度:11
	* 字段备注:电话
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String phone;

	/**
	* 数据库字段长度:64
	* 字段备注:注意事项
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String tips;

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



	public SyPublicService(){
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

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setTips(String tips){
		this.tips = tips;
	}

	public String getTips(){
		return tips;
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

