package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/3.
 */

/**
 * 餐馆query
 *
 * @author
 * @create 2017-09-03 10:35
 **/
public class SyRestaurantQuery extends BaseQuery {

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:64
     * 字段备注:中文名字
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String cnName;

    /**
     * @see com.songyang.tour.constants.TourConstants.STATUS
     */
    private Integer status;


    /**
     * 数据库字段长度:4
     * 字段备注:餐馆类型:1-中餐馆、2-西餐馆、3-日韩料理、4-土家菜,99-其他
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Integer type;


    /**
     * 数据库字段长度:128
     * 字段备注:地址描述
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String address;


    /**
     * 数据库字段长度:11
     * 字段备注:预定电话
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String phone;


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
