package com.songyang.tour.utils.export;

import java.util.List;

/**
 * @author DongPeng
 * 用于导出excel时候需要分页抓取数据源的情况
 */
public interface ExcelDataLoader <T>{

	//每页读取多少条数据
	public int getBufferSize();

	//从多少条开始读取
	public List<T> loadData(int fromIndex);

}