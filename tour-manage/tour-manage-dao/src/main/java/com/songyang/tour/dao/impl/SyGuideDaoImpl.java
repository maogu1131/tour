package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyGuideDao;
import com.songyang.tour.pojo.SyGuide;
import com.songyang.tour.query.SyGuideQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyGuideDaoImpl extends SqlSessionDaoSupport implements SyGuideDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyGuide.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyGuide syGuide) {
        Assert.notNull(syGuide);
        syGuide.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syGuide);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyGuide syGuide) {
        Assert.notNull(syGuide);
        Assert.notNull(syGuide.getId());
        syGuide.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syGuide);
    }

    public SyGuide selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyGuide selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyGuide> queryListByParam(SyGuideQuery syGuideQuery) {
        Assert.notNull(syGuideQuery);
        syGuideQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syGuideQuery);
    }

    public Long queryCountByParam(SyGuideQuery syGuideQuery) {
        Assert.notNull(syGuideQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syGuideQuery);
    }


}