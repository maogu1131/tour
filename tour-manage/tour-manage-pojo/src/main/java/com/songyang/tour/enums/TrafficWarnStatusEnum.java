package com.songyang.tour.enums;/**
 * Created by lenovo on 2017/9/24.
 */

/**
 * 交通预警Enum
 *1.缓行 2.畅通 3.拥堵
 * @author
 * @create 2017-09-24 16:48
 **/
public enum TrafficWarnStatusEnum {

    AMBLE(1,"缓行"),SMOOTH(2,"畅通"),CONGESTION(3,"拥堵");

    private int code;

    private String message;

    TrafficWarnStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static TrafficWarnStatusEnum getEnumByCode(Integer code) {
        if (null != code) {
            for (TrafficWarnStatusEnum entity : values()) {
                if (entity.getCode() == code) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        TrafficWarnStatusEnum spotTypeEnum = getEnumByCode(code);
        if (spotTypeEnum == null) {
            return "";
        }
        return spotTypeEnum.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
