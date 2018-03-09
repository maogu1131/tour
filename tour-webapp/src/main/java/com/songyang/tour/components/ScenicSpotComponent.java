package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/14.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.abstracts.AbstractBizComponent;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.BaseLabel;
import com.songyang.tour.pojo.SyScenicSpot;
import com.songyang.tour.pojo.SyScenicSpotModule;
import com.songyang.tour.query.SyScenicSpotQuery;
import com.songyang.tour.service.SyScenicSpotModuleService;
import com.songyang.tour.service.SyScenicSpotService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.ScenicSpotDetailVO;
import com.songyang.tour.vo.ScenicSpotListVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 景区组件
 *
 * @author
 * @create 2017-10-14 16:22
 **/
@Component
public class ScenicSpotComponent extends AbstractBizComponent {

    private Logger logger = LoggerFactory.getLogger(ScenicSpotComponent.class);

    @Resource
    private SyScenicSpotService syScenicSpotService;

    @Resource
    private SyScenicSpotModuleService syScenicSpotModuleService;

    public ScenicSpotListVO scenicSpotList(JSONObject param) {
        ScenicSpotListVO vo = new ScenicSpotListVO();

        if (param == null) {
            logger.warn("ScenicSpotComponent#scenicSpotList_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //请求起始页
        Integer offset = param.getInteger("offset");
        //请求多少行
        Integer rows = param.getInteger("rows");

        //景区模块类型
        Integer type = param.getInteger("type");


        SyScenicSpotQuery stsq2 = new SyScenicSpotQuery();
        stsq2.setOffset(0);
        stsq2.setRows(10);
        stsq2.setHot(TourConstants.HOT.YES);
        stsq2.setStatus(TourConstants.STATUS.NORMAL);
        stsq2.setType(type);
        List<SortColumn> sortColumns2 = new ArrayList<>();
        sortColumns2.add(new SortColumn("create_time", SortMode.DESC));
        stsq2.setSorts(sortColumns2);
        List<SyScenicSpot> hotScenicSpotDBList = syScenicSpotService.queryListByParam(stsq2);

        //设置hot List
        List<JSONObject> hotScenicSpotList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(hotScenicSpotDBList)) {
            constructScenicSpotDataList(hotScenicSpotDBList, hotScenicSpotList);
            vo.setHotList(hotScenicSpotList);
        }


        //分页数据
        int prePageSize = rows + 1;
        SyScenicSpotQuery stsq1 = new SyScenicSpotQuery();
        stsq1.setOffset(offset);
        stsq1.setRows(prePageSize);
        stsq1.setStatus(TourConstants.STATUS.NORMAL);
//        stsq1.setHot(TourConstants.HOT.NO);
        stsq1.setType(type);
        List<SortColumn> sortColumns1 = new ArrayList<>();
        sortColumns1.add(new SortColumn("create_time", SortMode.DESC));
        stsq1.setSorts(sortColumns1);
        List<SyScenicSpot> syScenicSpotList = syScenicSpotService.queryListByParam(stsq1);

        if (CollectionUtils.isEmpty(syScenicSpotList)) {
            vo.setEndFlag(true);
            vo.setScenicSpotList(new ArrayList<JSONObject>());
            return vo;
        }
        //结束标示
        vo.setEndFlag(false);

        if (syScenicSpotList.size() == prePageSize) {
            syScenicSpotList = syScenicSpotList.subList(0, prePageSize - 1);
        } else {
            vo.setEndFlag(true);
            if (offset != 0) {
                //到底标题
                vo.setEndTitle(TourConstants.END_TITLE_MSG);
            }
        }
        // 设置 下一个请求的偏移量
        vo.setOffset(offset + syScenicSpotList.size());

        List<JSONObject> jsonObjectList = new ArrayList<>();
        constructScenicSpotDataList(syScenicSpotList, jsonObjectList);
        vo.setScenicSpotList(jsonObjectList);
        return vo;

    }

    public ScenicSpotDetailVO scenicSpotDetail(JSONObject param) {
        ScenicSpotDetailVO vo = new ScenicSpotDetailVO();
        if (param == null) {
            logger.warn("ScenicSpotComponent#scenicSpotDetail_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //景区id
        Long id = param.getLong("id");
        if (null == id) {
            logger.warn("ScenicSpotComponent#scenicSpotDetail_scenicSpotId_is_null");
            return vo;
        }
        SyScenicSpot syScenicSpot = syScenicSpotService.selectById(id);
        if (null == syScenicSpot) {
            logger.warn("ScenicSpotComponent#scenicSpotDetail_syScenicSpot_is_null>>>id:" + id);
            return vo;
        }
        vo.setCnName(syScenicSpot.getCnName());
        List<BaseLabel> labelList = new ArrayList<>();
        labelList.add(new BaseLabel("地址", syScenicSpot.getAddress()));

        SyScenicSpotModule module = syScenicSpotModuleService.selectById(syScenicSpot.getType());
        if (null == module) {
            labelList.add(new BaseLabel("景区类型", "景区"));
        } else {
            labelList.add(new BaseLabel("景区类型", module.getTitle()));
        }

        if (StringUtils.isNotBlank(syScenicSpot.getOpenTimeDesc())) {
            labelList.add(new BaseLabel("开发时间", syScenicSpot.getOpenTimeDesc()));
        }

        if (StringUtils.isNotBlank(syScenicSpot.getPlayTips())) {
            labelList.add(new BaseLabel("游玩提示", syScenicSpot.getPlayTips()));
        }

        if (StringUtils.isNotBlank(syScenicSpot.getTrafficDesc())) {
            labelList.add(new BaseLabel("停车场", syScenicSpot.getTrafficDesc()));
        }

        //labelList.add(new BaseLabel("地理位置", syScenicSpot.getLongitude() + "|" + syScenicSpot.getLatitude()));
        //labelList.add(new BaseLabel("语音讲解", syScenicSpot.getTourAudioUrl()));
        //labelList.add(new BaseLabel("咨询电话", syScenicSpot.getPhone()));
        vo.setLabelList(labelList);

        vo.setTel(syScenicSpot.getPhone());
        vo.setLatitude(syScenicSpot.getLatitude());
        vo.setLongitude(syScenicSpot.getLongitude());
        vo.setTourAudioUrl(syScenicSpot.getTourAudioUrl());

        vo.setBannerUrlList(CommonUtil.analyzePicUrl(syScenicSpot.getPicUrl()));

        if (StringUtils.isNotBlank(syScenicSpot.getPicUrl()) || StringUtils.isNotBlank(syScenicSpot.getDesc())) {
            vo.setScenicSpotDesc(syScenicSpot.getDesc());
            vo.setScenicSpotPicUrlList(CommonUtil.analyzePicUrl(syScenicSpot.getPicUrl()));
            vo.setScenicSpotTitle("景区简介");
        }

        if (StringUtils.isNotBlank(syScenicSpot.getSpecialDesc()) || StringUtils.isNotBlank(syScenicSpot.getSpecialPicUrl())) {
            vo.setSpecialDesc(syScenicSpot.getSpecialDesc());
            vo.setSpecialDescPicUrlList(CommonUtil.analyzePicUrl(syScenicSpot.getSpecialPicUrl()));
            vo.setSpecialDescTitle("特色项目描述");
        }

        if(StringUtils.isNotBlank(syScenicSpot.getRouteDesc())) {
            vo.setRouteDescTitle("推荐路线");
            vo.setRouteDesc(syScenicSpot.getRouteDesc());
        }
        //vo.setNearbyTitle("周边推荐");

        vo.setPrice(syScenicSpot.getPrice());
        return vo;
    }
}
