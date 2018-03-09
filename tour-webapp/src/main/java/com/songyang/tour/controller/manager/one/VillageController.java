package com.songyang.tour.controller.manager.one;

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyOldVillage;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyOldVillageQuery;
import com.songyang.tour.service.SyOldVillageService;
import com.songyang.tour.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 古村落页面管理
 */
@Controller
@RequestMapping("/manage/village")
public class VillageController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(VillageController.class);

    @Autowired
    private SyOldVillageService syOldVillageService;

    /**
     * 新建
     *
     * @return
     */
    @RequestMapping(value = "/init")
    public String init(Model model) {

        // 加载古村落类型
//        loadVillageType(model);

        return VmConstans.VILLAGE_EDIT;
    }

    /**
     * 保存古村落
     *
     * @param syOldVillage 古村落
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(SyOldVillage syOldVillage) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;

            // 2.校验表单基本参数
            this.checkParam(syOldVillage);


            if (syOldVillage.getId() == null) {
                // TODO 登录用户
                syOldVillage.setCreator("SYSTEM");
                count = syOldVillageService.insert(syOldVillage);
            } else {
                // TODO 登录用户
                syOldVillage.setModifier("SYSTEM");
                count = syOldVillageService.updateById(syOldVillage);
            }

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "保存成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "保存失败");
            }

        } catch (Throwable e) {
            LOG.error("save is exception", e);
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
    public String queryList(SyOldVillageQuery qo, PageQuery query, Model model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);

        try {
            // 加载古村落类型
//            loadVillageType(model);

            if (StringUtils.isBlank(qo.getCnName())) {
                qo.setCnName(null);
            }

            // 2、查询条数
            Long count = syOldVillageService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyOldVillage> list = syOldVillageService.queryListByParam(qo);

            for (SyOldVillage temp : list) {
                temp.setSpecialPicUrlList(CommonUtil.analyzePicUrl(temp.getSpecialPicUrl()));
                temp.setPicUrlList(CommonUtil.analyzePicUrl(temp.getPicUrl()));
                temp.setHistoryPicUrlList(CommonUtil.analyzePicUrl(temp.getHistoryPicUrl()));
            }
            model.addAttribute("list", list);
            model.addAttribute("village", qo);
        } catch (Throwable e) {
            LOG.error("queryList is exception", e);
        }

        return VmConstans.VILLAGE_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
//            // 加载古村落类型
//            loadVillageType(model);
            SyOldVillage village = syOldVillageService.selectById(id);

            if (null == village) {
                return VmConstans.VILLAGE_DETAIL;
            }
            model.addAttribute("village", village);

            model.addAttribute("picUrlList", CommonUtil.analyzePicUrl(village.getPicUrl()));
            model.addAttribute("historyPicUrlList", CommonUtil.analyzePicUrl(village.getHistoryPicUrl()));
            model.addAttribute("specialPicUrlList", CommonUtil.analyzePicUrl(village.getSpecialPicUrl()));
            model.addAttribute("folkActPicUrlList", CommonUtil.analyzePicUrl(village.getFolkActPicUrl()));

        } catch (Exception e) {
            LOG.error("查询古村落[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询古村落[" + id + "]出现异常");
        }

        return VmConstans.VILLAGE_DETAIL;
    }


    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
//            // 加载古村落类型
//            loadVillageType(model);
            SyOldVillage village = syOldVillageService.selectById(id);

            // 构建 图片地址json字符串
            CommonUtil commonUtil = new CommonUtil();
            village.setPicUrlListStr(commonUtil.buildPicUrl(village.getPicUrl()));
            village.setSpecialPicUrlListStr(commonUtil.buildPicUrl(village.getSpecialPicUrl()));
            village.setHistoryPicUrlListStr(commonUtil.buildPicUrl(village.getHistoryPicUrl()));
            village.setFolkActPicUrlListStr(commonUtil.buildPicUrl(village.getFolkActPicUrl()));


            model.addAttribute("village", village);
        } catch (Exception e) {
            LOG.error("查询古村落[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询古村落[" + id + "]出现异常");
        }

        return VmConstans.VILLAGE_EDIT;
    }


    /**
     * 更新古村落状态
     *
     * @param id
     */
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam(required = true, value = "id") String id,
                             @RequestParam(required = true, value = "status") Integer status) {
        JSONObject res = new JSONObject();
        try {
            SyOldVillage param = new SyOldVillage();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syOldVillageService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "状态更新成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "状态更新失败");
            }

        } catch (Throwable e) {
            LOG.error("del exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "状态更新出现异常");
        }

        return res;
    }


    /**
     * 古村落基本参数校验
     *
     * @param syOldVillage 古村落
     */
    private void checkParam(SyOldVillage syOldVillage) {

        Assert.notNull(syOldVillage, "syOldVillage 不能为空");
        Assert.hasText(syOldVillage.getCnName(), "中文名称不能为空");
//        Assert.hasText(syOldVillage.getAddress(), "地址不能为空");


        if (StringUtils.isNotBlank(syOldVillage.getPicUrl())) {
            syOldVillage.setPicUrl(syOldVillage.getPicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath, ""));
        }

        if (StringUtils.isNotBlank(syOldVillage.getSpecialPicUrl())) {
            syOldVillage.setSpecialPicUrl(syOldVillage.getSpecialPicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath, ""));
        }

        if (StringUtils.isNotBlank(syOldVillage.getHistoryPicUrl())) {
            syOldVillage.setHistoryPicUrl(syOldVillage.getHistoryPicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath, ""));
        }

        if (StringUtils.isNotBlank(syOldVillage.getFolkActPicUrl())) {
            syOldVillage.setFolkActPicUrl(syOldVillage.getFolkActPicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath, ""));
        }

    }

//    /**
//     * 加载古村落类型
//     * @param model
//     */
//    private void loadVillageType(Model model) {
//
//        List<CodeName> villageTypeList = new ArrayList<CodeName>();
//        CodeName codeName1 = new CodeName();
//        codeName1.setCode(VillageType.MOUNTAIN.getCode());
//        codeName1.setName(VillageType.MOUNTAIN.getName());
//        villageTypeList.add(codeName1);
//
//        CodeName codeName2 = new CodeName();
//        codeName2.setCode(VillageType.WATER.getCode());
//        codeName2.setName(VillageType.WATER.getName());
//        villageTypeList.add(codeName2);
//
//        model.addAttribute("villageTypeList",villageTypeList);
//
//
//    }

}
