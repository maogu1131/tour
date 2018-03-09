package com.songyang.tour.service;

import com.songyang.tour.pojo.SyModule;
import com.songyang.tour.query.SyModuleQuery;


/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyModuleService extends BaseService<SyModule, SyModuleQuery, Long> {


}