package com.songyang.tour.controller.manager.commonconfig;/**
 * Created by lenovo on 2017/9/28.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyCommonConfig;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyCommonConfigQuery;
import com.songyang.tour.service.SyCommonConfigService;
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
 * 公告配置管理controller
 *
 * @author
 * @create 2017-09-28 0:29
 **/
@Controller
@RequestMapping(value = "/manage/commonConfig")
public class CommonConfigController {

    private Logger logger = LoggerFactory.getLogger(CommonConfigController.class);

    @Resource
    private SyCommonConfigService syCommonConfigService;


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


        return VmConstans.COMMON_CONFIG_EDIT;
    }

    /**
     * 保存
     *
     * @param syCommonConfig
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyCommonConfig syCommonConfig) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;
            // 2.校验表单基本参数
            this.checkParam(syCommonConfig);

            if (syCommonConfig.getId() == null) {
                // TODO 登录用户
                syCommonConfig.setCreator("SYSTEM");
                count = syCommonConfigService.insert(syCommonConfig);
            } else {
                count = syCommonConfigService.updateById(syCommonConfig);
            }

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "保存成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "保存失败");
            }

        } catch (Throwable e) {
            logger.error("NoticeController#save is exception", e);
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
    public String queryList(SyCommonConfigQuery qo, PageQuery query, ModelMap model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
        try {

            if (StringUtils.isBlank(qo.getName())) {
                qo.setName(null);
            }
            if (StringUtils.isBlank(qo.getKey())) {
                qo.setKey(null);
            }

            // 2、查询条数
            Long count = syCommonConfigService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyCommonConfig> list = syCommonConfigService.queryListByParam(qo);

            model.addAttribute("list", list);
            model.addAttribute("syCommonConfig", qo);
        } catch (Throwable e) {
            logger.error("CommonConfigController query is exception", e);
        }

        return VmConstans.COMMON_CONFIG_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            SyCommonConfig syCommonConfig = syCommonConfigService.selectById(id);
            model.addAttribute("syCommonConfig", syCommonConfig);
        } catch (Exception e) {
            logger.error("查询通用配置[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询通用配置[" + id + "]出现异常");
        }

        return VmConstans.COMMON_CONFIG_DETAIL ;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            SyCommonConfig syCommonConfig = syCommonConfigService.selectById(id);
            model.addAttribute("syCommonConfig", syCommonConfig);
        } catch (Exception e) {
            logger.error("编辑通用配置[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑公告[" + id + "]出现异常");
        }

        return VmConstans.COMMON_CONFIG_EDIT;
    }

    /**
     * 更新状态
     *
     * @param
     * @RequestMapping("/update") public JSONObject update(@RequestParam(required = true, value = "id") String id,
     * @RequestParam(required = true, value = "status") Integer status) {
     * JSONObject res = new JSONObject();
     * try {
     * SyCommonConfig param = new SyCommonConfig();
     * param.setId(Long.valueOf(id));
     * <p>
     * // TODO 更新者
     * //            param.setModifier();
     * int count = syCommonConfigService.updateById(param);
     * <p>
     * if (count > 0) {
     * res.put(TourConstants.CODE_KEY, "1");
     * res.put(TourConstants.MSG_KEY, "删除成功");
     * } else {
     * res.put(TourConstants.CODE_KEY, "2");
     * res.put(TourConstants.MSG_KEY, "删除失败");
     * }
     * } catch (Throwable e) {
     * logger.error("CommonConfigController#update is exception",e);
     * res.put(TourConstants.CODE_KEY, "2");
     * res.put(TourConstants.MSG_KEY, "删除出现异常");
     * }
     * return res;
     * }
     */

    private void checkParam(SyCommonConfig syCommonConfig) {
        Assert.notNull(syCommonConfig, "syCommonConfig 不能为空");
        Assert.hasText(syCommonConfig.getKey(), "配置key不能为空");
        Assert.hasText(syCommonConfig.getValue(), "配置值不能为空");
    }

}
