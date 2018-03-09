package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyOldVillageNearbyDao;
import com.songyang.tour.pojo.SyOldVillageNearby;
import com.songyang.tour.query.SyOldVillageNearbyQuery;
import com.songyang.tour.service.SyOldVillageNearbyService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyOldVillageNearbyServiceImpl implements SyOldVillageNearbyService {


	private static final String NAME_SPACE = SyOldVillageNearby.class.getName();


	@Resource
	private SyOldVillageNearbyDao syOldVillageNearbyDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyOldVillageNearby syOldVillageNearby) {
        Assert.notNull(syOldVillageNearby);
        return syOldVillageNearbyDao.insert(syOldVillageNearby);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syOldVillageNearbyDao.deleteById(id);
    }

    public int updateById(SyOldVillageNearby syOldVillageNearby) {
        Assert.notNull(syOldVillageNearby);
        Assert.notNull(syOldVillageNearby.getId());
        return syOldVillageNearbyDao.updateById(syOldVillageNearby);
    }

    public SyOldVillageNearby selectById(Long id) {
        Assert.notNull(id);
        return syOldVillageNearbyDao.selectById(id);
    }

    public SyOldVillageNearby selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syOldVillageNearbyDao.selectByIdForUpdate(id);
    }

    public List<SyOldVillageNearby> queryListByParam(SyOldVillageNearbyQuery syOldVillageNearbyQuery) {
        Assert.notNull(syOldVillageNearbyQuery);
        return syOldVillageNearbyDao.queryListByParam(syOldVillageNearbyQuery);
    }

    public Long queryCountByParam(SyOldVillageNearbyQuery syOldVillageNearbyQuery) {
        Assert.notNull(syOldVillageNearbyQuery);
        return syOldVillageNearbyDao.queryCountByParam(syOldVillageNearbyQuery);
    }


}