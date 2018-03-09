package com.songyang.tour.controller.manager.restaurant;/**
 * Created by lenovo on 2017/9/23.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.controller.manager.publicservice.PublicServiceController;
import com.songyang.tour.enums.RestaurantTypeEnum;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyRestaurant;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyRestaurantQuery;
import com.songyang.tour.service.SyRestaurantService;
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
 * 餐馆controller
 *
 * @author
 * @create 2017-09-23 11:07
 **/
@Controller
@RequestMapping(value = "/manage/restaurant")
public class RestaurantController {
    private Logger logger = LoggerFactory.getLogger(PublicServiceController.class);

    @Resource
    private SyRestaurantService syRestaurantService;

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

        // 加载餐馆类型
        loadRestaurantType(modelMap);
        return VmConstans.RESTAURANT_EDIT;
    }

    /**
     * 保存
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyRestaurant syRestaurant) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;
            // 2.校验表单基本参数
            this.checkParam(syRestaurant);

            if (syRestaurant.getId() == null) {
                // TODO 登录用户
                syRestaurant.setCreator("SYSTEM");
                count = syRestaurantService.insert(syRestaurant);
            } else {
                // TODO 登录用户
                syRestaurant.setModifier("SYSTEM");
                count = syRestaurantService.updateById(syRestaurant);
            }

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "保存成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "保存失败");
            }

        } catch (Throwable e) {
            logger.error("RestaurantController#save is exception", e);
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
    public String queryList(SyRestaurantQuery qo, PageQuery query, ModelMap modelMap) {


        // 1、准备参数
        modelMap.addAttribute("qo", qo);
        modelMap.addAttribute("query", query);

        // 加载餐馆类型
        loadRestaurantType(modelMap);
        try {

            if (StringUtils.isBlank(qo.getCnName())) {
                qo.setCnName(null);
            }
            if (StringUtils.isBlank(qo.getAddress())) {
                qo.setAddress(null);
            }

            // 2、查询条数
            Long count = syRestaurantService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyRestaurant> list = syRestaurantService.queryListByParam(qo);
            for (SyRestaurant temp : list) {
                temp.setBannerPicUrlList(CommonUtil.analyzePicUrl(temp.getBannerPicUrl()));
                temp.setPicUrlList(CommonUtil.analyzePicUrl(temp.getPicUrl()));
            }
            modelMap.addAttribute("list", list);
            modelMap.addAttribute("syRestaurant", qo);
        } catch (Throwable e) {
            logger.error("RestaurantController query is exception", e);
        }

        return VmConstans.RESTAURANT_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            // 加载餐馆类型
            loadRestaurantType(model);
            SyRestaurant syRestaurant = syRestaurantService.selectById(id);

            if(null==syRestaurant){
                return VmConstans.RESTAURANT_DETAIL;
            }
            model.addAttribute("picUrlList", CommonUtil.analyzePicUrl(syRestaurant.getPicUrl()));
            model.addAttribute("bannerPicUrlList", CommonUtil.analyzePicUrl(syRestaurant.getBannerPicUrl()));

            model.addAttribute("syRestaurant", syRestaurant);
        } catch (Exception e) {
            logger.error("查询公共服务[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询公共服务[" + id + "]出现异常");
        }

        return VmConstans.RESTAURANT_DETAIL;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            // 加载餐馆类型
            loadRestaurantType(model);
            SyRestaurant syRestaurant = syRestaurantService.selectById(id);

            // 构建 图片地址json字符串
            CommonUtil commonUtil = new CommonUtil();
            syRestaurant.setPicUrlListStr(commonUtil.buildPicUrl(syRestaurant.getPicUrl()));
            syRestaurant.setBannerPicUrlListStr(commonUtil.buildPicUrl(syRestaurant.getBannerPicUrl()));

            model.addAttribute("syRestaurant", syRestaurant);
        } catch (Exception e) {
            logger.error("编辑公共服务[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑公共服务[" + id + "]出现异常");
        }

        return VmConstans.RESTAURANT_EDIT;
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
            SyRestaurant param = new SyRestaurant();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syRestaurantService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "状态更新成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "状态更新失败");
            }
        } catch (Throwable e) {
            logger.error("RestaurantController#update is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "状态更新出现异常");
        }
        return res;
    }


    private void checkParam(SyRestaurant syRestaurant) {
        Assert.notNull(syRestaurant, "syRestaurant 不能为空");
        Assert.hasText(syRestaurant.getCnName(), "中文名字不能为空");
//        Assert.hasText(syRestaurant.getAddress(), "餐馆地址不能为空");

        if(StringUtils.isNotBlank(syRestaurant.getBannerPicUrl())){
            syRestaurant.setBannerPicUrl(syRestaurant.getBannerPicUrl().replace(TourConstants.PIC_URL_PREFIX+ TourConstants.remoteFilePath,""));
        }

        if(StringUtils.isNotBlank(syRestaurant.getPicUrl())){
            syRestaurant.setPicUrl(syRestaurant.getPicUrl().replace(TourConstants.PIC_URL_PREFIX+ TourConstants.remoteFilePath,""));
        }

    }

    private void loadRestaurantType(ModelMap modelMap) {
        List<CodeName> restaurantTypeList = new ArrayList<CodeName>();
        for (RestaurantTypeEnum tempEnum : RestaurantTypeEnum.values()) {
            CodeName cn = new CodeName();
            cn.setCode(String.valueOf(tempEnum.getCode()));
            cn.setName(tempEnum.getMessage());
            restaurantTypeList.add(cn);
        }
        modelMap.addAttribute("restaurantTypeList", restaurantTypeList);
    }
}
