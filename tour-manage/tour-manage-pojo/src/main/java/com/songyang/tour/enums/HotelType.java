package com.songyang.tour.enums;/**
 * Created by lenovo on 2017/10/13.
 */

/**
 * 民宿主类型
 *
 * @author
 * @create 2017-10-13 14:18
 **/
public enum HotelType {

    HOTEL(1, "纯酒店"), HOMESTAY(2, "民宿"), MIX(3, "酒店/民宿");

    private int code;

    private String name;

    HotelType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static HotelType getVillageType(Integer code) {
        if (null != code) {
            for (HotelType entity : values()) {
                if (entity.getCode() == code) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        HotelType villageType = getVillageType(code);
        if (villageType == null) {
            return "";
        }
        return villageType.getName();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
