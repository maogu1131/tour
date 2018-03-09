package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_scenic_spot_guide 实体类 
* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
*/ 
public class SyScenicSpotGuide implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:主键
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:64
	* 字段备注:景区ID
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String scenicSpotId;

	/**
	* 数据库字段长度:128
	* 字段备注:景区讲解音频url地址
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String scenicSpotAudioUrl;

	/**
	* 数据库字段长度:64
	* 字段备注:导游ID
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String guideId;

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



	public SyScenicSpotGuide(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setScenicSpotId(String scenicSpotId){
		this.scenicSpotId = scenicSpotId;
	}

	public String getScenicSpotId(){
		return scenicSpotId;
	}

	public void setScenicSpotAudioUrl(String scenicSpotAudioUrl){
		this.scenicSpotAudioUrl = scenicSpotAudioUrl;
	}

	public String getScenicSpotAudioUrl(){
		return scenicSpotAudioUrl;
	}

	public void setGuideId(String guideId){
		this.guideId = guideId;
	}

	public String getGuideId(){
		return guideId;
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

