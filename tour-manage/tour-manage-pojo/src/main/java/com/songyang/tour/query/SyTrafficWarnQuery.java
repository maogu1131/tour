package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/24.
 */

/**
 * 交通预警Query
 *
 * @author
 * @create 2017-09-24 15:32
 **/
public class SyTrafficWarnQuery extends BaseQuery {

    /**
     * 数据库字段长度:22
     * 字段备注:
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:4
     * 字段备注:记录状态 1:有效  2:删除
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Integer status;

    /**
     * 数据库字段长度:4
     * 字段备注:交通状态：1.缓行 2.畅通 3.拥堵
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Integer trafficStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTrafficStatus() {
        return trafficStatus;
    }

    public void setTrafficStatus(Integer trafficStatus) {
        this.trafficStatus = trafficStatus;
    }
}
