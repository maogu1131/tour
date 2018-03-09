package com.songyang.tour.controller.manager.trafficwarn;/**
 * Created by lenovo on 2017/9/24.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.enums.TrafficWarnStatusEnum;
import com.songyang.tour.pojo.SyTrafficWarn;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyTrafficWarnQuery;
import com.songyang.tour.service.SyTrafficWarnService;
import com.songyang.tour.vo.CodeName;
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
 * 交通预警controller
 *
 * @author
 * @create 2017-09-24 16:16
 **/
@Controller
@RequestMapping(value="/manage/trafficWarn")
public class TrafficWarnController {

    private Logger logger = LoggerFactory.getLogger(TrafficWarnController.class);

    @Resource
    private SyTrafficWarnService syTrafficWarnService;


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

        loadTrafficWarnStatus(modelMap);

        return VmConstans.TRAFFIC_WARN_EDIT;
    }

    /**
     * 保存
     * @param syTrafficWarn
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyTrafficWarn syTrafficWarn) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;
            // 2.校验表单基本参数
            this.checkParam(syTrafficWarn);

            if (syTrafficWarn.getId() == null) {
                // TODO 登录用户
                syTrafficWarn.setCreator("SYSTEM");
                count = syTrafficWarnService.insert(syTrafficWarn);
            } else {
                // TODO 登录用户
                syTrafficWarn.setModifier("SYSTEM");
                count = syTrafficWarnService.updateById(syTrafficWarn);
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
     * @param qo 公告
     * @param query 分页
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryList(SyTrafficWarnQuery qo, PageQuery query, ModelMap model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
        loadTrafficWarnStatus(model);
        try{

            // 2、查询条数
            Long count = syTrafficWarnService.queryCountByParam(qo);
            if(count > 0){
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyTrafficWarn> list = syTrafficWarnService.queryListByParam(qo);

            model.addAttribute("list", list);
            model.addAttribute("syTrafficWarn", qo);
        }catch (Throwable e){
            logger.error("TrafficWarnController query is exception",e);
        }

        return VmConstans.TRAFFIC_WARN_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            loadTrafficWarnStatus(model);
            SyTrafficWarn syTrafficWarn = syTrafficWarnService.selectById(id);
            model.addAttribute("syTrafficWarn", syTrafficWarn);
        } catch (Exception e) {
            logger.error("查询交通预警[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询交通预警[" + id + "]出现异常");
        }

        return VmConstans.TRAFFIC_WARN_DETAIL;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id,ModelMap model) {
        try {
            loadTrafficWarnStatus(model);
            SyTrafficWarn syTrafficWarn = syTrafficWarnService.selectById(id);
            model.addAttribute("syTrafficWarn", syTrafficWarn);
        } catch (Exception e) {
            logger.error("编辑交通预警[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑交通预警[" + id + "]出现异常");
        }

        return VmConstans.TRAFFIC_WARN_EDIT;
    }

    /**
     * 更新状态
     * @param id
     */
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam(required = true, value = "id") String id,
                             @RequestParam(required = true, value = "status") Integer status) {
        JSONObject res = new JSONObject();
        try {
            SyTrafficWarn param = new SyTrafficWarn();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syTrafficWarnService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "状态更新成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "状态更新失败");
            }
        } catch (Throwable e) {
            logger.error("TrafficWarnController#update is exception",e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "状态更新出现异常");
        }
        return res;
    }


    private void checkParam(SyTrafficWarn syTrafficWarn) {
        Assert.notNull(syTrafficWarn, "syTrafficWarn 不能为空");
//        Assert.hasText(syTrafficWarn.getJumpUrl(), "跳转地址不能为空");
//        Assert.hasText(syTrafficWarn.getContent(), "内容不能为空");

    }


    private void loadTrafficWarnStatus(ModelMap modelMap) {
        List<CodeName> noticeTypeList = new ArrayList<CodeName>();
        for (TrafficWarnStatusEnum tempEnum : TrafficWarnStatusEnum.values()) {
            CodeName cn = new CodeName();
            cn.setCode(String.valueOf(tempEnum.getCode()));
            cn.setName(tempEnum.getMessage());
            noticeTypeList.add(cn);
        }
        modelMap.addAttribute("trafficWarnStatusList", noticeTypeList);
    }
}
