package com.songyang.tour.service;

import com.songyang.tour.model.ShareTO;
import com.songyang.tour.pojo.SyProd;
import com.songyang.tour.query.SyProdQuery;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyProdService extends BaseService<SyProd, SyProdQuery, Long> {

    // 扣减份额
    Boolean deductByShareTO(ShareTO shareTO);

    // 增加份额
    Boolean plusByShareTO(ShareTO shareTO);
}