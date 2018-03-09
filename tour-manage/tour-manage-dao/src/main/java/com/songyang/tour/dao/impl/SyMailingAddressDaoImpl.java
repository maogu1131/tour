package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyMailingAddressDao;
import com.songyang.tour.pojo.SyMailingAddress;
import com.songyang.tour.query.SyMailingAddressQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/11/04.
 */
@Repository
public class SyMailingAddressDaoImpl extends SqlSessionDaoSupport implements SyMailingAddressDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyMailingAddress.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyMailingAddress syMailingAddress) {
        Assert.notNull(syMailingAddress);
        syMailingAddress.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syMailingAddress);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyMailingAddress syMailingAddress) {
        Assert.notNull(syMailingAddress);
        Assert.notNull(syMailingAddress.getId());
        syMailingAddress.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syMailingAddress);
    }

    public SyMailingAddress selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyMailingAddress selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyMailingAddress> queryListByParam(SyMailingAddressQuery syMailingAddressQuery) {
        Assert.notNull(syMailingAddressQuery);
        syMailingAddressQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syMailingAddressQuery);
    }

    public Long queryCountByParam(SyMailingAddressQuery syMailingAddressQuery) {
        Assert.notNull(syMailingAddressQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syMailingAddressQuery);
    }


}