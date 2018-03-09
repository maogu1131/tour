package com.songyang.tour.dao;

import com.songyang.tour.model.ShareTO;
import com.songyang.tour.pojo.SyProd;
import com.songyang.tour.query.SyProdQuery;

import java.util.Map;

/**
 * dao层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 * Query后缀类是 查询对象，如果没有范围等查询条件，不需Query类型，要可以修改自动生成代码，直接用po对象当参数。
 */
public interface SyProdDao extends BaseDao<SyProd, SyProdQuery, Long> {

    // 扣减份额
    Integer deductByMap(Map<String,Object> map);

    // 增加份额
    Integer plusByMap(Map<String,Object> map);

}