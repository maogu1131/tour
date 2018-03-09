package com.songyang.tour.controller.manager.admin;/**
 * Created by lenovo on 2017/12/11.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.pojo.SyAdminUser;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyAdminUserQuery;
import com.songyang.tour.service.SyAdminUserService;
import com.songyang.tour.utils.AdminUserSessionUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 管理员controller
 *
 * @author
 * @create 2017-12-11 20:31
 **/
@Controller
@RequestMapping("/manage/admin")
public class AdminManagerController {

    private Logger logger = LoggerFactory.getLogger(AdminManagerController.class);

    @Resource
    private SyAdminUserService syAdminUserService;


    @RequestMapping(value = "/superAdminPassInit", method = {RequestMethod.GET})
    public String superAdminPassInit(HttpServletRequest request, ModelMap modelMap) {
        SyAdminUserQuery query = new SyAdminUserQuery();
        query.setOffset(0);
        query.setRows(1);
        query.setRoleType(TourConstants.ADMIN_ROLE.SUPER_TYPE);
        query.setStatus(TourConstants.ADMIN_STATUS.AVAILABLE);
        List<SyAdminUser> userList = syAdminUserService.queryListByParam(query);
        if (CollectionUtils.isEmpty(userList)) {
            modelMap.put("msg", "超级管理员不存在");
        }
        modelMap.addAttribute("id", userList.get(0).getId());
        modelMap.addAttribute("userName", userList.get(0).getUserName());
        return VmConstans.SUPER_ADMIN;
    }

    @RequestMapping(value = "/superAdminPass", method = {RequestMethod.POST})
    public String superAdminPass(HttpServletRequest request, ModelMap modelMap) {
        String reqOldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String confirmPass = request.getParameter("confirmPass");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("userName", name);
        try {

            //拦截非超级管理员
            SyAdminUser sessionUser = AdminUserSessionUtil.getCurrMember();
            if (sessionUser.getRoleType() == TourConstants.ADMIN_ROLE.NORMAL_TYPE) {
                throw new TourBizException("该用户没有权限修改密码");
            }

            //参数验证
            if (StringUtils.isBlank(id)) {
                throw new TourBizException("参数有误");
            }
            if (StringUtils.isBlank(reqOldPass)) {
                throw new TourBizException("旧密码不能为空");
            }
            if (StringUtils.isBlank(newPass)) {
                throw new TourBizException("新密码不能为空");
            }
            if (StringUtils.isBlank(confirmPass)) {
                throw new TourBizException("确认密码不能为空");
            }
            if (!StringUtils.equals(newPass, confirmPass)) {
                throw new TourBizException("输入两次密码不一致");
            }

            SyAdminUser user = syAdminUserService.selectById(Long.valueOf(id));
            if (null == user) {
                throw new TourBizException("用户不存在");
            }
            //DB密码
            String dbPassSub = StringUtils.substring(user.getPassword(), 6, user.getPassword().length());
            //请求旧密码
            String oldPass = Base64.encodeBase64String(reqOldPass.trim().getBytes());

            //验证旧密码
            if (!StringUtils.equals(dbPassSub, oldPass)) {
                throw new TourBizException("旧密码不正确");
            }

            SyAdminUser updateUser = new SyAdminUser();
            updateUser.setPassword(getEncryptPass(newPass));
            updateUser.setId(Long.valueOf(id));
            syAdminUserService.updateById(updateUser);
            modelMap.addAttribute("msg", "密码更新成功");
        } catch (TourBizException tbe) {
            modelMap.addAttribute("msg", tbe.getMessage());
        } catch (Exception e) {
            modelMap.addAttribute("msg", "系统出错了，请稍后再试");
        }
        return VmConstans.SUPER_ADMIN;
    }

    @RequestMapping(value = "/addAdminInit", method = {RequestMethod.GET})
    public String addAdminInit(HttpServletRequest request, ModelMap modelMap) {

        return VmConstans.EDIT_ADMIN;
    }

