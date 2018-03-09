package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
* sy_old_village_nearby 实体类 
* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
*/ 
public class SyOldVillageNearby implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 数据库字段长度:20
	* 字段备注:主键
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Long id;

	/**
	* 数据库字段长度:20
	* 字段备注:古村落id
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private Long villageId;

	/**
	* 数据库字段长度:256
	* 字段备注:景区ID，以逗号分隔
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String scenicSpotIds;

	/**
	* 数据库字段长度:256
	* 字段备注:酒店id，以逗号分隔
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String hotelIds;

	/**
	* 数据库字段长度:256
	* 字段备注:餐馆id，以逗号分隔
	* Sun Sep 03 16:51:09 CST 2017 AutoGenerate 
	*/
	private String restaurantIds;

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



	public SyOldVillageNearby(){
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setVillageId(Long villageId){
		this.villageId = villageId;
	}

	public Long getVillageId(){
		return villageId;
	}

	public void setScenicSpotIds(String scenicSpotIds){
		this.scenicSpotIds = scenicSpotIds;
	}

	public String getScenicSpotIds(){
		return scenicSpotIds;
	}

	public void setHotelIds(String hotelIds){
		this.hotelIds = hotelIds;
	}

	public String getHotelIds(){
		return hotelIds;
	}

	public void setRestaurantIds(String restaurantIds){
		this.restaurantIds = restaurantIds;
	}

	public String getRestaurantIds(){
		return restaurantIds;
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

