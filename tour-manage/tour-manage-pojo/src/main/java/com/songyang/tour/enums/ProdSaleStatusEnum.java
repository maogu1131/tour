package com.songyang.tour.enums;/**
 * Created by lenovo on 2017/9/22.
 */

/**
 * 产品销售状态枚举
 *
 * @author
 * @create 2017-09-22 0:44
 **/
public enum ProdSaleStatusEnum {


    NOT_SELL(-1, "不可售"), FOR_SELL(1, "待售"), SELL_ING(3, "上架"), SELL_OVER(5, "售罄");

    private int code;

    private String message;

    ProdSaleStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ProdSaleStatusEnum getEnumByCode(Integer code) {
        if (null != code) {
            for (ProdSaleStatusEnum entity : values()) {
                if (entity.getCode() == code) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        ProdSaleStatusEnum noceTypeEnum = getEnumByCode(code);
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
