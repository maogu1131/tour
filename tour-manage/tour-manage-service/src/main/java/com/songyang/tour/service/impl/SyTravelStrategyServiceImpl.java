package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyTravelStrategyDao;
import com.songyang.tour.pojo.SyTravelStrategy;
import com.songyang.tour.query.SyTravelStrategyQuery;
import com.songyang.tour.service.SyTravelStrategyService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/09/24.
 */
@Service
public class SyTravelStrategyServiceImpl implements SyTravelStrategyService {


    private static final String NAME_SPACE = SyTravelStrategy.class.getName();


    @Resource
    private SyTravelStrategyDao syTravelStrategyDao;


    /**
     * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
     *
     * @param id id主键
     * @return namespace.id
     */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyTravelStrategy syTravelStrategy) {
        Assert.notNull(syTravelStrategy);
        syTravelStrategy.setCreateTime(new Date());
        syTravelStrategy.setStatus(TourConstants.STATUS.NORMAL);
        return syTravelStrategyDao.insert(syTravelStrategy);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syTravelStrategyDao.deleteById(id);
    }

    public int updateById(SyTravelStrategy syTravelStrategy) {
        Assert.notNull(syTravelStrategy);
        Assert.notNull(syTravelStrategy.getId());
        return syTravelStrategyDao.updateById(syTravelStrategy);
    }

    public SyTravelStrategy selectById(Long id) {
        Assert.notNull(id);
        return syTravelStrategyDao.selectById(id);
    }

    public SyTravelStrategy selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syTravelStrategyDao.selectByIdForUpdate(id);
    }

    public List<SyTravelStrategy> queryListByParam(SyTravelStrategyQuery syTravelStrategyQuery) {
        Assert.notNull(syTravelStrategyQuery);
        return syTravelStrategyDao.queryListByParam(syTravelStrategyQuery);
    }

    public Long queryCountByParam(SyTravelStrategyQuery syTravelStrategyQuery) {
        Assert.notNull(syTravelStrategyQuery);
        return syTravelStrategyDao.queryCountByParam(syTravelStrategyQuery);
    }


}