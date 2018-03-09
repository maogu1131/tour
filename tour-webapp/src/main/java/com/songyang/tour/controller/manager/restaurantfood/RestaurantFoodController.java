package com.songyang.tour.controller.manager.restaurantfood;/**
 * Created by lenovo on 2017/9/23.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.RestFoodTypeEnum;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyRestaurant;
import com.songyang.tour.pojo.SyRestaurantFood;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyRestaurantFoodQuery;
import com.songyang.tour.query.SyRestaurantQuery;
import com.songyang.tour.service.SyRestaurantFoodService;
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
 * 餐馆菜controller
 *
 * @author
 * @create 2017-09-23 14:18
 **/
@Controller
@RequestMapping(value = "/manage/restaurant/food")
public class RestaurantFoodController {
    private Logger logger = LoggerFactory.getLogger(RestaurantFoodController.class);

    @Resource
    private SyRestaurantFoodService syRestaurantFoodService;

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
        loadRestFoodType(modelMap);

        //加载所属餐馆
        loadRestaurant(modelMap);

        return VmConstans.RESTAURANT_FOOD_EDIT;
    }

    private void loadRestaurant(ModelMap modelMap) {
        SyRestaurantQuery query = new SyRestaurantQuery();
        query.setStatus(TourConstants.STATUS.NORMAL);
        query.setOffset(0);
        query.setRows(5000);
        List<SortColumn> list = new ArrayList<>();
        list.add(new SortColumn("create_time", SortMode.DESC));
        query.setSorts(list);
        List<SyRestaurant> syRestaurantList = syRestaurantService.queryListByParam(query);
        modelMap.addAttribute("syRestaurantList", syRestaurantList);

    }

    /**
     * 保存
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyRestaurantFood syRestaurantFood) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;
            // 2.校验表单基本参数
            this.checkParam(syRestaurantFood);

            if (syRestaurantFood.getId() == null) {
                // TODO 登录用户
                syRestaurantFood.setCreator("SYSTEM");
                count = syRestaurantFoodService.insert(syRestaurantFood);
            } else {
                // TODO 登录用户
                syRestaurantFood.setModifier("SYSTEM");
                count = syRestaurantFoodService.updateById(syRestaurantFood);
            }

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "保存成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "保存失败");
            }

        } catch (Throwable e) {
            logger.error("RestaurantFoodController#save is exception", e);
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
    public String queryList(SyRestaurantFoodQuery qo, PageQuery query, ModelMap modelMap) {


        // 1、准备参数
        modelMap.addAttribute("qo", qo);
        modelMap.addAttribute("query", query);

        // 加载餐馆类型
        loadRestFoodType(modelMap);
        //加载所属餐馆
        loadRestaurant(modelMap);
        try {

            if (StringUtils.isBlank(qo.getName())) {
                qo.setName(null);
            }

            // 2、查询条数
            Long count = syRestaurantFoodService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyRestaurantFood> list = syRestaurantFoodService.queryListByParam(qo);

            for (SyRestaurantFood temp : list) {
                temp.setPicUrlList(CommonUtil.analyzePicUrl(temp.getPicUrl()));
            }

            modelMap.addAttribute("list", list);
            modelMap.addAttribute("syRestaurantFood", qo);
        } catch (Throwable e) {
            logger.error("RestaurantFoodController query is exception", e);
        }

        return VmConstans.RESTAURANT_FOOD_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            // 加载餐馆类型
            loadRestFoodType(model);
            //加载所属餐馆
            loadRestaurant(model);
            SyRestaurantFood syRestaurantFood = syRestaurantFoodService.selectById(id);

            if (null == syRestaurantFood) {
                return VmConstans.RESTAURANT_FOOD_DETAIL;
            }
            model.addAttribute("picUrlList", CommonUtil.analyzePicUrl(syRestaurantFood.getPicUrl()));

            model.addAttribute("syRestaurantFood", syRestaurantFood);
        } catch (Exception e) {
            logger.error("查询餐馆菜[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询餐馆菜[" + id + "]出现异常");
        }
        return VmConstans.RESTAURANT_FOOD_DETAIL;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            // 加载餐馆菜类型
            loadRestFoodType(model);
            loadRestaurant(model);
            SyRestaurantFood syRestaurantFood = syRestaurantFoodService.selectById(id);

            // 构建 图片地址json字符串
            CommonUtil commonUtil = new CommonUtil();
            syRestaurantFood.setPicUrlListStr(commonUtil.buildPicUrl(syRestaurantFood.getPicUrl()));

            model.addAttribute("syRestaurantFood", syRestaurantFood);
        } catch (Exception e) {
            logger.error("编辑餐馆菜[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑餐馆菜[" + id + "]出现异常");
        }

        return VmConstans.RESTAURANT_FOOD_EDIT;
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
            SyRestaurantFood param = new SyRestaurantFood();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syRestaurantFoodService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "状态更新成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "状态更新失败");
            }
        } catch (Throwable e) {
            logger.error("RestaurantFoodController#update is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "状态更新出现异常");
        }
        return res;
    }


    private void checkParam(SyRestaurantFood syRestaurantFood) {
        Assert.notNull(syRestaurantFood, "syRestaurant 不能为空");
        Assert.hasText(syRestaurantFood.getName(), "菜名字不能为空");
//        Assert.notNull(syRestaurantFood.getPrice(), "单价不能为空");
        Assert.notNull(syRestaurantFood.getRestaurantId(), "所属餐馆不能为空");
		 if(StringUtils.isNotBlank(syRestaurantFood.getPicUrl())){
            syRestaurantFood.setPicUrl(syRestaurantFood.getPicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath,""));
        }
    }

    private void loadRestFoodType(ModelMap modelMap) {
        List<CodeName> restFoodTypeList = new ArrayList<CodeName>();
        for (RestFoodTypeEnum tempEnum : RestFoodTypeEnum.values()) {
            CodeName cn = new CodeName();
            cn.setCode(String.valueOf(tempEnum.getCode()));
            cn.setName(tempEnum.getMessage());
            restFoodTypeList.add(cn);
        }
        modelMap.addAttribute("restFoodTypeList", restFoodTypeList);
    }
}
