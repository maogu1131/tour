package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyFeedbackDao;
import com.songyang.tour.pojo.SyFeedback;
import com.songyang.tour.query.SyFeedbackQuery;
import com.songyang.tour.service.SyFeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * Created by  小工具  on 2017/12/22.
 *
 */
@Service
public class SyFeedbackServiceImpl implements SyFeedbackService {


	private static final String NAME_SPACE = SyFeedback.class.getName();


	@Resource
	private SyFeedbackDao syFeedbackDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyFeedback syFeedback) {
        Assert.notNull(syFeedback);
        syFeedback.setCreateTime(new Date());
        return syFeedbackDao.insert(syFeedback);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syFeedbackDao.deleteById(id);
    }

    public int updateById(SyFeedback syFeedback) {
        Assert.notNull(syFeedback);
        Assert.notNull(syFeedback.getId());
        syFeedback.setModifyTime(new Date());
        return syFeedbackDao.updateById(syFeedback);
    }

    public SyFeedback selectById(Long id) {
        Assert.notNull(id);
        return syFeedbackDao.selectById(id);
    }

    public SyFeedback selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syFeedbackDao.selectByIdForUpdate(id);
    }

    public List<SyFeedback> queryListByParam(SyFeedbackQuery syFeedbackQuery) {
        Assert.notNull(syFeedbackQuery);
        return syFeedbackDao.queryListByParam(syFeedbackQuery);
    }

    public Long queryCountByParam(SyFeedbackQuery syFeedbackQuery) {
        Assert.notNull(syFeedbackQuery);
        return syFeedbackDao.queryCountByParam(syFeedbackQuery);
    }


}