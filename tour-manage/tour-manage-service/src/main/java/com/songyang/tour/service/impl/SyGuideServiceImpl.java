package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyGuideDao;
import com.songyang.tour.pojo.SyGuide;
import com.songyang.tour.query.SyGuideQuery;
import com.songyang.tour.service.SyGuideService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyGuideServiceImpl implements SyGuideService {


	private static final String NAME_SPACE = SyGuide.class.getName();


	@Resource
	private SyGuideDao syGuideDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyGuide syGuide) {
        Assert.notNull(syGuide);
        syGuide.setStatus(TourConstants.STATUS.NORMAL);
        return syGuideDao.insert(syGuide);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syGuideDao.deleteById(id);
    }

    public int updateById(SyGuide syGuide) {
        Assert.notNull(syGuide);
        Assert.notNull(syGuide.getId());
        return syGuideDao.updateById(syGuide);
    }

    public SyGuide selectById(Long id) {
        Assert.notNull(id);
        return syGuideDao.selectById(id);
    }

    public SyGuide selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syGuideDao.selectByIdForUpdate(id);
    }

    public List<SyGuide> queryListByParam(SyGuideQuery syGuideQuery) {
        Assert.notNull(syGuideQuery);
        return syGuideDao.queryListByParam(syGuideQuery);
    }

    public Long queryCountByParam(SyGuideQuery syGuideQuery) {
        Assert.notNull(syGuideQuery);
        return syGuideDao.queryCountByParam(syGuideQuery);
    }


}