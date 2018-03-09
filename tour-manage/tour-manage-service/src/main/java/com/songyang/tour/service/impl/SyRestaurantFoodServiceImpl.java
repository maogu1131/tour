package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyRestaurantFoodDao;
import com.songyang.tour.pojo.SyRestaurantFood;
import com.songyang.tour.query.SyRestaurantFoodQuery;
import com.songyang.tour.service.SyRestaurantFoodService;
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
public class SyRestaurantFoodServiceImpl implements SyRestaurantFoodService {


	private static final String NAME_SPACE = SyRestaurantFood.class.getName();


	@Resource
	private SyRestaurantFoodDao syRestaurantFoodDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyRestaurantFood syRestaurantFood) {
        Assert.notNull(syRestaurantFood);
        syRestaurantFood.setCreateTime(new Date());
        syRestaurantFood.setStatus(TourConstants.STATUS.NORMAL);
        return syRestaurantFoodDao.insert(syRestaurantFood);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syRestaurantFoodDao.deleteById(id);
    }

    public int updateById(SyRestaurantFood syRestaurantFood) {
        Assert.notNull(syRestaurantFood);
        Assert.notNull(syRestaurantFood.getId());
        return syRestaurantFoodDao.updateById(syRestaurantFood);
    }

    public SyRestaurantFood selectById(Long id) {
        Assert.notNull(id);
        return syRestaurantFoodDao.selectById(id);
    }

    public SyRestaurantFood selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syRestaurantFoodDao.selectByIdForUpdate(id);
    }

    public List<SyRestaurantFood> queryListByParam(SyRestaurantFoodQuery syRestaurantFoodQuery) {
        Assert.notNull(syRestaurantFoodQuery);
        return syRestaurantFoodDao.queryListByParam(syRestaurantFoodQuery);
    }

    public Long queryCountByParam(SyRestaurantFoodQuery syRestaurantFoodQuery) {
        Assert.notNull(syRestaurantFoodQuery);
        return syRestaurantFoodDao.queryCountByParam(syRestaurantFoodQuery);
    }


}