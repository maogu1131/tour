package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyTravelStrategyDao;
import com.songyang.tour.pojo.SyTravelStrategy;
import com.songyang.tour.query.SyTravelStrategyQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/09/24.
 */
@Repository
public class SyTravelStrategyDaoImpl extends SqlSessionDaoSupport implements SyTravelStrategyDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyTravelStrategy.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyTravelStrategy syTravelStrategy) {
        Assert.notNull(syTravelStrategy);
        syTravelStrategy.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syTravelStrategy);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyTravelStrategy syTravelStrategy) {
        Assert.notNull(syTravelStrategy);
        Assert.notNull(syTravelStrategy.getId());
        syTravelStrategy.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syTravelStrategy);
    }

    public SyTravelStrategy selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyTravelStrategy selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyTravelStrategy> queryListByParam(SyTravelStrategyQuery syTravelStrategyQuery) {
        Assert.notNull(syTravelStrategyQuery);
        syTravelStrategyQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syTravelStrategyQuery);
    }

    public Long queryCountByParam(SyTravelStrategyQuery syTravelStrategyQuery) {
        Assert.notNull(syTravelStrategyQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syTravelStrategyQuery);
    }


}