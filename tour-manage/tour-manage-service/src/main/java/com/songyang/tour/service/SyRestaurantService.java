package com.songyang.tour.service;

import com.songyang.tour.pojo.SyRestaurant;
import com.songyang.tour.query.SyRestaurantQuery;


/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyRestaurantService extends BaseService<SyRestaurant, SyRestaurantQuery, Long> {


}