package com.songyang.tour.components;

import com.songyang.tour.model.Model;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * 返回jsonModel 回调方法 , 用于处理返回json的接口
 *
 * @author bi.yao
 * @create 2017-09-29 22:52
 **/
public interface ControllerExecuteTemplate {

    /**
     * 执行callback回调对象的回调方法
     *
     * @param request  请求对象
     * @param callback 回调实现方法
     * @param logger   日志
     * @param errorMsg 错误消息
     * @param <T>      泛型
     * @return 返回json模型对象
     */
    <T> Model<T> execute(HttpServletRequest request, ControllerExecuteCallback<T> callback, Logger logger, String errorMsg);
}
