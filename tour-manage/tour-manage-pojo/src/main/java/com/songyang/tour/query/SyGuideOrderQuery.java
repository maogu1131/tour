package com.songyang.tour.query;/**
 * Created by lenovo on 2017/11/23.
 */

/**
 * 导游业务查询
 *
 * @author
 * @create 2017-11-23 21:00
 **/
public class SyGuideOrderQuery extends BaseQuery {
    /**
     * 数据库字段长度:22
     * 字段备注:主键
     * Thu Nov 23 20:54:54 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:32
     * 字段备注:用户uid
     * Thu Nov 23 20:54:54 CST 2017 AutoGenerate
     */
    private String userId;

    /**
     * 数据库字段长度:16
     * 字段备注:客户联系电话
     * Thu Nov 23 20:54:54 CST 2017 AutoGenerate
     */
    private String phone;

    /**
     * 数据库字段长度:22
     * 字段备注:导游id
     * Thu Nov 23 20:54:54 CST 2017 AutoGenerate
     */
    private Long guideId;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getGuideId() {
        return guideId;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }
}
