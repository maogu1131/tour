package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/3.
 */

/**
 * 公共场所query
 *
 * @author
 * @create 2017-09-03 10:33
 **/
public class SyPublicPlaceQuery extends BaseQuery {

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:64
     * 字段备注:名称
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String name;

    /**
     * @see com.songyang.tour.constants.TourConstants.STATUS
     */
    private Integer status;
    /**
     * 数据库字段长度:64
     * 字段备注:地址描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
