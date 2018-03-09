package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyConsumeFlowDao;
import com.songyang.tour.pojo.SyConsumeFlow;
import com.songyang.tour.query.SyConsumeFlowQuery;
import com.songyang.tour.service.SyConsumeFlowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
/**
 * Created by  小工具  on 2017/12/02.
 *
 */
@Service
public class SyConsumeFlowServiceImpl implements SyConsumeFlowService {


	private static final String NAME_SPACE = SyConsumeFlow.class.getName();


	@Resource
	private SyConsumeFlowDao syConsumeFlowDao;


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
    public int insert(SyConsumeFlow syConsumeFlow) {
        Assert.notNull(syConsumeFlow);
        return syConsumeFlowDao.insert(syConsumeFlow);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syConsumeFlowDao.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int updateById(SyConsumeFlow syConsumeFlow) {
        Assert.notNull(syConsumeFlow);
        Assert.notNull(syConsumeFlow.getId());
        return syConsumeFlowDao.updateById(syConsumeFlow);
    }

    public SyConsumeFlow selectById(Long id) {
        Assert.notNull(id);
        return syConsumeFlowDao.selectById(id);
    }

    public SyConsumeFlow selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syConsumeFlowDao.selectByIdForUpdate(id);
    }

    public List<SyConsumeFlow> queryListByParam(SyConsumeFlowQuery syConsumeFlowQuery) {
        Assert.notNull(syConsumeFlowQuery);
        return syConsumeFlowDao.queryListByParam(syConsumeFlowQuery);
    }

    public Long queryCountByParam(SyConsumeFlowQuery syConsumeFlowQuery) {
        Assert.notNull(syConsumeFlowQuery);
        return syConsumeFlowDao.queryCountByParam(syConsumeFlowQuery);
    }


    @Override
    public SyConsumeFlow selectByReqId(String reqId) {
        Assert.notNull(reqId);
        return syConsumeFlowDao.selectByReqId(reqId);
    }
}