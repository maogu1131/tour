package com.songyang.tour.pojo;

import java.util.Date;
import java.io.Serializable;

/**
 * sy_notice 实体类
 * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
 */
public class SyNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:32
     * 字段备注:标题
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String content;

    /**
     * 记录状态 1：正常   -1：删除
     */
    private Integer status;

    /**
     * 数据库字段长度:128
     * 字段备注:跳转地址
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String jumpUrl;

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
     * 字段备注:
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Date modifyTime;


    public SyNotice() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
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

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

