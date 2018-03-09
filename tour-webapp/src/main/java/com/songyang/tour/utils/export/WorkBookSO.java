package com.songyang.tour.utils.export; /**
 *
 */


import java.util.ArrayList;
import java.util.List;

/**
 * @author DongPeng
 * 代表一个excel文件
 */
public class WorkBookSO {

	private String fileName;

	private List<ExcelSheetSO>  sheets;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the sheets
	 */
	public List<ExcelSheetSO> getSheets() {
		return sheets;
	}

	/**
	 * @param sheets the sheets to set
	 */
	public void setSheets(List<ExcelSheetSO> sheets) {
		this.sheets = sheets;
	}

	public void addSheet(ExcelSheetSO so){
		if(null==sheets)
			sheets = new ArrayList<ExcelSheetSO>();
		sheets.add(so);
	}
}
