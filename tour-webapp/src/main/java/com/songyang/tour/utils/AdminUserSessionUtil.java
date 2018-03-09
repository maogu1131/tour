package com.songyang.tour.utils;/**
 * Created by lenovo on 2017/12/13.
 */

import com.songyang.tour.pojo.SyAdminUser;

/**
 * 管理员用户session工具
 *
 * @author
 * @create 2017-12-13 0:31
 **/
public class AdminUserSessionUtil {
    private static final ThreadLocal<SyAdminUser> ADMIN_USER_SESSION = new ThreadLocal<SyAdminUser>();

    public static final String ATTRIBUTE_USER_ID = "userId";
    public static final String ATTRIBUTE_PHONE = "phone";
    public static final String ATTRIBUTE_MEMBER = "member";


    /**
     * 从本地线程变量中删除当前存放的值，因为服务器的线程池线程不一定会释放
     */
    public static void removeCurrMember() {
        ADMIN_USER_SESSION.remove();
    }

    /**
     * 设置当前的登录人信息到本地线程变量中
     *
     * @param member
     */
    public static void setCurrMember(SyAdminUser member) {
        ADMIN_USER_SESSION.set(member);
    }

    /**
     * 获取当前登录的member
     *
     * @return
     */
    public static SyAdminUser getCurrMember() {
        return ADMIN_USER_SESSION.get();
    }

    /**
     * 获取当前登录用户的ID
     *
     * @return
     */
    public static String getCurrUserName() {
        if (getCurrMember() == null) {
            return null;
        }
        return getCurrMember().getUserName();
    }

    /**
     * 获取当前登录用户的phone
     */
    public static Long getCurrUserId() {
        if (getCurrMember() == null) {
            return null;
        }
        return getCurrMember().getId();
    }


    public static boolean isLogin() {
        return getCurrMember() != null;
    }
}
