package com.songyang.tour.query;/**
 * Created by lenovo on 2017/12/22.
 */

/**
 * 意见建议Query
 *
 * @author
 * @create 2017-12-22 22:20
 **/
public class SyFeedbackQuery extends BaseQuery {
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
