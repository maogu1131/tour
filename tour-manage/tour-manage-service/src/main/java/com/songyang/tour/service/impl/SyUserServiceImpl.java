package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyUserDao;
import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.query.SyUserQuery;
import com.songyang.tour.service.SyUserService;
import com.songyang.tour.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * Created by  小工具  on 2017/11/19.
 *
 */
@Service
public class SyUserServiceImpl implements SyUserService {


    private static final Logger LOG = LoggerFactory.getLogger(SyUserServiceImpl.class);

	private static final String NAME_SPACE = SyUser.class.getName();


	@Resource
	private SyUserDao syUserDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyUser syUser) {
        Assert.notNull(syUser);
        syUser.setCreateTime(new Date());
        return syUserDao.insert(syUser);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syUserDao.deleteById(id);
    }

    public int updateById(SyUser syUser) {
        Assert.notNull(syUser);
        Assert.notNull(syUser.getId());
        syUser.setModifyTime(new Date());
        return syUserDao.updateById(syUser);
    }

    public SyUser selectById(Long id) {
        Assert.notNull(id);
        return syUserDao.selectById(id);
    }

    public SyUser selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syUserDao.selectByIdForUpdate(id);
    }

    public List<SyUser> queryListByParam(SyUserQuery syUserQuery) {
        Assert.notNull(syUserQuery);
        return syUserDao.queryListByParam(syUserQuery);
    }

    public Long queryCountByParam(SyUserQuery syUserQuery) {
        Assert.notNull(syUserQuery);
        return syUserDao.queryCountByParam(syUserQuery);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    @Override
    public Boolean save(SyUser user) {

        Assert.notNull(user);
        Integer count = 0;
        String LoginId = null;
        SyUser tempUser = null;
        if(StringUtils.isNotBlank(user.getQqLoginId())){
            tempUser = syUserDao.selectByqqLoginId(user.getQqLoginId());
        }else{
            tempUser = syUserDao.selectByWxLoginId( user.getWxLoginId());
        }

        if (null == tempUser) {
            count = syUserDao.insert(user);
            if(count < 1){
                LOG.error("syUserDao.insert is error");
                throw new RuntimeException("syUserDao.insert is exception");
            }

            // 更新用户id
            String userId = CommonUtil.buildUserId(user.getId());
            SyUser param = new SyUser();
            param.setId(user.getId());
            param.setUserId(userId);
            count = syUserDao.updateById(param);
        } else {
            user.setUserId(tempUser.getUserId());
            count = syUserDao.updateByUser(user);
        }

        if(count < 1){
            LOG.error("save is error");
            throw new RuntimeException("save userId is error");
        }else{
            return true;
        }
    }

    @Override
    public SyUser selectByWxLoginId(String loginId) {
        return syUserDao.selectByWxLoginId(loginId);
    }

    @Override
    public SyUser selectByUserId(String userId) {
        return syUserDao.selectByUserId(userId);
    }
}