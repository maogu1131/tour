package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
* sy_guide 实体类 
* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
*/ 
public class SyGuide implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:主键
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:32
	* 字段备注:名字
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String name;

	/**
	* 数据库字段长度:128
	* 字段备注:简介
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String desc;

	/**
	* 数据库字段长度:11
	* 字段备注:电话
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String phone;

	/**
	* 数据库字段长度:256
	* 字段备注:关键词，多个以逗号分隔
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String keyWord;

	/**
	 * 头像地址
	 */
	private String picUrl;

	/**
	 * 音频地址
	 */
	private String audioUrl;

	/**
	 * 状态：1-有效、-1-无效
	 */
	private Integer status;

	/**
	 * 临时字段 不配置XML 只做页面展示用
	 */
	private List<String> picUrlList;

	// url 列表  编辑图片是使用 不用配置XML
	private String picUrlListStr;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	/**
	* 数据库字段长度:64
	* 字段备注:创建者
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String creator;

	/**
	* 数据库字段长度:19
	* 字段备注:创建时间
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Date createTime;

	/**
	* 数据库字段长度:64
	* 字段备注:修改者
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private String modifier;

	/**
	* 数据库字段长度:19
	* 字段备注:修改时间
	* Sun Sep 03 16:51:08 CST 2017 AutoGenerate 
	*/
	private Date modifyTime;



	public SyGuide(){
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setKeyWord(String keyWord){
		this.keyWord = keyWord;
	}

	public String getKeyWord(){
		return keyWord;
	}

	public List<String> getPicUrlList() {
		return picUrlList;
	}

	public void setPicUrlList(List<String> picUrlList) {
		this.picUrlList = picUrlList;
	}

	public String getPicUrlListStr() {
		return picUrlListStr;
	}

	public void setPicUrlListStr(String picUrlListStr) {
		this.picUrlListStr = picUrlListStr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}

