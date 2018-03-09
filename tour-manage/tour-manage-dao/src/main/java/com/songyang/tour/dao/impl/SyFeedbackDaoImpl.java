package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyFeedbackDao;
import com.songyang.tour.pojo.SyFeedback;
import com.songyang.tour.query.SyFeedbackQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/12/22.
 */
@Repository
public class SyFeedbackDaoImpl extends SqlSessionDaoSupport implements SyFeedbackDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyFeedback.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyFeedback syFeedback) {
        Assert.notNull(syFeedback);
        syFeedback.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syFeedback);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyFeedback syFeedback) {
        Assert.notNull(syFeedback);
        Assert.notNull(syFeedback.getId());
        syFeedback.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syFeedback);
    }

    public SyFeedback selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyFeedback selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyFeedback> queryListByParam(SyFeedbackQuery syFeedbackQuery) {
        Assert.notNull(syFeedbackQuery);
        syFeedbackQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syFeedbackQuery);
    }

    public Long queryCountByParam(SyFeedbackQuery syFeedbackQuery) {
        Assert.notNull(syFeedbackQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syFeedbackQuery);
    }


}