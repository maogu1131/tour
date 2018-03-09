package com.songyang.tour.utils;/**
 * Created by lenovo on 2017/12/5.
 */


import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * jessionid工具
 *
 * @author
 * @create 2017-12-05 18:59
 **/
public class JSessionIdUtils {

    // jsessionId失效时长 单位：天
    public static final int JSESSIONID_EXPIRED_TIME = 7;

    // refreshToken失效时长，单位：天
    public static final int REFRESH_TOKEN_EXPIRED_TIME = 28;

    public static final String JSESSIONID_PREFIX_MOBILE = "MOBILE_";

    public static final String JSESSIONID_PREFIX_WAP = "WAP_";

    /**
     * 生成32位随机码，加prefix前缀，作为jsessionId
     */
    public static String generateJSessionId(String prefix) {
        String jsessionId = UUID.randomUUID().toString().replace("-", "");
        if (StringUtils.isBlank(prefix))
            return jsessionId;
        return prefix + jsessionId;
    }


    public static void main(String[] args) {
        String jsessionId = generateJSessionId(JSESSIONID_PREFIX_MOBILE);
        System.out.println(jsessionId);
    }
}
