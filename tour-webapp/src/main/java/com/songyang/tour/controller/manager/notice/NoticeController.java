package com.songyang.tour.controller.manager.notice;/**
 * Created by lenovo on 2017/9/17.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.controller.manager.scenicspot.ScenicSpotController;
import com.songyang.tour.enums.NoticeTypeEnum;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyNotice;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyNoticeQuery;
import com.songyang.tour.service.SyNoticeService;
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
 * 公告Controller
 *
 * @author
 * @create 2017-09-17 23:43
 **/
@Controller
@RequestMapping("/manage/notice")
public class NoticeController {
    private Logger logger = LoggerFactory.getLogger(ScenicSpotController.class);

    @Resource
    private SyNoticeService syNoticeService;


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

        loadNoticeType(modelMap);

        return VmConstans.NOTICE_EDIT;
    }

    /**
     * 保存
     * @param syNotice
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyNotice syNotice) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;
            // 2.校验表单基本参数
            this.checkParam(syNotice);

            if (syNotice.getId() == null) {
                // TODO 登录用户
                syNotice.setCreator("SYSTEM");
                count = syNoticeService.insert(syNotice);
            } else {
                // TODO 登录用户
                syNotice.setModifier("SYSTEM");
                count = syNoticeService.updateById(syNotice);
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
     * @param qo 公告
     * @param query 分页
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryList(SyNoticeQuery qo, PageQuery query, ModelMap model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
        loadNoticeType(model);
        try{

            if(StringUtils.isBlank(qo.getContent())){
                qo.setContent(null);
            }

            // 2、查询条数
            Long count = syNoticeService.queryCountByParam(qo);
            if(count > 0){
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyNotice> list = syNoticeService.queryListByParam(qo);

            model.addAttribute("list", list);
            model.addAttribute("syNotice", qo);
        }catch (Throwable e){
            logger.error("NoticeController query is exception",e);
        }

        return VmConstans.NOTICE_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            loadNoticeType(model);
            SyNotice syNotice = syNoticeService.selectById(id);
            model.addAttribute("syNotice", syNotice);
        } catch (Exception e) {
            logger.error("查询公告[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询公告[" + id + "]出现异常");
        }

        return VmConstans.NOTICE_DETAIL;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id,ModelMap model) {
        try {
            loadNoticeType(model);
            SyNotice syNotice = syNoticeService.selectById(id);
            model.addAttribute("syNotice", syNotice);
        } catch (Exception e) {
            logger.error("编辑公告[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑公告[" + id + "]出现异常");
        }

        return VmConstans.NOTICE_EDIT;
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
            SyNotice param = new SyNotice();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syNoticeService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "删除成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "删除失败");
            }
        } catch (Throwable e) {
            logger.error("NoticeController#update is exception",e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "删除出现异常");
        }
        return res;
    }


    private void checkParam(SyNotice syNotice) {
        Assert.notNull(syNotice, "syScenicSpot 不能为空");
        Assert.hasText(syNotice.getJumpUrl(), "跳转地址不能为空");
        Assert.hasText(syNotice.getContent(), "公告内容不能为空");
    }


    private void loadNoticeType(ModelMap modelMap) {
        List<CodeName> noticeTypeList = new ArrayList<CodeName>();
        for (NoticeTypeEnum tempEnum : NoticeTypeEnum.values()) {
            CodeName cn = new CodeName();
            cn.setCode(String.valueOf(tempEnum.getCode()));
            cn.setName(tempEnum.getMessage());
            noticeTypeList.add(cn);
        }
        modelMap.addAttribute("noticeTypeList", noticeTypeList);
    }
}
