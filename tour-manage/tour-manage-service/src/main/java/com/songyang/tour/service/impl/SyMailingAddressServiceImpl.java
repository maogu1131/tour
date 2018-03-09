package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyMailingAddressDao;
import com.songyang.tour.pojo.SyMailingAddress;
import com.songyang.tour.query.SyMailingAddressQuery;
import com.songyang.tour.service.SyMailingAddressService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/11/04.
 */
@Service
public class SyMailingAddressServiceImpl implements SyMailingAddressService {


    private static final String NAME_SPACE = SyMailingAddress.class.getName();


    @Resource
    private SyMailingAddressDao syMailingAddressDao;


    /**
     * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
     *
     * @param id id主键
     * @return namespace.id
     */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyMailingAddress syMailingAddress) {
        Assert.notNull(syMailingAddress);
        syMailingAddress.setCreateTime(new Date());
        return syMailingAddressDao.insert(syMailingAddress);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syMailingAddressDao.deleteById(id);
    }

    public int updateById(SyMailingAddress syMailingAddress) {
        Assert.notNull(syMailingAddress);
        Assert.notNull(syMailingAddress.getId());
        syMailingAddress.setModifyTime(new Date());
        return syMailingAddressDao.updateById(syMailingAddress);
    }

    public SyMailingAddress selectById(Long id) {
        Assert.notNull(id);
        return syMailingAddressDao.selectById(id);
    }

    public SyMailingAddress selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syMailingAddressDao.selectByIdForUpdate(id);
    }

    public List<SyMailingAddress> queryListByParam(SyMailingAddressQuery syMailingAddressQuery) {
        Assert.notNull(syMailingAddressQuery);
        return syMailingAddressDao.queryListByParam(syMailingAddressQuery);
    }

    public Long queryCountByParam(SyMailingAddressQuery syMailingAddressQuery) {
        Assert.notNull(syMailingAddressQuery);
        return syMailingAddressDao.queryCountByParam(syMailingAddressQuery);
    }


}