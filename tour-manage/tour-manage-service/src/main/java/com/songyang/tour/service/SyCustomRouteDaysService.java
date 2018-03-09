package com.songyang.tour.service;

import com.songyang.tour.pojo.SyCustomRouteDays;
import com.songyang.tour.query.SyCustomRouteDaysQuery;

import java.util.List;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyCustomRouteDaysService extends BaseService<SyCustomRouteDays, SyCustomRouteDaysQuery, Long> {

    List<SyCustomRouteDays> selectByCustomRouteId(Long customRouteId);

}