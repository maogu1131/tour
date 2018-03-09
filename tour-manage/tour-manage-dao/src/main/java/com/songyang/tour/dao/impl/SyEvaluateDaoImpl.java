package com.songyang.tour.dao.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyEvaluateDao;
import com.songyang.tour.pojo.SyEvaluate;
import com.songyang.tour.query.SyEvaluateQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyEvaluateDaoImpl extends SqlSessionDaoSupport implements SyEvaluateDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyEvaluate.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyEvaluate syEvaluate) {
        Assert.notNull(syEvaluate);
        syEvaluate.setStatus(TourConstants.STATUS.NORMAL);
        syEvaluate.setEffectTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syEvaluate);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyEvaluate syEvaluate) {
        Assert.notNull(syEvaluate);
        Assert.notNull(syEvaluate.getId());
        return getSqlSession().update(generateStatement("updateById"), syEvaluate);
    }

    public SyEvaluate selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyEvaluate selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyEvaluate> queryListByParam(SyEvaluateQuery query) {
        Assert.notNull(query);
        query.checkBaseQuery(MAX_ROWS);
        query.setStatus(TourConstants.STATUS.NORMAL);
        return getSqlSession().selectList(generateStatement("queryListByParam"), query);
    }

    public Long queryCountByParam(SyEvaluateQuery query) {
        Assert.notNull(query);
        query.setStatus(TourConstants.STATUS.NORMAL);
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), query);
    }


    @Override
    public SyEvaluate selectLastestByProdId(SyEvaluateQuery query) {
        Assert.notNull(query);
        query.setStatus(TourConstants.STATUS.NORMAL);
        return getSqlSession().selectOne(generateStatement("selectLastestByProdId"), query);
    }
}