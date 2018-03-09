package com.songyang.tour.utils.export;

import com.songyang.tour.utils.export.ExcelSheetSO;
import com.songyang.tour.utils.export.WorkBookSO;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DongPeng
 * 导出功能工具类
 */
public class ExcelFileGenerator {

	//单表最大导入65000条数据
	public static final int SHEETMAXSIZE = 65000;

	@SuppressWarnings("unchecked")
	public static <T> HSSFWorkbook  createWorkbook(WorkBookSO workbookso){
		HSSFWorkbook workbook = new HSSFWorkbook();

		for(ExcelSheetSO<T> sheetso : workbookso.getSheets()){
			createHSSFSheet(workbook,sheetso);
		}

		return workbook;
	}

	//创建sheet
	public static <T> HSSFSheet  createHSSFSheet(HSSFWorkbook workbook,ExcelSheetSO<T> sheetso){
		HSSFSheet sheet =createHSSFSheetHead(workbook,sheetso);
		createHSSFSheetBody(sheet,sheetso);
		return sheet;
	}


	public static <T> HSSFSheet  createHSSFSheetHead(HSSFWorkbook workbook,ExcelSheetSO<T> sheetso){
		HSSFSheet sheet = workbook.createSheet(sheetso.getSheetName());
		String[] columnNames = sheetso.getColumnNames() ;

		//标题颜色
		CellStyle style = workbook.createCellStyle();

		int i =0;
		if(StringUtils.isNotEmpty(sheetso.getTitleName())){
			HSSFRow headColumnRow = sheet.createRow((int)i);
			i++;
			HSSFCell cell = headColumnRow.createCell(0);

			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setFillForegroundColor(sheetso.getTitleColor());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(style);

			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(sheetso.getTitleName());

			// 四个参数分别是：起始行，起始列，结束行，结束列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnNames.length-1));
		}


		HSSFRow columnNameRow = sheet.createRow((int)i);

		//设置列宽
		int[] columnwidth =  sheetso.getColumnWidths();
		for(int columnno =0; columnno < columnwidth.length; columnno++){
			sheet.setColumnWidth(columnno, columnwidth[columnno]*256);
		}

		//列头颜色
		style = workbook.createCellStyle();
		style.setFillForegroundColor(sheetso.getHeadColor());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);



		for( int columnno =0;columnno < columnNames.length; columnno++){
			String headColumn = columnNames[columnno];
			HSSFCell cell = columnNameRow.createCell(columnno);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(headColumn);
			cell.setCellStyle(style);
		}

		return sheet;
	}


	public static <T> void  createHSSFSheetBody(HSSFSheet sheet,ExcelSheetSO<T> sheetso){
		int i =2;
		if(CollectionUtils.isNotEmpty(sheetso.getDatas()))
			for(T data:sheetso.getDatas()){
				loadOneRecordToSheet(data,sheet,sheetso,i);
				i++;
			}

		if(null==sheetso.getExcelDataLoader()){
			return ;
		}
		ExcelDataLoader<T> loader = sheetso.getExcelDataLoader();
		int offset = 0;
		int buffersize = loader.getBufferSize();
		int realsize = buffersize;
		while(realsize==buffersize){
			List<T> loaddata = loader.loadData(offset);
			if(i>SHEETMAXSIZE)
				break;
			for(T data:loaddata){
				loadOneRecordToSheet(data,sheet,sheetso,i);
				i++;
			}
			realsize = loaddata.size();
			offset = offset + realsize;
		}

	}



	//创建表格的一行数据
	public static <T> void loadOneRecordToSheet(T data,HSSFSheet sheet,ExcelSheetSO<T> sheetso,int rowno){
		String[] fieldNames = sheetso.getFieldNames();
		HSSFRow valueRow = sheet.createRow(rowno);
		int columnno = 0;
		for(String fieldName : fieldNames){
			Object value = null;
			try {
				value = PropertyUtils.getProperty(data, fieldName);
			} catch (Exception e) {
				value="";
			}
			HSSFCell cell = valueRow.createCell(columnno++);
			cell.setCellType(sheetso.getColumnTypes()[columnno-1]);
			String valueStr = parseValue(value,sheetso.getConventorByFieldName(fieldName));
			if(0 == cell.getCellType()){
				if(StringUtils.isEmpty(valueStr)){
					cell.setCellValue(new Integer(0));
				}else {
					try{
						cell.setCellValue(Double.parseDouble(valueStr));
					}catch(Exception ex){
						cell.setCellValue(new Integer(0));
					}
				}
			}else{
				cell.setCellValue(valueStr);
			}

		}
	}



	public static String parseValue(Object value,ExcelDataConventor conventor) {
		if(null==value)
			return "";
		if(null!=conventor){
			return conventor.convent(value);
		}
		if(value instanceof Date){
			return FormatUtils.dateFormatForLong((Date)value);
		}else if(value instanceof BigDecimal){
			//return FormatUtils.moneyFormat((BigDecimal)value);
		}else if(value instanceof Money){
			return FormatUtils.moneyFormat(((Money)value).getAmount());
		}else if(value.getClass().isEnum()){
			String descp = null;
			try{
				descp= PropertyUtils.getProperty(value, "message").toString();
			}catch(Exception ex){
				return value.toString();
			}

			if(!StringUtils.isEmpty(descp))
				return descp;

			try{
				return PropertyUtils.getProperty(value, "description").toString();
			}catch(Exception ex){
				return value.toString();
			}

		}
		return value.toString();
	}



	/**
	 * 创建一个自动分表的excel文档
	 * @param sheetso
	 * @return
	 */
	public static <T> HSSFWorkbook createMultiPageWorkbook(ExcelSheetSO<T> sheetso){

		HSSFWorkbook workbook = new HSSFWorkbook();

		Map<Integer,HSSFSheet> sheetMap = new HashMap<Integer,HSSFSheet>();

		ExcelDataLoader<T> excelDataLoader = sheetso.getExcelDataLoader();

		int offset = 0;
		int buffersize = excelDataLoader.getBufferSize();
		int realsize = buffersize;

		int recordnum = 0;
		int i =2;
		int sheetsize = 0;
		while(realsize==buffersize){
			List<T> loaddata = excelDataLoader.loadData(offset);
			for(T data:loaddata){
				if(recordnum % SHEETMAXSIZE ==0){
					sheetsize++;
					ExcelSheetSO<T> onesheet = ExcelSheetSOBuilder.copyExcelSheetSO(sheetso, sheetsize);
					HSSFSheet onehssfsheet = createHSSFSheetHead(workbook,onesheet);
					sheetMap.put(sheetsize, onehssfsheet);
					i =2;
				}
				loadOneRecordToSheet(data,sheetMap.get(sheetsize),sheetso,i);
				i++;
				recordnum++;
			}
			realsize = loaddata.size();
			offset = offset + realsize;
		}

		return workbook;
	}

}
