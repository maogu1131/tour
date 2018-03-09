package com.songyang.tour.query;

import java.util.Date;

/**
 * @author
 * @desc:
 * @date 2017/12/2
 */
public class SyTradeOrderQuery extends BaseQuery {

    // 订单id
    private String orderId;

    // 用户id
    private String userId;

    /**
     * 产品类型
     * @see com.songyang.tour.constants.TourConstants.PROD_SRC
     */
    private Integer src;

    private String prodId;

    // 订单状态
    private Integer status;

    // 发生日期
    private Integer occurDate;

    // 日期
    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSrc() {
        return src;
    }

    public void setSrc(Integer src) {
        this.src = src;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Integer occurDate) {
        this.occurDate = occurDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
}
