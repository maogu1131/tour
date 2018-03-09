package com.songyang.tour.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 旅游类型
 * @author
 * @create 2017-09-01 11:08
 **/
public enum TourType {

    ONE_DAY("1","一日游"), TWO_DAY("2","两日游"),THREE_DAY("3","三日游"),FOUR_DAY("4","四日游"),FIVE_DAY("5","五日游");
//    SIX_DAY("6","六日游"),SEVEN_DAY("7","七日游");

    private String code;

    private String name;

    private TourType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TourType getVillageType(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (TourType entity : values()) {
                if (entity.getCode().equals(code)) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(String code) {
        TourType villageType = getVillageType(code);
        if (villageType == null) {
            return "";
        }
        return villageType.getName();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}