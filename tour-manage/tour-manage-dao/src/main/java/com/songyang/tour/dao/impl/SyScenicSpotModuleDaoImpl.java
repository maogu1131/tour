package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyScenicSpotModuleDao;
import com.songyang.tour.pojo.SyScenicSpotModule;
import com.songyang.tour.query.SyScenicSpotModuleQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/09/24.
 */
@Repository
public class SyScenicSpotModuleDaoImpl extends SqlSessionDaoSupport implements SyScenicSpotModuleDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyScenicSpotModule.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyScenicSpotModule syScenicSpotModule) {
        Assert.notNull(syScenicSpotModule);
        syScenicSpotModule.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syScenicSpotModule);
    }


    public int deleteById(Integer id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyScenicSpotModule syScenicSpotModule) {
        Assert.notNull(syScenicSpotModule);
        Assert.notNull(syScenicSpotModule.getId());
        syScenicSpotModule.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syScenicSpotModule);
    }

    public SyScenicSpotModule selectById(Integer id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyScenicSpotModule selectByIdForUpdate(Integer id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyScenicSpotModule> queryListByParam(SyScenicSpotModuleQuery syScenicSpotModuleQuery) {
        Assert.notNull(syScenicSpotModuleQuery);
        syScenicSpotModuleQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syScenicSpotModuleQuery);
    }

    public Long queryCountByParam(SyScenicSpotModuleQuery syScenicSpotModuleQuery) {
        Assert.notNull(syScenicSpotModuleQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syScenicSpotModuleQuery);
    }


}