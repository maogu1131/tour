package com.songyang.tour.service;

import com.songyang.tour.pojo.SyAdminUser;
import com.songyang.tour.query.SyAdminUserQuery;


/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyAdminUserService extends BaseService<SyAdminUser, SyAdminUserQuery, Long> {


}