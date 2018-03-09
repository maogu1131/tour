package com.songyang.tour.enums;/**
 * Created by lenovo on 2017/9/22.
 */

/**
 * 产品类型枚举
 *
 * @author
 * @create 2017-09-22 0:02
 **/
public enum ProdTypeEnum {


    TRAVEL(1, "旅行"), WENCUANG(2, "文创"), FARMER(3, "农特"), OTHER(99, "其他");

    private int code;

    private String message;

    ProdTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ProdTypeEnum getEnumByCode(Integer code) {
        if (null != code) {
            for (ProdTypeEnum entity : values()) {
                if (entity.getCode() == code) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        ProdTypeEnum noceTypeEnum = getEnumByCode(code);
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
