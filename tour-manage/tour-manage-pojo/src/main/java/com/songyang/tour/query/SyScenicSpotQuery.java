package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/3.
 */

/**
 * 景区query
 *
 * @author
 * @create 2017-09-03 10:38
 **/
public class SyScenicSpotQuery extends BaseQuery {

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     */
    private Long id;

    /**
     * 数据库字段长度:64
     * 字段备注:中文名字
     */
    private String cnName;


    /**
     * 状态：1-有效、-1-删除
     */
    private Integer status;

    /**
     * 热度  1：热   2：不热
     *
     * @see com.songyang.tour.constants.TourConstants.HOT
     */
    private Integer hot;

    /**
     * 数据库字段长度:64
     * 字段备注:类型
     */
    private Integer type;

    /**
     * 数据库字段长度:64
     * 字段备注:地址
     */
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }
}
