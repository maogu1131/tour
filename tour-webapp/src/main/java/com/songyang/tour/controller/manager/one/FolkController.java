package com.songyang.tour.controller.manager.one;

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyFolk;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyFolkQuery;
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
 * 民俗活动管理
 */
@Controller
@RequestMapping("/manage/folk")
public class FolkController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(FolkController.class);

    @Autowired
    private SyFolkService syFolkService;

    /**
     * 新建
     *
     * @return
     */
    @RequestMapping(value = "/init")
    public String init(Model model) {

        return VmConstans.FOLK_EDIT;
    }

    /**
     * 保存 民俗活动
     *
     * @param syFolk 民俗活动
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(SyFolk syFolk) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;

            // 2.校验表单基本参数
            this.checkParam(syFolk);


            if (syFolk.getId() == null) {
                // TODO 登录用户
                syFolk.setCreator("SYSTEM");
                count = syFolkService.insert(syFolk);
            } else {
                // TODO 登录用户
                syFolk.setModifier("SYSTEM");
                count = syFolkService.updateById(syFolk);
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
     * @param qo    民俗活动
     * @param query 分页
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryList(SyFolkQuery qo, PageQuery query, Model model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);

        try {

            if (StringUtils.isBlank(qo.getTitle())) {
                qo.setTitle(null);
            }
//
//            if(StringUtils.isBlank(qo.getAddress())){
//                qo.setAddress(null);
//            }
            // 2、查询条数
            Long count = syFolkService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyFolk> list = syFolkService.queryListByParam(qo);

            for (SyFolk temp : list) {
                temp.setSpecialPicUrlList(CommonUtil.analyzePicUrl(temp.getSpecialPicUrl()));
                temp.setPicUrlList(CommonUtil.analyzePicUrl(temp.getPicUrl()));
                temp.setBannerUrlList(CommonUtil.analyzePicUrl(temp.getBannerUrl()));
            }

            model.addAttribute("list", list);
            model.addAttribute("folk", qo);
        } catch (Throwable e) {
            LOG.error("queryList is exception", e);
        }

        return VmConstans.FOLK_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
            SyFolk folk = syFolkService.selectById(id);

            if (null == folk) {
                return VmConstans.FOLK_DETAIL;
            }
            model.addAttribute("picUrlList", CommonUtil.analyzePicUrl(folk.getPicUrl()));
            model.addAttribute("specialPicUrlList", CommonUtil.analyzePicUrl(folk.getSpecialPicUrl()));
            model.addAttribute("bannerUrlList", CommonUtil.analyzePicUrl(folk.getBannerUrl()));


            model.addAttribute("folk", folk);
        } catch (Exception e) {
            LOG.error("查询民俗活动[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询民俗活动[" + id + "]出现异常");
        }

        return VmConstans.FOLK_DETAIL;
    }


    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
            SyFolk folk = syFolkService.selectById(id);

            // 构建 图片地址json字符串
            CommonUtil commonUtil = new CommonUtil();
            folk.setPicUrlListStr(commonUtil.buildPicUrl(folk.getPicUrl()));
            folk.setSpecialPicUrlListStr(commonUtil.buildPicUrl(folk.getSpecialPicUrl()));
            folk.setBannerUrlListStr(commonUtil.buildPicUrl(folk.getBannerUrl()));

            model.addAttribute("folk", folk);
        } catch (Exception e) {
            LOG.error("查询民俗活动[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询民俗活动[" + id + "]出现异常");
        }

        return VmConstans.FOLK_EDIT;
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
            SyFolk param = new SyFolk();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syFolkService.updateById(param);

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
     * 民俗活动基本参数校验
     *
     * @param syFolk 民俗活动
     */
    private void checkParam(SyFolk syFolk) {

        Assert.notNull(syFolk, "syFolk 不能为空");
        Assert.hasText(syFolk.getTitle(), "名称为空");
//        Assert.hasText(syFolk.getAddress(), "地址不能为空");


        if (StringUtils.isNotBlank(syFolk.getPicUrl())) {
            syFolk.setPicUrl(syFolk.getPicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath, ""));
        }

        if (StringUtils.isNotBlank(syFolk.getSpecialPicUrl())) {
            syFolk.setSpecialPicUrl(syFolk.getSpecialPicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath, ""));
        }

        if (StringUtils.isNotBlank(syFolk.getBannerUrl())) {
            syFolk.setBannerUrl(syFolk.getBannerUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath, ""));
        }

    }
}
