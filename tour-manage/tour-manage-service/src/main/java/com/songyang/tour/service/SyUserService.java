package com.songyang.tour.service;

import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.query.SyUserQuery;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyUserService extends BaseService<SyUser, SyUserQuery, Long> {


    Boolean save(SyUser user);

    // loginId 查询用户
    SyUser selectByWxLoginId(String loginId);

    SyUser selectByUserId(String userId);

}