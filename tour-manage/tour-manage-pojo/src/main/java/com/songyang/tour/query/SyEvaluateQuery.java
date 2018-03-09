package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/3.
 */

/**
 * 评价Query
 *
 * @author
 * @create 2017-09-03 10:20
 **/
public class SyEvaluateQuery extends BaseQuery {

    // 类型
    private Integer aEffectType;
    // 被评价方的id
    private String aEffectId;
    // 评价方的id
    private String bEffectId;
    //  状态
    private Integer status;

    public Integer getaEffectType() {
        return aEffectType;
    }

    public void setaEffectType(Integer aEffectType) {
        this.aEffectType = aEffectType;
    }

    public String getaEffectId() {
        return aEffectId;
    }

    public void setaEffectId(String aEffectId) {
        this.aEffectId = aEffectId;
    }

    public String getbEffectId() {
        return bEffectId;
    }

    public void setbEffectId(String bEffectId) {
        this.bEffectId = bEffectId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
