package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyMerchantDao;
import com.songyang.tour.pojo.SyMerchant;
import com.songyang.tour.query.SyMerchantQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyMerchantDaoImpl extends SqlSessionDaoSupport implements SyMerchantDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyMerchant.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyMerchant syMerchant) {
        Assert.notNull(syMerchant);
        syMerchant.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syMerchant);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyMerchant syMerchant) {
        Assert.notNull(syMerchant);
        Assert.notNull(syMerchant.getId());
        syMerchant.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syMerchant);
    }

    public SyMerchant selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyMerchant selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyMerchant> queryListByParam(SyMerchantQuery syMerchantQuery) {
        Assert.notNull(syMerchantQuery);
        syMerchantQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syMerchantQuery);
    }

    public Long queryCountByParam(SyMerchantQuery syMerchantQuery) {
        Assert.notNull(syMerchantQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syMerchantQuery);
    }


}