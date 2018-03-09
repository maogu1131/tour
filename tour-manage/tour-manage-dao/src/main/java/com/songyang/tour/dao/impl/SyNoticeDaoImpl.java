package com.songyang.tour.dao.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyNoticeDao;
import com.songyang.tour.pojo.SyNotice;
import com.songyang.tour.query.SyNoticeQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyNoticeDaoImpl extends SqlSessionDaoSupport implements SyNoticeDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyNotice.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyNotice syNotice) {
        Assert.notNull(syNotice);
        syNotice.setCreateTime(new Date());
        syNotice.setStatus(TourConstants.STATUS.NORMAL);
        return getSqlSession().insert(generateStatement("insert"), syNotice);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyNotice syNotice) {
        Assert.notNull(syNotice);
        Assert.notNull(syNotice.getId());
        syNotice.setModifyTime(new Date());
        return getSqlSession().update(generateStatement("updateById"), syNotice);
    }

    public SyNotice selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyNotice selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyNotice> queryListByParam(SyNoticeQuery syNoticeQuery) {
        Assert.notNull(syNoticeQuery);
        syNoticeQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syNoticeQuery);
    }

    public Long queryCountByParam(SyNoticeQuery syNoticeQuery) {
        Assert.notNull(syNoticeQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syNoticeQuery);
    }


}