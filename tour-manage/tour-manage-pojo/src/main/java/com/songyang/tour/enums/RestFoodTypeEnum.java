package com.songyang.tour.enums;/**
 * Created by lenovo on 2017/9/23.
 */

/**
 * 餐馆菜类型枚举
 * 餐馆类型:1-杭帮菜、2-西餐、3-日韩料理、4-土家菜,5-湘菜，6-川菜，7-徽菜，8-粤菜，9-东北菜，99-其他',
 * @author
 * @create 2017-09-23 14:22
 **/
public enum  RestFoodTypeEnum {

    HANGZHOU_FOOD(1, "杭帮菜"),
    SOIL_REST(2,"土菜馆"), XIANG_FOOD(3,"湘菜"),
    CHUAN_FOOD(4,"川菜"), HUI_FOOD(5,"徽菜"),
    YUE_FOOD(6,"粤菜"),DONGBEI_FOOD(7,"东北菜"),
    WESTERN_REST(21, "西餐"), JAPANANDKOREANCUISINE(31, "日韩料理"),OTHER(99, "其他");

    private int code;

    private String message;

    RestFoodTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static RestFoodTypeEnum getEnumByCode(Integer code) {
        if (null != code) {
            for (RestFoodTypeEnum entity : values()) {
                if (entity.getCode() == code) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        RestFoodTypeEnum noceTypeEnum = getEnumByCode(code);
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
