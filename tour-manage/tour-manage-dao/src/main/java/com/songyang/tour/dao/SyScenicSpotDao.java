package com.songyang.tour.dao;

import com.songyang.tour.pojo.SyScenicSpot;
import com.songyang.tour.query.SyScenicSpotQuery;

import java.util.Map;

/**
 * dao层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 * Query后缀类是 查询对象，如果没有范围等查询条件，不需Query类型，要可以修改自动生成代码，直接用po对象当参数。
 */
public interface SyScenicSpotDao extends BaseDao<SyScenicSpot, SyScenicSpotQuery, Long> {

    // 扣减份额
    Integer deductByMap(Map<String,Object> map);

    // 增加份额
    Integer plusByMap(Map<String,Object> map);
}