package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyCustomRouteDao;
import com.songyang.tour.pojo.SyCustomRoute;
import com.songyang.tour.query.SyCustomRouteQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyCustomRouteDaoImpl extends SqlSessionDaoSupport implements SyCustomRouteDao{


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyCustomRoute.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyCustomRoute syCustomRoute) {
        Assert.notNull(syCustomRoute);
        syCustomRoute.setCreateTime(new Date());

        return getSqlSession().insert(generateStatement("insert"), syCustomRoute);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyCustomRoute syCustomRoute) {
        Assert.notNull(syCustomRoute);
        Assert.notNull(syCustomRoute.getId());
        syCustomRoute.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syCustomRoute);
    }

    public SyCustomRoute selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyCustomRoute selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyCustomRoute> queryListByParam(SyCustomRouteQuery syCustomRouteQuery) {
        Assert.notNull(syCustomRouteQuery);
        syCustomRouteQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syCustomRouteQuery);
    }

    public Long queryCountByParam(SyCustomRouteQuery syCustomRouteQuery) {
        Assert.notNull(syCustomRouteQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syCustomRouteQuery);
    }


}