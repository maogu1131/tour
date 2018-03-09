package com.songyang.tour.controller.manager.one;

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.ModuleType;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.pojo.SyModule;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyModuleQuery;
import com.songyang.tour.service.SyModuleService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.CodeName;
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
 * 模块管理
 */
@Controller
@RequestMapping("/manage/module")
public class ModuleController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    private SyModuleService syModuleService;

    /**
     * 新建
     *
     * @return
     */
    @RequestMapping(value = "/init")
    public String init(Model model) {
        loadModuleType(model);
        return VmConstans.MODULE_EDIT;
    }

    /**
     * 保存 模块
     *
     * @param syModule 模块
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(SyModule syModule) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;

            // 2.校验表单基本参数
            this.checkParam(syModule);


            if (syModule.getId() == null) {
                // TODO 登录用户
                syModule.setCreator("SYSTEM");
                count = syModuleService.insert(syModule);
            } else {
                // TODO 登录用户
                syModule.setModifier("SYSTEM");
                count = syModuleService.updateById(syModule);
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
     * @param qo    模块
     * @param query 分页
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryList(SyModuleQuery qo, PageQuery query, Model model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);

        try {
            loadModuleType(model);
            if (StringUtils.isBlank(qo.getTitle())) {
                qo.setTitle(null);
            }

            if (StringUtils.isBlank(qo.getType())) {
                qo.setType(null);
            }
//
//            if(StringUtils.isBlank(qo.getAddress())){
//                qo.setAddress(null);
//            }
            // 2、查询条数
            Long count = syModuleService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyModule> list = syModuleService.queryListByParam(qo);

            for (SyModule temp : list) {
//                temp.setTitlePicUrl(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath + temp.getTitlePicUrl());
                temp.setTitlePicUrl(CommonUtil.analyzeOnePicUrl(temp.getTitlePicUrl()));
            }


            model.addAttribute("list", list);
            model.addAttribute("module", qo);
        } catch (Throwable e) {
            LOG.error("queryList is exception", e);
        }

        return VmConstans.MODULE_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
            loadModuleType(model);
            SyModule module = syModuleService.selectById(id);

            if (null != module) {
//                module.setTitlePicUrl(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath + module.getTitlePicUrl());
                module.setTitlePicUrl(CommonUtil.analyzeOnePicUrl(module.getTitlePicUrl()));
            }

            model.addAttribute("module", module);
        } catch (Exception e) {
            LOG.error("查询模块[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询模块[" + id + "]出现异常");
        }

        return VmConstans.MODULE_DETAIL;
    }


    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
            loadModuleType(model);
            SyModule module = syModuleService.selectById(id);

            // 构建 图片地址json字符串
            CommonUtil commonUtil = new CommonUtil();
            module.setTitlePicUrlListStr(commonUtil.buildPicUrl(module.getTitlePicUrl()));


            model.addAttribute("module", module);
        } catch (Exception e) {
            LOG.error("查询模块[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询模块[" + id + "]出现异常");
        }

        return VmConstans.MODULE_EDIT;
    }


    /**
     * 更新模块状态
     *
     * @param id
     */
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam(required = true, value = "id") String id,
                             @RequestParam(required = true, value = "status") Integer status) {
        JSONObject res = new JSONObject();
        try {

            SyModule param = new SyModule();
            param.setId(Long.valueOf(id));

            // TODO 更新者
//            param.setModifier();
            int count = syModuleService.updateById(param);

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
     * 模块基本参数校验
     *
     * @param syModule 模块
     */
    private void checkParam(SyModule syModule) {

        Assert.notNull(syModule, "syModule 不能为空");
        Assert.hasText(syModule.getTitle(), "名称为空");
        Assert.notNull(syModule.getType(), "类型不能为空");
        if (syModule.getTitlePicUrl().contains("|")) {
            throw new TourBizException("一个模块只能对应一张图片");
        }

        if (StringUtils.isNotBlank(syModule.getTitlePicUrl())) {
            syModule.setTitlePicUrl(syModule.getTitlePicUrl().replace(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath, ""));
        }

    }


    /**
     * 加载模块类型
     *
     * @param model
     */
    private void loadModuleType(Model model) {


        List<CodeName> moduleList = new ArrayList<CodeName>();


        //大类型:1:美食 2:民宿 3:旅游攻略 4:景区 5:产品 6:民俗 7:古村落 8:定制化路线 9：商家 10：公共场所 11：公共服务

        CodeName codeName1 = new CodeName();
        codeName1.setCode(String.valueOf(ModuleType.RESTAURANT_FOOD.getCode()));
        codeName1.setName(ModuleType.RESTAURANT_FOOD.getName());

        CodeName codeName2 = new CodeName();
        codeName2.setCode(String.valueOf(ModuleType.HOTEL.getCode()));
        codeName2.setName(ModuleType.HOTEL.getName());


        CodeName codeName3 = new CodeName();
        codeName3.setCode(String.valueOf(ModuleType.TRAVEL_STRATEGY.getCode()));
        codeName3.setName(ModuleType.TRAVEL_STRATEGY.getName());


        CodeName codeName4 = new CodeName();
        codeName4.setCode(String.valueOf(ModuleType.SCENIC_SPOT.getCode()));
        codeName4.setName(ModuleType.SCENIC_SPOT.getName());

        CodeName codeName5 = new CodeName();
        codeName5.setCode(String.valueOf(ModuleType.PROD.getCode()));
        codeName5.setName(ModuleType.PROD.getName());


        CodeName codeName6 = new CodeName();
        codeName6.setCode(String.valueOf(ModuleType.FOLK.getCode()));
        codeName6.setName(ModuleType.FOLK.getName());


        moduleList.add(codeName1);
        moduleList.add(codeName2);
        moduleList.add(codeName3);
        moduleList.add(codeName4);
        moduleList.add(codeName5);
        moduleList.add(codeName6);

        model.addAttribute("moduleList", moduleList);
    }

}
