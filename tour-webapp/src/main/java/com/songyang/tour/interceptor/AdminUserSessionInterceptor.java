package com.songyang.tour.interceptor;/**
 * Created by lenovo on 2017/10/20.
 */

import com.songyang.tour.component.RedisComponent;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.pojo.SyAdminUser;
import com.songyang.tour.service.SyAdminUserService;
import com.songyang.tour.utils.AdminUserSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 管理员用户登录拦截器
 *
 * @author
 * @create 2017-10-20 1:32
 **/
public class AdminUserSessionInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(AdminUserSessionInterceptor.class);
    @Resource
    private RedisComponent redisComponent;

    @Resource
    private SyAdminUserService syAdminUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {


        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        if (null==userId) {
            logger.warn("AdminUserSessionInterceptor#preHandle>>>id_is_null>>param" + userId);
            String redirectUrl = "/admin/login/init";
            response.sendRedirect(redirectUrl);
            return false;
        }

        SyAdminUser syAdminUser = syAdminUserService.selectById(userId);
        if (null == syAdminUser)

        {
            logger.warn("AdminUserSessionInterceptor#preHandle>>>syAdminUser_is_null>>param" + userId);
            String redirectUrl = "/admin/login/init";
            response.sendRedirect(redirectUrl);
            return false;
        }

        if (syAdminUser.getStatus() == TourConstants.ADMIN_STATUS.UNAVAILABLE)

        {
            logger.warn("AdminUserSessionInterceptor#preHandle>>>syAdminUser_is_unavailable>>param" + userId);
            String redirectUrl = "/admin/login/init";
            response.sendRedirect(redirectUrl);
            return false;
        }

        request.setAttribute(AdminUserSessionUtil.ATTRIBUTE_MEMBER, syAdminUser);
        request.setAttribute(AdminUserSessionUtil.ATTRIBUTE_USER_ID, syAdminUser.getId());
        AdminUserSessionUtil.setCurrMember(syAdminUser);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
