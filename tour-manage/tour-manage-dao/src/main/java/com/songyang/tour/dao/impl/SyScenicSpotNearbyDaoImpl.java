package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyScenicSpotNearbyDao;
import com.songyang.tour.pojo.SyScenicSpotNearby;
import com.songyang.tour.query.SyScenicSpotNearbyQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyScenicSpotNearbyDaoImpl extends SqlSessionDaoSupport implements SyScenicSpotNearbyDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyScenicSpotNearby.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyScenicSpotNearby syScenicSpotNearby) {
        Assert.notNull(syScenicSpotNearby);
        syScenicSpotNearby.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syScenicSpotNearby);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyScenicSpotNearby syScenicSpotNearby) {
        Assert.notNull(syScenicSpotNearby);
        Assert.notNull(syScenicSpotNearby.getId());
        syScenicSpotNearby.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syScenicSpotNearby);
    }

    public SyScenicSpotNearby selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyScenicSpotNearby selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyScenicSpotNearby> queryListByParam(SyScenicSpotNearbyQuery syScenicSpotNearbyQuery) {
        Assert.notNull(syScenicSpotNearbyQuery);
        syScenicSpotNearbyQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syScenicSpotNearbyQuery);
    }

    public Long queryCountByParam(SyScenicSpotNearbyQuery syScenicSpotNearbyQuery) {
        Assert.notNull(syScenicSpotNearbyQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syScenicSpotNearbyQuery);
    }


}