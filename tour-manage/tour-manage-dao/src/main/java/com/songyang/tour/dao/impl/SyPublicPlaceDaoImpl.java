package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyPublicPlaceDao;
import com.songyang.tour.pojo.SyPublicPlace;
import com.songyang.tour.query.SyPublicPlaceQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyPublicPlaceDaoImpl extends SqlSessionDaoSupport implements SyPublicPlaceDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyPublicPlace.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyPublicPlace syPublicPlace) {
        Assert.notNull(syPublicPlace);
        syPublicPlace.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syPublicPlace);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyPublicPlace syPublicPlace) {
        Assert.notNull(syPublicPlace);
        Assert.notNull(syPublicPlace.getId());
        syPublicPlace.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syPublicPlace);
    }

    public SyPublicPlace selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyPublicPlace selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyPublicPlace> queryListByParam(SyPublicPlaceQuery syPublicPlaceQuery) {
        Assert.notNull(syPublicPlaceQuery);
        syPublicPlaceQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syPublicPlaceQuery);
    }

    public Long queryCountByParam(SyPublicPlaceQuery syPublicPlaceQuery) {
        Assert.notNull(syPublicPlaceQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syPublicPlaceQuery);
    }


}