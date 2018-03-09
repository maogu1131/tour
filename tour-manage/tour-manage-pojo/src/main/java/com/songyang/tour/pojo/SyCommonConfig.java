package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_common_config 实体类 
* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
*/ 
public class SyCommonConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:自增主键id
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:64
	* 字段备注:键
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private String key;

	/**
	* 数据库字段长度:100
	* 字段备注:描述
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private String name;

	/**
	* 数据库字段长度:21845
	* 字段备注:值
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private String value;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:22
	* 字段备注:创建人
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private String creator;

	/**
	* 数据库字段长度:19
	* 字段备注:最后修改时间
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;

	/**
	* 数据库字段长度:22
	* 字段备注:最后修改人
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private String operator;



	public SyCommonConfig(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setKey(String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setCreator(String creator){
		this.creator = creator;
	}

	public String getCreator(){
		return creator;
	}

	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime(){
		return modifyTime;
	}

	public void setOperator(String operator){
		this.operator = operator;
	}

	public String getOperator(){
		return operator;
	}

}

