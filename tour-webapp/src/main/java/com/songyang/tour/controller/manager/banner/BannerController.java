package com.songyang.tour.controller.manager.banner;/**
 * Created by lenovo on 2017/10/1.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.BannerBizTypeEnum;
import com.songyang.tour.enums.ModuleType;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.pojo.*;
import com.songyang.tour.query.*;
import com.songyang.tour.service.*;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.CodeName;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Banner Controller
 *
 * @author
 * @create 2017-10-01 23:10
 **/
@Controller
@RequestMapping("/manage/banner")
public class BannerController {

    private Logger logger = LoggerFactory.getLogger(BannerController.class);

    @Resource
    private SyBannerService syBannerService;

    @Resource
    private SyRestaurantFoodService syRestaurantFoodService;

    @Resource
    private SyHotelService syHotelService;

    @Resource
    private SyTravelStrategyService syTravelStrategyService;

    @Resource
    private SyScenicSpotService syScenicSpotService;

    @Resource
    private SyProdService syProdService;

    @Resource
    private SyFolkService syFolkService;

    @Resource
    private SyOldVillageService syOldVillageService;

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
        //初始化业务类型
        loadBizType(modelMap);

        //初始化模块类型
        loadModuleType(modelMap);

        return VmConstans.BANNER_EDIT;
    }


    /**
     * 保存
     *
     * @param syBanner
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyBanner syBanner) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;

            if (syBanner.getBizType() == 2) {
                syBanner.setContent(syBanner.getJumpContent());
                syBanner.setRemark("");
                syBanner.setModuleType(null);
            } else if (syBanner.getBizType() == 1) {
                String scenicContent = syBanner.getScenicContent();
                if (StringUtils.isNotBlank(scenicContent)) {
                    String[] scenicContentArray = scenicContent.split("_", 2);
                    syBanner.setContent(scenicContentArray[0]);
                    syBanner.setRemark(scenicContentArray[1]);
                }
            }

            // 校验表单基本参数
            this.checkParam(syBanner);

            if (syBanner.getId() == null) {
                // TODO 登录用户
                syBanner.setCreator("SYSTEM");
                count = syBannerService.insert(syBanner);
            } else {
                count = syBannerService.updateById(syBanner);
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
     * 保存
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getScenicContent", method = RequestMethod.POST)
    public JSONObject getScenicContent(HttpServletRequest request) {

        JSONObject res = new JSONObject();
        String moduleType = request.getParameter("moduleType");

        try {
            if (StringUtils.isBlank(moduleType)) {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "无数据");
                return res;
            }
            Integer moduleTypeInt = Integer.valueOf(moduleType);
            //时间来不及直接 if else  苦逼
            if (moduleTypeInt == ModuleType.RESTAURANT_FOOD.getCode()) {
                //查询美食
                SyRestaurantFoodQuery query = new SyRestaurantFoodQuery();
                query.setStatus(TourConstants.STATUS.NORMAL);
                query.setOffset(0);
                query.setRows(5000);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                query.setSorts(sortColumns);
                List<SyRestaurantFood> foodList = syRestaurantFoodService.queryListByParam(query);
                if (CollectionUtils.isEmpty(foodList)) {
                    res.put(TourConstants.CODE_KEY, "2");
                    res.put(TourConstants.MSG_KEY, "无数据");
                    return res;
                }
                List<JSONObject> jsonObjectList = new ArrayList<>();
                JSONObject jsonObjectFood = null;
                for (SyRestaurantFood food : foodList) {
                    jsonObjectFood = new JSONObject();
                    jsonObjectFood.put("name", food.getName());
                    jsonObjectFood.put("id", food.getId());
                    jsonObjectList.add(jsonObjectFood);
                }
                res.put(TourConstants.CODE_KEY, "1");
                res.put("jsonObjectList", jsonObjectList);
                return res;
            } else if (moduleTypeInt == ModuleType.HOTEL.getCode()) {
                //查询民宿
                SyHotelQuery query = new SyHotelQuery();
                query.setStatus(TourConstants.STATUS.NORMAL);
                query.setOffset(0);
                query.setRows(5000);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                query.setSorts(sortColumns);
                List<SyHotel> hotelList = syHotelService.queryListByParam(query);
                if (CollectionUtils.isEmpty(hotelList)) {
                    res.put(TourConstants.CODE_KEY, "2");
                    res.put(TourConstants.MSG_KEY, "无数据");
                    return res;
                }
                List<JSONObject> jsonObjectList = new ArrayList<>();
                JSONObject jsonObjectHotel = null;
                for (SyHotel hotel : hotelList) {
                    jsonObjectHotel = new JSONObject();
                    jsonObjectHotel.put("name", hotel.getCnName());
                    jsonObjectHotel.put("id", hotel.getId());
                    jsonObjectList.add(jsonObjectHotel);
                }
                res.put(TourConstants.CODE_KEY, "1");
                res.put("jsonObjectList", jsonObjectList);
                return res;
            } else if (moduleTypeInt == ModuleType.TRAVEL_STRATEGY.getCode()) {
                //查询旅游攻略
                SyTravelStrategyQuery query = new SyTravelStrategyQuery();
                query.setStatus(TourConstants.STATUS.NORMAL);
                query.setOffset(0);
                query.setRows(5000);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                query.setSorts(sortColumns);
                List<SyTravelStrategy> travelStrategyList = syTravelStrategyService.queryListByParam(query);
                if (CollectionUtils.isEmpty(travelStrategyList)) {
                    res.put(TourConstants.CODE_KEY, "2");
                    res.put(TourConstants.MSG_KEY, "无数据");
                    return res;
                }
                List<JSONObject> jsonObjectList = new ArrayList<>();
                JSONObject jsonObjectHotel = null;
                for (SyTravelStrategy travelStrategy : travelStrategyList) {
                    jsonObjectHotel = new JSONObject();
                    jsonObjectHotel.put("name", travelStrategy.getTitle());
                    jsonObjectHotel.put("id", travelStrategy.getId());
                    jsonObjectList.add(jsonObjectHotel);
                }
                res.put(TourConstants.CODE_KEY, "1");
                res.put("jsonObjectList", jsonObjectList);
                return res;
            } else if (moduleTypeInt == ModuleType.SCENIC_SPOT.getCode()) {
                //景区
                SyScenicSpotQuery query = new SyScenicSpotQuery();
                query.setStatus(TourConstants.STATUS.NORMAL);
                query.setOffset(0);
                query.setRows(5000);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                query.setSorts(sortColumns);
                List<SyScenicSpot> syScenicSpotList = syScenicSpotService.queryListByParam(query);
                if (CollectionUtils.isEmpty(syScenicSpotList)) {
                    res.put(TourConstants.CODE_KEY, "2");
                    res.put(TourConstants.MSG_KEY, "无数据");
                    return res;
                }
                List<JSONObject> jsonObjectList = new ArrayList<>();
                JSONObject jsonObjectHotel = null;
                for (SyScenicSpot scenicSpot : syScenicSpotList) {
                    jsonObjectHotel = new JSONObject();
                    jsonObjectHotel.put("name", scenicSpot.getCnName());
                    jsonObjectHotel.put("id", scenicSpot.getId());
                    jsonObjectList.add(jsonObjectHotel);
                }
                res.put(TourConstants.CODE_KEY, "1");
                res.put("jsonObjectList", jsonObjectList);
                return res;
            } else if (moduleTypeInt == ModuleType.PROD.getCode()) {
                //产品
                SyProdQuery query = new SyProdQuery();
                query.setStatus(TourConstants.STATUS.NORMAL);
                query.setOffset(0);
                query.setRows(5000);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                query.setSorts(sortColumns);
                List<SyProd> prodList = syProdService.queryListByParam(query);
                if (CollectionUtils.isEmpty(prodList)) {
                    res.put(TourConstants.CODE_KEY, "2");
                    res.put(TourConstants.MSG_KEY, "无数据");
                    return res;
                }
                List<JSONObject> jsonObjectList = new ArrayList<>();
                JSONObject jsonObjectHotel = null;
                for (SyProd prod : prodList) {
                    jsonObjectHotel = new JSONObject();
                    jsonObjectHotel.put("name", prod.getName());
                    jsonObjectHotel.put("id", prod.getId());
                    jsonObjectList.add(jsonObjectHotel);
                }
                res.put(TourConstants.CODE_KEY, "1");
                res.put("jsonObjectList", jsonObjectList);
                return res;
            } else if (moduleTypeInt == ModuleType.FOLK.getCode()) {
                //民俗
                SyFolkQuery query = new SyFolkQuery();
                query.setOffset(0);
                query.setRows(5000);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                query.setSorts(sortColumns);
                List<SyFolk> folkList = syFolkService.queryListByParam(query);
                if (CollectionUtils.isEmpty(folkList)) {
                    res.put(TourConstants.CODE_KEY, "2");
                    res.put(TourConstants.MSG_KEY, "无数据");
                    return res;
                }
                List<JSONObject> jsonObjectList = new ArrayList<>();
                JSONObject jsonObjectHotel = null;
                for (SyFolk folk : folkList) {
                    jsonObjectHotel = new JSONObject();
                    jsonObjectHotel.put("name", folk.getTitle());
                    jsonObjectHotel.put("id", folk.getId());
                    jsonObjectList.add(jsonObjectHotel);
                }
                res.put(TourConstants.CODE_KEY, "1");
                res.put("jsonObjectList", jsonObjectList);
                return res;
            } else if (moduleTypeInt == ModuleType.OLD_VILLAGE.getCode()) {
                SyOldVillageQuery query = new SyOldVillageQuery();
                query.setStatus(TourConstants.STATUS.NORMAL);
                query.setOffset(0);
                query.setRows(5000);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                query.setSorts(sortColumns);
                List<SyOldVillage> oldVillageList = syOldVillageService.queryListByParam(query);
                if (CollectionUtils.isEmpty(oldVillageList)) {
                    res.put(TourConstants.CODE_KEY, "2");
                    res.put(TourConstants.MSG_KEY, "无数据");
                    return res;
                }
                List<JSONObject> jsonObjectList = new ArrayList<>();
                JSONObject jsonObjectHotel = null;
                for (SyOldVillage oldVillage : oldVillageList) {
                    jsonObjectHotel = new JSONObject();
                    jsonObjectHotel.put("name", oldVillage.getCnName());
                    jsonObjectHotel.put("id", oldVillage.getId());
                    jsonObjectList.add(jsonObjectHotel);
                }
                res.put(TourConstants.CODE_KEY, "1");
                res.put("jsonObjectList", jsonObjectList);
                return res;
            }

            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "无数据");
            return res;

        } catch (Throwable e) {
            logger.error("NoticeController#save is exception", e);
            res.put(TourConstants.CODE_KEY, "3");
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
    public String queryList(SyBannerQuery qo, PageQuery query, ModelMap model) {
        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
        try {
            //初始化业务类型
            loadBizType(model);

            //初始化模块类型
            loadModuleType(model);

            if (StringUtils.isBlank(qo.getDesc())) {
                qo.setDesc(null);
            }
            // 2、查询条数
            Long count = syBannerService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyBanner> list = syBannerService.queryListByParam(qo);

            for (SyBanner banner : list) {
//                banner.setPicUrl(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath + banner.getPicUrl());
                banner.setPicUrl(CommonUtil.analyzeOnePicUrl(banner.getPicUrl()));
            }

            model.addAttribute("list", list);
            model.addAttribute("syBanner", qo);
        } catch (Throwable e) {
            logger.error("BannerController query is exception", e);
        }
        return VmConstans.BANNER_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Integer id, ModelMap model) {
        try {
            //初始化业务类型
            loadBizType(model);

            //初始化模块类型
            loadModuleType(model);

            SyBanner syBanner = syBannerService.selectById(id);
            if (null != syBanner) {
//                syBanner.setPicUrl(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath + syBanner.getPicUrl());
                syBanner.setPicUrl(CommonUtil.analyzeOnePicUrl(syBanner.getPicUrl()));
            }

            model.addAttribute("syBanner", syBanner);
        } catch (Exception e) {
            logger.error("查询banner[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询banner[" + id + "]出现异常");
        }
        return VmConstans.BANNER_DETAIL;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Integer id, ModelMap model) {
        try {
            //初始化业务类型
            loadBizType(model);

            //初始化模块类型
            loadModuleType(model);

            SyBanner syBanner = syBannerService.selectById(id);
            CommonUtil commonUtil = new CommonUtil();
            syBanner.setPicUrlListStr(commonUtil.buildPicUrl(syBanner.getPicUrl()));

            model.addAttribute("syBanner", syBanner);
        } catch (Exception e) {
            logger.error("编辑banner配置[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑banner[" + id + "]出现异常");
        }
        return VmConstans.BANNER_EDIT;
    }


    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam(required = true, value = "id") String id,
                             @RequestParam(required = true, value = "status") Integer status) {
        JSONObject res = new JSONObject();
        try {
            SyBanner param = new SyBanner();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            //TODO 更新者
            // param.setModifier();
            int count = syBannerService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "删除成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "删除失败");
            }
        } catch (Throwable e) {
            logger.error("BannerController#update is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "删除出现异常");
        }
        return res;
    }


    private void checkParam(SyBanner syBanner) {
        Assert.notNull(syBanner, "syBanner 不能为空");
        Assert.hasText(syBanner.getPicUrl(), "图不能为空");
        if (syBanner.getPicUrl().contains("|")) {
            throw new TourBizException("一个景区只能对应一张图片");
        }

        if (syBanner.getBizType() == 1) {
            Assert.hasText(syBanner.getContent(), "景区关联信息不能为空");
        }

        if(org.apache.commons.lang3.StringUtils.isNotBlank(syBanner.getPicUrl())){
            syBanner.setPicUrl(syBanner.getPicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath,""));
        }
    }


    private void loadModuleType(ModelMap modelMap) {

        List<CodeName> moduleTypeList = new ArrayList<CodeName>();
        for (ModuleType tempEnum : ModuleType.values()) {
            if (tempEnum.getCode() <= 7) {
                CodeName cn = new CodeName();
                cn.setCode(String.valueOf(tempEnum.getCode()));
                cn.setName(tempEnum.getName());
                moduleTypeList.add(cn);
            }
        }
        modelMap.addAttribute("moduleTypeList", moduleTypeList);
    }

    private void loadBizType(ModelMap modelMap) {
        List<CodeName> bizTypeList = new ArrayList<CodeName>();
        for (BannerBizTypeEnum tempEnum : BannerBizTypeEnum.values()) {
            CodeName cn = new CodeName();
            cn.setCode(String.valueOf(tempEnum.getCode()));
            cn.setName(tempEnum.getName());
            bizTypeList.add(cn);
        }
        modelMap.addAttribute("bizTypeList", bizTypeList);
    }
}
