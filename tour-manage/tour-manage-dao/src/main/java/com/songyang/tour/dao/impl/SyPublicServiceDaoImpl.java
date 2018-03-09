package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyPublicServiceDao;
import com.songyang.tour.pojo.SyPublicService;
import com.songyang.tour.query.SyPublicServiceQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyPublicServiceDaoImpl extends SqlSessionDaoSupport implements SyPublicServiceDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyPublicService.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyPublicService syPublicService) {
        Assert.notNull(syPublicService);
        syPublicService.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syPublicService);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyPublicService syPublicService) {
        Assert.notNull(syPublicService);
        Assert.notNull(syPublicService.getId());
        syPublicService.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syPublicService);
    }

    public SyPublicService selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyPublicService selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyPublicService> queryListByParam(SyPublicServiceQuery syPublicServiceQuery) {
        Assert.notNull(syPublicServiceQuery);
        syPublicServiceQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syPublicServiceQuery);
    }

    public Long queryCountByParam(SyPublicServiceQuery syPublicServiceQuery) {
        Assert.notNull(syPublicServiceQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syPublicServiceQuery);
    }


}