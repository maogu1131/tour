package com.songyang.tour.controller.manager.one;

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyGuide;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyGuideQuery;
import com.songyang.tour.service.SyGuideService;
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
 * 导游管理
 */
@Controller
@RequestMapping("/manage/guide")
public class GuideController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(GuideController.class);

    @Autowired
    private SyGuideService syGuideService;

    /**
     * 新建
     * @return
     */
    @RequestMapping(value = "/init")
    public String init(Model model) {

        return VmConstans.GUIDE_EDIT;
    }

    /**
     * 保存 导游
     * @param syGuide 导游
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(SyGuide syGuide) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;

            // 2.校验表单基本参数
            this.checkParam(syGuide);


            if(syGuide.getId() == null){
                // TODO 登录用户
                syGuide.setCreator("SYSTEM");
                count = syGuideService.insert(syGuide);
            }else{
                // TODO 登录用户
                syGuide.setModifier("SYSTEM");
                count = syGuideService.updateById(syGuide);
            }

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "保存成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "保存失败");
            }

        } catch (Throwable e) {
            LOG.error("save is exception",e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, e.getMessage());
        }
        return res;
    }



    /**
     * 查询
     * @param qo 导游
     * @param query 分页
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryList(SyGuideQuery qo, PageQuery query, Model model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);

        try{

            if(StringUtils.isBlank(qo.getName())){
                qo.setName(null);
            }

            if(StringUtils.isBlank(qo.getKeyWord())){
                qo.setKeyWord(null);
            }

            if(StringUtils.isBlank(qo.getPhone())){
                qo.setPhone(null);
            }
            // 2、查询条数
            Long count = syGuideService.queryCountByParam(qo);
            if(count > 0){
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyGuide> list = syGuideService.queryListByParam(qo);

            for (SyGuide temp : list) {
                temp.setPicUrlList(CommonUtil.analyzePicUrl(temp.getPicUrl()));
            }

            model.addAttribute("list", list);
            model.addAttribute("guide", qo);
        }catch (Throwable e){
            LOG.error("queryList is exception",e);
        }

        return VmConstans.GUIDE_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
            SyGuide guide = syGuideService.selectById(id);

            if (null == guide) {
                return VmConstans.FOLK_DETAIL;
            }
            model.addAttribute("picUrlList", CommonUtil.analyzePicUrl(guide.getPicUrl()));
            model.addAttribute("guide", guide);
        } catch (Exception e) {
            LOG.error("查询导游[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询导游[" + id + "]出现异常");
        }

        return VmConstans.GUIDE_DETAIL;
    }


    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id,Model model) {
        try {
            SyGuide guide = syGuideService.selectById(id);

            // 构建 图片地址json字符串
            CommonUtil commonUtil = new CommonUtil();
            guide.setPicUrlListStr(commonUtil.buildPicUrl(guide.getPicUrl()));

            model.addAttribute("guide", guide);
        } catch (Exception e) {
            LOG.error("查询导游[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询导游[" + id + "]出现异常");
        }

        return VmConstans.GUIDE_EDIT;
    }


    /**
     * 更新导游状态
     * @param id
     */
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam(required = true, value = "id") String id,
                             @RequestParam(required = true, value = "status") Integer status) {
        JSONObject res = new JSONObject();
        try {
            SyGuide param = new SyGuide();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syGuideService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "状态更新成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "状态更新失败");
            }

        } catch (Throwable e) {
            LOG.error("del exception",e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "状态更新出现异常");
        }

        return res;
    }


    /**
     * 导游基本参数校验
     * @param syGuide 导游
     */
    private void checkParam(SyGuide syGuide) {

        Assert.notNull(syGuide, "syGuide 不能为空");
        Assert.hasText(syGuide.getName(), "名称为空");
        Assert.hasText(syGuide.getPhone(), "手机号不能为空");

    }

}
