package com.songyang.tour.controller.manager.admin;/**
 * Created by lenovo on 2017/10/20.
 */

import com.songyang.tour.component.RedisComponent;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.pojo.SyAdminUser;
import com.songyang.tour.query.SyAdminUserQuery;
import com.songyang.tour.service.SyAdminUserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登录controller
 *
 * @author
 * @create 2017-10-20 0:56
 **/
@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    private Logger logger = LoggerFactory.getLogger(AdminLoginController.class);

    @Resource
    private SyAdminUserService syAdminUserService;

    @Resource
    private RedisComponent redisComponent;


    @RequestMapping(value = "/login/init")
    public String login(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "/login/do")
    public String doLogin(HttpServletRequest request, ModelMap modelMap) {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        //1.判断用户名空
        if (StringUtils.isBlank(name)) {
            modelMap.addAttribute("errorMsg", "用户名不能为空");
            return "login";
        }
        //2.密码空
        if (StringUtils.isBlank(password)) {
            modelMap.addAttribute("errorMsg", "密码不能为空");
            return "login";
        }

        try {

            SyAdminUserQuery query = new SyAdminUserQuery();
            query.setOffset(0);
            query.setRows(1);
            query.setUserName(name.trim());
            List<SyAdminUser> syAdminUsers = syAdminUserService.queryListByParam(query);
            if (CollectionUtils.isEmpty(syAdminUsers)) {
                modelMap.addAttribute("errorMsg", "用户信息不存在");
                return "login";
            }
            SyAdminUser user = syAdminUsers.get(0);
            if (TourConstants.ADMIN_STATUS.UNAVAILABLE == user.getStatus()) {
                modelMap.addAttribute("errorMsg", "该用户已被停用");
                return "login";
            }

            String dbPass = user.getPassword();
            String dbPassSub = StringUtils.substring(dbPass, 6, dbPass.length());
            String requestPass = Base64.encodeBase64String(password.getBytes());
            if (!StringUtils.equals(dbPassSub, requestPass)) {
                modelMap.addAttribute("errorMsg", "用户名或密码不正确");
                return "login";
            }

            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
        } catch (Exception e) {
            logger.error("AdminLoginController#login is exception>>>>", e);
            modelMap.addAttribute("errorMsg", "系统错误，请稍后再试");
        }
        return "adminIndex";
    }


    @RequestMapping(value = "/login/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        return "login";
    }
}
