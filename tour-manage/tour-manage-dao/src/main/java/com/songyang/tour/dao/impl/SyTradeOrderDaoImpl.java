package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyTradeOrderDao;
import com.songyang.tour.pojo.SyTradeOrder;
import com.songyang.tour.query.SyTradeOrderQuery;
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
public class SyTradeOrderDaoImpl extends SqlSessionDaoSupport implements SyTradeOrderDao {


	/**
	 * mybatis mapper的命名空间，包路径加类名
	 */
	private static final String NAME_SPACE = SyTradeOrder.class.getName();


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyTradeOrder syTradeOrder) {
        Assert.notNull(syTradeOrder);
        syTradeOrder.setOccurDate(Integer.valueOf(DateUtil.format(new Date(),DateUtil.yyyyMMdd)));
        syTradeOrder.setCreateTime(new Date());
        return getSqlSession().insert(generateStatement("insert"), syTradeOrder);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyTradeOrder syTradeOrder) {
        Assert.notNull(syTradeOrder);
        Assert.notNull(syTradeOrder.getId());
        syTradeOrder.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syTradeOrder);
    }

    public SyTradeOrder selectById(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyTradeOrder selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        
        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyTradeOrder> queryListByParam(SyTradeOrderQuery syTradeOrderQuery) {
        Assert.notNull(syTradeOrderQuery);
//        syTradeOrderQuery.checkBaseQuery(MAX_ROWS);
        
        return getSqlSession().selectList(generateStatement("queryListByParam"), syTradeOrderQuery);
    }

    public Long queryCountByParam(SyTradeOrderQuery syTradeOrderQuery) {
        Assert.notNull(syTradeOrderQuery);
        
        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syTradeOrderQuery);
    }


}