package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/6.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.abstracts.AbstractBizComponent;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.BaseLabel;
import com.songyang.tour.pojo.SyRestaurant;
import com.songyang.tour.pojo.SyRestaurantFood;
import com.songyang.tour.query.SyRestaurantFoodQuery;
import com.songyang.tour.query.SyRestaurantQuery;
import com.songyang.tour.service.SyRestaurantFoodService;
import com.songyang.tour.service.SyRestaurantService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.RestaurantDetailVO;
import com.songyang.tour.vo.RestaurantListVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 餐馆相关业务组件
 *
 * @author
 * @create 2017-10-06 23:32
 **/
@Component
public class RestaurantComponent extends AbstractBizComponent {

    private Logger logger = LoggerFactory.getLogger(RestaurantComponent.class);

    @Resource
    private SyRestaurantService syRestaurantService;

    @Resource
    private SyRestaurantFoodService syRestaurantFoodService;

    public RestaurantListVO restaurantList(JSONObject param) {
        if (param == null) {
            logger.warn("RestaurantBizComponet#restaurantList_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //请求起始页
        Integer offset = param.getInteger("offset");
        //请求多少行
        Integer rows = param.getInteger("rows");

        RestaurantListVO vo = new RestaurantListVO();

        int prePageSize = rows + 1;
        SyRestaurantQuery srq = new SyRestaurantQuery();
        srq.setOffset(offset);
        srq.setRows(prePageSize);
        srq.setStatus(TourConstants.STATUS.NORMAL);
        List<SortColumn> sortColumns = new ArrayList<>();
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        srq.setSorts(sortColumns);
        List<SyRestaurant> restaurantList = syRestaurantService.queryListByParam(srq);
        if (CollectionUtils.isEmpty(restaurantList)) {
            vo.setEndFlag(true);
            vo.setRestaurantList(new ArrayList<JSONObject>());
            return vo;
        }
        //结束标示
        vo.setEndFlag(false);

        if (restaurantList.size() == prePageSize) {
            restaurantList = restaurantList.subList(0, prePageSize - 1);
        } else {
            vo.setEndFlag(true);
            if (offset != 0) {
                //到底标题
                vo.setEndTitle(TourConstants.END_TITLE_MSG);
            }
        }

        // 设置 下一个请求的偏移量
        vo.setOffset(offset + restaurantList.size());

        List<JSONObject> jsonObjects = new ArrayList<>();

        construcRestaurantDataList(restaurantList, jsonObjects);

        vo.setRestaurantList(jsonObjects);
        return vo;
    }

    public RestaurantDetailVO restaurantDetail(JSONObject param) {
        RestaurantDetailVO vo = new RestaurantDetailVO();
        if (param == null) {
            logger.warn("RestaurantComponent#restaurantDetail_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //餐馆id
        Long id = param.getLong("id");
        if (null == id) {
            logger.warn("RestaurantBizComponet#restaurantId_is_null");
            return vo;
        }
        SyRestaurant syRestaurant = syRestaurantService.selectById(id);
        if (null == syRestaurant) {
            logger.warn("RestaurantBizComponet#restaurantDetail_query_object_is_null>>>id:" + id);
            return vo;
        }
        vo.setTitle(syRestaurant.getCnName());
        vo.setBannerUrlList(CommonUtil.analyzePicUrl(syRestaurant.getBannerPicUrl()));

        if (StringUtils.isNotBlank(syRestaurant.getDesc()) || StringUtils.isNotBlank(syRestaurant.getPicUrl())) {
            vo.setRestaurantDescTitle("餐馆简介");
            vo.setRestaurantDesc(syRestaurant.getDesc());
            vo.setRestaurantDescPicUrlList(CommonUtil.analyzePicUrl(syRestaurant.getPicUrl()));
        }

        List<BaseLabel> labelList = new ArrayList<>();
        labelList.add(new BaseLabel("地址", syRestaurant.getAddress()));
        labelList.add(new BaseLabel("营业时间", syRestaurant.getOpenTimeDesc()));
        labelList.add(new BaseLabel("人均消费", syRestaurant.getPerPayDesc()));
        labelList.add(new BaseLabel("交通/停车", syRestaurant.getTrafficDesc()));
        vo.setLabelList(labelList);


        vo.setLatitude(syRestaurant.getLatitude());
        vo.setLongitude(syRestaurant.getLongitude());
        vo.setTel(syRestaurant.getPhone());

        List<JSONObject> foodList = new ArrayList<>();
        SyRestaurantFoodQuery query = new SyRestaurantFoodQuery();
        query.setOffset(0);
        query.setRows(3);
        query.setStatus(TourConstants.STATUS.NORMAL);
        query.setRestaurantId(syRestaurant.getId());
        List<SyRestaurantFood> restaurantFoodList = syRestaurantFoodService.queryListByParam(query);
        //先查询菜馆关联的美食，如果没有就按照时间倒序
        if (CollectionUtils.isEmpty(restaurantFoodList)) {
            restaurantFoodList = getSyRestaurantFoods(query);
            if (CollectionUtils.isEmpty(restaurantFoodList)) {
                return vo;
            } else {
                buildRestaurantFood(foodList, restaurantFoodList);
            }
        } else {
            if (restaurantFoodList.size() == 3) {
                buildRestaurantFood(foodList, restaurantFoodList);
            } else if (restaurantFoodList.size() == 2) {
                query.setRows(1);
                restaurantFoodList = getSyRestaurantFoods(query);
                buildRestaurantFood(foodList, restaurantFoodList);
            } else if (restaurantFoodList.size() == 1) {
                query.setRows(2);
                restaurantFoodList = getSyRestaurantFoods(query);
                buildRestaurantFood(foodList, restaurantFoodList);
            }
        }
        vo.setFoodTitle("美食推荐");
        vo.setFoodList(foodList);
        return vo;
    }

    private List<SyRestaurantFood> getSyRestaurantFoods(SyRestaurantFoodQuery query) {
        List<SyRestaurantFood> restaurantFoodList;
        List<SortColumn> sortColumns = new ArrayList<>();
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        query.setSorts(sortColumns);
        query.setRestaurantId(null);
        restaurantFoodList = syRestaurantFoodService.queryListByParam(query);
        return restaurantFoodList;
    }

    private void buildRestaurantFood(List<JSONObject> foodList, List<SyRestaurantFood> restaurantFoodList) {
        JSONObject jo = null;
        for (SyRestaurantFood food : restaurantFoodList) {
            jo = new JSONObject();
            jo.put("id", food.getId());
            jo.put("title", food.getName());
            jo.put("picUrl", CommonUtil.analyzeOnePicUrl(food.getPicUrl()));
            foodList.add(jo);
        }
    }
}
