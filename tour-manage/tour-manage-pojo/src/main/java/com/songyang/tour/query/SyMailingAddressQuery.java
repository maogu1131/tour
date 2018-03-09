package com.songyang.tour.query;/**
 * Created by lenovo on 2017/11/4.
 */

/**
 * 邮箱query
 *
 * @author
 * @create 2017-11-04 16:14
 **/
public class SyMailingAddressQuery extends BaseQuery {

    private Long id;

    private String userId;

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
}
