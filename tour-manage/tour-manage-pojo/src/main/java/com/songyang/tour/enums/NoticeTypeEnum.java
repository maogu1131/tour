package com.songyang.tour.enums;/**
 * Created by lenovo on 2017/9/18.
 */

/**
 * 公告类型枚举
 *
 * @author
 * @create 2017-09-18 0:12
 **/
public enum NoticeTypeEnum {

    LEFT(1, "左边"), RIGHT(2, "右边");

    private int code;

    private String message;

    NoticeTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static NoticeTypeEnum getEnumByCode(Integer code) {
        if (null != code) {
            for (NoticeTypeEnum entity : values()) {
                if (entity.getCode() == code) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        NoticeTypeEnum noceTypeEnum = getEnumByCode(code);
        if (noceTypeEnum == null) {
            return "";
        }
        return noceTypeEnum.getMessage();
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
