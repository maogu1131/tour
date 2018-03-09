package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyOldVillageDao;
import com.songyang.tour.pojo.SyOldVillage;
import com.songyang.tour.query.SyOldVillageQuery;
import com.songyang.tour.service.SyOldVillageService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyOldVillageServiceImpl implements SyOldVillageService {


	private static final String NAME_SPACE = SyOldVillage.class.getName();


	@Resource
	private SyOldVillageDao syOldVillageDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyOldVillage syOldVillage) {
        Assert.notNull(syOldVillage);
        syOldVillage.setStatus(TourConstants.STATUS.NORMAL);
        return syOldVillageDao.insert(syOldVillage);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syOldVillageDao.deleteById(id);
    }

    public int updateById(SyOldVillage syOldVillage) {
        Assert.notNull(syOldVillage);
        Assert.notNull(syOldVillage.getId());
        return syOldVillageDao.updateById(syOldVillage);
    }

    public SyOldVillage selectById(Long id) {
        Assert.notNull(id);
        return syOldVillageDao.selectById(id);
    }

    public SyOldVillage selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syOldVillageDao.selectByIdForUpdate(id);
    }

    public List<SyOldVillage> queryListByParam(SyOldVillageQuery syOldVillageQuery) {
        Assert.notNull(syOldVillageQuery);
        return syOldVillageDao.queryListByParam(syOldVillageQuery);
    }

    public Long queryCountByParam(SyOldVillageQuery syOldVillageQuery) {
        Assert.notNull(syOldVillageQuery);
        return syOldVillageDao.queryCountByParam(syOldVillageQuery);
    }


}