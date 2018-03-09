package com.songyang.tour.controller.manager.publicplace;/**
 * Created by lenovo on 2017/9/22.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyPublicPlace;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyPublicPlaceQuery;
import com.songyang.tour.service.SyPublicPlaceService;
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
 * 公共场所controller
 *
 * @author
 * @create 2017-09-22 22:34
 **/
@Controller
@RequestMapping(value = "/manage/publicPlace")
public class PublicPlaceController {

    private Logger logger = LoggerFactory.getLogger(PublicPlaceController.class);

    @Resource
    private SyPublicPlaceService syPublicPlaceService;

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

        return VmConstans.PUBLICPLACE_EDIT;
    }

    /**
     * 保存
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONObject save(SyPublicPlace syPublicPlace) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;
            // 2.校验表单基本参数
            this.checkParam(syPublicPlace);

            if (syPublicPlace.getId() == null) {
                // TODO 登录用户
                syPublicPlace.setCreator("SYSTEM");
                count = syPublicPlaceService.insert(syPublicPlace);
            } else {
                // TODO 登录用户
                syPublicPlace.setModifier("SYSTEM");
                count = syPublicPlaceService.updateById(syPublicPlace);
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
    public String queryList(SyPublicPlaceQuery qo, PageQuery query, ModelMap model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
        try {

            if (StringUtils.isBlank(qo.getName())) {
                qo.setName(null);
            }
            if (StringUtils.isBlank(qo.getAddress())) {
                qo.setAddress(null);
            }

            // 2、查询条数
            Long count = syPublicPlaceService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyPublicPlace> list = syPublicPlaceService.queryListByParam(qo);

            model.addAttribute("list", list);
            model.addAttribute("syPublicPlace", qo);
        } catch (Throwable e) {
            logger.error("PublicPlaceController query is exception", e);
        }

        return VmConstans.PUBLICPLACE_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            SyPublicPlace syPublicPlace = syPublicPlaceService.selectById(id);
            model.addAttribute("syPublicPlace", syPublicPlace);
        } catch (Exception e) {
            logger.error("查询公共场所[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询公共场所[" + id + "]出现异常");
        }

        return VmConstans.PUBLICPLACE_DETAIL;
    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, ModelMap model) {
        try {
            SyPublicPlace syPublicPlace = syPublicPlaceService.selectById(id);
            model.addAttribute("syPublicPlace", syPublicPlace);
        } catch (Exception e) {
            logger.error("编辑公共场所[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "编辑公共场所[" + id + "]出现异常");
        }

        return VmConstans.PUBLICPLACE_EDIT;
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
            SyPublicPlace param = new SyPublicPlace();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syPublicPlaceService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "状态更新成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "状态更新失败");
            }
        } catch (Throwable e) {
            logger.error("NoticeController#update is exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "状态更新出现异常");
        }
        return res;
    }


    private void checkParam(SyPublicPlace syPublicPlace) {
        Assert.notNull(syPublicPlace, "syPublicPlace 不能为空");
        Assert.hasText(syPublicPlace.getName(), "公共场所名称不能为空");
//        Assert.hasText(syPublicPlace.getAddress(), "公共场所地址不能为空");
    }
}
