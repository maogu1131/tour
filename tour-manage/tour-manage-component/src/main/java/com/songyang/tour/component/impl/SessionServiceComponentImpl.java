package com.songyang.tour.component.impl;/**
 * Created by lenovo on 2017/8/27.
 */

import com.songyang.tour.component.RedisComponent;
import com.songyang.tour.component.SessionServiceComponent;
import com.songyang.tour.constants.RedisKeyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @create 2017-08-27 15:59
 **/
@Service
public class SessionServiceComponentImpl implements SessionServiceComponent {


    private static final Logger LOG = LoggerFactory.getLogger(SessionServiceComponentImpl.class);

    @Resource
    private RedisComponent redisComponent;

    /**
     * 存储SESSION
     */
    public void storeSession(String userId, String sessionId, int second) {
        // sessionId下关联userId
        redisComponent.set(getJSessionIdKey(sessionId), userId, second);

        /** String zrangKey = getUserIdForSession(userId);

         // 新数据SE= 改为 UE= USER_SESSION的意思
         redisService.zadd(zrangKey, 0, sessionId);

         // 清除过期SESSION
         List<String> userAllSession = redisService.zrange(zrangKey, 0, -1, String.class);
         if (userAllSession != null && userAllSession.size() != 0) {
         for (String session : userAllSession) {
         String existUserId = getUserIdBySessionId(session);
         if (existUserId == null) {
         redisService.zremrange(zrangKey, "\"" + session + "\"");
         }
         }
         if (userAllSession.size() >= 50) {
         LOG.warn("登录次数过多 USERID : " + userId);
         }
         }**/
    }

    // 重新设置SESSION的生存时间
    public void reflashSession(String sessionId, int second) {
        redisComponent.expire(getJSessionIdKey(sessionId), second);
    }

    @Override
    public String getUserIdBySessionId(String sessionId) {
        return redisComponent.get(getJSessionIdKey(sessionId), String.class);
    }

    @Override
    public void deleteSessionId(String sessionId) {
        redisComponent.del(getJSessionIdKey(sessionId));
    }


    private String getJSessionIdKey(String jsessionId) {
        return RedisKeyConstants.JSESSIONID_KEY + jsessionId + ";";
    }

    private String getUserIdForSession(String userId) {
        return "UE=" + userId;
    }

    /**
     * 锁定用户：清除用户所有登录信息
     */
    @Override
    public void clearUserAllSessionInfo(String userId) {
        // 清除过期SESSION
        String zrangKey = getUserIdForSession(userId);

        List<String> userAllSession = redisComponent.zrange(zrangKey, 0, -1, String.class);
        if (userAllSession != null && userAllSession.size() != 0) {
            for (String session : userAllSession) {
                deleteSessionId(session);
            }
            redisComponent.del(zrangKey);
        }

        // 设备中心session清除
        //  memberDeviceService.offLineAllDevice(userId, "您已被强制下线，请重新登录！");
    }
}
