package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyProdDao;
import com.songyang.tour.model.ShareTO;
import com.songyang.tour.pojo.SyProd;
import com.songyang.tour.query.SyProdQuery;
import com.songyang.tour.service.SyProdService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Service
public class SyProdServiceImpl implements SyProdService {


    private static final String NAME_SPACE = SyProd.class.getName();


    @Resource
    private SyProdDao syProdDao;


    /**
     * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
     *
     * @param id id主键
     * @return namespace.id
     */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyProd syProd) {
        Assert.notNull(syProd);
        syProd.setRemainNum(0);
        syProd.setSendNum(0);
        syProd.setCreateTime(new Date());
        syProd.setStatus(TourConstants.PROD_STATUS.FOR_SALE);
        syProd.setRemainNum(syProd.getTotalNum());
        syProd.setSendNum(0);
//        syProd.setSaleStatus(TourConstants.PROD_PAY_STATUS.FOR_SALE);
        return syProdDao.insert(syProd);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syProdDao.deleteById(id);
    }

    public int updateById(SyProd syProd) {
        Assert.notNull(syProd);
        Assert.notNull(syProd.getId());
        syProd.setModifyTime(new Date());
        return syProdDao.updateById(syProd);
    }

    public SyProd selectById(Long id) {
        Assert.notNull(id);
        return syProdDao.selectById(id);
    }

    public SyProd selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syProdDao.selectByIdForUpdate(id);
    }

    public List<SyProd> queryListByParam(SyProdQuery syProdQuery) {
        Assert.notNull(syProdQuery);
        return syProdDao.queryListByParam(syProdQuery);
    }

    public Long queryCountByParam(SyProdQuery syProdQuery) {
        Assert.notNull(syProdQuery);
        return syProdDao.queryCountByParam(syProdQuery);
    }


    @Override
    public Boolean deductByShareTO(ShareTO shareTO) {
        Assert.notNull(shareTO);
        Map<String, Object> map = new HashMap<String, Object>(3);
        map.put("id", shareTO.getProdId());
        map.put("num", shareTO.getNum());
        map.put("modifier", shareTO.getOperator());
        return (syProdDao.deductByMap(map) > 0 ? true : false);
    }

    @Override
    public Boolean plusByShareTO(ShareTO shareTO) {
        Assert.notNull(shareTO);
        Map<String, Object> map = new HashMap<String, Object>(3);
        map.put("id", shareTO.getProdId());
        map.put("num", shareTO.getNum());
        map.put("modifier", shareTO.getOperator());
        return (syProdDao.plusByMap(map) > 0 ? true : false);
    }
}