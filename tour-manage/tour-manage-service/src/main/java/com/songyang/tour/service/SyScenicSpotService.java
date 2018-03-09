package com.songyang.tour.service;

import com.songyang.tour.model.ShareTO;
import com.songyang.tour.pojo.SyScenicSpot;
import com.songyang.tour.query.SyScenicSpotQuery;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyScenicSpotService extends BaseService<SyScenicSpot, SyScenicSpotQuery, Long> {


    // 扣减份额
    Boolean deductByShareTO(ShareTO shareTO);

    // 增加份额
    Boolean plusByShareTO(ShareTO shareTO);
}