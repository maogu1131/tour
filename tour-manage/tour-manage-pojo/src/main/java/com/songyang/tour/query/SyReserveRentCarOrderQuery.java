package com.songyang.tour.query;/**
 * Created by lenovo on 2017/11/19.
 */

/**
 * 租车订单查询
 *
 * @author
 * @create 2017-11-19 19:32
 **/
public class SyReserveRentCarOrderQuery extends BaseQuery {
    private Long id;

    private String type;

    private String userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
