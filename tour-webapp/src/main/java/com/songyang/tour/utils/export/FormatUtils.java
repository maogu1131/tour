package com.songyang.tour.utils.export; /**
 *
 */

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
	 * @author DongPeng
	 *
	 */
	public class FormatUtils {

		public static final String sdf_long = "yyyy-MM-dd HH:mm:ss";
		public static final String sdf_long_noyear = "MM-dd HH:mm:ss";
		public static final String sdf_short = "yyyy-MM-dd";

		public static String moneyFormat(BigDecimal money){
			if(null==money)
				return "";
			DecimalFormat df = new DecimalFormat("#,###.00");
			String formatMoney = df.format(money);
			if(formatMoney.startsWith("."))
				formatMoney ="0" + formatMoney;
			return formatMoney;
		}

		public static String dateFormatForLong(Date date){
			if(null==date)
				return "";
			return DateFormatUtils.format(date, sdf_long);
		}

		public static String dateFormatForShort(Date date){
			if(null==date)
				return "";
			return DateFormatUtils.format(date, sdf_short);
		}

		public static String dateFormatWithNoyear(Date date){
			if(null==date)
				return "";
			return DateFormatUtils.format(date, sdf_long_noyear);
		}


		public static void main(String[] args) {
			System.out.println(moneyFormat(new BigDecimal(0)));
		}


	private static final Logger log = LoggerFactory.getLogger(ExportUtils.class);

	/**
	 * 适用于数据量比较大的情况
	 * 导出excle 可以根据ExcelDataLoader 自动抓取数据，当数据大于65000条时，自动进行分表
	 * @param sheetso
	 * @param fileName
	 * @param response
	 */
	public static <T> void exportExcel(ExcelSheetSO<T> sheetso,String fileName,
									   HttpServletResponse response) {
		exportExcel(ExcelFileGenerator.createMultiPageWorkbook(sheetso),fileName,response);
	}


	/**
	 * 适用于数据量较小的导出
	 * @param workbook (需要设置workbook的文件名，和sheetso的属性)
	 * @param response
	 */
	public static <T> void exportExcel(WorkBookSO workbook,
									   HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = java.net.URLEncoder.encode(workbook.getFileName(),
					"UTF-8");
			response.setHeader("content-disposition", "attachment;filename="
					+ codedFileName + ".xls");
			HSSFWorkbook hssworkbook = ExcelFileGenerator.createWorkbook(workbook);
			fOut = response.getOutputStream();
			hssworkbook.write(fOut);
		} catch (Exception e) {
			log.warn("导出文件失败",e);
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {
				log.warn("关闭流发生异常",e);
			}
		}
	}

	/**
	 * 已经创建好 HSSFWorkbook 的情况下从浏览器导出
	 * @param hssworkbook
	 * @param fileName
	 * @param response
	 */
	public static <T> void exportExcel(HSSFWorkbook hssworkbook,String fileName,
									   HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = java.net.URLEncoder.encode(fileName,"UTF-8");
			response.setHeader("content-disposition", "attachment;filename="
					+ codedFileName + ".xls");
			fOut = response.getOutputStream();
			hssworkbook.write(fOut);
		} catch (Exception e) {
			log.warn("导出文件失败",e);
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {
				log.warn("关闭流发生异常",e);
			}
		}
	}

	//可用于导出文件到指定的输出流
	public static <T> void   exportExcel(WorkBookSO workbook,OutputStream outStream){
		try {
			ExcelFileGenerator.createWorkbook(workbook).write(outStream);
		} catch (IOException e) {
			log.warn("导出文件失败");
		} finally{
			try {
				outStream.flush();
				outStream.close();
			} catch (IOException e) {
				log.warn("关闭流发生异常",e);
			}
		}
	}

}
