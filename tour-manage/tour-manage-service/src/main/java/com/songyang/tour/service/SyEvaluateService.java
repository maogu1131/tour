package com.songyang.tour.service;

import com.songyang.tour.pojo.SyEvaluate;
import com.songyang.tour.query.SyEvaluateQuery;

import java.util.List;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyEvaluateService extends BaseService<SyEvaluate, SyEvaluateQuery, Long> {

    /**
     * 根据 产品id 查询 评价列表
     * @param prodId
     * @return
     */
    SyEvaluate selectLastestByProdId(Long prodId);

}