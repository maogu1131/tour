package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyScenicSpotGuideDao;
import com.songyang.tour.pojo.SyScenicSpotGuide;
import com.songyang.tour.query.SyScenicSpotGuideQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyScenicSpotGuideDaoImpl extends SqlSessionDaoSupport implements SyScenicSpotGuideDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyScenicSpotGuide.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyScenicSpotGuide syScenicSpotGuide) {
        Assert.notNull(syScenicSpotGuide);
        syScenicSpotGuide.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syScenicSpotGuide);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyScenicSpotGuide syScenicSpotGuide) {
        Assert.notNull(syScenicSpotGuide);
        Assert.notNull(syScenicSpotGuide.getId());
        syScenicSpotGuide.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syScenicSpotGuide);
    }

    public SyScenicSpotGuide selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyScenicSpotGuide selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyScenicSpotGuide> queryListByParam(SyScenicSpotGuideQuery syScenicSpotGuideQuery) {
        Assert.notNull(syScenicSpotGuideQuery);
        syScenicSpotGuideQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syScenicSpotGuideQuery);
    }

    public Long queryCountByParam(SyScenicSpotGuideQuery syScenicSpotGuideQuery) {
        Assert.notNull(syScenicSpotGuideQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syScenicSpotGuideQuery);
    }


}