package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyFolkReserveOrderDao;
import com.songyang.tour.pojo.SyFolkReserveOrder;import com.songyang.tour.query.SyFolkReserveOrderQuery;
import com.songyang.tour.service.SyFolkReserveOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/12/22.
 *
 */
@Service
public class SyFolkReserveOrderServiceImpl implements SyFolkReserveOrderService {


	private static final String NAME_SPACE = SyFolkReserveOrder.class.getName();


	@Resource
	private SyFolkReserveOrderDao syFolkReserveOrderDao;


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
        return syFolkReserveOrderDao.insert(syFolkReserveOrder);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syFolkReserveOrderDao.deleteById(id);
    }

    public int updateById(SyFolkReserveOrder syFolkReserveOrder) {
        Assert.notNull(syFolkReserveOrder);
        Assert.notNull(syFolkReserveOrder.getId());
        return syFolkReserveOrderDao.updateById(syFolkReserveOrder);
    }

    public SyFolkReserveOrder selectById(Long id) {
        Assert.notNull(id);
        return syFolkReserveOrderDao.selectById(id);
    }

    public SyFolkReserveOrder selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syFolkReserveOrderDao.selectByIdForUpdate(id);
    }

    public List<SyFolkReserveOrder> queryListByParam(SyFolkReserveOrderQuery syFolkReserveOrderQuery) {
        Assert.notNull(syFolkReserveOrderQuery);
        return syFolkReserveOrderDao.queryListByParam(syFolkReserveOrderQuery);
    }

    public Long queryCountByParam(SyFolkReserveOrderQuery syFolkReserveOrderQuery) {
        Assert.notNull(syFolkReserveOrderQuery);
        return syFolkReserveOrderDao.queryCountByParam(syFolkReserveOrderQuery);
    }


}