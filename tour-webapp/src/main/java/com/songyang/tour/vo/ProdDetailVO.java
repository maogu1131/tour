package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/11/1.
 */

import com.songyang.tour.pojo.SyEvaluate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 产品详情VO
 *
 * @author
 * @create 2017-11-01 17:38
 **/
public class ProdDetailVO implements Serializable {
    //产品名称
    private String title;
    //产品图片集合
    private List<String> picUrlList;
    //产品类型 @see com.songyang.tour.enums.ProdTypeEnum
    //TRAVEL(1, "旅行"), WENCUANG(2, "文创"), FARMER(3, "农特"), OTHER(99, "其他");
    private String type;
    //支付数量
    private Integer sendNum;
    //库存数量
    private Integer remainNum;
    //描述
    private String desc;
    //价格
    private BigDecimal price;
    //评论标题
    private String commentTitle;
    //购买状态 -1-不可售,1-待售,3-上架，5-售罄',
    private Integer status;

    // 最新用户评论（取最新一条）
    private SyEvaluate syEvaluate;

    // 能够评价
    private Boolean canComment = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(List<String> picUrlList) {
        this.picUrlList = picUrlList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public SyEvaluate getSyEvaluate() {
        return syEvaluate;
    }

    public void setSyEvaluate(SyEvaluate syEvaluate) {
        this.syEvaluate = syEvaluate;
    }

    public Boolean getCanComment() {
        return canComment;
    }

    public void setCanComment(Boolean canComment) {
        this.canComment = canComment;
    }
}
