package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyRestaurantDao;
import com.songyang.tour.pojo.SyRestaurant;
import com.songyang.tour.query.SyRestaurantQuery;
import com.songyang.tour.service.SyRestaurantService;
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
public class SyRestaurantServiceImpl implements SyRestaurantService {


	private static final String NAME_SPACE = SyRestaurant.class.getName();


	@Resource
	private SyRestaurantDao syRestaurantDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyRestaurant syRestaurant) {
        Assert.notNull(syRestaurant);
        syRestaurant.setStatus(TourConstants.STATUS.NORMAL);
        syRestaurant.setCreateTime(new Date());
        return syRestaurantDao.insert(syRestaurant);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syRestaurantDao.deleteById(id);
    }

    public int updateById(SyRestaurant syRestaurant) {
        Assert.notNull(syRestaurant);
        Assert.notNull(syRestaurant.getId());
        return syRestaurantDao.updateById(syRestaurant);
    }

    public SyRestaurant selectById(Long id) {
        Assert.notNull(id);
        return syRestaurantDao.selectById(id);
    }

    public SyRestaurant selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syRestaurantDao.selectByIdForUpdate(id);
    }

    public List<SyRestaurant> queryListByParam(SyRestaurantQuery syRestaurantQuery) {
        Assert.notNull(syRestaurantQuery);
        return syRestaurantDao.queryListByParam(syRestaurantQuery);
    }

    public Long queryCountByParam(SyRestaurantQuery syRestaurantQuery) {
        Assert.notNull(syRestaurantQuery);
        return syRestaurantDao.queryCountByParam(syRestaurantQuery);
    }


}