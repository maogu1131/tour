package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyOldVillageNearbyDao;
import com.songyang.tour.pojo.SyOldVillageNearby;
import com.songyang.tour.query.SyOldVillageNearbyQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyOldVillageNearbyDaoImpl extends SqlSessionDaoSupport implements SyOldVillageNearbyDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyOldVillageNearby.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyOldVillageNearby syOldVillageNearby) {
        Assert.notNull(syOldVillageNearby);
        syOldVillageNearby.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syOldVillageNearby);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyOldVillageNearby syOldVillageNearby) {
        Assert.notNull(syOldVillageNearby);
        Assert.notNull(syOldVillageNearby.getId());
        syOldVillageNearby.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syOldVillageNearby);
    }

    public SyOldVillageNearby selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyOldVillageNearby selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyOldVillageNearby> queryListByParam(SyOldVillageNearbyQuery syOldVillageNearbyQuery) {
        Assert.notNull(syOldVillageNearbyQuery);
        syOldVillageNearbyQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syOldVillageNearbyQuery);
    }

    public Long queryCountByParam(SyOldVillageNearbyQuery syOldVillageNearbyQuery) {
        Assert.notNull(syOldVillageNearbyQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syOldVillageNearbyQuery);
    }


}