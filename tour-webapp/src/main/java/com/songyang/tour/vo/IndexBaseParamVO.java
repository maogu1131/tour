package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/9/27.
 */

import java.io.Serializable;

/**
 * 首页通用VO
 *
 * @author
 * @create 2017-09-27 0:44
 **/
public class IndexBaseParamVO implements Serializable {


    /**
     * 具体旅游ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 子标题
     */
    private String subTitle;

    /**
     ** banner业务类型 1:关联景区信息  2:跳转静态链接
     */
    private Integer type;


    /**
     * 板块类型
     * CUSTOM_ROUTE("1","定制化路线"), FOLK("2","民俗"),
     * MERCHANT("3","商家"),OLD_VILLAGE("4","古村落"),
     * PROD("5","产品"),PUBLIC_PLACE("6","公共场所"),SERVICE("7","公共服务"),
     * RESTAURANT("8","餐馆"),SCENIC_SPOT("9","景区");
     *
     * @see com.songyang.tour.enums.ModuleType
     */
    private Integer  moduleType;

    /**
     * 图片地址
     */
    private String picUrl;

    /**
     * 跳转内容
     */
    private String jumpContent;

    /**
     * 描述
     */
    private String desc;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getJumpContent() {
        return jumpContent;
    }

    public void setJumpContent(String jumpContent) {
        this.jumpContent = jumpContent;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
