package com.songyang.tour.service;

import java.util.List;

/**
 * service层 提供基础查询接口
 * insert、deleteById、updateById、selectById、selectByIdForUpdate、queryListByParam、queryCountByParam、
 * Query后缀类是 查询对象，如果没有范围等查询条件，不需Query类型，要可以修改自动生成代码，直接用po对象当参数。
 */
public interface BaseService<PO, Query, KEY> {



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