package com.songyang.tour.service;

import com.songyang.tour.pojo.SyConsumeFlow;
import com.songyang.tour.query.SyConsumeFlowQuery;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyConsumeFlowService extends BaseService<SyConsumeFlow, SyConsumeFlowQuery, Long> {


    SyConsumeFlow selectByReqId(String reqId);

}