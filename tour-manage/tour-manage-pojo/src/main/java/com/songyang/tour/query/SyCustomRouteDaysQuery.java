package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/24.
 */

/**
 * 定制旅行几日Query
 *
 * @author
 * @create 2017-09-24 15:35
 **/
public class SyCustomRouteDaysQuery extends BaseQuery {

    /**
     * 数据库字段长度:22
     * 字段备注:主键id
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:22
     * 字段备注:定制路线id
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Long customRouteId;

    /**
     * 数据库字段长度:64
     * 字段备注:标题
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Integer dayNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomRouteId() {
        return customRouteId;
    }

    public void setCustomRouteId(Long customRouteId) {
        this.customRouteId = customRouteId;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }
}
