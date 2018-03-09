package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyPublicPlaceDao;
import com.songyang.tour.pojo.SyPublicPlace;
import com.songyang.tour.query.SyPublicPlaceQuery;
import com.songyang.tour.service.SyPublicPlaceService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyPublicPlaceServiceImpl implements SyPublicPlaceService {


	private static final String NAME_SPACE = SyPublicPlace.class.getName();


	@Resource
	private SyPublicPlaceDao syPublicPlaceDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyPublicPlace syPublicPlace) {
        Assert.notNull(syPublicPlace);
        syPublicPlace.setCreateTime(new Date());
        syPublicPlace.setStatus(TourConstants.STATUS.NORMAL);
        return syPublicPlaceDao.insert(syPublicPlace);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syPublicPlaceDao.deleteById(id);
    }

    public int updateById(SyPublicPlace syPublicPlace) {
        Assert.notNull(syPublicPlace);
        Assert.notNull(syPublicPlace.getId());
        return syPublicPlaceDao.updateById(syPublicPlace);
    }

    public SyPublicPlace selectById(Long id) {
        Assert.notNull(id);
        return syPublicPlaceDao.selectById(id);
    }

    public SyPublicPlace selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syPublicPlaceDao.selectByIdForUpdate(id);
    }

    public List<SyPublicPlace> queryListByParam(SyPublicPlaceQuery syPublicPlaceQuery) {
        Assert.notNull(syPublicPlaceQuery);
        return syPublicPlaceDao.queryListByParam(syPublicPlaceQuery);
    }

    public Long queryCountByParam(SyPublicPlaceQuery syPublicPlaceQuery) {
        Assert.notNull(syPublicPlaceQuery);
        return syPublicPlaceDao.queryCountByParam(syPublicPlaceQuery);
    }


}