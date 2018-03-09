package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyScenicSpotDao;
import com.songyang.tour.pojo.SyScenicSpot;
import com.songyang.tour.query.SyScenicSpotQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyScenicSpotDaoImpl extends SqlSessionDaoSupport implements SyScenicSpotDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyScenicSpot.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyScenicSpot syScenicSpot) {
        Assert.notNull(syScenicSpot);
        syScenicSpot.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syScenicSpot);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyScenicSpot syScenicSpot) {
        Assert.notNull(syScenicSpot);
        Assert.notNull(syScenicSpot.getId());
        syScenicSpot.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syScenicSpot);
    }

    public SyScenicSpot selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyScenicSpot selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyScenicSpot> queryListByParam(SyScenicSpotQuery syScenicSpotQuery) {
        Assert.notNull(syScenicSpotQuery);
        syScenicSpotQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syScenicSpotQuery);
    }

    public Long queryCountByParam(SyScenicSpotQuery syScenicSpotQuery) {
        Assert.notNull(syScenicSpotQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syScenicSpotQuery);
    }


    @Override
    public Integer deductByMap(Map<String, Object> map) {
        Assert.notEmpty(map);
        return getSqlSession().update(generateStatement("deductByMap"), map);
    }

    @Override
    public Integer plusByMap(Map<String, Object> map) {
        Assert.notEmpty(map);
        return getSqlSession().update(generateStatement("plusByMap"), map);
    }
}