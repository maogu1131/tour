package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyNoticeDao;
import com.songyang.tour.pojo.SyNotice;
import com.songyang.tour.query.SyNoticeQuery;
import com.songyang.tour.service.SyNoticeService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyNoticeServiceImpl implements SyNoticeService {


	private static final String NAME_SPACE = SyNotice.class.getName();


	@Resource
	private SyNoticeDao syNoticeDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyNotice syNotice) {
        Assert.notNull(syNotice);
        return syNoticeDao.insert(syNotice);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syNoticeDao.deleteById(id);
    }

    public int updateById(SyNotice syNotice) {
        Assert.notNull(syNotice);
        Assert.notNull(syNotice.getId());
        return syNoticeDao.updateById(syNotice);
    }

    public SyNotice selectById(Long id) {
        Assert.notNull(id);
        return syNoticeDao.selectById(id);
    }

    public SyNotice selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syNoticeDao.selectByIdForUpdate(id);
    }

    public List<SyNotice> queryListByParam(SyNoticeQuery syNoticeQuery) {
        Assert.notNull(syNoticeQuery);
        return syNoticeDao.queryListByParam(syNoticeQuery);
    }

    public Long queryCountByParam(SyNoticeQuery syNoticeQuery) {
        Assert.notNull(syNoticeQuery);
        return syNoticeDao.queryCountByParam(syNoticeQuery);
    }


}