package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyModuleDao;
import com.songyang.tour.pojo.SyModule;
import com.songyang.tour.query.SyModuleQuery;
import com.songyang.tour.service.SyModuleService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyModuleServiceImpl implements SyModuleService {


	private static final String NAME_SPACE = SyModule.class.getName();


	@Resource
	private SyModuleDao syModuleDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyModule syModule) {
        Assert.notNull(syModule);
        return syModuleDao.insert(syModule);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syModuleDao.deleteById(id);
    }

    public int updateById(SyModule syModule) {
        Assert.notNull(syModule);
        Assert.notNull(syModule.getId());
        return syModuleDao.updateById(syModule);
    }

    public SyModule selectById(Long id) {
        Assert.notNull(id);
        return syModuleDao.selectById(id);
    }

    public SyModule selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syModuleDao.selectByIdForUpdate(id);
    }

    public List<SyModule> queryListByParam(SyModuleQuery syModuleQuery) {
        Assert.notNull(syModuleQuery);
        return syModuleDao.queryListByParam(syModuleQuery);
    }

    public Long queryCountByParam(SyModuleQuery syModuleQuery) {
        Assert.notNull(syModuleQuery);
        return syModuleDao.queryCountByParam(syModuleQuery);
    }


}