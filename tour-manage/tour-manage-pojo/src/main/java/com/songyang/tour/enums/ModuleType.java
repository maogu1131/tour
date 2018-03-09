package com.songyang.tour.enums;

/**
 * 模块类型
 * 大类型:1:美食 2:民宿 3:旅游攻略 4:景区 5:产品 6:民俗 7:古村落 8:定制化路线 9：商家 10：公共场所 11：公共服务
 *
 * @author
 * @create 2017-09-01 11:08
 **/
public enum ModuleType {

    RESTAURANT_FOOD(1, "美食"),//食美食

    HOTEL(2, "民宿"), //住田园

    TRAVEL_STRATEGY(3, "旅游攻略"), //行茶乡

    SCENIC_SPOT(4, "景区"),//游松阳

    PROD(5, "产品"), //购实在

    FOLK(6, "民俗"),  //娱生活

    OLD_VILLAGE(7, "古村落"),  //古村落

    CUSTOM_ROUTE(8, "定制化路线"),

    MERCHANT(9, "商家"),

    PUBLIC_PLACE(10, "公共场所"),

    SERVICE(11, "公共服务");

    private int code;

    private String name;

    ModuleType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ModuleType getVillageType(Integer code) {
        if (null == code) {
            for (ModuleType entity : values()) {
                if (entity.getCode() == code) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        ModuleType villageType = getVillageType(code);
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