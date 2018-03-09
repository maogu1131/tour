package com.songyang.tour.controller.manager.prod;/**
 * Created by lenovo on 2017/9/21.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.ProdSaleStatusEnum;
import com.songyang.tour.enums.ProdTypeEnum;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyProd;
import com.songyang.tour.pojo.SyScenicSpot;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyProdQuery;
import com.songyang.tour.service.SyProdService;
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
 * 产品业务controller
 *
 * @author
 * @create 2017-09-21 23:35
 **/
@Controller
@RequestMapping(value = "/manage/prod")
public class SyProdController {

    private Logger logger = LoggerFactory.getLogger(SyProdController.class);

    @Resource
    private SyProdService syProdService;

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

        loadProdType(modelMap);

        return VmConstans.PROD_EDIT;
    }

    /**
     * 保存
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyProd syProd) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;
            // 2.校验表单基本参数
            this.checkParam(syProd);

            if (syProd.getId() == null) {
                // TODO 登录用户
                syProd.setCreator("SYSTEM");
                syProd.setStatus(ProdSaleStatusEnum.FOR_SELL.getCode());
                count = syProdService.insert(syProd);
            } else {
                // TODO 登录用户
                syProd.setModifier("SYSTEM");
                count = syProdService.updateById(syProd);
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
     *
     * @param qo    公告
     * @param query 分页
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryList(SyProdQuery qo, PageQuery query, ModelMap model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
        loadProdType(model);
        try {

            if (StringUtils.isBlank(qo.getName())) {
                qo.setName(null);
            }

            // 2、查询条数
            Long count = syProdService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyProd> list = syProdService.queryListByParam(qo);

            for (SyProd temp : list) {
                temp.setPicUrlList(CommonUtil.analyzePicUrl(temp.getPicUrl()));
            }


            model.addAttribute("list", list);
            model.addAttribute("syProd", qo);
        } catch (Throwable e) {
            logger.error("SyProdController query is exception", e);
        }

        return VmConstans.PROD_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            loadProdType(model);
            SyProd syProd = syProdService.selectById(id);

            if(null==syProd){
                return VmConstans.PROD_DETAIL;
            }
            model.addAttribute("picUrlList", CommonUtil.analyzePicUrl(syProd.getPicUrl()));

            model.addAttribute("syProd", syProd);
        } catch (Exception e) {
            logger.error("查询公告[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询公告[" + id + "]出现异常");
        }

        return VmConstans.PROD_DETAIL;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            loadProdType(model);
            SyProd syProd = syProdService.selectById(id);

            // 构建 图片地址json字符串
            CommonUtil commonUtil = new CommonUtil();
            syProd.setPicUrlListStr(commonUtil.buildPicUrl(syProd.getPicUrl()));

            model.addAttribute("syProd", syProd);
        } catch (Exception e) {
            logger.error("编辑产品[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑公告[" + id + "]出现异常");
        }

        return VmConstans.PROD_EDIT;
    }

    /**
     * 更新状态
     *
     * @param syProd
     */
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestBody SyProd syProd) {
        JSONObject res = new JSONObject();
        try {

            // TODO 更新者
            syProd.setModifier("SYSTEM");
            String msg = "状态更新";
            if(syProd.getTotalNum() != null){
                msg = "修改产品数量";
            }
            int count = syProdService.updateById(syProd);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY,  msg+"成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY,  msg+"失败");
            }
        } catch (Throwable e) {
            logger.error("SyProdController#update is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "更新出现异常");
        }
        return res;
    }


    private void checkParam(SyProd syProd) {
        Assert.notNull(syProd, "syProd 不能为空");
        Assert.hasText(syProd.getName(), "产品名称不能为空");
        Assert.notNull(syProd.getPrice(), "产品价格不能为空");
        Assert.notNull(syProd.getTotalNum(), "产品总量不能为空");

        if(StringUtils.isNotBlank(syProd.getPicUrl())){
            syProd.setPicUrl(syProd.getPicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath,""));
        }

    }


    private void loadProdType(ModelMap modelMap) {
        List<CodeName> prodTypeList = new ArrayList<CodeName>();
        for (ProdTypeEnum tempEnum : ProdTypeEnum.values()) {
            CodeName cn = new CodeName();
            cn.setCode(String.valueOf(tempEnum.getCode()));
            cn.setName(tempEnum.getMessage());
            prodTypeList.add(cn);
        }
        modelMap.addAttribute("prodTypeList", prodTypeList);

        List<CodeName> prodSaleTypeList = new ArrayList<CodeName>();
        for (ProdSaleStatusEnum tempEnum : ProdSaleStatusEnum.values()) {
            CodeName cn = new CodeName();
            cn.setCode(String.valueOf(tempEnum.getCode()));
            cn.setName(tempEnum.getMessage());
            prodSaleTypeList.add(cn);
        }
        modelMap.addAttribute("prodSaleTypeList", prodSaleTypeList);

    }
}
