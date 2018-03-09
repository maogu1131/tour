package com.songyang.tour.model;/**
 * Created by lenovo on 2017/10/14.
 */

import java.io.Serializable;

/**
 * 基本标签key-value
 *
 * @author
 * @create 2017-10-14 18:08
 **/
public class BaseLabel implements Serializable{

    /***
     * label 标题名称
     */
    private String title;

    /***
     * label 右边名称的值
     */
    private String text;


    public BaseLabel() {

    }

    public BaseLabel(String title, String text) {
        this.title = title;
        this.text = text;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
