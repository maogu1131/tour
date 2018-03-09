package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyGuideOrderDao;
import com.songyang.tour.pojo.SyGuideOrder;
import com.songyang.tour.query.SyGuideOrderQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/11/23.
 */
@Repository
public class SyGuideOrderDaoImpl extends SqlSessionDaoSupport implements SyGuideOrderDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyGuideOrder.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyGuideOrder syGuideOrder) {
        Assert.notNull(syGuideOrder);
        syGuideOrder.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syGuideOrder);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyGuideOrder syGuideOrder) {
        Assert.notNull(syGuideOrder);
        Assert.notNull(syGuideOrder.getId());
        syGuideOrder.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syGuideOrder);
    }

    public SyGuideOrder selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyGuideOrder selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyGuideOrder> queryListByParam(SyGuideOrderQuery syGuideOrderQuery) {
        Assert.notNull(syGuideOrderQuery);
        syGuideOrderQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syGuideOrderQuery);
    }

    public Long queryCountByParam(SyGuideOrderQuery syGuideOrderQuery) {
        Assert.notNull(syGuideOrderQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syGuideOrderQuery);
    }


}