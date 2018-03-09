package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/24.
 */

/**
 * 旅游攻略查询
 *
 * @author
 * @create 2017-09-24 15:30
 **/
public class SyTravelStrategyQuery extends BaseQuery {

    /**
     * 数据库字段长度:20
     * 字段备注:
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:64
     * 字段备注:标题
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private String title;

    /**
     * 数据库字段长度:4
     * 字段备注:热度 1:热  2:不热
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Integer hot;

    /**
     * 数据库字段长度:4
     * 字段备注:记录状态 1:有效  2:删除
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Integer status;

    /**
     *type 1-地址 2-图片',
     */
    private Integer type;

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

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
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
}
