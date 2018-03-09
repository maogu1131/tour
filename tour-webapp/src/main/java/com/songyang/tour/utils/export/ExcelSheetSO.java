package com.songyang.tour.utils.export;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DongPeng
 * 代表文件里的一张表
 */
public class ExcelSheetSO <T>{

	private String sheetName;//表名

	private String titleName; //表的标题

	private short titleColor; //标题的颜色

	private short headColor; //标题的颜色

	private String[] columnNames;//列名

	private String[] fieldNames; //类中字段的名

	private List<T> datas;//数据

	private int[] columnWidths;//列宽

	private int[] columnTypes;//列宽

	private ExcelDataLoader<T> excelDataLoader; //数据加载器

	private Map<String,ExcelDataConventor> conventors ; //针对某些字段的转换器


	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * @return the titleName
	 */
	public String getTitleName() {
		return titleName;
	}

	/**
	 * @param titleName the titleName to set
	 */
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	/**
	 * @return the titleColor
	 */
	public short getTitleColor() {
		return titleColor;
	}

	/**
	 * @param titleColor the titleColor to set
	 */
	public void setTitleColor(short titleColor) {
		this.titleColor = titleColor;
	}

	/**
	 * @return the headColor
	 */
	public short getHeadColor() {
		return headColor;
	}

	/**
	 * @param headColor the headColor to set
	 */
	public void setHeadColor(short headColor) {
		this.headColor = headColor;
	}

	/**
	 * @return the columnNames
	 */
	public String[] getColumnNames() {
		return columnNames;
	}

	/**
	 * @param columnNames the columnNames to set
	 */
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	/**
	 * @return the fieldNames
	 */
	public String[] getFieldNames() {
		return fieldNames;
	}

	/**
	 * @param fieldNames the fieldNames to set
	 */
	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}

	/**
	 * @return the datas
	 */
	public List<T> getDatas() {
		return datas;
	}

	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	/**
	 * @return the columnWidths
	 */
	public int[] getColumnWidths() {
		return columnWidths;
	}

	/**
	 * @param columnWidths the columnWidths to set
	 */
	public void setColumnWidths(int[] columnWidths) {
		this.columnWidths = columnWidths;
	}


	/**
	 * @return the excelDataLoader
	 */
	public ExcelDataLoader<T> getExcelDataLoader() {
		return excelDataLoader;
	}

	/**
	 * @param excelDataLoader the excelDataLoader to set
	 */
	public void setExcelDataLoader(ExcelDataLoader<T> excelDataLoader) {
		this.excelDataLoader = excelDataLoader;
	}


	/**
	 * @return the conventors
	 */
	public Map<String, ExcelDataConventor> getConventors() {
		return conventors;
	}

	/**
	 * @param conventors the conventors to set
	 */
	public void setConventors(Map<String, ExcelDataConventor> conventors) {
		this.conventors = conventors;
	}

	public ExcelDataConventor getConventorByFieldName(String fieldName){
		if(StringUtils.isEmpty(fieldName))
			return null;
		if(null == conventors)
			return null;
		return conventors.get(fieldName);
	}

	public int[] getColumnTypes() {
		return columnTypes;
	}

	public void setColumnTypes(int[] columnTypes) {
		this.columnTypes = columnTypes;
	}

	public void addConventor(String fieldName,ExcelDataConventor conventor){
		if(null == conventor)
			return;
		if(StringUtils.isEmpty(fieldName))
			return;
		if(null == conventors){
			conventors = new HashMap<String,ExcelDataConventor>();
		}
		conventors.put(fieldName, conventor);
	}


}
