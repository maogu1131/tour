package com.songyang.tour.service;

import com.songyang.tour.pojo.SyFeedback;
import com.songyang.tour.query.SyFeedbackQuery;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyFeedbackService extends BaseService<SyFeedback, SyFeedbackQuery, Long> {


}