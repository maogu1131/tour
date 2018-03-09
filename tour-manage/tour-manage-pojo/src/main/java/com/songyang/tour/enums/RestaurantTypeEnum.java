package com.songyang.tour.enums;/**
 * Created by lenovo on 2017/9/23.
 */

/**
 * 餐馆类型枚举
 *
 * 餐馆类型:1-中餐馆、2-西餐馆、3-日韩料理、4-土家菜,99-其他',
 * @author
 * @create 2017-09-23 12:10
 **/
public enum RestaurantTypeEnum {

    CHINESE_REST(1, "中餐厅"), WESTERN_REST(2, "西餐厅"), JapanAndKoreanCuisine(3, "日韩料理"), OTHER(99, "其他");
//    SOIL_REST(4,"土菜馆"),OTHER(99, "其他");

    private int code;

    private String message;

    RestaurantTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static RestaurantTypeEnum getEnumByCode(Integer code) {
        if (null != code) {
            for (RestaurantTypeEnum entity : values()) {
                if (entity.getCode() == code) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        RestaurantTypeEnum noceTypeEnum = getEnumByCode(code);
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
