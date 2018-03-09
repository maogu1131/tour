package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyAdminUserDao;
import com.songyang.tour.pojo.SyAdminUser;
import com.songyang.tour.query.SyAdminUserQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/12/09.
 */
@Repository
public class SyAdminUserDaoImpl extends SqlSessionDaoSupport implements SyAdminUserDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyAdminUser.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyAdminUser syAdminUser) {
        Assert.notNull(syAdminUser);
        syAdminUser.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syAdminUser);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyAdminUser syAdminUser) {
        Assert.notNull(syAdminUser);
        Assert.notNull(syAdminUser.getId());
        syAdminUser.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syAdminUser);
    }

    public SyAdminUser selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyAdminUser selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyAdminUser> queryListByParam(SyAdminUserQuery syAdminUserQuery) {
        Assert.notNull(syAdminUserQuery);
        syAdminUserQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syAdminUserQuery);
    }

    public Long queryCountByParam(SyAdminUserQuery syAdminUserQuery) {
        Assert.notNull(syAdminUserQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syAdminUserQuery);
    }


}