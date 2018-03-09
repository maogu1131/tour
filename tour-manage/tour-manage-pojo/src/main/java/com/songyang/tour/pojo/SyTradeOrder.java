package com.songyang.tour.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * sy_trade_order 实体类
 * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
 */
public class SyTradeOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:32
     * 字段备注:订单id
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private String orderId;

    /**
     * 数据库字段长度:32
     * 字段备注:用户id
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private String userId;

    /**
     * 数据库字段长度:4
     * 字段备注:商品来源:1-景区,2-旅游商品
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private Integer src;

    /**
     * 数据库字段长度:32
     * 字段备注:商品id
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private String prodId;

    /**
     * 数据库字段长度:128
     * 字段备注:产品名称
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private String prodName;

    /**
     * 数据库字段长度:18
     * 字段备注:金额
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private BigDecimal amount;

    /**
     * 购买个数
     */
    private Integer num;

    /**
     * 数据库字段长度:18
     * 字段备注:实付金额
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private BigDecimal realPayAmount;

    /**
     * 数据库字段长度:4
     * 字段备注:订单的状态,-1-确认失败,0待确认，1成功，2确认中，4退款中，5已退款
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private Integer status;

    // 旅游商品-收货地址
    private Long mailAddressId;

    /**
     * 数据库字段长度:1
     * 字段备注:支付方式:1.支付宝 2.微信
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private Integer payWay;

    /**
     * 数据库字段长度:1
     * 字段备注:支付状态:0待支付,1支付成功,-1支付失败,2-处理中
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private Integer payStatus;

    /**
     * 数据库字段长度:128
     * 字段备注:第三方订单id
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private String thirdOrderId;

    /**
     * 数据库字段长度:8
     * 字段备注:业务发生日期整数，如20171202
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private Integer occurDate;

    /**
     * 交易信息
     */
    private String remark;

    /**
     * 数据库字段长度:19
     * 字段备注:创建时间
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private Date createTime;

    /**
     * 数据库字段长度:19
     * 字段备注:修改时间
     * Sat Dec 02 21:26:54 CST 2017 AutoGenerate
     */
    private Date modifyTime;


    public SyTradeOrder() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setSrc(Integer src) {
        this.src = src;
    }

    public Integer getSrc() {
        return src;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdName() {
        return prodName;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    public BigDecimal getRealPayAmount() {
        return realPayAmount;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getMailAddressId() {
        return mailAddressId;
    }

    public void setMailAddressId(Long mailAddressId) {
        this.mailAddressId = mailAddressId;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setThirdOrderId(String thirdOrderId) {
        this.thirdOrderId = thirdOrderId;
    }

    public String getThirdOrderId() {
        return thirdOrderId;
    }

    public void setOccurDate(Integer occurDate) {
        this.occurDate = occurDate;
    }

    public Integer getOccurDate() {
        return occurDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}

