package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyRestaurantFoodDao;
import com.songyang.tour.pojo.SyRestaurantFood;
import com.songyang.tour.query.SyRestaurantFoodQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyRestaurantFoodDaoImpl extends SqlSessionDaoSupport implements SyRestaurantFoodDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyRestaurantFood.class.getName();


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
        
        return getSqlSession().insert(generateStatement("insert"), syRestaurantFood);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyRestaurantFood syRestaurantFood) {
        Assert.notNull(syRestaurantFood);
        Assert.notNull(syRestaurantFood.getId());
        syRestaurantFood.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syRestaurantFood);
    }

    public SyRestaurantFood selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyRestaurantFood selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyRestaurantFood> queryListByParam(SyRestaurantFoodQuery syRestaurantFoodQuery) {
        Assert.notNull(syRestaurantFoodQuery);
        syRestaurantFoodQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syRestaurantFoodQuery);
    }

    public Long queryCountByParam(SyRestaurantFoodQuery syRestaurantFoodQuery) {
        Assert.notNull(syRestaurantFoodQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syRestaurantFoodQuery);
    }


}