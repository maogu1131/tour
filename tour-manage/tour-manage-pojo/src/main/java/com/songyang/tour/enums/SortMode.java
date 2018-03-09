package com.songyang.tour.enums;/**
 * Created by lenovo on 2017/9/1.
 */

/**
 * 排序类型
 *
 * @author
 * @create 2017-09-01 11:08
 **/
public enum SortMode {

    ASC("ASC"), DESC("DESC");

    private final String mode;

    SortMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

}