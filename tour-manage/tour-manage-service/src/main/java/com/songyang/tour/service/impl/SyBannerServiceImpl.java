package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyBannerDao;
import com.songyang.tour.pojo.SyBanner;
import com.songyang.tour.query.SyBannerQuery;
import com.songyang.tour.service.SyBannerService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/10/01.
 */
@Service
public class SyBannerServiceImpl implements SyBannerService {


    private static final String NAME_SPACE = SyBanner.class.getName();


    @Resource
    private SyBannerDao syBannerDao;


    /**
     * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
     *
     * @param id id主键
     * @return namespace.id
     */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyBanner syBanner) {
        Assert.notNull(syBanner);
        syBanner.setCreateTime(new Date());
        syBanner.setStatus(TourConstants.STATUS.NORMAL);
        return syBannerDao.insert(syBanner);
    }


    public int deleteById(Integer id) {
        Assert.notNull(id);
        return syBannerDao.deleteById(id);
    }

    public int updateById(SyBanner syBanner) {
        Assert.notNull(syBanner);
        Assert.notNull(syBanner.getId());
        return syBannerDao.updateById(syBanner);
    }

    public SyBanner selectById(Integer id) {
        Assert.notNull(id);
        return syBannerDao.selectById(id);
    }

    public SyBanner selectByIdForUpdate(Integer id) {
        Assert.notNull(id);
        return syBannerDao.selectByIdForUpdate(id);
    }

    public List<SyBanner> queryListByParam(SyBannerQuery syBannerQuery) {
        Assert.notNull(syBannerQuery);
        return syBannerDao.queryListByParam(syBannerQuery);

    }

    public Long queryCountByParam(SyBannerQuery syBannerQuery) {
        Assert.notNull(syBannerQuery);
        return syBannerDao.queryCountByParam(syBannerQuery);
    }


}