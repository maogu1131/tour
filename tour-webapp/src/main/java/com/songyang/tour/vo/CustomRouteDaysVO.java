package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/11/26.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 智能推荐详情接口
 *
 * @author
 * @create 2017-11-26 15:42
 **/
public class CustomRouteDaysVO implements Serializable{

    //头标题
    private String headTitle;

    //定制路线Id
    private Long customRouteId;

    //智能推荐详情List
    /**
     * dayDesc 第一天[
     * {
     * title 【游】 大木山景区
     * content
     * },
     * {
     *  title 【住】 万达嘉华酒店
     *  content
     * }
     *
     * ]
     */
    private List<CustomRouteDetailVO> customRouteDaysVOList;

    public String getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle;
    }

    public Long getCustomRouteId() {
        return customRouteId;
    }

    public void setCustomRouteId(Long customRouteId) {
        this.customRouteId = customRouteId;
    }

    public List<CustomRouteDetailVO> getCustomRouteDaysVOList() {
        return customRouteDaysVOList;
    }

    public void setCustomRouteDaysVOList(List<CustomRouteDetailVO> customRouteDaysVOList) {
        this.customRouteDaysVOList = customRouteDaysVOList;
    }
}
