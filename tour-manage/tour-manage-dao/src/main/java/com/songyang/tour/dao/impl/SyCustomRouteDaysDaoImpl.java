package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyCustomRouteDaysDao;
import com.songyang.tour.pojo.SyCustomRouteDays;
import com.songyang.tour.query.SyCustomRouteDaysQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/09/24.
 */
@Repository
public class SyCustomRouteDaysDaoImpl extends SqlSessionDaoSupport implements SyCustomRouteDaysDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyCustomRouteDays.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyCustomRouteDays syCustomRouteDays) {
        Assert.notNull(syCustomRouteDays);
        syCustomRouteDays.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syCustomRouteDays);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyCustomRouteDays syCustomRouteDays) {
        Assert.notNull(syCustomRouteDays);
        Assert.notNull(syCustomRouteDays.getId());
        syCustomRouteDays.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syCustomRouteDays);
    }

    public SyCustomRouteDays selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyCustomRouteDays selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyCustomRouteDays> queryListByParam(SyCustomRouteDaysQuery syCustomRouteDaysQuery) {
        Assert.notNull(syCustomRouteDaysQuery);
        syCustomRouteDaysQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syCustomRouteDaysQuery);
    }

    public Long queryCountByParam(SyCustomRouteDaysQuery syCustomRouteDaysQuery) {
        Assert.notNull(syCustomRouteDaysQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syCustomRouteDaysQuery);
    }


    @Override
    public List<SyCustomRouteDays> selectByCustomRouteId(Long customRouteId) {
        Assert.notNull(customRouteId);
        return getSqlSession().selectList(generateStatement("selectByCustomRouteId"), customRouteId);
    }
}