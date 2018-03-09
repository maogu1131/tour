package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/6.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.pojo.SyEvaluate;

import java.util.List;

/**
 * 评价列表list
 *
 * @author
 * @create 2017-10-06 23:01
 **/
public class EvaluateListVO extends PageVO {

    private List<SyEvaluate> evaluateList;

    public List<SyEvaluate> getEvaluateList() {
        return evaluateList;
    }

    public void setEvaluateList(List<SyEvaluate> evaluateList) {
        this.evaluateList = evaluateList;
    }
}
