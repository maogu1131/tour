package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyConsumeFlowDao;
import com.songyang.tour.pojo.SyConsumeFlow;
import com.songyang.tour.query.SyConsumeFlowQuery;
import com.songyang.tour.utils.DateUtil;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/12/02.
 */
@Repository
public class SyConsumeFlowDaoImpl extends SqlSessionDaoSupport implements SyConsumeFlowDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyConsumeFlow.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyConsumeFlow syConsumeFlow) {
        Assert.notNull(syConsumeFlow);
        syConsumeFlow.setOccurDate(Integer.valueOf(DateUtil.format(new Date(),DateUtil.yyyyMMdd)));
        syConsumeFlow.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syConsumeFlow);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyConsumeFlow syConsumeFlow) {
        Assert.notNull(syConsumeFlow);
        Assert.notNull(syConsumeFlow.getId());
        syConsumeFlow.setModifyTime(new Date());

        // ceshi
        return getSqlSession().update(generateStatement("updateById"), syConsumeFlow);
    }



    public SyConsumeFlow selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyConsumeFlow selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyConsumeFlow> queryListByParam(SyConsumeFlowQuery syConsumeFlowQuery) {
        Assert.notNull(syConsumeFlowQuery);
//        syConsumeFlowQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syConsumeFlowQuery);
    }

    public Long queryCountByParam(SyConsumeFlowQuery syConsumeFlowQuery) {
        Assert.notNull(syConsumeFlowQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syConsumeFlowQuery);
    }


    @Override
    public SyConsumeFlow selectByReqId(String reqId) {
        Assert.notNull(reqId);
        return getSqlSession().selectOne(generateStatement("selectByReqId"), reqId);
    }
}