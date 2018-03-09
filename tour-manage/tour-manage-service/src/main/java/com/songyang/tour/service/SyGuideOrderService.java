package com.songyang.tour.service;

import com.songyang.tour.pojo.SyGuideOrder;
import com.songyang.tour.query.SyGuideOrderQuery;


/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyGuideOrderService extends BaseService<SyGuideOrder, SyGuideOrderQuery, Long> {


}