package com.songyang.tour.controller.manager.one;

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.controller.mobile.api.EvaluateController;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyEvaluate;
import com.songyang.tour.pojo.SyFolk;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyEvaluateQuery;
import com.songyang.tour.query.SyFolkQuery;
import com.songyang.tour.service.SyEvaluateService;
import com.songyang.tour.service.SyFolkService;
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
 * 评价管理
 */
@Controller
@RequestMapping("/manage/evaluate")
public class SyEvaluateController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(SyEvaluateController.class);

    @Autowired
    private SyEvaluateService syEvaluateService;

    /**
     * 查询
     *
     * @param aEffectId    评价查询
     * @param query 分页
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryList(@RequestParam(required = true, value = "aEffectId") String aEffectId, PageQuery query, Model model) {


        // 1、准备参数
        SyEvaluateQuery qo = new SyEvaluateQuery();
        qo.setaEffectId(aEffectId);
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);

        try {
            // 2、查询条数
            Long count = syEvaluateService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("effect_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyEvaluate> list = syEvaluateService.queryListByParam(qo);

            for (SyEvaluate temp : list) {
                temp.setPicUrlList(CommonUtil.analyzePicUrl(temp.getEffectPicUrl()));
                temp.setbEffectImg(CommonUtil.analyzeOnePicUrl(temp.getBEffectImg()));
            }

            model.addAttribute("list", list);
            model.addAttribute("evaluate", qo);
        } catch (Throwable e) {
            LOG.error("queryList is exception", e);
        }

        return VmConstans.EVALUATE_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
            SyEvaluate evaluate = syEvaluateService.selectById(id);

            if (null == evaluate) {
                return VmConstans.EVALUATE_DETAIL;
            }
            model.addAttribute("picUrlList", CommonUtil.analyzePicUrl(evaluate.getEffectPicUrl()));
            model.addAttribute("evaluate", evaluate);
        } catch (Exception e) {
            LOG.error("查询评价[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询评价[" + id + "]出现异常");
        }

        return VmConstans.EVALUATE_DETAIL;
    }

    /**
     * 更新民俗活动开关状态
     *
     * @param id
     */
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam(required = true, value = "id") String id,
                             @RequestParam(required = true, value = "status") Integer status) {
        JSONObject res = new JSONObject();
        try {
            SyEvaluate param = new SyEvaluate();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syEvaluateService.updateById(param);

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



}
