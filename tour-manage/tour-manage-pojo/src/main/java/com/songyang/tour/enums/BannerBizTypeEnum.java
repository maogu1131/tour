package com.songyang.tour.enums;/**
 * Created by lenovo on 2017/10/16.
 */

/**
 * banner业务类型枚举
 *
 * @author
 * @create 2017-10-16 17:26
 **/
public enum BannerBizTypeEnum {

    JUMP_URL_TYPE(1, "关联景区信息"),

    LINK_INSIDE_SYS(2, "跳转静态链接");

    private int code;

    private String name;

    BannerBizTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static BannerBizTypeEnum getBizTypeEnum(Integer code) {
        if (null == code) {
            for (BannerBizTypeEnum entity : values()) {
                if (entity.getCode() == code) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        BannerBizTypeEnum villageType = getBizTypeEnum(code);
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
