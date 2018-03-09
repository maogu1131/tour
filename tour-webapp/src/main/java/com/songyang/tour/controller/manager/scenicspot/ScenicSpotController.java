package com.songyang.tour.controller.manager.scenicspot;/**
 * Created by lenovo on 2017/9/10.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyScenicSpot;
import com.songyang.tour.pojo.SyScenicSpotModule;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyScenicSpotModuleQuery;
import com.songyang.tour.query.SyScenicSpotQuery;
import com.songyang.tour.service.SyScenicSpotModuleService;
import com.songyang.tour.service.SyScenicSpotService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.CodeName;
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
 * 景区业务controller
 *
 * @author
 * @create 2017-09-10 22:29
 **/
@Controller
@RequestMapping(value = "/manage/scenicSpot")
public class ScenicSpotController {

    private Logger logger = LoggerFactory.getLogger(ScenicSpotController.class);

    @Resource
    private SyScenicSpotService syScenicSpotService;

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
        loadScenicSpotType(modelMap);
        return VmConstans.SCENICSPOT_EDIT;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyScenicSpot syScenicSpot) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;

            // 2.校验表单基本参数
            this.checkParam(syScenicSpot);


            if (syScenicSpot.getId() == null) {
                // TODO 登录用户
                syScenicSpot.setCreator("SYSTEM");
                count = syScenicSpotService.insert(syScenicSpot);
            } else {
                // TODO 登录用户
                syScenicSpot.setModifier("SYSTEM");
                count = syScenicSpotService.updateById(syScenicSpot);
            }

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "保存成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "保存失败");
            }

        } catch (Throwable e) {
            logger.error("ScenicSpotController#save is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, e.getMessage());
        }
        return res;
    }


    /**
     * 查询
     *
     * @param qo    古村落
     * @param query 分页
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryList(SyScenicSpotQuery qo, PageQuery query, ModelMap model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);

        try {
            // 加载古村落类型
            loadScenicSpotType(model);

            if (StringUtils.isBlank(qo.getCnName())) {
                qo.setCnName(null);
            }

            if (StringUtils.isBlank(qo.getAddress())) {
                qo.setAddress(null);
            }
            // 2、查询条数
            Long count = syScenicSpotService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyScenicSpot> list = syScenicSpotService.queryListByParam(qo);

            for (SyScenicSpot temp : list) {
                temp.setSpecialPicUrlList(CommonUtil.analyzePicUrl(temp.getSpecialPicUrl()));
                temp.setPicUrlList(CommonUtil.analyzePicUrl(temp.getPicUrl()));
                temp.setWechatPicUrlList(CommonUtil.analyzePicUrl(temp.getWechatPicUrl()));
            }


            model.addAttribute("list", list);
            model.addAttribute("syScenicSpot", qo);
        } catch (Throwable e) {
            logger.error("ScenicSpotController query is exception", e);
        }

        return VmConstans.SCENICSPOT_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            // 加载古村落类型
            loadScenicSpotType(model);
            SyScenicSpot syScenicSpot = syScenicSpotService.selectById(id);

            if(null==syScenicSpot){
                return VmConstans.SCENICSPOT_DETAIL;
            }
            model.addAttribute("picUrlList", CommonUtil.analyzePicUrl(syScenicSpot.getPicUrl()));
            model.addAttribute("specialPicUrlList", CommonUtil.analyzePicUrl(syScenicSpot.getSpecialPicUrl()));
            model.addAttribute("wechatPicUrlList", CommonUtil.analyzePicUrl(syScenicSpot.getWechatPicUrl()));

            model.addAttribute("syScenicSpot", syScenicSpot);
        } catch (Exception e) {
            logger.error("查询景区[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询景区[" + id + "]出现异常");
        }

        return VmConstans.SCENICSPOT_DETAIL;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            // 加载古村落类型
            loadScenicSpotType(model);
            SyScenicSpot syScenicSpot = syScenicSpotService.selectById(id);

            // 构建 图片地址json字符串
            CommonUtil commonUtil = new CommonUtil();
            syScenicSpot.setPicUrlListStr(commonUtil.buildPicUrl(syScenicSpot.getPicUrl()));
            syScenicSpot.setSpecialPicUrlListStr(commonUtil.buildPicUrl(syScenicSpot.getSpecialPicUrl()));
            syScenicSpot.setWechatPicUrlListStr(commonUtil.buildPicUrl(syScenicSpot.getWechatPicUrl()));

            model.addAttribute("syScenicSpot", syScenicSpot);
        } catch (Exception e) {
            logger.error("编辑景区[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑景区[" + id + "]出现异常");
        }

        return VmConstans.SCENICSPOT_EDIT;
    }

    /**
     * 更新古村落状态/门票数量
     * @param
     */
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestBody SyScenicSpot param) {
        JSONObject res = new JSONObject();
        try {

            // TODO 更新者
            param.setModifier("SYSTEM");
            String msg = "状态更新";
            if(param.getTicketTotalNum() != null){
                msg = "修改门票数量";
            }
            int count = syScenicSpotService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, msg+"成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, msg+"失败");
            }
        } catch (Throwable e) {
            logger.error("ScenicSpotController#update is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "更新出现异常");
        }
        return res;
    }


    private void checkParam(SyScenicSpot syScenicSpot) {
        Assert.notNull(syScenicSpot, "syScenicSpot 不能为空");
        Assert.hasText(syScenicSpot.getCnName(), "中文名称不能为空");
        Assert.notNull(syScenicSpot.getLongitude(), "经度不能为空");
        Assert.notNull(syScenicSpot.getLatitude(), "纬度不能为空");

        if(StringUtils.isNotBlank(syScenicSpot.getPicUrl())){
            syScenicSpot.setPicUrl(syScenicSpot.getPicUrl().replace(TourConstants.PIC_URL_PREFIX+TourConstants.remoteFilePath,""));
        }

        if(StringUtils.isNotBlank(syScenicSpot.getSpecialPicUrl())){
            syScenicSpot.setSpecialPicUrl(syScenicSpot.getSpecialPicUrl().replace(TourConstants.PIC_URL_PREFIX+TourConstants.remoteFilePath,""));
        }
        if(StringUtils.isNotBlank(syScenicSpot.getWechatPicUrl())){
            syScenicSpot.setWechatPicUrl(syScenicSpot.getWechatPicUrl().replace(TourConstants.PIC_URL_PREFIX+TourConstants.remoteFilePath,""));
        }



    }

    private void loadScenicSpotType(ModelMap modelMap) {
        SyScenicSpotModuleQuery query = new SyScenicSpotModuleQuery();
        query.setOffset(0);
        query.setRows(50);
        query.setStatus(TourConstants.STATUS.NORMAL);

        List<SyScenicSpotModule> modules = syScenicSpotModuleService.queryListByParam(query);
        List<CodeName> scenicSpotTypeList = new ArrayList<CodeName>();
        for (SyScenicSpotModule spotModule : modules) {
            if (spotModule.getType() == 1) {
                CodeName cn = new CodeName();
                cn.setCode(String.valueOf(spotModule.getId()));
                cn.setName(spotModule.getTitle());
                scenicSpotTypeList.add(cn);
            }
        }
        modelMap.addAttribute("scenicSpotTypeList", scenicSpotTypeList);
    }

}
