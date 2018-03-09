package com.songyang.tour.controller.manager.one;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.HotelType;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.enums.TourType;
import com.songyang.tour.pojo.SyCustomRoute;
import com.songyang.tour.pojo.SyCustomRouteDays;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyCustomRouteQuery;
import com.songyang.tour.service.SyCustomRouteDaysService;
import com.songyang.tour.service.SyCustomRouteService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.CodeName;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 定制路线管理
 */
@Controller
@RequestMapping("/manage/customRoute")
public class CustomRouteController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomRouteController.class);

    @Autowired
    private SyCustomRouteService syCustomRouteService;

    @Autowired
    private SyCustomRouteDaysService syCustomRouteDaysService;

    /**
     * 新建
     *
     * @return
     */
    @RequestMapping(value = "/init")
    public String init(Model model) {

        // 加载旅游类型
        loadTourType(model);
        return VmConstans.CUSTOMROUTE_EDIT;
    }

    /**
     * 保存 定制路线
     * @param syCustomRoute 定制路线
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(SyCustomRoute syCustomRoute,HttpServletRequest request) {

        JSONObject res = new JSONObject();

        try {
            Boolean flag = syCustomRouteService.save(syCustomRoute);
            // 2.校验表单基本参数
            this.checkParam(syCustomRoute);

            if (flag) {
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
     * @param qo    定制路线
     * @param query 分页
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryList(SyCustomRouteQuery qo, PageQuery query, Model model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);

        try {
            // 加载旅游路线类型
            loadTourType(model);

            if (StringUtils.isBlank(qo.getType())) {
                qo.setType(null);
            }

            if (StringUtils.isBlank(qo.getSubType())) {
                qo.setSubType(null);
            }

            if (StringUtils.isBlank(qo.getKeyWord())) {
                qo.setKeyWord(null);
            }
//
//            if(StringUtils.isBlank(qo.getAddress())){
//                qo.setAddress(null);
//            }
            // 2、查询条数
            Long count = syCustomRouteService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyCustomRoute> list = syCustomRouteService.queryListByParam(qo);
            model.addAttribute("list", list);
            model.addAttribute("customRoute", qo);
        } catch (Throwable e) {
            LOG.error("queryList is exception", e);
        }

        return VmConstans.CUSTOMROUTE_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
            // 加载定制路线类型
            loadTourType(model);
            SyCustomRoute customRoute = syCustomRouteService.selectById(id);

            if (null == customRoute) {
                return VmConstans.PROD_DETAIL;
            }

            // TODO 构建 各个路线的url地址
            List<SyCustomRouteDays> tempList = syCustomRouteDaysService.selectByCustomRouteId(customRoute.getId());

            JSONObject res = new JSONObject();
            res.put("num",tempList.size());
            res.put("list",tempList);
            customRoute.setCustomRouteDaysStr(JSON.toJSONString(res));

            model.addAttribute("customRoute", customRoute);
        } catch (Exception e) {
            LOG.error("查询定制路线[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询定制路线[" + id + "]出现异常");
        }

        return VmConstans.CUSTOMROUTE_DETAIL;
    }


    private void buildPicUrl(List<SyCustomRouteDays> tempList){
        for (SyCustomRouteDays temp: tempList) {
            // TODO 处理图片的转换
            temp.setContent(temp.getContent());
        }


    }

    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
            // 加载定制路线类型
            loadTourType(model);
            SyCustomRoute customRoute = syCustomRouteService.selectById(id);
            CommonUtil commonUtil = new CommonUtil();

            // TODO 构建 各个路线的url地址
            List<SyCustomRouteDays> tempList = syCustomRouteDaysService.selectByCustomRouteId(customRoute.getId());
//            if(CollectionUtils.isNotEmpty(tempList)){
//                buildPicUrl(tempList);
//            }
//            customRoute.setCustomRouteDaysList(tempList);
//            customRoute.setCustomRouteDaysStr(JSON.toJSONString(tempList));

            JSONObject res = new JSONObject();
            res.put("num",tempList.size());
            res.put("list",tempList);
            customRoute.setCustomRouteDaysStr(JSON.toJSONString(res));

            model.addAttribute("customRoute", customRoute);
        } catch (Exception e) {
            LOG.error("查询定制路线[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询定制路线[" + id + "]出现异常");
        }

        return VmConstans.CUSTOMROUTE_EDIT;
    }


    /**
     * 更新定制路线状态
     *
     * @param id
     */
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam(required = true, value = "id") String id,
                             @RequestParam(required = true, value = "status") Integer status) {
        JSONObject res = new JSONObject();
        try {
            SyCustomRoute param = new SyCustomRoute();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syCustomRouteService.updateById(param);

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
     * 定制路线基本参数校验
     *
     * @param syCustomRoute 定制路线
     */
    private void checkParam(SyCustomRoute syCustomRoute) {

        Assert.notNull(syCustomRoute, "syCustomRoute 不能为空");
        Assert.hasText(syCustomRoute.getType(), "旅游大类型为空");
        Assert.hasText(syCustomRoute.getSubType(), "旅游子类型为空");
//        Assert.hasText(syCustomRoute.getKeyWord(), "关键词为空");
    }

    /**
     * 加载旅游类型
     *
     * @param model
     */
    private void loadTourType(Model model) {

        List<CodeName> fatherList = new ArrayList<CodeName>();

        CodeName codeName1 = new CodeName();
        codeName1.setCode(TourType.ONE_DAY.getCode());
        codeName1.setName(TourType.ONE_DAY.getName());

        CodeName codeName2 = new CodeName();
        codeName2.setCode(TourType.TWO_DAY.getCode());
        codeName2.setName(TourType.TWO_DAY.getName());

        CodeName codeName3 = new CodeName();
        codeName3.setCode(TourType.THREE_DAY.getCode());
        codeName3.setName(TourType.THREE_DAY.getName());

        CodeName codeName4 = new CodeName();
        codeName4.setCode(TourType.FOUR_DAY.getCode());
        codeName4.setName(TourType.FOUR_DAY.getName());

        CodeName codeName5 = new CodeName();
        codeName5.setCode(TourType.FIVE_DAY.getCode());
        codeName5.setName(TourType.FIVE_DAY.getName());

//        CodeName codeName6 = new CodeName();
//        codeName6.setCode(TourType.SIX_DAY.getCode());
//        codeName6.setName(TourType.SIX_DAY.getName());
//
//        CodeName codeName7 = new CodeName();
//        codeName7.setCode(TourType.SEVEN_DAY.getCode());
//        codeName7.setName(TourType.SEVEN_DAY.getName());

        fatherList.add(codeName1);
        fatherList.add(codeName2);
        fatherList.add(codeName3);
        fatherList.add(codeName4);
        fatherList.add(codeName5);
//        fatherList.add(codeName6);
//        fatherList.add(codeName7);

        List<CodeName> subList = new ArrayList<CodeName>();

        CodeName codeNameSub1 = new CodeName();
        codeNameSub1.setCode(String.valueOf(HotelType.HOTEL.getCode()));
        codeNameSub1.setName(HotelType.HOTEL.getName());

        CodeName codeNameSub2 = new CodeName();
        codeNameSub2.setCode(String.valueOf(HotelType.HOMESTAY.getCode()));
        codeNameSub2.setName(HotelType.HOMESTAY.getName());

        CodeName codeNameSub3 = new CodeName();
        codeNameSub3.setCode(String.valueOf(HotelType.MIX.getCode()));
        codeNameSub3.setName(HotelType.MIX.getName());

        subList.add(codeNameSub1);
        subList.add(codeNameSub2);
        subList.add(codeNameSub3);

        model.addAttribute("fatherList",fatherList);
        model.addAttribute("subList",subList);

    }

}
