package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/3.
 */

/**
 * 通知query
 *
 * @author
 * @create 2017-09-03 10:30
 **/
public class SyNoticeQuery extends BaseQuery {

    private Long id;

    /**
     * 数据库字段长度:32
     * 字段备注:标题
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String content;

    /**
     * 记录状态 1：正常   -1：删除
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
