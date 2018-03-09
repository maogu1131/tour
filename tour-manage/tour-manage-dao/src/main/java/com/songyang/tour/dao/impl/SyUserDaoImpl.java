package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyUserDao;
import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.query.SyUserQuery;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/11/19.
 */
@Repository
public class SyUserDaoImpl extends SqlSessionDaoSupport implements SyUserDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyUser.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }


    public int insert(SyUser syUser) {
        Assert.notNull(syUser);
        syUser.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syUser);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyUser syUser) {
        Assert.notNull(syUser);
        Assert.notNull(syUser.getId());
        syUser.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syUser);
    }

    public SyUser selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyUser selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyUser> queryListByParam(SyUserQuery syUserQuery) {
        Assert.notNull(syUserQuery);
        syUserQuery.checkBaseQuery(MAX_ROWS);
        return getSqlSession().selectList(generateStatement("queryListByParam"), syUserQuery);
    }

    public Long queryCountByParam(SyUserQuery syUserQuery) {
        Assert.notNull(syUserQuery);
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syUserQuery);
    }


    @Override
    public SyUser selectByWxLoginId(String loginId) {
        Assert.notNull(loginId);
        return getSqlSession().selectOne(generateStatement("selectByWxLoginId"), loginId);
    }

    @Override
    public SyUser selectByqqLoginId(String loginId) {
        Assert.notNull(loginId);
        return getSqlSession().selectOne(generateStatement("selectByqqLoginId"), loginId);
    }

    @Override
    public Integer updateByUser(SyUser user) {
        Assert.notNull(user);
        user.setModifyTime(new Date());
        if(StringUtils.isNotBlank(user.getQqLoginId())){
            return getSqlSession().update(generateStatement("updateByqqLoginId"), user);
        }else{
            return getSqlSession().update(generateStatement("updateByLoginId"), user);
        }
    }

    @Override
    public SyUser selectByUserId(String userId) {
        Assert.notNull(userId);
        return getSqlSession().selectOne(generateStatement("selectByUserId"), userId);
    }
}