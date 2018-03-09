package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyTrafficWarnDao;
import com.songyang.tour.pojo.SyTrafficWarn;
import com.songyang.tour.query.SyTrafficWarnQuery;
import com.songyang.tour.service.SyTrafficWarnService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * Created by  小工具  on 2017/09/24.
 *
 */
@Service
public class SyTrafficWarnServiceImpl implements SyTrafficWarnService {


	private static final String NAME_SPACE = SyTrafficWarn.class.getName();


	@Resource
	private SyTrafficWarnDao syTrafficWarnDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyTrafficWarn syTrafficWarn) {
        Assert.notNull(syTrafficWarn);
        syTrafficWarn.setCreateTime(new Date());
        syTrafficWarn.setStatus(TourConstants.STATUS.NORMAL);
        return syTrafficWarnDao.insert(syTrafficWarn);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syTrafficWarnDao.deleteById(id);
    }

    public int updateById(SyTrafficWarn syTrafficWarn) {
        Assert.notNull(syTrafficWarn);
        Assert.notNull(syTrafficWarn.getId());
        syTrafficWarn.setModifyTime(new Date());
        return syTrafficWarnDao.updateById(syTrafficWarn);
    }

    public SyTrafficWarn selectById(Long id) {
        Assert.notNull(id);
        return syTrafficWarnDao.selectById(id);
    }

    public SyTrafficWarn selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syTrafficWarnDao.selectByIdForUpdate(id);
    }

    public List<SyTrafficWarn> queryListByParam(SyTrafficWarnQuery syTrafficWarnQuery) {
        Assert.notNull(syTrafficWarnQuery);
        return syTrafficWarnDao.queryListByParam(syTrafficWarnQuery);
    }

    public Long queryCountByParam(SyTrafficWarnQuery syTrafficWarnQuery) {
        Assert.notNull(syTrafficWarnQuery);
        return syTrafficWarnDao.queryCountByParam(syTrafficWarnQuery);
    }


}