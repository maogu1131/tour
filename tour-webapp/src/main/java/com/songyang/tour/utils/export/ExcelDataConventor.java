package com.songyang.tour.utils.export; /**
 *
 */


/**
 * @author DongPeng
 * 将java对象中字段的属性转换成导出的excel中要展示的字段
 * 主要针对一些枚举，或者字段中为1，2，3等表示某种状态的情况
 */
public interface ExcelDataConventor {

	/**
	 * 传入将要被转换的值
	 * @param valueWillBeConvented
	 * @return
	 */
	public String convent(Object valueWillBeConvented);

}
