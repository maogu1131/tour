package com.songyang.tour.dao.impl;


import com.songyang.tour.dao.SyOldVillageDao;
import com.songyang.tour.pojo.SyOldVillage;
import com.songyang.tour.query.SyOldVillageQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Repository
public class SyOldVillageDaoImpl extends SqlSessionDaoSupport implements SyOldVillageDao {


    /**
     * mybatis mapper的命名空间，包路径加类名
     */
    private static final String NAME_SPACE = SyOldVillage.class.getName();


    /**
     * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
     *
     * @param id id主键
     * @return namespace.id
     */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }


    public int insert(SyOldVillage syOldVillage) {
        Assert.notNull(syOldVillage);
        syOldVillage.setCreateTime(new Date());

        return getSqlSession().insert(generateStatement("insert"), syOldVillage);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);

        return getSqlSession().delete(generateStatement("deleteById"), id);
    }

    public int updateById(SyOldVillage syOldVillage) {
        Assert.notNull(syOldVillage);
        Assert.notNull(syOldVillage.getId());
        syOldVillage.setModifyTime(new Date());

        return getSqlSession().update(generateStatement("updateById"), syOldVillage);
    }

    public SyOldVillage selectById(Long id) {
        Assert.notNull(id);

        return getSqlSession().selectOne(generateStatement("selectById"), id);
    }

    public SyOldVillage selectByIdForUpdate(Long id) {
        Assert.notNull(id);

        return getSqlSession().selectOne(generateStatement("selectByIdForUpdate"), id);
    }

    public List<SyOldVillage> queryListByParam(SyOldVillageQuery syOldVillageQuery) {
        Assert.notNull(syOldVillageQuery);
        syOldVillageQuery.checkBaseQuery(MAX_ROWS);

        return getSqlSession().selectList(generateStatement("queryListByParam"), syOldVillageQuery);
    }

    public Long queryCountByParam(SyOldVillageQuery syOldVillageQuery) {
        Assert.notNull(syOldVillageQuery);

        return getSqlSession().selectOne(generateStatement("queryCountByParam"), syOldVillageQuery);
    }


}