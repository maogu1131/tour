package com.songyang.tour.interceptor;/**
 * Created by lenovo on 2017/12/7.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.component.SessionServiceComponent;
import com.songyang.tour.components.UserLoginComponent;
import com.songyang.tour.constants.ModelStatusCodeConstants;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.utils.JSessionIdUtils;
import com.songyang.tour.utils.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户登录拦截器
 *
 * @author
 * @create 2017-12-07 11:06
 **/
public class UserSessionInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(UserSessionInterceptor.class);

    @Resource
    private UserLoginComponent userLoginComponent;

    @Resource
    private SessionServiceComponent sessionServiceComponent;
    /**
     * 不需要授权的uri集合，当needAuthorized为true才生效
     */
    private List<String> doNotNeedAutorizedURIs;

    /**
     * 需要授权的uri集合，当needAuthorized为false才生效
     */
    private List<String> needAutorizedURIs;

    /**
     * 默认所有uri都要授权，当设为false时， 所有uri都不需要授权
     */
    private boolean needAuthorized = true;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取sessionId
        String sessionId = SessionUtil.getSessionId(request);

        // 没sessionId代表没登录
        if (StringUtils.isBlank(sessionId)) {
            return unauthorized(request, response, sessionId);
        }

        // 查询member
        SyUser syUser = null;
        try {
            syUser = userLoginComponent.getMemberByJsessionIdForMobile(sessionId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handlerException(response);
        }

        // 没有查询到
        if (syUser == null) {
            logger.warn("sessionId expired!, sessionid={}", sessionId);
            return unauthorized(request, response, sessionId);
        } else {
            //请求成功后，在刷新session
            sessionServiceComponent.reflashSession(sessionId,SessionUtil.COOKIE_MAX_AGE);
            request.setAttribute(SessionUtil.ATTRIBUTE_MEMBER, syUser);
            request.setAttribute(SessionUtil.ATTRIBUTE_PHONE, syUser.getPhone());
            request.setAttribute(SessionUtil.ATTRIBUTE_USER_ID, syUser.getUserId());
            SessionUtil.setCurrMember(syUser);
            MDC.put("userId", syUser.getUserId());
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 当前请求结束后，清空threadLocal的值，服务器的线程池线程不一定会释放
        SessionUtil.removeCurrMember();
        MDC.clear();
    }

    /**
     * 未授权
     *
     * @param response
     * @return
     */
    private boolean unauthorized(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        if (!needAuthorized) {
            if (request.getRequestURI() != null && needAutorizedURIs != null && needAutorizedURIs.contains(request.getRequestURI())) {
                // 需要授权的连接继续执行
            } else {
                // 不需要授权的连接
                return true;
            }

        }
        if (needAuthorized) {
            if (request.getRequestURI() != null && doNotNeedAutorizedURIs != null && doNotNeedAutorizedURIs.contains(request.getRequestURI())) {
                // 不需要授权的连接
                return true;
            }
        }
        Cookie[] cookies = request.getCookies();
        if (sessionId != null && sessionId.startsWith(JSessionIdUtils.JSESSIONID_PREFIX_WAP)
                && cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (SessionUtil.MOBILE_SESSIONID_COOKIE_NAME.equals(cookie.getName())) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setDomain(SessionUtil.COOKIE_DOMAIN);
                    cookie.setPath(SessionUtil.COOKIE_PATH);
                    response.addCookie(cookie);
                }
            }
        }

        try {
            Model<Void> model = new Model<Void>();
            model.setCode(ModelStatusCodeConstants.UNAUTHORIZED);
            model.setErrorMsg("Unauthorized Request.");
            String jsonString = JSONObject.toJSONString(model);
            response.getWriter().write(jsonString);
            response.getWriter().flush();
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    /**
     * 可能是account的服务没有调用成功,而这并不代表session失效了,所以这个返回一个BussnissExcepiton
     */
    private boolean handlerException(HttpServletResponse response) {
        try {
            Model<Void> model = new Model<Void>();
            model.setCode(ModelStatusCodeConstants.BUSINESS_ERROR);
            model.setErrorMsg("系统异常");
            String jsonString = JSONObject.toJSONString(model);
            response.getWriter().write(jsonString);
            response.getWriter().flush();
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    public void setNeedAuthorized(boolean needAuthorized) {
        this.needAuthorized = needAuthorized;
    }

    public List<String> getDoNotNeedAutorizedURIs() {
        return doNotNeedAutorizedURIs;
    }

    public void setDoNotNeedAutorizedURIs(List<String> doNotNeedAutorizedURIs) {
        this.doNotNeedAutorizedURIs = doNotNeedAutorizedURIs;
    }

    public List<String> getNeedAutorizedURIs() {
        return needAutorizedURIs;
    }

    public void setNeedAutorizedURIs(List<String> needAutorizedURIs) {
        this.needAutorizedURIs = needAutorizedURIs;
    }
}
