package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/6.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.abstracts.AbstractBizComponent;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.pojo.SyRestaurant;
import com.songyang.tour.pojo.SyRestaurantFood;
import com.songyang.tour.query.SyRestaurantFoodQuery;
import com.songyang.tour.query.SyRestaurantQuery;
import com.songyang.tour.service.SyRestaurantFoodService;
import com.songyang.tour.service.SyRestaurantService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.FoodDetailVO;
import com.songyang.tour.vo.FoodListVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 美食业务相关组件
 *
 * @author
 * @create 2017-10-06 23:16
 **/
@Component
public class FoodComponent extends AbstractBizComponent {
    private Logger logger = LoggerFactory.getLogger(FoodComponent.class);

    @Resource
    private SyRestaurantFoodService syRestaurantFoodService;

    @Resource
    private SyRestaurantService syRestaurantService;

    public FoodListVO foodList(JSONObject param) {
        if (param == null) {
            logger.warn("FoodBizComponent#foodList_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //请求起始页
        Integer offset = param.getInteger("offset");
        //请求多少行
        Integer rows = param.getInteger("rows");

        FoodListVO vo = new FoodListVO();

        int prePageSize = rows + 1;
        SyRestaurantFoodQuery srfq = new SyRestaurantFoodQuery();
        srfq.setOffset(offset);
        srfq.setRows(prePageSize);
        srfq.setStatus(TourConstants.STATUS.NORMAL);
        List<SortColumn> sortColumns = new ArrayList<>();
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        srfq.setSorts(sortColumns);
        List<SyRestaurantFood> foodList = syRestaurantFoodService.queryListByParam(srfq);
        if (CollectionUtils.isEmpty(foodList)) {
            vo.setEndFlag(true);
            vo.setFoodList(new ArrayList<JSONObject>());
            return vo;
        }
        //结束标示
        vo.setEndFlag(false);

        if (foodList.size() == prePageSize) {
            foodList = foodList.subList(0, prePageSize - 1);
        } else {
            vo.setEndFlag(true);
            if (offset != 0) {
                //到底标题
                vo.setEndTitle(TourConstants.END_TITLE_MSG);
            }
        }

        // 设置 下一个请求的偏移量
        vo.setOffset(offset + foodList.size());

        List<JSONObject> jsonObjects = new ArrayList<>();
        JSONObject jo = null;
        for (SyRestaurantFood food : foodList) {
            jo = new JSONObject();
            jo.put("picUrl", CommonUtil.analyzeOnePicUrl(food.getPicUrl()));
            jo.put("id", food.getId());
            jo.put("title", food.getName());
            jo.put("desc", food.getDesc());
            jsonObjects.add(jo);
        }
        vo.setFoodList(jsonObjects);
        return vo;
    }

    public FoodDetailVO foodDetail(JSONObject param) {
        FoodDetailVO vo = new FoodDetailVO();
        if (param == null) {
            logger.warn("FoodBizComponent#foodDetail_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //美食id
        Long id = param.getLong("id");
        if (null == id) {
            logger.warn("FoodBizComponent#foodId_is_null");
            return vo;
        }
        SyRestaurantFood food = syRestaurantFoodService.selectById(id);
        if (null == food) {
            logger.warn("FoodBizComponent#foodId_is_null>>>>foodId:" + id);
            return vo;
        }
        //TODO 截取
        vo.setDesc(food.getDesc());
        vo.setPicList(CommonUtil.analyzePicUrl(food.getPicUrl()));
        vo.setTitle(food.getName());
        Long restaurantId = food.getRestaurantId();

        // 7-1、时间倒序查出三个餐厅
        SyRestaurantQuery srq = new SyRestaurantQuery();
        srq.setOffset(0);
        srq.setRows(3);
        srq.setStatus(TourConstants.STATUS.NORMAL);
        List<SortColumn> sortColumns = new ArrayList<>();
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        srq.setSorts(sortColumns);
        List<SyRestaurant> syRestaurantDBList = syRestaurantService.queryListByParam(srq);
        if (CollectionUtils.isEmpty(syRestaurantDBList)) {
            return vo;
        }

        List<JSONObject> jsonObjects = new ArrayList<>();

        // 7-1、查询关联的餐厅
        SyRestaurant restaurant = syRestaurantService.selectById(restaurantId);
        if (null == restaurant) {
            logger.warn("FoodBizComponent#SyRestaurant_is_null>>>>restaurantId:" + restaurantId);
            construcRestaurantDataList(syRestaurantDBList, jsonObjects);
            vo.setRestaurantList(jsonObjects);
            return vo;
        }


        // 7-2、有推荐餐厅排在第一位
        boolean flag = false;
        for (SyRestaurant sr : syRestaurantDBList) {
            if (sr.getId().longValue() == restaurant.getId().longValue()) {
                flag = true;
                break;
            }
        }
        if (flag) {
            if (syRestaurantDBList.size() >= 3) {
                syRestaurantDBList.add(0,restaurant);
                syRestaurantDBList = syRestaurantDBList.subList(0,3);
            }
        }

        construcRestaurantDataList(syRestaurantDBList, jsonObjects);
        vo.setRestaurantList(jsonObjects);
        return vo;
    }
}
