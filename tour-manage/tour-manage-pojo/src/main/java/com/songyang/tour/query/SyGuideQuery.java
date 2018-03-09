package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/3.
 */

/**
 * 导游Query
 *
 * @author
 * @create 2017-09-03 10:25
 **/
public class SyGuideQuery extends BaseQuery {

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     */
    private Long id;
    // 名称
    String name;
    // 关键词
    String keyWord;
    // 电话
    String phone;
    // 状态
    Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
