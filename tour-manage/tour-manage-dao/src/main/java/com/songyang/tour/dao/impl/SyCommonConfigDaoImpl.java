package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyCommonConfigDao;
import com.songyang.tour.pojo.SyCommonConfig;
import com.songyang.tour.query.SyCommonConfigQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/09/24.
 */
@Repository
public class SyCommonConfigDaoImpl extends SqlSessionDaoSupport implements SyCommonConfigDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyCommonConfig.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyCommonConfig syCommonConfig) {
        Assert.notNull(syCommonConfig);
        syCommonConfig.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syCommonConfig);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyCommonConfig syCommonConfig) {
        Assert.notNull(syCommonConfig);
        Assert.notNull(syCommonConfig.getId());
        syCommonConfig.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syCommonConfig);
    }

    public SyCommonConfig selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyCommonConfig selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyCommonConfig> queryListByParam(SyCommonConfigQuery syCommonConfigQuery) {
        Assert.notNull(syCommonConfigQuery);
        syCommonConfigQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syCommonConfigQuery);
    }

    public Long queryCountByParam(SyCommonConfigQuery syCommonConfigQuery) {
        Assert.notNull(syCommonConfigQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syCommonConfigQuery);
    }


}