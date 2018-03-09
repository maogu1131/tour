package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/1.
 */

/**
 * SyCustomRouteQuery
 *
 * @author
 * @create 2017-09-01 10:57
 **/
public class SyCustomRouteQuery extends BaseQuery {

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     */
    private Long id;

    /**
     * 旅游大类型：1-一日游,2-二日游
     */
    private String type;

    /**
     * 旅游子类型：1-纯酒店，2-民宿
     */
    private String subType;

    // 关键字
    private String keyWord;

    /**
     * 状态：1-有效、-1-删除
     */
    private Integer status;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }


}
