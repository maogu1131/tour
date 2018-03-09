package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_module 实体类 
* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
*/ 
public class SyModule implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:主键
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	 * 数据库字段长度:20
	 * 字段备注:大类型:1-定制化路线（custom_route），2-民俗(folk)，3-商家(merchant)，4-古村落(old_village)，5-产品（prod），6-公共场所（public_place）,7-公共服务（service）,8-餐馆（restaurant）,9-景区（scenic_spot）
	 * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
	 */
	private String title;

	/**
	 * 子标题
	 */
	private String subTitle;

	/**
	 * 数据库字段长度:20
	 * 字段备注:大类型:1-定制化路线（custom_route），2-民俗(folk)，3-商家(merchant)，4-古村落(old_village)，5-产品（prod），6-公共场所（public_place）,7-公共服务（service）,8-餐馆（restaurant）,9-景区（scenic_spot）
	 * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
	 */
	private String titlePicUrl;


	// url 列表  编辑图片是使用 不用配置XML
	private String titlePicUrlListStr;


	/**
	* 数据库字段长度:20
	* 字段备注:大类型:1-定制化路线（custom_route），2-民俗(folk)，3-商家(merchant)，4-古村落(old_village)，5-产品（prod），6-公共场所（public_place）,7-公共服务（service）,8-餐馆（restaurant）,9-景区（scenic_spot）
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Integer type;

	/**
	 * 排序
	 */
	private Integer sort;


//	/**
//	* 数据库字段长度:128
//	* 字段备注:关键词
//	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate
//	*/
//	private String keyWord;

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



	public SyModule(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getTitlePicUrl() {
		return titlePicUrl;
	}

	public void setTitlePicUrl(String titlePicUrl) {
		this.titlePicUrl = titlePicUrl;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public String getTitlePicUrlListStr() {
		return titlePicUrlListStr;
	}

	public void setTitlePicUrlListStr(String titlePicUrlListStr) {
		this.titlePicUrlListStr = titlePicUrlListStr;
	}
}

