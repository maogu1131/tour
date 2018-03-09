package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyScenicSpotDao;
import com.songyang.tour.model.ShareTO;
import com.songyang.tour.pojo.SyScenicSpot;
import com.songyang.tour.query.SyScenicSpotQuery;
import com.songyang.tour.service.SyScenicSpotService;
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
public class SyScenicSpotServiceImpl implements SyScenicSpotService {


    private static final String NAME_SPACE = SyScenicSpot.class.getName();


    @Resource
    private SyScenicSpotDao syScenicSpotDao;


    /**
     * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
     *
     * @param id id主键
     * @return namespace.id
     */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyScenicSpot syScenicSpot) {
        Assert.notNull(syScenicSpot);
        syScenicSpot.setCreateTime(new Date());
        syScenicSpot.setStatus(TourConstants.STATUS.NORMAL);
        syScenicSpot.setTicketSendNum(0);
        syScenicSpot.setTicketRemainNum(syScenicSpot.getTicketTotalNum());
        return syScenicSpotDao.insert(syScenicSpot);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syScenicSpotDao.deleteById(id);
    }

    public int updateById(SyScenicSpot syScenicSpot) {
        Assert.notNull(syScenicSpot);
        Assert.notNull(syScenicSpot.getId());
        syScenicSpot.setModifyTime(new Date());
        return syScenicSpotDao.updateById(syScenicSpot);
    }

    public SyScenicSpot selectById(Long id) {
        Assert.notNull(id);
        return syScenicSpotDao.selectById(id);
    }

    public SyScenicSpot selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syScenicSpotDao.selectByIdForUpdate(id);
    }

    public List<SyScenicSpot> queryListByParam(SyScenicSpotQuery syScenicSpotQuery) {
        Assert.notNull(syScenicSpotQuery);
        return syScenicSpotDao.queryListByParam(syScenicSpotQuery);
    }

    public Long queryCountByParam(SyScenicSpotQuery syScenicSpotQuery) {
        Assert.notNull(syScenicSpotQuery);
        return syScenicSpotDao.queryCountByParam(syScenicSpotQuery);
    }


    @Override
    public Boolean deductByShareTO(ShareTO shareTO) {
        Assert.notNull(shareTO);
        Map<String,Object> map = new HashMap<String,Object>(3);
        map.put("id",shareTO.getProdId());
        map.put("num",shareTO.getNum());
        map.put("modifier",shareTO.getOperator());
        return syScenicSpotDao.deductByMap(map) > 0? true:false;
    }

    @Override
    public Boolean plusByShareTO(ShareTO shareTO) {
        Assert.notNull(shareTO);
        Map<String,Object> map = new HashMap<String,Object>(3);
        map.put("id",shareTO.getProdId());
        map.put("num",shareTO.getNum());
        map.put("modifier",shareTO.getOperator());
        return syScenicSpotDao.plusByMap(map) > 0? true:false;
    }
}