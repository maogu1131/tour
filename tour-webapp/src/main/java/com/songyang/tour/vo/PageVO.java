package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/6.
 */

import java.io.Serializable;

/**
 * 页面VO
 *
 * @author
 * @create 2017-10-06 22:56
 **/
public class PageVO implements Serializable {

    /**
     * 设置 下一个请求的偏移量
     */
    private Integer offset;

    /**
     * 列表结束标示
     */
    private Boolean endFlag;

    /**
     * 结束标题
     */
    private String endTitle;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(Boolean endFlag) {
        this.endFlag = endFlag;
    }

    public String getEndTitle() {
        return endTitle;
    }

    public void setEndTitle(String endTitle) {
        this.endTitle = endTitle;
    }
}
