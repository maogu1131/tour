package com.songyang.tour.enums;/**
 * Created by lenovo on 2017/9/1.
 */

/**
 * 排序枚举
 *
 * @author
 * @create 2017-09-01 11:07
 **/
public class SortColumn {
    private static final long serialVersionUID = 1L;

    // 排序字段
    private String columnName;

    // 排序:asc或desc
    private SortMode sortMode;

    public SortColumn(String columnName, SortMode sortMode) {
        this.columnName = columnName;
        this.sortMode = sortMode;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public SortMode getSortMode() {
        return sortMode;
    }

}
