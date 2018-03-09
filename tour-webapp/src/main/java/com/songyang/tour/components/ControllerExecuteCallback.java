package com.songyang.tour.components;

import com.songyang.tour.model.Model;/**
 * Created by lenovo on 2017/9/29.
 */

import java.io.IOException;

/**
 * 抽象回调类
 *
 * @author bi.yao
 * @create 2017-09-29 22:49
 **/
public abstract class ControllerExecuteCallback<T> {

    public abstract Model<T> doExecute() throws IOException;
}
