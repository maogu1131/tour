package com.songyang.tour.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 星等级
 * @author
 * @create 2017-09-01 11:08
 **/
public enum StarType {

    ONE_STAR("1","一星"), TWO_STAR("2","两星"),THREE_STAR("3","三星"),FOUR_STAR("4","四星"),FIVE_STAR("5","五星"),
    SIX_STAR("6","六星"),SENVEN_STAR("7","七星");
    ;


    private String code;

    private String name;

    private StarType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StarType getVillageType(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (StarType entity : values()) {
                if (entity.getCode().equals(code)) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(String code) {
        StarType villageType = getVillageType(code);
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