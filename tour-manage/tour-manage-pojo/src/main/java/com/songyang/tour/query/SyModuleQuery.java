package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/3.
 */

/**
 * 模块query
 *
 * @author
 * @create 2017-09-03 10:28
 **/
public class SyModuleQuery extends BaseQuery {

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     */
    private Long id;
    // 名称
    String title;
    // 类型
    String type;
    // 电话
    Integer sort;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}

