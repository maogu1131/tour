package com.songyang.tour.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 古村落类型
 * @author
 * @create 2017-09-01 11:08
 **/
public enum VillageType {

    MOUNTAIN("1","依山"), WATER("2","傍水"),OTHER("99","其他");

    private String code;

    private String name;

    private VillageType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static VillageType getVillageType(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (VillageType entity : values()) {
                if (entity.getCode().equals(code)) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(String code) {
        VillageType villageType = getVillageType(code);
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