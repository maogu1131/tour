package com.songyang.tour.utils;/**
 * Created by lenovo on 2017/12/7.
 */

import com.songyang.tour.pojo.SyUser;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


/**
 * @author
 * @create 2017-12-07 0:35
 **/
public class SessionUtil {

    private static final ThreadLocal<SyUser> MEMBER_SESSION = new ThreadLocal<SyUser>();

    /************************APP端定义的常量******************************/
    public static final String MOBILE_SESSIONID_COOKIE_NAME = "JSESSIONID";
    public static final String COOKIE_DOMAIN = ".sylyfz.cn";
    public static final String COOKIE_PATH = "/";
    public static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 15; //15天


    public static final String ATTRIBUTE_USER_ID = "userId";
    public static final String ATTRIBUTE_PHONE = "phone";
    public static final String ATTRIBUTE_MEMBER = "member";


    /**
     * 从本地线程变量中删除当前存放的值，因为服务器的线程池线程不一定会释放
     */
    public static void removeCurrMember() {
        MEMBER_SESSION.remove();
    }

    /**
     * 设置当前的登录人信息到本地线程变量中
     *
     * @param member
     */
    public static void setCurrMember(SyUser member) {
        MEMBER_SESSION.set(member);
    }

    /**
     * 获取当前登录的member
     *
     * @return
     */
    public static SyUser getCurrMember() {
        return MEMBER_SESSION.get();
    }

    /**
     * 获取当前登录用户的ID
     *
     * @return
     */
    public static String getCurrUserId() {
        if (getCurrMember() == null) {
            return null;
        }
        return getCurrMember().getUserId();
    }

    /**
     * 获取当前登录用户的phone
     */
    public static String getCurrUserPhone() {
        if (getCurrMember() == null) {
            return null;
        }
        return getCurrMember().getPhone();
    }

    /**
     * 获取sessionId
     *
     * @param request
     * @return
     */
    public static String getSessionId(HttpServletRequest request) {
        String jsessionId = null;

        jsessionId = request.getParameter(MOBILE_SESSIONID_COOKIE_NAME);

        if (StringUtils.isNotBlank(jsessionId)) {
            jsessionId = jsessionId.replace(";", "");
            return jsessionId;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            // 从 cookies 中获取.
            for (Cookie cookie : cookies) {
                if (MOBILE_SESSIONID_COOKIE_NAME.equals(cookie.getName())) {
                    jsessionId = cookie.getValue();
                }
            }
        }

        if(StringUtils.isNotBlank(jsessionId)){
            return jsessionId;
        }

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            if (StringUtils.equals(key.toUpperCase(), MOBILE_SESSIONID_COOKIE_NAME)) {
                jsessionId = request.getHeader(key);
                break;
            }
        }
        return jsessionId;
    }


    public static boolean isLogin() {
        return getCurrMember() != null;
    }
}
