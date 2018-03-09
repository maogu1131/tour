package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/3.
 */

/**
 * 导游Query
 *
 * @author
 * @create 2017-09-03 10:25
 **/
public class SyUserQuery extends BaseQuery {

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     */
    private Long id;

    private String userId;

    private String wxLoginId;

    private String qqLoginId;

    private String phone;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWxLoginId() {
        return wxLoginId;
    }

    public void setWxLoginId(String wxLoginId) {
        this.wxLoginId = wxLoginId;
    }

    public String getQqLoginId() {
        return qqLoginId;
    }

    public void setQqLoginId(String qqLoginId) {
        this.qqLoginId = qqLoginId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
