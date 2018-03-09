package com.songyang.tour.dao;

import com.songyang.tour.pojo.SyCustomRouteDays;
import com.songyang.tour.query.SyCustomRouteDaysQuery;

import java.util.List;


/**
 * dao层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 * Query后缀类是 查询对象，如果没有范围等查询条件，不需Query类型，要可以修改自动生成代码，直接用po对象当参数。
 */
public interface SyCustomRouteDaysDao extends BaseDao<SyCustomRouteDays, SyCustomRouteDaysQuery, Long> {

    List<SyCustomRouteDays> selectByCustomRouteId(Long customRouteId);

}