package com.songyang.tour.service.impl;


import com.songyang.tour.dao.SyFolkDao;
import com.songyang.tour.pojo.SyFolk;
import com.songyang.tour.query.SyFolkQuery;
import com.songyang.tour.service.SyFolkService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by  小工具  on 2017/08/31.
 *
 */
@Service
public class SyFolkServiceImpl implements SyFolkService {


	private static final String NAME_SPACE = SyFolk.class.getName();


	@Resource
	private SyFolkDao syFolkDao;


	/**
	 * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
	 *
	 * @param id id主键
	 * @return namespace.id
	 */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyFolk syFolk) {
        Assert.notNull(syFolk);
        return syFolkDao.insert(syFolk);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syFolkDao.deleteById(id);
    }

    public int updateById(SyFolk syFolk) {
        Assert.notNull(syFolk);
        Assert.notNull(syFolk.getId());
        return syFolkDao.updateById(syFolk);
    }

    public SyFolk selectById(Long id) {
        Assert.notNull(id);
        return syFolkDao.selectById(id);
    }

    public SyFolk selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syFolkDao.selectByIdForUpdate(id);
    }

    public List<SyFolk> queryListByParam(SyFolkQuery syFolkQuery) {
        Assert.notNull(syFolkQuery);
        return syFolkDao.queryListByParam(syFolkQuery);
    }

    public Long queryCountByParam(SyFolkQuery syFolkQuery) {
        Assert.notNull(syFolkQuery);
        return syFolkDao.queryCountByParam(syFolkQuery);
    }


}