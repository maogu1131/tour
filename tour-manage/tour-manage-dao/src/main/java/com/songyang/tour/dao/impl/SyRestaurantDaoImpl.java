package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyRestaurantDao;
import com.songyang.tour.pojo.SyRestaurant;
import com.songyang.tour.query.SyRestaurantQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyRestaurantDaoImpl extends SqlSessionDaoSupport implements SyRestaurantDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyRestaurant.class.getName();


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
        syRestaurant.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syRestaurant);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyRestaurant syRestaurant) {
        Assert.notNull(syRestaurant);
        Assert.notNull(syRestaurant.getId());
        syRestaurant.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syRestaurant);
    }

    public SyRestaurant selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyRestaurant selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyRestaurant> queryListByParam(SyRestaurantQuery syRestaurantQuery) {
        Assert.notNull(syRestaurantQuery);
        syRestaurantQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syRestaurantQuery);
    }

    public Long queryCountByParam(SyRestaurantQuery syRestaurantQuery) {
        Assert.notNull(syRestaurantQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syRestaurantQuery);
    }


}