package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyCustomRouteDaysDao;
import com.songyang.tour.pojo.SyCustomRouteDays;
import com.songyang.tour.query.SyCustomRouteDaysQuery;
import com.songyang.tour.service.SyCustomRouteDaysService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/09/24.
 *
 */
@Service
public class SyCustomRouteDaysServiceImpl implements SyCustomRouteDaysService {


	private static final String NAME_SPACE = SyCustomRouteDays.class.getName();


	@Resource
	private SyCustomRouteDaysDao syCustomRouteDaysDao;


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
    public int insert(SyCustomRouteDays syCustomRouteDays) {
        Assert.notNull(syCustomRouteDays);
        return syCustomRouteDaysDao.insert(syCustomRouteDays);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int deleteById(Long id) {
        Assert.notNull(id);
        return syCustomRouteDaysDao.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int updateById(SyCustomRouteDays syCustomRouteDays) {
        Assert.notNull(syCustomRouteDays);
        Assert.notNull(syCustomRouteDays.getId());
        return syCustomRouteDaysDao.updateById(syCustomRouteDays);
    }

    public SyCustomRouteDays selectById(Long id) {
        Assert.notNull(id);
        return syCustomRouteDaysDao.selectById(id);
    }

    public SyCustomRouteDays selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syCustomRouteDaysDao.selectByIdForUpdate(id);
    }

    public List<SyCustomRouteDays> queryListByParam(SyCustomRouteDaysQuery syCustomRouteDaysQuery) {
        Assert.notNull(syCustomRouteDaysQuery);
        return syCustomRouteDaysDao.queryListByParam(syCustomRouteDaysQuery);
    }

    public Long queryCountByParam(SyCustomRouteDaysQuery syCustomRouteDaysQuery) {
        Assert.notNull(syCustomRouteDaysQuery);
        return syCustomRouteDaysDao.queryCountByParam(syCustomRouteDaysQuery);
    }


    @Override
    public List<SyCustomRouteDays> selectByCustomRouteId(Long customRouteId) {
        Assert.notNull(customRouteId);
        return syCustomRouteDaysDao.selectByCustomRouteId(customRouteId);
    }
}