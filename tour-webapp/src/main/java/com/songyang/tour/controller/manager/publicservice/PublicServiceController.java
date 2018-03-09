package com.songyang.tour.controller.manager.publicservice;/**
 * Created by lenovo on 2017/9/22.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyPublicService;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyPublicServiceQuery;
import com.songyang.tour.service.SyPublicServiceService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公共服务Controller
 *
 * @author
 * @create 2017-09-22 23:47
 **/
@Controller
@RequestMapping("/manage/publicService")
public class PublicServiceController {
    private Logger logger = LoggerFactory.getLogger(PublicServiceController.class);

    @Resource
    private SyPublicServiceService syPublicServiceService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 新建
     *
     * @return
     */
    @RequestMapping(value = "/init")
    public String init(ModelMap modelMap) {

        return VmConstans.PUBLICSERVICE_EDIT;
    }

    /**
     * 保存
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyPublicService SyPublicService) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;
            // 2.校验表单基本参数
            this.checkParam(SyPublicService);

            if (SyPublicService.getId() == null) {
                // TODO 登录用户
                SyPublicService.setCreator("SYSTEM");
                count = syPublicServiceService.insert(SyPublicService);
            } else {
                // TODO 登录用户
                SyPublicService.setModifier("SYSTEM");
                count = syPublicServiceService.updateById(SyPublicService);
            }

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "保存成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "保存失败");
            }

        } catch (Throwable e) {
            logger.error("PublicServiceController#save is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, e.getMessage());
        }
        return res;
    }


    /**
     * 查询
     *
     * @param qo    公告
     * @param query 分页
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryList(SyPublicServiceQuery qo, PageQuery query, ModelMap model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
        try {

            if (StringUtils.isBlank(qo.getName())) {
                qo.setName(null);
            }
            if (StringUtils.isBlank(qo.getAddress())) {
                qo.setAddress(null);
            }

            // 2、查询条数
            Long count = syPublicServiceService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyPublicService> list = syPublicServiceService.queryListByParam(qo);

            model.addAttribute("list", list);
            model.addAttribute("syPublicService", qo);
        } catch (Throwable e) {
            logger.error("PublicServiceController query is exception", e);
        }

        return VmConstans.PUBLICSERVICE_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            SyPublicService syPublicService = syPublicServiceService.selectById(id);
            model.addAttribute("syPublicService", syPublicService);
        } catch (Exception e) {
            logger.error("查询公共服务[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询公共服务[" + id + "]出现异常");
        }

        return VmConstans.PUBLICSERVICE_DETAIL;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            SyPublicService syPublicService = syPublicServiceService.selectById(id);
            model.addAttribute("syPublicService", syPublicService);
        } catch (Exception e) {
            logger.error("编辑公共服务[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑公共服务[" + id + "]出现异常");
        }

        return VmConstans.PUBLICSERVICE_EDIT;
    }

    /**
     * 更新状态
     *
     * @param id
     */
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam(required = true, value = "id") String id,
                             @RequestParam(required = true, value = "status") Integer status) {
        JSONObject res = new JSONObject();
        try {
            SyPublicService param = new SyPublicService();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syPublicServiceService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "状态更新成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "状态更新失败");
            }
        } catch (Throwable e) {
            logger.error("NoticeController#update is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "状态更新出现异常");
        }
        return res;
    }


    private void checkParam(SyPublicService SyPublicService) {
        Assert.notNull(SyPublicService, "SyPublicService 不能为空");
        Assert.hasText(SyPublicService.getName(), "公共场所名称不能为空");
//        Assert.hasText(SyPublicService.getAddress(), "公共场所地址不能为空");
    }
}
