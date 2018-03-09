package com.songyang.tour.dao;

import java.util.List;

/**
 * dao层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 */
public interface BaseDao<PO, Query, KEY> {

    /**
     * 交易日校验-20121212---21121212这一百年间为有效
     */
    int EFFECT_START_DATE = 20121212;


    /**
     * 最大有效日期
     */
    int EFFECT_END_DATE = 21121212;


    /**
     * 最大返回条数
     */
    int MAX_ROWS = 5000;


    /**
     * 插入持久化对象
     *
     * @param po 持久化对象
     * @return 影响条数
     */
    int insert(PO po);


    /**
     * 根据id删除记录
     *
     * @param id id主键
     * @return 影响条数
     */
    int deleteById(KEY id);


    /**
     * 根据id更新更新记录
     *
     * @param po 持久化对象
     * @return 影响条数
     */
    int updateById(PO po);


    /**
     * 根据id查询记录
     *
     * @param id 主键
     * @return 返回id对应记录的持久化对象
     */
    PO selectById(KEY id);


    /**
     * 根据id查询记录，在事物将锁住该条记录
     *
     * @param id 主键
     * @return 返回查询记录对应的持久化对象
     */
    PO selectByIdForUpdate(KEY id);


    /**
     * 根据Query 查询对象的参数值，获取符合条件的记录集合
     *
     * @param query 查询对象
     * @return 返回查询记录集合
     */
    List<PO> queryListByParam(Query query);


    /**
     * 根据Query 查询对象的参数值，获取符合条件的记录条数
     *
     * @param query 查询对象
     * @return 返回符合查询对象的记录条数
     */
    Long queryCountByParam(Query query);


}