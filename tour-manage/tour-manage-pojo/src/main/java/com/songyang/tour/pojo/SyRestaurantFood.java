package com.songyang.tour.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * sy_restaurant_food 实体类
 * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
 */
public class SyRestaurantFood implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:20
     * 字段备注:餐馆ID
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Long restaurantId;

    /**
     * 数据库字段长度:64
     * 字段备注:菜名
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String name;

    /**
     * 数据库字段长度:4
     * 字段备注:餐馆类型:1-杭帮菜、2-西餐、3-日韩料理、4-土家菜,5-湘菜，6-川菜，7-徽菜，8-粤菜，9-东北菜，99-其他
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Integer type;

    /**
     * 记录 1：有效   -1：删除
     *
     * @see com.songyang.tour.constants.TourConstants.STATUS
     */
    private Integer status;

    /**
     * 数据库字段长度:128
     * 字段备注:其他描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String otherDesc;

    /**
     * 数据库字段长度:11
     * 字段备注:菜等级:星数量，最多五个星
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Integer level;

    /**
     * 数据库字段长度:64
     * 字段备注:菜简介
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String desc;

    /**
     * 数据库字段长度:22
     * 字段备注:单价
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private BigDecimal price;

    /**
     * 数据库字段长度:128
     * 字段备注:图片地址
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String picUrl;

    // 编辑图片时使用 不用配置XML
    private String picUrlListStr;

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

    public String getPicUrlListStr() {
        return picUrlListStr;
    }

    public void setPicUrlListStr(String picUrlListStr) {
        this.picUrlListStr = picUrlListStr;
    }

    public SyRestaurantFood() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() {
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


}

