package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyTrafficWarnDao;
import com.songyang.tour.pojo.SyTrafficWarn;
import com.songyang.tour.query.SyTrafficWarnQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/09/24.
 */
@Repository
public class SyTrafficWarnDaoImpl extends SqlSessionDaoSupport implements SyTrafficWarnDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyTrafficWarn.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyTrafficWarn syTrafficWarn) {
        Assert.notNull(syTrafficWarn);
        syTrafficWarn.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syTrafficWarn);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyTrafficWarn syTrafficWarn) {
        Assert.notNull(syTrafficWarn);
        Assert.notNull(syTrafficWarn.getId());
        syTrafficWarn.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syTrafficWarn);
    }

    public SyTrafficWarn selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyTrafficWarn selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyTrafficWarn> queryListByParam(SyTrafficWarnQuery syTrafficWarnQuery) {
        Assert.notNull(syTrafficWarnQuery);
        syTrafficWarnQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syTrafficWarnQuery);
    }

    public Long queryCountByParam(SyTrafficWarnQuery syTrafficWarnQuery) {
        Assert.notNull(syTrafficWarnQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syTrafficWarnQuery);
    }


}