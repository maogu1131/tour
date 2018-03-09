package com.songyang.tour.service;

import com.songyang.tour.pojo.SyMerchant;
import com.songyang.tour.query.SyMerchantQuery;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyMerchantService extends BaseService<SyMerchant, SyMerchantQuery, Long> {


}