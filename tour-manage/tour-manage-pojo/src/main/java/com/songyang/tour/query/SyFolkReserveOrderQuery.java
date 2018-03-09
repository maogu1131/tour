package com.songyang.tour.query;/**
 * Created by lenovo on 2017/12/22.
 */

/**
 * 民俗活动预约订单查询
 *
 * @author
 * @create 2017-12-22 22:19
 **/
public class SyFolkReserveOrderQuery extends BaseQuery {

    private Long id;

    private String userId;

    private String rentPhone;

    private Long folkId;

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

    public String getRentPhone() {
        return rentPhone;
    }

    public void setRentPhone(String rentPhone) {
        this.rentPhone = rentPhone;
    }

    public Long getFolkId() {
        return folkId;
    }

    public void setFolkId(Long folkId) {
        this.folkId = folkId;
    }
}
