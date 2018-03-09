package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyScenicSpotNearbyDao;
import com.songyang.tour.pojo.SyScenicSpotNearby;
import com.songyang.tour.query.SyScenicSpotNearbyQuery;
import com.songyang.tour.service.SyScenicSpotNearbyService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyScenicSpotNearbyServiceImpl implements SyScenicSpotNearbyService {


	private static final String NAME_SPACE = SyScenicSpotNearby.class.getName();


	@Resource
	private SyScenicSpotNearbyDao syScenicSpotNearbyDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyScenicSpotNearby syScenicSpotNearby) {
        Assert.notNull(syScenicSpotNearby);
        return syScenicSpotNearbyDao.insert(syScenicSpotNearby);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syScenicSpotNearbyDao.deleteById(id);
    }

    public int updateById(SyScenicSpotNearby syScenicSpotNearby) {
        Assert.notNull(syScenicSpotNearby);
        Assert.notNull(syScenicSpotNearby.getId());
        return syScenicSpotNearbyDao.updateById(syScenicSpotNearby);
    }

    public SyScenicSpotNearby selectById(Long id) {
        Assert.notNull(id);
        return syScenicSpotNearbyDao.selectById(id);
    }

    public SyScenicSpotNearby selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syScenicSpotNearbyDao.selectByIdForUpdate(id);
    }

    public List<SyScenicSpotNearby> queryListByParam(SyScenicSpotNearbyQuery syScenicSpotNearbyQuery) {
        Assert.notNull(syScenicSpotNearbyQuery);
        return syScenicSpotNearbyDao.queryListByParam(syScenicSpotNearbyQuery);
    }

    public Long queryCountByParam(SyScenicSpotNearbyQuery syScenicSpotNearbyQuery) {
        Assert.notNull(syScenicSpotNearbyQuery);
        return syScenicSpotNearbyDao.queryCountByParam(syScenicSpotNearbyQuery);
    }


}