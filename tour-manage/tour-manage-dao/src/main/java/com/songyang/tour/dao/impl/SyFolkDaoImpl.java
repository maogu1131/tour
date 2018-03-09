package com.songyang.tour.dao.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyFolkDao;
import com.songyang.tour.pojo.SyFolk;
import com.songyang.tour.query.SyFolkQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyFolkDaoImpl extends SqlSessionDaoSupport implements SyFolkDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyFolk.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyFolk syFolk) {
        Assert.notNull(syFolk);
        syFolk.setCreateTime(new Date());
        syFolk.setStatus(TourConstants.STATUS.NORMAL);
        return getSqlSession().insert(generateStatement("insert"), syFolk);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyFolk syFolk) {
        Assert.notNull(syFolk);
        Assert.notNull(syFolk.getId());
        syFolk.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syFolk);
    }

    public SyFolk selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyFolk selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyFolk> queryListByParam(SyFolkQuery syFolkQuery) {
        Assert.notNull(syFolkQuery);
        syFolkQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syFolkQuery);
    }

    public Long queryCountByParam(SyFolkQuery syFolkQuery) {
        Assert.notNull(syFolkQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syFolkQuery);
    }


}