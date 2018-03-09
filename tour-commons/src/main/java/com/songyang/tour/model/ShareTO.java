package com.songyang.tour.model;

/**
 * @author
 * @desc: 份额参数
 * @date 2017/12/3
 */
public class ShareTO {

    // 产品id
    private String prodId;
    /**
     * @see com.songyang.tour.constants.TourConstants.DIRECTION
     * 变动方向
     */
    private Integer direction;
    // 变动数量
    private Integer num;
    // 操作者
    private String operator;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
