package com.songyang.tour.service;

import com.songyang.tour.pojo.SyRestaurantFood;
import com.songyang.tour.query.SyRestaurantFoodQuery;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyRestaurantFoodService extends BaseService<SyRestaurantFood, SyRestaurantFoodQuery, Long> {


}