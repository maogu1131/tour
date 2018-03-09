package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_custom_route_days 实体类 
* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
*/ 
public class SyCustomRouteDays implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:22
	* 字段备注:主键id
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:22
	* 字段备注:定制路线id
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private Long customRouteId;

	/**
	* 数据库字段长度:4
	* 字段备注:第几天
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private Integer dayNum;

	/**
	 * 总的富文本
	 */
	private String content;

	/**
	* 数据库字段长度:65535
	* 字段备注:游-富文本
	* Sun Sep 24 15:24:05 CST 2017 AutoGenerate 
	*/
	private String tourContent;

	/**
	 * 数据库字段长度:65535
	 * 字段备注:住-富文本
	 * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
	 */
	private String liveContent;


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

	// 操作类型
	private Integer OperateType;

	public SyCustomRouteDays(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setCustomRouteId(Long customRouteId){
		this.customRouteId = customRouteId;
	}

	public Long getCustomRouteId(){
		return customRouteId;
	}

//	public void setTitle(String title){
//		this.title = title;
//	}
//
//	public String getTitle(){
//		return title;
//	}
//
//	public void setType(Integer type){
//		this.type = type;
//	}
//
//	public Integer getType(){
//		return type;
//	}

	public void setDayNum(Integer dayNum){
		this.dayNum = dayNum;
	}

	public Integer getDayNum(){
		return dayNum;
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

	public Integer getOperateType() {
		return OperateType;
	}

	public void setOperateType(Integer operateType) {
		OperateType = operateType;
	}

	public String getTourContent() {
		return tourContent;
	}

	public void setTourContent(String tourContent) {
		this.tourContent = tourContent;
	}

	public String getLiveContent() {
		return liveContent;
	}

	public void setLiveContent(String liveContent) {
		this.liveContent = liveContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

