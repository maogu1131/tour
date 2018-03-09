package com.songyang.tour.component;/**
 * Created by lenovo on 2017/12/7.
 */

/**
 * Session服务
 *
 * @author
 * @create 2017-12-07 11:47
 **/
public interface SessionServiceComponent {

    // 手机端 Session 持续时间
    int SESSION_EXPIRED_TIME_IN_SECOND = 3600 * 24;
    // PC端 Session 持续时间
    int SESSION_EXPIRED_TIME_IN_SECOND_FOR_WEB = 60 * 30;
    //原session清除后异步session可持续时长
    int SESSION_EXPIRED_TIME_IN_SECOND_ASYN = 3600;

    /**
     * 保存 Session ID.
     */
    void storeSession(String userId, String sessionId, int second);

    /**
     * 刷新SESSION有效期
     *
     * @param second
     * @param sessionId
     */
    void reflashSession(String sessionId, int second);

    /**
     * 根据sessionId获取userId
     *
     * @param sessionId
     * @return
     */
    String getUserIdBySessionId(String sessionId);

    /**
     * 删除sessionId
     *
     * @param sessionId
     */
    void deleteSessionId(String sessionId);

    /**
     * 删除缓存中某个用户的所有SESSION
     *
     * @param userId
     */
    void clearUserAllSessionInfo(String userId);
}
