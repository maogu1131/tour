package com.songyang.tour.exception;/**
 * Created by lenovo on 2017/9/29.
 */

import com.songyang.tour.constants.ModelStatusCodeConstants;

/**
 * TOUR统一异常类
 *
 * @author
 * @create 2017-09-29 22:35
 **/
public class TourBizException extends RuntimeException {


    private int code;

    private String message;

    /**
     * 转让系统异常构造方法
     *
     * @param code    错误码
     * @param message 错误消息
     */
    public TourBizException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public TourBizException(String message) {
        super(message);
        this.code = ModelStatusCodeConstants.BUSINESS_ERROR;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

