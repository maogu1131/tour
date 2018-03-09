package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyScenicSpotModuleDao;
import com.songyang.tour.pojo.SyScenicSpotModule;
import com.songyang.tour.query.SyScenicSpotModuleQuery;
import com.songyang.tour.service.SyScenicSpotModuleService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * Created by  小工具  on 2017/09/24.
 *
 */
@Service
public class SyScenicSpotModuleServiceImpl implements SyScenicSpotModuleService {


	private static final String NAME_SPACE = SyScenicSpotModule.class.getName();


	@Resource
	private SyScenicSpotModuleDao syScenicSpotModuleDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyScenicSpotModule syScenicSpotModule) {
        Assert.notNull(syScenicSpotModule);
        syScenicSpotModule.setCreateTime(new Date());
        syScenicSpotModule.setStatus(TourConstants.STATUS.NORMAL);
        return syScenicSpotModuleDao.insert(syScenicSpotModule);
    }


    public int deleteById(Integer id) {
        Assert.notNull(id);
        return syScenicSpotModuleDao.deleteById(id);
    }

    public int updateById(SyScenicSpotModule syScenicSpotModule) {
        Assert.notNull(syScenicSpotModule);
        Assert.notNull(syScenicSpotModule.getId());
        return syScenicSpotModuleDao.updateById(syScenicSpotModule);
    }

    public SyScenicSpotModule selectById(Integer id) {
        Assert.notNull(id);
        return syScenicSpotModuleDao.selectById(id);
    }

    public SyScenicSpotModule selectByIdForUpdate(Integer id) {
        Assert.notNull(id);
        return syScenicSpotModuleDao.selectByIdForUpdate(id);
    }

    public List<SyScenicSpotModule> queryListByParam(SyScenicSpotModuleQuery syScenicSpotModuleQuery) {
        Assert.notNull(syScenicSpotModuleQuery);
        return syScenicSpotModuleDao.queryListByParam(syScenicSpotModuleQuery);
    }

    public Long queryCountByParam(SyScenicSpotModuleQuery syScenicSpotModuleQuery) {
        Assert.notNull(syScenicSpotModuleQuery);
        return syScenicSpotModuleDao.queryCountByParam(syScenicSpotModuleQuery);
    }


}