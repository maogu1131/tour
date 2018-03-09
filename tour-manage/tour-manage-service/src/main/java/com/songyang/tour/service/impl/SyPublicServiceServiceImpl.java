package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyPublicServiceDao;
import com.songyang.tour.pojo.SyPublicService;
import com.songyang.tour.query.SyPublicServiceQuery;
import com.songyang.tour.service.SyPublicServiceService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyPublicServiceServiceImpl implements SyPublicServiceService {


	private static final String NAME_SPACE = SyPublicService.class.getName();


	@Resource
	private SyPublicServiceDao syPublicServiceDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyPublicService syPublicService) {
        Assert.notNull(syPublicService);
        syPublicService.setCreateTime(new Date());
        syPublicService.setStatus(TourConstants.STATUS.NORMAL);
        return syPublicServiceDao.insert(syPublicService);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syPublicServiceDao.deleteById(id);
    }

    public int updateById(SyPublicService syPublicService) {
        Assert.notNull(syPublicService);
        Assert.notNull(syPublicService.getId());
        syPublicService.setModifyTime(new Date());
        return syPublicServiceDao.updateById(syPublicService);
    }

    public SyPublicService selectById(Long id) {
        Assert.notNull(id);
        return syPublicServiceDao.selectById(id);
    }

    public SyPublicService selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syPublicServiceDao.selectByIdForUpdate(id);
    }

    public List<SyPublicService> queryListByParam(SyPublicServiceQuery syPublicServiceQuery) {
        Assert.notNull(syPublicServiceQuery);
        return syPublicServiceDao.queryListByParam(syPublicServiceQuery);
    }

    public Long queryCountByParam(SyPublicServiceQuery syPublicServiceQuery) {
        Assert.notNull(syPublicServiceQuery);
        return syPublicServiceDao.queryCountByParam(syPublicServiceQuery);
    }


}