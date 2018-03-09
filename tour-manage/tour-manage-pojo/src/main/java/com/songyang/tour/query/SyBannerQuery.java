package com.songyang.tour.query;/**
 * Created by lenovo on 2017/10/1.
 */

/**
 * banner查询类
 *
 * @author
 * @create 2017-10-01 23:04
 **/
public class SyBannerQuery extends BaseQuery {

    /**
     * 数据库字段长度:8
     * 字段备注:
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private Integer id;

    /**
     * 数据库字段长度:22
     * 字段备注:配注信息
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private String desc;

    /**
     * banner业务类型 1:首页banner 2:智能推荐banner
     */
    private Integer type;

    /**
     * 数据库字段长度:4
     * 字段备注:'记录状态 1:有效  2:删除
     * Sun Oct 01 22:57:37 CST 2017 AutoGenerate
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
