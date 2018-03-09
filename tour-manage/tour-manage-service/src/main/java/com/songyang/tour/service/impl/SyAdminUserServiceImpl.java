package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyAdminUserDao;
import com.songyang.tour.pojo.SyAdminUser;
import com.songyang.tour.query.SyAdminUserQuery;
import com.songyang.tour.service.SyAdminUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * Created by  小工具  on 2017/12/09.
 *
 */
@Service
public class SyAdminUserServiceImpl implements SyAdminUserService {


	private static final String NAME_SPACE = SyAdminUser.class.getName();


	@Resource
	private SyAdminUserDao syAdminUserDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyAdminUser syAdminUser) {
        Assert.notNull(syAdminUser);
        syAdminUser.setStatus(TourConstants.ADMIN_STATUS.AVAILABLE);
        return syAdminUserDao.insert(syAdminUser);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syAdminUserDao.deleteById(id);
    }

    public int updateById(SyAdminUser syAdminUser) {
        Assert.notNull(syAdminUser);
        Assert.notNull(syAdminUser.getId());
        syAdminUser.setModifyTime(new Date());
        return syAdminUserDao.updateById(syAdminUser);
    }

    public SyAdminUser selectById(Long id) {
        Assert.notNull(id);
        return syAdminUserDao.selectById(id);
    }

    public SyAdminUser selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syAdminUserDao.selectByIdForUpdate(id);
    }

    public List<SyAdminUser> queryListByParam(SyAdminUserQuery syAdminUserQuery) {
        Assert.notNull(syAdminUserQuery);
        return syAdminUserDao.queryListByParam(syAdminUserQuery);
    }

    public Long queryCountByParam(SyAdminUserQuery syAdminUserQuery) {
        Assert.notNull(syAdminUserQuery);
        return syAdminUserDao.queryCountByParam(syAdminUserQuery);
    }


}