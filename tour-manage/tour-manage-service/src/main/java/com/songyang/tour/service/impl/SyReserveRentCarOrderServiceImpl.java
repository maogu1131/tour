package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyReserveRentCarOrderDao;
import com.songyang.tour.pojo.SyReserveRentCarOrder;
import com.songyang.tour.query.SyReserveRentCarOrderQuery;
import com.songyang.tour.service.SyReserveRentCarOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * Created by  小工具  on 2017/11/19.
 *
 */
@Service
public class SyReserveRentCarOrderServiceImpl implements SyReserveRentCarOrderService {


	private static final String NAME_SPACE = SyReserveRentCarOrder.class.getName();


	@Resource
	private SyReserveRentCarOrderDao syReserveRentCarOrderDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyReserveRentCarOrder syReserveRentCarOrder) {
        Assert.notNull(syReserveRentCarOrder);
        syReserveRentCarOrder.setCreateTime(new Date());
        return syReserveRentCarOrderDao.insert(syReserveRentCarOrder);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syReserveRentCarOrderDao.deleteById(id);
    }

    public int updateById(SyReserveRentCarOrder syReserveRentCarOrder) {
        Assert.notNull(syReserveRentCarOrder);
        Assert.notNull(syReserveRentCarOrder.getId());
        syReserveRentCarOrder.setModifyTime(new Date());
        return syReserveRentCarOrderDao.updateById(syReserveRentCarOrder);
    }

    public SyReserveRentCarOrder selectById(Long id) {
        Assert.notNull(id);
        return syReserveRentCarOrderDao.selectById(id);
    }

    public SyReserveRentCarOrder selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syReserveRentCarOrderDao.selectByIdForUpdate(id);
    }

    public List<SyReserveRentCarOrder> queryListByParam(SyReserveRentCarOrderQuery syReserveRentCarOrderQuery) {
        Assert.notNull(syReserveRentCarOrderQuery);
        return syReserveRentCarOrderDao.queryListByParam(syReserveRentCarOrderQuery);
    }

    public Long queryCountByParam(SyReserveRentCarOrderQuery syReserveRentCarOrderQuery) {
        Assert.notNull(syReserveRentCarOrderQuery);
        return syReserveRentCarOrderDao.queryCountByParam(syReserveRentCarOrderQuery);
    }


}