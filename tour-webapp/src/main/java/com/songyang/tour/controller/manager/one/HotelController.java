package com.songyang.tour.controller.manager.one;

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.constants.VmConstans;
import com.songyang.tour.enums.*;
import com.songyang.tour.pojo.SyHotel;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyHotelQuery;
import com.songyang.tour.service.SyHotelService;
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
 * 酒店管理
 */
@Controller
@RequestMapping("/manage/hotel")
public class HotelController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private SyHotelService syHotelService;

    /**
     * 新建
     *
     * @return
     */
    @RequestMapping(value = "/init")
    public String init(Model model) {

        // 加载酒店类型
        loadHotelType(model);
        return VmConstans.HOTEL_EDIT;
    }

    /**
     * 保存 酒店
     *
     * @param hotel 酒店
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(SyHotel hotel) {

        JSONObject res = new JSONObject();

        try {
            int count = 0;

            // 2.校验表单基本参数
            this.checkParam(hotel);


            if (hotel.getId() == null) {
                // TODO 登录用户
                hotel.setCreator("SYSTEM");
                count = syHotelService.insert(hotel);
            } else {
                // TODO 登录用户
                hotel.setModifier("SYSTEM");
                count = syHotelService.updateById(hotel);
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
     * @param qo    酒店
     * @param query 分页
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryList(SyHotelQuery qo, PageQuery query, Model model) {


        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);

        try {
            // 加载酒店类型
            loadHotelType(model);

            if (StringUtils.isBlank(qo.getCnName())) {
                qo.setCnName(null);
            }


            if (StringUtils.isBlank(qo.getSubType())) {
                qo.setSubType(null);
            }


            if (StringUtils.isBlank(qo.getAddress())) {
                qo.setAddress(null);
            }

            // 2、查询条数
            Long count = syHotelService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyHotel> list = syHotelService.queryListByParam(qo);


            for (SyHotel temp : list) {
                temp.setBannerPicUrlList(CommonUtil.analyzePicUrl(temp.getBannerPicUrl()));
                temp.setPicUrlList(CommonUtil.analyzePicUrl(temp.getPicUrl()));
            }

            model.addAttribute("list", list);
            model.addAttribute("hotel", qo);
        } catch (Throwable e) {
            LOG.error("queryList is exception", e);
        }

        return VmConstans.HOTEL_MNG;
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
            // 加载酒店类型
            loadHotelType(model);
            SyHotel hotel = syHotelService.selectById(id);

            if(null==hotel){
                return VmConstans.HOTEL_DETAIL;
            }
            model.addAttribute("picUrlList", CommonUtil.analyzePicUrl(hotel.getPicUrl()));
            model.addAttribute("bannerPicUrlList", CommonUtil.analyzePicUrl(hotel.getBannerPicUrl()));
            model.addAttribute("hotel", hotel);
        } catch (Exception e) {
            LOG.error("查询酒店[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询酒店[" + id + "]出现异常");
        }

        return VmConstans.HOTEL_DETAIL;
    }


    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(required = true, value = "id") Long id, Model model) {
        try {
            // 加载酒店类型
            loadHotelType(model);
            SyHotel hotel = syHotelService.selectById(id);

            // 构建 图片地址json字符串
            CommonUtil commonUtil = new CommonUtil();
            hotel.setPicUrlListStr(commonUtil.buildPicUrl(hotel.getPicUrl()));
            hotel.setBannerPicUrlListStr(commonUtil.buildPicUrl(hotel.getBannerPicUrl()));

            model.addAttribute("hotel", hotel);
        } catch (Exception e) {
            LOG.error("查询酒店[" + id + "]出现异常", e);
            model.addAttribute(TourConstants.CODE_KEY, "2");
            model.addAttribute(TourConstants.MSG_KEY, "查询酒店[" + id + "]出现异常");
        }

        return VmConstans.HOTEL_EDIT;
    }


    /**
     * 更新酒店状态
     *
     * @param id
     */
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam(required = true, value = "id") String id,
                             @RequestParam(required = true, value = "status") Integer status) {
        JSONObject res = new JSONObject();
        try {
            SyHotel param = new SyHotel();
            param.setId(Long.valueOf(id));
            param.setStatus(status);

            // TODO 更新者
//            param.setModifier();
            int count = syHotelService.updateById(param);

            if (count > 0) {
                res.put(TourConstants.CODE_KEY, "1");
                res.put(TourConstants.MSG_KEY, "删除成功");
            } else {
                res.put(TourConstants.CODE_KEY, "2");
                res.put(TourConstants.MSG_KEY, "删除失败");
            }

        } catch (Throwable e) {
            LOG.error("del exception", e);
            res.put(TourConstants.CODE_KEY, "2");
            res.put(TourConstants.MSG_KEY, "删除出现异常");
        }

        return res;
    }


    /**
     * 酒店基本参数校验
     *
     * @param hotel 酒店
     */
    private void checkParam(SyHotel hotel) {

        Assert.notNull(hotel, "hotel 不能为空");
//        Assert.hasText(hotel.getAddress(), "地址为空");
//        Assert.hasText(hotel.getPhone(), "电话为空");

        if(StringUtils.isNotBlank(hotel.getPicUrl())){
            hotel.setPicUrl(hotel.getPicUrl().replace(TourConstants.PIC_URL_PREFIX+TourConstants.remoteFilePath,""));
        }

        if(StringUtils.isNotBlank(hotel.getBannerPicUrl())){
            hotel.setBannerPicUrl(hotel.getBannerPicUrl().replace(TourConstants.PIC_URL_PREFIX+TourConstants.remoteFilePath,""));
        }

//        Assert.notNull(hotel.getCnName(), "中文名称不能为空");
//        Assert.notNull(hotel.getAddress(), "地址不能为空");

    }

    /**
     * 加载酒店类型
     *
     * @param model
     */
    private void loadHotelType(Model model) {


        List<CodeName> fatherList = new ArrayList<CodeName>();

        CodeName codeNameSub1 = new CodeName();
        codeNameSub1.setCode(String.valueOf(HotelType.HOTEL.getCode()));
        codeNameSub1.setName(HotelType.HOTEL.getName());

        CodeName codeNameSub2 = new CodeName();
        codeNameSub2.setCode(String.valueOf(HotelType.HOMESTAY.getCode()));
        codeNameSub2.setName(HotelType.HOMESTAY.getName());

        CodeName codeNameSub3 = new CodeName();
        codeNameSub3.setCode(String.valueOf(HotelType.MIX.getCode()));
        codeNameSub3.setName(HotelType.MIX.getName());

        fatherList.add(codeNameSub1);
        fatherList.add(codeNameSub2);
        fatherList.add(codeNameSub3);

        List<CodeName> subList = new ArrayList<CodeName>();

        CodeName codeName01 = new CodeName();
        codeName01.setCode(String.valueOf(HotelSubType.ECONOMICAL.getCode()));
        codeName01.setName(HotelSubType.ECONOMICAL.getName());

        CodeName codeName02 = new CodeName();
        codeName02.setCode(String.valueOf(HotelSubType.BUSINESS.getCode()));
        codeName02.setName(HotelSubType.BUSINESS.getName());

        CodeName codeName03 = new CodeName();
        codeName03.setCode(String.valueOf(HotelSubType.DELUXE.getCode()));
        codeName03.setName(HotelSubType.DELUXE.getName());

        CodeName codeName04 = new CodeName();
        codeName04.setCode(String.valueOf(HotelSubType.INTERNATIONAL.getCode()));
        codeName04.setName(HotelSubType.INTERNATIONAL.getName());

        subList.add(codeName01);
        subList.add(codeName02);
        subList.add(codeName03);
        subList.add(codeName04);

        List<CodeName> levelList = new ArrayList<CodeName>();

        CodeName codeName1 = new CodeName();
        codeName1.setCode(StarType.ONE_STAR.getCode());
        codeName1.setName(StarType.ONE_STAR.getName());

        CodeName codeName2 = new CodeName();
        codeName2.setCode(StarType.TWO_STAR.getCode());
        codeName2.setName(StarType.TWO_STAR.getName());

        CodeName codeName3 = new CodeName();
        codeName3.setCode(StarType.THREE_STAR.getCode());
        codeName3.setName(StarType.THREE_STAR.getName());

        CodeName codeName4 = new CodeName();
        codeName4.setCode(StarType.FOUR_STAR.getCode());
        codeName4.setName(StarType.FOUR_STAR.getName());

        CodeName codeName5 = new CodeName();
        codeName5.setCode(StarType.FIVE_STAR.getCode());
        codeName5.setName(StarType.FIVE_STAR.getName());

        CodeName codeName6 = new CodeName();
        codeName6.setCode(StarType.SIX_STAR.getCode());
        codeName6.setName(StarType.SIX_STAR.getName());

        CodeName codeName7 = new CodeName();
        codeName7.setCode(StarType.SENVEN_STAR.getCode());
        codeName7.setName(StarType.SENVEN_STAR.getName());

        levelList.add(codeName1);
        levelList.add(codeName2);
        levelList.add(codeName3);
        levelList.add(codeName4);
        levelList.add(codeName5);
        levelList.add(codeName6);
        levelList.add(codeName7);

        model.addAttribute("fatherList", fatherList);
        model.addAttribute("subList", subList);
        model.addAttribute("levelList", levelList);

    }

}
