package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyBannerDao;
import com.songyang.tour.pojo.SyBanner;
import com.songyang.tour.query.SyBannerQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/10/01.
 */
@Repository
public class SyBannerDaoImpl extends SqlSessionDaoSupport implements SyBannerDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyBanner.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyBanner syBanner) {
        Assert.notNull(syBanner);
        syBanner.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syBanner);
    }


    public int deleteById(Integer id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyBanner syBanner) {
        Assert.notNull(syBanner);
        Assert.notNull(syBanner.getId());
        syBanner.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syBanner);
    }

    public SyBanner selectById(Integer id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyBanner selectByIdForUpdate(Integer id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyBanner> queryListByParam(SyBannerQuery syBannerQuery) {
        Assert.notNull(syBannerQuery);
        syBannerQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syBannerQuery);
    }

    public Long queryCountByParam(SyBannerQuery syBannerQuery) {
        Assert.notNull(syBannerQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syBannerQuery);
    }


}