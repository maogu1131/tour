package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/24.
 */

/**
 * 通用配置Query
 *
 * @author
 * @create 2017-09-24 15:37
 **/
public class SyCommonConfigQuery extends BaseQuery {

    /**
     * 数据库字段长度:20
     * 字段备注:自增主键id
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:64
     * 字段备注:键
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private String key;

    /**
     * 数据库字段长度:100
     * 字段备注:描述
     * Sun Sep 24 15:24:05 CST 2017 AutoGenerate
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
