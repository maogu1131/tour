package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/29.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 搜索模型
 *
 * @author
 * @create 2017-10-29 13:47
 **/
public class SearchVO {

    //模块名称
    private String moduleName;
    //模块类型
    private Integer moduleType;
    //模块数据列表
    private List<JSONObject> moduleDataList;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public List<JSONObject> getModuleDataList() {
        return moduleDataList;
    }

    public void setModuleDataList(List<JSONObject> moduleDataList) {
        this.moduleDataList = moduleDataList;
    }
}
