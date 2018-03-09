package com.songyang.tour.service;

import com.songyang.tour.pojo.SyCommonConfig;
import com.songyang.tour.query.SyCommonConfigQuery;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface SyCommonConfigService extends BaseService<SyCommonConfig, SyCommonConfigQuery, Long> {

    /**
     * 根据key搜索对象
     * @param key
     * @return
     */
    SyCommonConfig selectByKey(String key);

}