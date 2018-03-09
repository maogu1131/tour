package com.songyang.tour.enums;

/**
 * 酒店类型
 * 1-经济型，2-商务型，3-豪华型，4-国际型；99-其他
 *
 * @author
 * @create 2017-09-01 11:08
 **/
public enum HotelSubType {

    ECONOMICAL(1, "经济型"), BUSINESS(2, "商务型"), DELUXE(3, "豪华型"), INTERNATIONAL(4, "国际型"), OTHER(99, "其他");

    private int code;

    private String name;

     HotelSubType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static HotelSubType getVillageType(Integer code) {
        if (null != code) {
            for (HotelSubType entity : values()) {
                if (entity.getCode() == code) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        HotelSubType villageType = getVillageType(code);
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