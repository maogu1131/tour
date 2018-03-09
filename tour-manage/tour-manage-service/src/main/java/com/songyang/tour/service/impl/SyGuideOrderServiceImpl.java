package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyGuideOrderDao;
import com.songyang.tour.pojo.SyGuideOrder;
import com.songyang.tour.query.SyGuideOrderQuery;
import com.songyang.tour.service.SyGuideOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/11/23.
 *
 */
@Service
public class SyGuideOrderServiceImpl implements SyGuideOrderService {


	private static final String NAME_SPACE = SyGuideOrder.class.getName();


	@Resource
	private SyGuideOrderDao syGuideOrderDao;


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
        return syGuideOrderDao.insert(syGuideOrder);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syGuideOrderDao.deleteById(id);
    }

    public int updateById(SyGuideOrder syGuideOrder) {
        Assert.notNull(syGuideOrder);
        Assert.notNull(syGuideOrder.getId());
        return syGuideOrderDao.updateById(syGuideOrder);
    }

    public SyGuideOrder selectById(Long id) {
        Assert.notNull(id);
        return syGuideOrderDao.selectById(id);
    }

    public SyGuideOrder selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syGuideOrderDao.selectByIdForUpdate(id);
    }

    public List<SyGuideOrder> queryListByParam(SyGuideOrderQuery syGuideOrderQuery) {
        Assert.notNull(syGuideOrderQuery);
        return syGuideOrderDao.queryListByParam(syGuideOrderQuery);
    }

    public Long queryCountByParam(SyGuideOrderQuery syGuideOrderQuery) {
        Assert.notNull(syGuideOrderQuery);
        return syGuideOrderDao.queryCountByParam(syGuideOrderQuery);
    }


}