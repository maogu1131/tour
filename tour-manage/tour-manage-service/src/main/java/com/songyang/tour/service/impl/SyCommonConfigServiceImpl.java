package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyCommonConfigDao;
import com.songyang.tour.pojo.SyCommonConfig;
import com.songyang.tour.query.SyCommonConfigQuery;
import com.songyang.tour.service.SyCommonConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by  小工具  on 2017/09/24.
 */
@Service
public class SyCommonConfigServiceImpl implements SyCommonConfigService {


    private static final String NAME_SPACE = SyCommonConfig.class.getName();


    @Resource
    private SyCommonConfigDao syCommonConfigDao;


    /**
     * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
     *
     * @param id id主键
     * @return namespace.id
     */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyCommonConfig syCommonConfig) {
        Assert.notNull(syCommonConfig);
        return syCommonConfigDao.insert(syCommonConfig);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syCommonConfigDao.deleteById(id);
    }

    public int updateById(SyCommonConfig syCommonConfig) {
        Assert.notNull(syCommonConfig);
        Assert.notNull(syCommonConfig.getId());
        return syCommonConfigDao.updateById(syCommonConfig);
    }

    public SyCommonConfig selectById(Long id) {
        Assert.notNull(id);
        return syCommonConfigDao.selectById(id);
    }

    public SyCommonConfig selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syCommonConfigDao.selectByIdForUpdate(id);
    }

    public List<SyCommonConfig> queryListByParam(SyCommonConfigQuery syCommonConfigQuery) {
        Assert.notNull(syCommonConfigQuery);
        return syCommonConfigDao.queryListByParam(syCommonConfigQuery);
    }

    public Long queryCountByParam(SyCommonConfigQuery syCommonConfigQuery) {
        Assert.notNull(syCommonConfigQuery);
        return syCommonConfigDao.queryCountByParam(syCommonConfigQuery);
    }


    /**
     * 根据key搜索对象
     *
     * @param key
     * @return
     */
    @Override
    public SyCommonConfig selectByKey(String key) {
        SyCommonConfigQuery query = new SyCommonConfigQuery();
        query.setKey(key);
        query.setOffset(0);
        query.setRows(1);
        List<SyCommonConfig> commonConfigs = queryListByParam(query);
        if (CollectionUtils.isEmpty(commonConfigs)) {
            return null;
        }
        return commonConfigs.get(0);
    }
}