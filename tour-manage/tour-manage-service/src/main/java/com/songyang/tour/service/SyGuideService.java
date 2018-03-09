package com.songyang.tour.service;

import com.songyang.tour.pojo.SyGuide;
import com.songyang.tour.query.SyGuideQuery;


/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyGuideService extends BaseService<SyGuide, SyGuideQuery, Long> {


}