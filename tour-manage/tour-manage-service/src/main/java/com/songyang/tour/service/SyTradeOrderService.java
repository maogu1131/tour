package com.songyang.tour.service;

import com.songyang.tour.pojo.SyTradeOrder;
import com.songyang.tour.query.SyTradeOrderQuery;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyTradeOrderService extends BaseService<SyTradeOrder, SyTradeOrderQuery, Long> {

}