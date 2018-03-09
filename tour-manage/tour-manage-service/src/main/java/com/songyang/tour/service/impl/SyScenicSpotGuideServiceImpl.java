package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyScenicSpotGuideDao;
import com.songyang.tour.pojo.SyScenicSpotGuide;
import com.songyang.tour.query.SyScenicSpotGuideQuery;
import com.songyang.tour.service.SyScenicSpotGuideService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyScenicSpotGuideServiceImpl implements SyScenicSpotGuideService {


	private static final String NAME_SPACE = SyScenicSpotGuide.class.getName();


	@Resource
	private SyScenicSpotGuideDao syScenicSpotGuideDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyScenicSpotGuide syScenicSpotGuide) {
        Assert.notNull(syScenicSpotGuide);
        return syScenicSpotGuideDao.insert(syScenicSpotGuide);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syScenicSpotGuideDao.deleteById(id);
    }

    public int updateById(SyScenicSpotGuide syScenicSpotGuide) {
        Assert.notNull(syScenicSpotGuide);
        Assert.notNull(syScenicSpotGuide.getId());
        return syScenicSpotGuideDao.updateById(syScenicSpotGuide);
    }

    public SyScenicSpotGuide selectById(Long id) {
        Assert.notNull(id);
        return syScenicSpotGuideDao.selectById(id);
    }

    public SyScenicSpotGuide selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syScenicSpotGuideDao.selectByIdForUpdate(id);
    }

    public List<SyScenicSpotGuide> queryListByParam(SyScenicSpotGuideQuery syScenicSpotGuideQuery) {
        Assert.notNull(syScenicSpotGuideQuery);
        return syScenicSpotGuideDao.queryListByParam(syScenicSpotGuideQuery);
    }

    public Long queryCountByParam(SyScenicSpotGuideQuery syScenicSpotGuideQuery) {
        Assert.notNull(syScenicSpotGuideQuery);
        return syScenicSpotGuideDao.queryCountByParam(syScenicSpotGuideQuery);
    }


}