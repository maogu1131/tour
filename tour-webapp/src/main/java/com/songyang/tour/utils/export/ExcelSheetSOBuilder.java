package com.songyang.tour.utils.export;


import com.songyang.tour.utils.export.ExcelSheetSO;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @author DongPeng
 *
 */
public class ExcelSheetSOBuilder {

	//创建一个默认的 sheetso
	public static <T> ExcelSheetSO<T> buildExcelSheetSO(int length){
		ExcelSheetSO<T> so = new ExcelSheetSO<T> ();
		int[] widths = new int[length] ;
		int[] columnTypes = new int[length] ;
		for(int i=0;i<length;i++){
			widths[i]=10;
			columnTypes[i]=1;
		}
		so.setColumnWidths(widths);
		so.setColumnTypes(columnTypes);
		so.setTitleColor(IndexedColors.AQUA.getIndex());
		so.setHeadColor(IndexedColors.BRIGHT_GREEN.getIndex());

		return so;
	}

	//拷贝一个 sheetso
	public static <T> ExcelSheetSO<T> copyExcelSheetSO(ExcelSheetSO<T> sheetso,int i){
		ExcelSheetSO<T> so = new ExcelSheetSO<T> ();
		so.setColumnNames(sheetso.getColumnNames());
		so.setColumnWidths(sheetso.getColumnWidths());
		so.setColumnTypes(sheetso.getColumnTypes());
		so.setFieldNames(sheetso.getFieldNames());
		so.setHeadColor(sheetso.getHeadColor());
		so.setTitleColor(sheetso.getTitleColor());
		so.setTitleName(sheetso.getTitleName());
		so.setSheetName(sheetso.getSheetName()+""+i);
		return so;
	}




}
