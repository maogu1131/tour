package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyMerchantDao;
import com.songyang.tour.pojo.SyMerchant;
import com.songyang.tour.query.SyMerchantQuery;
import com.songyang.tour.service.SyMerchantService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyMerchantServiceImpl implements SyMerchantService {


	private static final String NAME_SPACE = SyMerchant.class.getName();


	@Resource
	private SyMerchantDao syMerchantDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyMerchant syMerchant) {
        Assert.notNull(syMerchant);
        return syMerchantDao.insert(syMerchant);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syMerchantDao.deleteById(id);
    }

    public int updateById(SyMerchant syMerchant) {
        Assert.notNull(syMerchant);
        Assert.notNull(syMerchant.getId());
        return syMerchantDao.updateById(syMerchant);
    }

    public SyMerchant selectById(Long id) {
        Assert.notNull(id);
        return syMerchantDao.selectById(id);
    }

    public SyMerchant selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syMerchantDao.selectByIdForUpdate(id);
    }

    public List<SyMerchant> queryListByParam(SyMerchantQuery syMerchantQuery) {
        Assert.notNull(syMerchantQuery);
        return syMerchantDao.queryListByParam(syMerchantQuery);
    }

    public Long queryCountByParam(SyMerchantQuery syMerchantQuery) {
        Assert.notNull(syMerchantQuery);
        return syMerchantDao.queryCountByParam(syMerchantQuery);
    }


}