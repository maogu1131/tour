package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyModuleDao;
import com.songyang.tour.pojo.SyModule;
import com.songyang.tour.query.SyModuleQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyModuleDaoImpl extends SqlSessionDaoSupport implements SyModuleDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyModule.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyModule syModule) {
        Assert.notNull(syModule);
        syModule.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syModule);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyModule syModule) {
        Assert.notNull(syModule);
        Assert.notNull(syModule.getId());
        syModule.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syModule);
    }

    public SyModule selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyModule selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyModule> queryListByParam(SyModuleQuery syModuleQuery) {
        Assert.notNull(syModuleQuery);
        syModuleQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syModuleQuery);
    }

    public Long queryCountByParam(SyModuleQuery syModuleQuery) {
        Assert.notNull(syModuleQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syModuleQuery);
    }


}