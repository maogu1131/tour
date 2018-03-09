package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyFolkReserveOrderDao;
import com.songyang.tour.pojo.SyFolkReserveOrder;
import com.songyang.tour.query.SyFolkReserveOrderQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/12/22.
 */
@Repository
public class SyFolkReserveOrderDaoImpl extends SqlSessionDaoSupport implements SyFolkReserveOrderDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyFolkReserveOrder.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyFolkReserveOrder syFolkReserveOrder) {
        Assert.notNull(syFolkReserveOrder);
        syFolkReserveOrder.setCreateTime(new Date());
        
        return getSqlSession().insert(generateStatement("insert"), syFolkReserveOrder);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyFolkReserveOrder syFolkReserveOrder) {
        Assert.notNull(syFolkReserveOrder);
        Assert.notNull(syFolkReserveOrder.getId());
        syFolkReserveOrder.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syFolkReserveOrder);
    }

    public SyFolkReserveOrder selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyFolkReserveOrder selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyFolkReserveOrder> queryListByParam(SyFolkReserveOrderQuery syFolkReserveOrderQuery) {
        Assert.notNull(syFolkReserveOrderQuery);
        syFolkReserveOrderQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syFolkReserveOrderQuery);
    }

    public Long queryCountByParam(SyFolkReserveOrderQuery syFolkReserveOrderQuery) {
        Assert.notNull(syFolkReserveOrderQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syFolkReserveOrderQuery);
    }


}