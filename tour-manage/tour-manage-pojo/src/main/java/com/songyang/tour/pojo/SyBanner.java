package com.songyang.tour.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * sy_banner 实体类
 * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
 */
public class SyBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库字段长度:8
     * 字段备注:
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:22
     * 字段备注:配注信息
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private String desc;

    /**
     * 数据库字段长度:4
     * 字段备注:banner类型 1:首页banner 2.智能推荐banner
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private Integer type;

    /**
         * banner业务类型 1:关联景区信息  2:跳转静态链接
     */
    private Integer bizType;

    /**
     * 模块类型
     *
     * @see 1:美食 2:民宿 3:旅游攻略 4:景区 5:产品 6:民俗 7:古村落
     */
    private Integer moduleType;
    /**
     * 数据库字段长度:4
     * 字段备注:'记录状态 1:有效  2:删除
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private Integer status;

    /**
     * 数据库字段长度:512
     * 字段备注:
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private String picUrl;

    /**
     * 数据库字段长度:512
     * 字段备注:跳转内容
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private String content;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 数据库字段长度:64
     * 字段备注:创建者
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private String creator;

    /**
     * 数据库字段长度:19
     * 字段备注:创建时间
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private Date createTime;

    /**
     * 数据库字段长度:64
     * 字段备注:修改者
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private String modifier;

    /**
     * 数据库字段长度:19
     * 字段备注:修改时间
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private Date modifyTime;

    //临时属性静态跳转内容 不用配置XML
    private String jumpContent;

    //临时属性景区信息内容 不用配置XML
    private String scenicContent;

    // url 列表  编辑图片是使用 不用配置XML
    private String picUrlListStr;

    public SyBanner() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public String getJumpContent() {
        return jumpContent;
    }

    public void setJumpContent(String jumpContent) {
        this.jumpContent = jumpContent;
    }

    public String getScenicContent() {
        return scenicContent;
    }

    public void setScenicContent(String scenicContent) {
        this.scenicContent = scenicContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPicUrlListStr() {
        return picUrlListStr;
    }

    public void setPicUrlListStr(String picUrlListStr) {
        this.picUrlListStr = picUrlListStr;
    }
}

