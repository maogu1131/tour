package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyProdDao;
import com.songyang.tour.model.ShareTO;
import com.songyang.tour.pojo.SyProd;
import com.songyang.tour.query.SyProdQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyProdDaoImpl extends SqlSessionDaoSupport implements SyProdDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyProd.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyProd syProd) {
        Assert.notNull(syProd);
        syProd.setCreateTime(new Date());
        return getSqlSession().insert(generateStatement("insert"), syProd);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyProd syProd) {
        Assert.notNull(syProd);
        Assert.notNull(syProd.getId());
        syProd.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syProd);
    }

    public SyProd selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyProd selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyProd> queryListByParam(SyProdQuery syProdQuery) {
        Assert.notNull(syProdQuery);
        syProdQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syProdQuery);
    }

    public Long queryCountByParam(SyProdQuery syProdQuery) {
        Assert.notNull(syProdQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syProdQuery);
    }


    @Override
    public Integer deductByMap(Map<String,Object> map) {
        Assert.notEmpty(map);
        return getSqlSession().update(generateStatement("deductByMap"), map);
    }

    @Override
    public Integer plusByMap(Map<String,Object> map) {
        Assert.notEmpty(map);
        return getSqlSession().update(generateStatement("plusByMap"), map);
    }
}