package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/24.
 */

/**
 * 景区类型Query
 *
 * @author
 * @create 2017-09-24 15:34
 **/
public class SyScenicSpotModuleQuery extends BaseQuery {

    /**
     * 数据库字段长度:22
     * 字段备注:景区类型id
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:64
     * 字段备注:景区类型标题
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private String title;


    /**
     * 数据库字段长度:4
     * 字段备注:记录状态 1:有效  2:删除
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
