package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/6.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.abstracts.AbstractBizComponent;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.HotelType;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.enums.TourType;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.pojo.SyCustomRoute;
import com.songyang.tour.pojo.SyRestaurant;
import com.songyang.tour.pojo.SyRestaurantFood;
import com.songyang.tour.query.SyCustomRouteQuery;
import com.songyang.tour.query.SyRestaurantFoodQuery;
import com.songyang.tour.query.SyRestaurantQuery;
import com.songyang.tour.service.SyCustomRouteDaysService;
import com.songyang.tour.service.SyCustomRouteService;
import com.songyang.tour.service.SyRestaurantFoodService;
import com.songyang.tour.service.SyRestaurantService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.vo.CustomRouteDaysVO;
import com.songyang.tour.vo.FoodDetailVO;
import com.songyang.tour.vo.FoodListVO;
import com.songyang.tour.vo.RouteListVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 定制路线相关组件
 *
 * @author
 * @create 2017-10-06 23:16
 **/
@Component
public class RouteComponent extends AbstractBizComponent {
    private Logger logger = LoggerFactory.getLogger(RouteComponent.class);

    @Resource
    private SyCustomRouteDaysService syCustomRouteDaysService;

    @Resource
    private SyCustomRouteService syCustomRouteService;

    public RouteListVO routeList(JSONObject param) {
        if (param == null) {
            logger.warn("RouteComponent# routeList_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

//        //请求起始页
//        Integer offset = param.getInteger("offset");
//        //请求多少行
//        Integer rows = param.getInteger("rows");
//
//        FoodListVO vo = new FoodListVO();
//
//        int prePageSize = rows + 1;
//        SyRestaurantFoodQuery srfq = new SyRestaurantFoodQuery();
//        srfq.setOffset(offset);
//        srfq.setRows(prePageSize);
//        srfq.setStatus(TourConstants.STATUS.NORMAL);
//        List<SortColumn> sortColumns = new ArrayList<>();
//        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
//        srfq.setSorts(sortColumns);
//        List<SyRestaurantFood> foodList = syRestaurantFoodService.queryListByParam(srfq);
//        if (CollectionUtils.isEmpty(foodList)) {
//            vo.setEndFlag(true);
//            vo.setFoodList(new ArrayList<JSONObject>());
//            return vo;
//        }
//        //结束标示
//        vo.setEndFlag(false);
//
//        if (foodList.size() == prePageSize) {
//            foodList = foodList.subList(0, prePageSize - 1);
//        } else {
//            vo.setEndFlag(true);
//            if (offset != 0) {
//                //到底标题
//                vo.setEndTitle(TourConstants.END_TITLE_MSG);
//            }
//        }
//
//        // 设置 下一个请求的偏移量
//        vo.setOffset(offset + foodList.size());
//
//        List<JSONObject> jsonObjects = new ArrayList<>();
//        JSONObject jo = null;
//        for (SyRestaurantFood food : foodList) {
//            jo = new JSONObject();
//            jo.put("picUrl", CommonUtil.analyzeOnePicUrl(food.getPicUrl()));
//            jo.put("id", food.getId());
//            jo.put("title", food.getName());
//            jo.put("desc", food.getDesc());
//            jsonObjects.add(jo);
//        }
//        vo.setFoodList(jsonObjects);
//        return vo;


        String type = param.getString("playTime");
        String subType = param.getString("favour");
        if (StringUtils.isBlank(type)) {
            throw new TourBizException("参数有误");
        }

        // 酒店/名宿 则  捞出全部
        if(StringUtils.isNotBlank(subType)){
            int typeTemp = Integer.valueOf(subType);
            if(typeTemp == HotelType.MIX.getCode()){
                subType = null;
            }
        }

        //请求起始页
        Integer offset = param.getInteger("offset");
        //请求多少行
        Integer rows = param.getInteger("rows");

        int prePageSize = rows + 1;
        RouteListVO vo = new RouteListVO();

        SyCustomRouteQuery routeQuery = new SyCustomRouteQuery();
        routeQuery.setOffset(offset);
        routeQuery.setRows(prePageSize);
        routeQuery.setType(type);
        routeQuery.setSubType(subType);
        routeQuery.setStatus(TourConstants.STATUS.NORMAL);
        List<SortColumn> sortColumns = new ArrayList<>();
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        routeQuery.setSorts(sortColumns);
        List<SyCustomRoute> customRouteList = syCustomRouteService.queryListByParam(routeQuery);
        if (org.apache.commons.collections.CollectionUtils.isEmpty(customRouteList)) {
            logger.warn("CustomRouteBizController#customRouteQuery_customRouteList_is_null>>>type:" + type + ",subType" + subType);
            vo.setEndFlag(true);
            vo.setRouteList(new ArrayList<JSONObject>());
            return vo;
        }
        //结束标示
        vo.setEndFlag(false);

        if (customRouteList.size() == prePageSize) {
            customRouteList = customRouteList.subList(0, prePageSize - 1);
        } else {
            vo.setEndFlag(true);
            if (offset != 0) {
                //到底标题
                vo.setEndTitle(TourConstants.END_TITLE_MSG);
            }
        }

        // 设置 下一个请求的偏移量
        vo.setOffset(offset + customRouteList.size());

        List<JSONObject> jsonObjects = new ArrayList<>();
        JSONObject jo = null;
        for (SyCustomRoute route : customRouteList) {
            jo = new JSONObject();
            jo.put("id", route.getId());
            jo.put("type", TourType.getNameByCode(route.getType()));
            jo.put("subType", HotelType.getNameByCode(Integer.valueOf(route.getSubType())));
            jo.put("keyWord", route.getKeyWord());
            jo.put("desc", route.getDesc());
            jsonObjects.add(jo);
        }
        vo.setRouteList(jsonObjects);
        return vo;

    }

}
