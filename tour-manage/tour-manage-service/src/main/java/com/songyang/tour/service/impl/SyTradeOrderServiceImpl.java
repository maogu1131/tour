package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyTradeOrderDao;
import com.songyang.tour.pojo.SyTradeOrder;
import com.songyang.tour.query.SyTradeOrderQuery;
import com.songyang.tour.service.SyTradeOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by  小工具  on 2017/12/02.
 */
@Service
public class SyTradeOrderServiceImpl implements SyTradeOrderService {


    private static final String NAME_SPACE = SyTradeOrder.class.getName();


    @Resource
    private SyTradeOrderDao syTradeOrderDao;


    /**
     * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
     *
     * @param id id主键
     * @return namespace.id
     */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int insert(SyTradeOrder syTradeOrder) {
        Assert.notNull(syTradeOrder);
        return syTradeOrderDao.insert(syTradeOrder);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syTradeOrderDao.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int updateById(SyTradeOrder syTradeOrder) {
        Assert.notNull(syTradeOrder);
        Assert.notNull(syTradeOrder.getId());
        return syTradeOrderDao.updateById(syTradeOrder);
    }

    public SyTradeOrder selectById(Long id) {
        Assert.notNull(id);
        return syTradeOrderDao.selectById(id);
    }

    public SyTradeOrder selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syTradeOrderDao.selectByIdForUpdate(id);
    }

    public List<SyTradeOrder> queryListByParam(SyTradeOrderQuery syTradeOrderQuery) {
        Assert.notNull(syTradeOrderQuery);
        return syTradeOrderDao.queryListByParam(syTradeOrderQuery);
    }

    public Long queryCountByParam(SyTradeOrderQuery syTradeOrderQuery) {
        Assert.notNull(syTradeOrderQuery);
        return syTradeOrderDao.queryCountByParam(syTradeOrderQuery);
    }

}