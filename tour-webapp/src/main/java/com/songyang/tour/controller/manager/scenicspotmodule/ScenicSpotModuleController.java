package com.songyang.tour.controller.manager.scenicspotmodule;/**
 * Created by lenovo on 2017/9/24.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.pojo.SyScenicSpotModule;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyScenicSpotModuleQuery;
import com.songyang.tour.service.SyScenicSpotModuleService;
import com.songyang.tour.utils.CommonUtil;
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
 * 景区模块类别controller
 *
 * @author
 * @create 2017-09-24 23:22
 **/
@Controller
@RequestMapping(value = "/manage/scenicSpotModule")
public class ScenicSpotModuleController {

    private Logger logger = LoggerFactory.getLogger(ScenicSpotModuleController.class);

    @Resource
    private SyScenicSpotModuleService syScenicSpotModuleService;


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

        return VmConstans.SCENIC_SPOT_MODULE_EDIT;
    }

    /**
     * 保存
     *
     * @param syScenicSpotModule
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyScenicSpotModule syScenicSpotModule) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;
            // 2.校验表单基本参数
            this.checkParam(syScenicSpotModule);

            if (syScenicSpotModule.getId() == null) {
                // TODO 登录用户
                syScenicSpotModule.setCreator("SYSTEM");
                count = syScenicSpotModuleService.insert(syScenicSpotModule);
            } else {
                // TODO 登录用户
                syScenicSpotModule.setModifier("SYSTEM");
                count = syScenicSpotModuleService.updateById(syScenicSpotModule);
            }

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "保存成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "保存失败");
            }

        } catch (Throwable e) {
            logger.error("TrafficWarnController#save is exception", e);
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
    public String queryList(SyScenicSpotModuleQuery qo, PageQuery query, ModelMap model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
        try {
            if (StringUtils.isBlank(qo.getTitle())) {
                qo.setTitle(null);
            }

            // 2、查询条数
            Long count = syScenicSpotModuleService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyScenicSpotModule> list = syScenicSpotModuleService.queryListByParam(qo);
            for (SyScenicSpotModule module : list) {
//                module.setPicUrl(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath + module.getPicUrl());
                module.setPicUrl(CommonUtil.analyzeOnePicUrl(module.getPicUrl()));
//                module.setPicUrl(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath + module.getPicUrl());
            }
            model.addAttribute("list", list);
            model.addAttribute("syScenicSpotModule", qo);
        } catch (Throwable e) {
            logger.error("TrafficWarnController query is exception", e);
        }

        return VmConstans.SCENIC_SPOT_MODULE_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Integer id, ModelMap model) {
        try {
            SyScenicSpotModule syScenicSpotModule = syScenicSpotModuleService.selectById(id);
            if (null != syScenicSpotModule) {
//                syScenicSpotModule.setPicUrl(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath + syScenicSpotModule.getPicUrl());
                syScenicSpotModule.setPicUrl(CommonUtil.analyzeOnePicUrl(syScenicSpotModule.getPicUrl()));
            }

            model.addAttribute("syScenicSpotModule", syScenicSpotModule);
        } catch (Exception e) {
            logger.error("查询景区模块[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询景区模块[" + id + "]出现异常");
        }

        return VmConstans.SCENIC_SPOT_MODULE_DETAIL;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Integer id, ModelMap model) {
        try {

            SyScenicSpotModule syScenicSpotModule = syScenicSpotModuleService.selectById(id);
            // 构建 图片地址json字符串
            CommonUtil commonUtil = new CommonUtil();
            syScenicSpotModule.setPicUrlListStr(commonUtil.buildPicUrl(syScenicSpotModule.getPicUrl()));

            model.addAttribute("syScenicSpotModule", syScenicSpotModule);
        } catch (Exception e) {
            logger.error("编辑景区模块[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑景区模块[" + id + "]出现异常");
        }

        return VmConstans.SCENIC_SPOT_MODULE_EDIT;
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
            SyScenicSpotModule param = new SyScenicSpotModule();
            param.setId(Integer.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syScenicSpotModuleService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "状态更新成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "状态更新失败");
            }
        } catch (Throwable e) {
            logger.error("TrafficWarnController#update is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "状态更新出现异常");
        }
        return res;
    }


    private void checkParam(SyScenicSpotModule syScenicSpotModule) {
        Assert.notNull(syScenicSpotModule, "syScenicSpotModule 不能为空");
        Assert.hasText(syScenicSpotModule.getTitle(), "标题不能为空");
        Assert.hasText(syScenicSpotModule.getPicUrl(), "图片地址不能为空");
        if (syScenicSpotModule.getPicUrl().contains("|")) {
            throw new TourBizException("一个景区类型只能配置一张背景图片");
        }


        if(StringUtils.isNotBlank(syScenicSpotModule.getPicUrl())){
            syScenicSpotModule.setPicUrl(syScenicSpotModule.getPicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath,""));
        }

    }

}
