package com.songyang.tour.service;

import com.songyang.tour.pojo.SyNotice;
import com.songyang.tour.query.SyNoticeQuery;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyNoticeService extends BaseService<SyNotice, SyNoticeQuery, Long> {


}