package com.songyang.tour.enums;/**
 * Created by lenovo on 2017/9/10.
 */

/**
 * 景区类型枚举
 *
 * @author
 * @create 2017-09-10 22:33
 **/
public enum ScenicSpotTypeEnum {

    NATURAL_SCENERY(1,"自然风光"),HISTORICAL_FIGURE(2,"历史人物"),

    MONUMENTS(3,"名胜古迹"),OTHER(99,"其他");

    private int code;

    private String message;

    ScenicSpotTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ScenicSpotTypeEnum getEnumByCode(Integer code) {
        if (null != code) {
            for (ScenicSpotTypeEnum entity : values()) {
                if (entity.getCode() == code) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        ScenicSpotTypeEnum spotTypeEnum = getEnumByCode(code);
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
