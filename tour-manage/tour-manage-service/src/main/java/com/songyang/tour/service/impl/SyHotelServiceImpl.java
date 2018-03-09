package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyHotelDao;
import com.songyang.tour.pojo.SyHotel;
import com.songyang.tour.query.SyHotelQuery;
import com.songyang.tour.service.SyHotelService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyHotelServiceImpl implements SyHotelService {


	private static final String NAME_SPACE = SyHotel.class.getName();


	@Resource
	private SyHotelDao syHotelDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyHotel syHotel) {
        Assert.notNull(syHotel);
        syHotel.setStatus(TourConstants.STATUS.NORMAL);
        return syHotelDao.insert(syHotel);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syHotelDao.deleteById(id);
    }

    public int updateById(SyHotel syHotel) {
        Assert.notNull(syHotel);
        Assert.notNull(syHotel.getId());
        return syHotelDao.updateById(syHotel);
    }

    public SyHotel selectById(Long id) {
        Assert.notNull(id);
        return syHotelDao.selectById(id);
    }

    public SyHotel selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syHotelDao.selectByIdForUpdate(id);
    }

    public List<SyHotel> queryListByParam(SyHotelQuery syHotelQuery) {
        Assert.notNull(syHotelQuery);
        return syHotelDao.queryListByParam(syHotelQuery);
    }

    public Long queryCountByParam(SyHotelQuery syHotelQuery) {
        Assert.notNull(syHotelQuery);
        return syHotelDao.queryCountByParam(syHotelQuery);
    }


}