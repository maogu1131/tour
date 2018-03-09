package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/9/27.
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单VO
 *
 * @author
 * @create 2017-09-27 1:16
 **/
public class OrderVO implements Serializable{

    /**
     * 订单状态
     *@see com.songyang.tour.constants.TourConstants.ORDER_STATUS
     */
    private Integer status;

    /**
     * 支付状态
     *@see com.songyang.tour.constants.TourConstants.PAY_STATUS
     */
    private Integer payStatus;

    // 订单描述
    private String desc;

    /**
     * 订单类型
     * @see com.songyang.tour.constants.TourConstants.PROD_SRC
     */
    private Integer src;
    // 订单id
    private String orderId;

    // 订单创建时间
    private Date createTime;

    // 购买的商品名称
    private String prodName;

    // 购买的商品图片地址
    private String picUrl;
    // 购买的商品 单价
    private BigDecimal price;
    // 购买的商品 数量
    private Integer num;
    // 购买的商品 总付款金额
    private BigDecimal payAmount;
    // 收货地址 信息
    private MailAddressVO mailAddressVO;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSrc() {
        return src;
    }

    public void setSrc(Integer src) {
        this.src = src;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public MailAddressVO getMailAddressVO() {
        return mailAddressVO;
    }

    public void setMailAddressVO(MailAddressVO mailAddressVO) {
        this.mailAddressVO = mailAddressVO;
    }
}