    @ResponseBody
    @RequestMapping(value = "/addAdmin", method = {RequestMethod.POST})
    public JSONObject addAdmin(SyAdminUser syAdminUser) {
        JSONObject res = new JSONObject();

        try {
            int count = 0;

            //1.判断用户名空
            if (StringUtils.isBlank(syAdminUser.getUserName())) {
                throw new TourBizException("用户名不能为空");
            }
            //2.密码空
            if (StringUtils.isBlank(syAdminUser.getPassword())) {
                throw new TourBizException("密码不能为空");
            }
            //3.用户名不包含中文
            String regex = "[\\u4e00-\\u9fa5]";
            Matcher m = Pattern.compile(regex).matcher(syAdminUser.getUserName());
            if (m.find()) {
                throw new TourBizException("用户名只支持字母、数字、符合");
            }

            //拦截非超级管理员
            SyAdminUser sessionUser = AdminUserSessionUtil.getCurrMember();
            if (sessionUser.getRoleType() == TourConstants.ADMIN_ROLE.NORMAL_TYPE) {
                throw new TourBizException("该用户没有权限创建管理员用户");
            }


            SyAdminUserQuery query = new SyAdminUserQuery();
            query.setOffset(0);
            query.setRows(1);
            query.setUserName(syAdminUser.getUserName().trim());
            List<SyAdminUser> syAdminUsers = syAdminUserService.queryListByParam(query);
            if (CollectionUtils.isNotEmpty(syAdminUsers)) {
                throw new TourBizException("该用户名已存在");
            }

            //4.加密密码保存
            syAdminUser.setPassword(getEncryptPass(syAdminUser.getPassword()));
            //5.拦截非超级管理员用户

            syAdminUser.setRoleType(TourConstants.ADMIN_ROLE.NORMAL_TYPE);
            if (syAdminUser.getId() == null) {
                count = syAdminUserService.insert(syAdminUser);
            } else {
                count = syAdminUserService.updateById(syAdminUser);
            }

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "保存成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "保存失败");
            }
        } catch (Throwable e) {
            logger.error("AdminManagerController#addAdmin is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, e.getMessage());
        }
        return res;
    }


    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public String query(@RequestParam(value = "msg", defaultValue = "") String msg, SyAdminUserQuery qo, PageQuery query, ModelMap model) {

        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);

        try {
            if (qo.getStatus() != null && qo.getStatus() == 0) {
                qo.setStatus(null);
            }

            if (StringUtils.isBlank(qo.getUserName())) {
                qo.setUserName(null);
            }

            // 2、查询条数
            Long count = syAdminUserService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue() - 1);
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyAdminUser> list = syAdminUserService.queryListByParam(qo);
            Iterator<SyAdminUser> it = list.iterator();
            while (it.hasNext()) {
                SyAdminUser user = it.next();
                if (user.getRoleType() == TourConstants.ADMIN_ROLE.SUPER_TYPE) {
                    it.remove();
                }
            }
            model.addAttribute("list", list);
            model.addAttribute("syAdminUser", qo);
            if (StringUtils.equals(msg, "1")) {
                model.addAttribute("errorMsg", "该用户没有权限编辑管理员用户");
            }
        } catch (Exception e) {
            logger.error("AdminManagerController#query is exception", e);
        }
        return VmConstans.MNG_ADMIN;
    }


    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {

            SyAdminUser syAdminUser = syAdminUserService.selectById(id);

            SyAdminUser sessionUser = AdminUserSessionUtil.getCurrMember();
            if (sessionUser.getRoleType() == TourConstants.ADMIN_ROLE.NORMAL_TYPE) {
                return "redirect:query?msg=1";
            }

            String dbPassSub = StringUtils.substring(syAdminUser.getPassword(), 6, syAdminUser.getPassword().length());
            byte[] decodePass = Base64.decodeBase64(dbPassSub);

            syAdminUser.setPassword(new String(decodePass, "ISO-8859-1"));
            model.addAttribute("syAdminUser", syAdminUser);
        } catch (Exception e) {
            logger.error("编辑syAdminUser配置[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑syAdminUser[" + id + "]出现异常");
        }
        return VmConstans.EDIT_ADMIN;
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam(required = true, value = "id") String id,
                             @RequestParam(required = true, value = "status") Integer status) {
        JSONObject res = new JSONObject();
        try {
            SyAdminUser param = new SyAdminUser();
            param.setId(Long.valueOf(id));
            param.setStatus(status);
            SyAdminUser sessionUser = AdminUserSessionUtil.getCurrMember();
            if (sessionUser.getRoleType() == TourConstants.ADMIN_ROLE.NORMAL_TYPE) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "该用户没有权限编辑管理员用户");
                return res;
            }

            int count = syAdminUserService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "删除成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "删除失败");
            }
        } catch (Throwable e) {
            logger.error("AdminManageController#update is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "删除出现异常");
        }
        return res;
    }


    private String getEncryptPass(String newPass) {
        //BASE64加密 前缀随机六位
        String prefix = "";
        for (int i = 0; i < 6; i++) {
            int ranNum = RandomUtils.nextInt(24);
            prefix += TourConstants.RANDOM_CHAR[ranNum];
        }
        //新密码
        return prefix + Base64.encodeBase64String(newPass.getBytes()).trim();
    }
}
