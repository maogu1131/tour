package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/9.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.pojo.SyTravelStrategy;
import com.songyang.tour.query.SyTravelStrategyQuery;
import com.songyang.tour.service.SyTravelStrategyService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.TravelStrategyDetailVO;
import com.songyang.tour.vo.TravelStrategyListVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 旅游攻略组件
 *
 * @author
 * @create 2017-10-09 20:49
 **/
@Component
public class TravelStrategyComponent {

    private Logger logger = LoggerFactory.getLogger(TravelStrategyComponent.class);

    @Resource
    private SyTravelStrategyService syTravelStrategyService;


    public TravelStrategyListVO travelStrategyList(JSONObject param) {

        TravelStrategyListVO vo = new TravelStrategyListVO();


        if (param == null) {
            logger.warn("TravelStrategyComponent#restaurantList_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //请求起始页
        Integer offset = param.getInteger("offset");
        //请求多少行
        Integer rows = param.getInteger("rows");


        SyTravelStrategyQuery stsq2 = new SyTravelStrategyQuery();
        stsq2.setOffset(0);
        stsq2.setRows(10);
        stsq2.setHot(TourConstants.HOT.YES);
        stsq2.setStatus(TourConstants.STATUS.NORMAL);
        List<SortColumn> sortColumns2 = new ArrayList<>();
        sortColumns2.add(new SortColumn("create_time", SortMode.DESC));
        stsq2.setSorts(sortColumns2);
        List<SyTravelStrategy> hotStrategyDbList = syTravelStrategyService.queryListByParam(stsq2);

        //设置hot List
        List<JSONObject> hotStrategyList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(hotStrategyDbList)) {
            constructDataList(hotStrategyDbList, hotStrategyList);
            vo.setHotList(hotStrategyList);
        }


        //分页数据
        int prePageSize = rows + 1;
        SyTravelStrategyQuery stsq1 = new SyTravelStrategyQuery();
        stsq1.setOffset(offset);
        stsq1.setRows(prePageSize);
        stsq1.setStatus(TourConstants.STATUS.NORMAL);
        //stsq1.setHot(TourConstants.HOT.NO);
        List<SortColumn> sortColumns1 = new ArrayList<>();
        sortColumns1.add(new SortColumn("create_time", SortMode.DESC));
        stsq1.setSorts(sortColumns1);
        List<SyTravelStrategy> syTravelStrategyList = syTravelStrategyService.queryListByParam(stsq1);

        if (CollectionUtils.isEmpty(syTravelStrategyList)) {
            vo.setEndFlag(true);
            vo.setTravelStrategyList(new ArrayList<JSONObject>());
            return vo;
        }
        //结束标示
        vo.setEndFlag(false);

        if (syTravelStrategyList.size() == prePageSize) {
            syTravelStrategyList = syTravelStrategyList.subList(0, prePageSize - 1);
        } else {
            vo.setEndFlag(true);
            if (offset != 0) {
                //到底标题
                vo.setEndTitle(TourConstants.END_TITLE_MSG);
            }
        }
        // 设置 下一个请求的偏移量
        vo.setOffset(offset + syTravelStrategyList.size());

        List<JSONObject> tavelStrategyList = new ArrayList<>();
        constructDataList(syTravelStrategyList, tavelStrategyList);
        vo.setTravelStrategyList(tavelStrategyList);
        return vo;

    }


    public TravelStrategyDetailVO   travelStrategyDetail(JSONObject param) {
        TravelStrategyDetailVO vo = new TravelStrategyDetailVO();

        if (param == null) {
            logger.warn("TravelStrategyComponent#travelStrategyDetail_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //旅游攻略id
        Long id = param.getLong("id");
        if (null == id) {
            logger.warn("TravelStrategyComponent#travelStrategyDetail_is_null");
            return vo;
        }

        SyTravelStrategy syTravelStrategy = syTravelStrategyService.selectById(id);
        if (null == syTravelStrategy) {
            logger.warn("TravelStrategyComponent#travelStrategyDetail_object_is_null>>>>id:" + id);
        }

        vo.setDesc(syTravelStrategy.getDesc());
        vo.setTitle(syTravelStrategy.getTitle());
        vo.setType(syTravelStrategy.getType());
        vo.setJumpUrl(syTravelStrategy.getJumpUrl());
        vo.setPicUrlList(CommonUtil.analyzePicUrl(syTravelStrategy.getPicUrl()));
        return vo;
    }

    private void constructDataList(List<SyTravelStrategy> syTravelStrategyList, List<JSONObject> tavelStrategyList) {
        JSONObject jo = null;
        for (SyTravelStrategy syTravelStrategy : syTravelStrategyList) {
            jo = new JSONObject();
            //id
            jo.put("id", syTravelStrategy.getId());

            //图片
            jo.put("picUrl", CommonUtil.analyzeOnePicUrl(syTravelStrategy.getPicUrl()));

            //图片标题
            jo.put("title", syTravelStrategy.getTitle());


            if (StringUtils.isNotBlank(syTravelStrategy.getDesc())) {
                if (syTravelStrategy.getDesc().length() > 33) {
                    jo.put("desc", StringUtils.substring(syTravelStrategy.getDesc(), 0, 33));
                } else {
                    jo.put("desc", syTravelStrategy.getDesc());
                }
            }

            jo.put("type", syTravelStrategy.getType());

            jo.put("jumpUrl", syTravelStrategy.getJumpUrl());


            tavelStrategyList.add(jo);
        }
    }
}
