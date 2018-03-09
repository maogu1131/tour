package com.songyang.tour.controller.manager.travelstrategy;/**
 * Created by lenovo on 2017/9/24.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyTravelStrategy;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyTravelStrategyQuery;
import com.songyang.tour.service.SyTravelStrategyService;
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
 * 旅游攻略controller
 *
 * @author
 * @create 2017-09-24 22:04
 **/
@Controller
@RequestMapping(value = "/manage/travelStrategy")
public class TravelStrategyController {
    private Logger logger = LoggerFactory.getLogger(TravelStrategyController.class);

    @Resource
    private SyTravelStrategyService syTravelStrategyService;


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

        return VmConstans.TRAVEL_STRATEGY_EDIT;
    }

    /**
     * 保存
     *
     * @param syTravelStrategy
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyTravelStrategy syTravelStrategy) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;
            // 2.校验表单基本参数
            this.checkParam(syTravelStrategy);

            if (syTravelStrategy.getId() == null) {
                // TODO 登录用户
                syTravelStrategy.setCreator("SYSTEM");
                count = syTravelStrategyService.insert(syTravelStrategy);
            } else {
                // TODO 登录用户
                syTravelStrategy.setModifier("SYSTEM");
                count = syTravelStrategyService.updateById(syTravelStrategy);
            }

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "保存成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "保存失败");
            }

        } catch (Throwable e) {
            logger.error("TravelStrategyController#save is exception", e);
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
    public String queryList(SyTravelStrategyQuery qo, PageQuery query, ModelMap model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
//        loadTrafficWarnStatus(model);
        try {
            if (StringUtils.isBlank(qo.getTitle())) {
                qo.setTitle(null);
            }

            // 2、查询条数
            Long count = syTravelStrategyService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyTravelStrategy> list = syTravelStrategyService.queryListByParam(qo);
            for (SyTravelStrategy temp : list) {
                temp.setPicUrlList(CommonUtil.analyzePicUrl(temp.getPicUrl()));
            }
            model.addAttribute("list", list);
            model.addAttribute("syTravelStrategy", qo);
        } catch (Throwable e) {
            logger.error("TravelStrategyController query is exception", e);
        }

        return VmConstans.TRAVEL_STRATEGY_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            SyTravelStrategy syTravelStrategy = syTravelStrategyService.selectById(id);

            if(null==syTravelStrategy){
                return VmConstans.TRAVEL_STRATEGY_DETAIL;
            }
            model.addAttribute("picUrlList", CommonUtil.analyzePicUrl(syTravelStrategy.getPicUrl()));
            model.addAttribute("syTravelStrategy", syTravelStrategy);
        } catch (Exception e) {
            logger.error("查询旅游攻略[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询旅游攻略[" + id + "]出现异常");
        }

        return VmConstans.TRAVEL_STRATEGY_DETAIL;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
//            loadTrafficWarnStatus(model);
            SyTravelStrategy syTravelStrategy = syTravelStrategyService.selectById(id);

            // 构建 图片地址json字符串
            CommonUtil commonUtil = new CommonUtil();
            syTravelStrategy.setPicUrlListStr(commonUtil.buildPicUrl(syTravelStrategy.getPicUrl()));

            model.addAttribute("syTravelStrategy", syTravelStrategy);
        } catch (Exception e) {
            logger.error("编辑旅游攻略[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑旅游攻略[" + id + "]出现异常");
        }

        return VmConstans.TRAVEL_STRATEGY_EDIT;
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
            SyTravelStrategy param = new SyTravelStrategy();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syTravelStrategyService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "状态更新成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "状态更新失败");
            }
        } catch (Throwable e) {
            logger.error("TravelStrategyController#update is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "状态更新出现异常");
        }
        return res;
    }


    private void checkParam(SyTravelStrategy syTravelStrategy) {
        Assert.notNull(syTravelStrategy, "syTravelStrategy 不能为空");
        Assert.hasText(syTravelStrategy.getTitle(), "标题不能为空");
//        Assert.hasText(syTravelStrategy.getPicUrl(), "图片地址不能为空");

        if(StringUtils.isNotBlank(syTravelStrategy.getPicUrl())){
            syTravelStrategy.setPicUrl(syTravelStrategy.getPicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath,""));
        }


    }
}
