package com.songyang.tour.constants;/**
 * Created by lenovo on 2017/9/29.
 */

/**
 * 接口模型返回状态常量类
 *
 * @author
 * @create 2017-09-29 22:40
 **/
public class ModelStatusCodeConstants {
    //正常业务
    public static final int OK = 0;
    //系统错误
    public static final int SYSTEM_ERROR = 1;
    //业务错误
    public static final int BUSINESS_ERROR = 2;
    //内容错误(暂时未用的)
    public static final int INTERNAL_ERROR = 3;
    //未授权
    public static final int UNAUTHORIZED = 10;
    //被禁止
    public static final int FORBIDDEN = 11;

    public ModelStatusCodeConstants() {
    }

}
