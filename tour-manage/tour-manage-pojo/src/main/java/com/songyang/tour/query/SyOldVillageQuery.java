package com.songyang.tour.query;

/**
 * 古落村query
 *
 * @author
 * @create 2017-09-03 10:30
 **/
public class SyOldVillageQuery extends BaseQuery {

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

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

}
