package com.songyang.tour.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* sy_prod 实体类 
* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
*/ 
public class SyProd implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:主键
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:20
	* 字段备注:商家id
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Long merchantId;

	/**
	* 数据库字段长度:64
	* 字段备注:产品名称
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String name;

	/**
	* 数据库字段长度:4
	* 字段备注:类型:1-旅游，2-文创，3-农特,99-其他
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Integer type;

	//状态  状态 1正常  -1删除  3-售罄
	private Integer status;

	/**
	* 数据库字段长度:64
	* 字段备注:其他类型描述
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String desc;

	/**
	* 数据库字段长度:4
	* 字段备注:评分：总共5星
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate
	*/
	private Integer level;

	/**
	* 数据库字段长度:128
	* 字段备注:商家图片,以逗号分隔
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate
	*/
	private String picUrl;


	// url 列表  编辑图片是使用 不用配置XML
	private String picUrlListStr;

//	/**
//	* 数据库字段长度:4
//	* 字段备注:销售状态：-1-不可售,1-待售,3-销售中，5-售罄
//	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate
//	*/
//	private Integer saleStatus;

	/**
	* 数据库字段长度:22
	* 字段备注:单价
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private BigDecimal price;

	/**
	* 数据库字段长度:20
	* 字段备注:总量
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Integer totalNum;

	/**
	* 数据库字段长度:20
	* 字段备注:剩余量
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Integer remainNum;

	/**
	* 数据库字段长度:20
	* 字段备注:卖出量
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Integer sendNum;

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

	/**
	 * 临时字段 不配置XML 只做页面展示用
	 */
	private List<String> picUrlList;

	public SyProd(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setMerchantId(Long merchantId){
		this.merchantId = merchantId;
	}

	public Long getMerchantId(){
		return merchantId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getType(){
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}

	public String getPicUrl(){
		return picUrl;
	}

//	public void setSaleStatus(Integer saleStatus){
//		this.saleStatus = saleStatus;
//	}
//
//	public Integer getSaleStatus(){
//		return saleStatus;
//	}

	public void setPrice(BigDecimal price){
		this.price = price;
	}

	public BigDecimal getPrice(){
		return price;
	}

	public void setTotalNum(Integer totalNum){
		this.totalNum = totalNum;
	}

	public Integer getTotalNum(){
		return totalNum;
	}

	public void setRemainNum(Integer remainNum){
		this.remainNum = remainNum;
	}

	public Integer getRemainNum(){
		return remainNum;
	}

	public void setSendNum(Integer sendNum){
		this.sendNum = sendNum;
	}

	public Integer getSendNum(){
		return sendNum;
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
}

