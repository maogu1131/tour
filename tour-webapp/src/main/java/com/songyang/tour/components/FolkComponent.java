package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/5.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.abstracts.AbstractBizComponent;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.ModuleType;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.BaseLabel;
import com.songyang.tour.pojo.SyFolk;
import com.songyang.tour.query.SyFolkQuery;
import com.songyang.tour.query.SyFolkReserveOrderQuery;
import com.songyang.tour.service.SyFolkReserveOrderService;
import com.songyang.tour.service.SyFolkService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.DateUtil;
import com.songyang.tour.vo.FolkDetailVO;
import com.songyang.tour.vo.FolkParamVO;
import com.songyang.tour.vo.FolkVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 本土民俗组件
 *
 * @author
 * @create 2017-10-05 22:34
 **/
@Component
public class FolkComponent extends AbstractBizComponent {

    private Logger logger = LoggerFactory.getLogger(FolkComponent.class);

    @Resource
    private SyFolkService syFolkService;

    @Resource
    private SyFolkReserveOrderService syFolkReserveOrderService;


    public FolkVO initFolkVO() {
        FolkVO vo = new FolkVO();
        //古村落list
        List<FolkParamVO> folkParamList = new ArrayList<>();

        SyFolkQuery query = new SyFolkQuery();
        List<SortColumn> sortColumns = new ArrayList<>();
        query.setOffset(0);
        query.setRows(2);
        query.setStatus(TourConstants.STATUS.NORMAL);
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        query.setSorts(sortColumns);
        List<SyFolk> folks = syFolkService.queryListByParam(query);
        if (CollectionUtils.isEmpty(folks)) {
            //TODO
            return vo;
        }
        FolkParamVO paramVO = null;
        for (SyFolk syFolk : folks) {
            paramVO = new FolkParamVO();
            paramVO.setId(syFolk.getId());
            paramVO.setType(ModuleType.FOLK.getCode());
            paramVO.setTitle(syFolk.getTitle());
            paramVO.setAddress(syFolk.getAddress());
            paramVO.setTimeDesc(DateUtil.format(syFolk.getStartTime(), DateUtil.shortChineseDtFormat_Mdd) + "-"
                    + DateUtil.format(syFolk.getEndTime(), DateUtil.shortChineseDtFormat_Mdd));
            paramVO.setPicUrl(CommonUtil.analyzeOnePicUrl(syFolk.getPicUrl()));
            folkParamList.add(paramVO);
        }
        vo.setFolkList(folkParamList);
        return vo;
    }

    public FolkVO folkList(JSONObject param) {
        FolkVO vo = new FolkVO();

        if (param == null) {
            logger.warn("FolkComponent#folkList_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //请求起始页
        Integer offset = param.getInteger("offset");
        //请求多少行
        Integer rows = param.getInteger("rows");


        SyFolkQuery sfq2 = new SyFolkQuery();
        sfq2.setOffset(0);
        sfq2.setRows(10);
        sfq2.setHot(TourConstants.HOT.YES);
        sfq2.setStatus(TourConstants.STATUS.NORMAL);
        List<SortColumn> sortColumns2 = new ArrayList<>();
        sortColumns2.add(new SortColumn("create_time", SortMode.DESC));
        sfq2.setSorts(sortColumns2);
        List<SyFolk> hotFolkDbList = syFolkService.queryListByParam(sfq2);

        //设置hot List
        List<FolkParamVO> hotFolkList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(hotFolkDbList)) {
            constructFolkDataList(hotFolkDbList, hotFolkList);
            vo.setHotList(hotFolkList);
        }


        //分页数据
        int prePageSize = rows + 1;
        SyFolkQuery stsq1 = new SyFolkQuery();
        stsq1.setOffset(offset);
        stsq1.setRows(prePageSize);
        stsq1.setStatus(TourConstants.STATUS.NORMAL);
//        stsq1.setHot(TourConstants.HOT.NO);
        List<SortColumn> sortColumns1 = new ArrayList<>();
        sortColumns1.add(new SortColumn("create_time", SortMode.DESC));
        stsq1.setSorts(sortColumns1);
        List<SyFolk> syFolkList = syFolkService.queryListByParam(stsq1);

        if (CollectionUtils.isEmpty(syFolkList)) {
            vo.setEndFlag(true);
            vo.setFolkList(new ArrayList<FolkParamVO>());
            return vo;
        }
        //结束标示
        vo.setEndFlag(false);

        if (syFolkList.size() == prePageSize) {
            syFolkList = syFolkList.subList(0, prePageSize - 1);
        } else {
            vo.setEndFlag(true);
            if (offset != 0) {
                //到底标题
                vo.setEndTitle(TourConstants.END_TITLE_MSG);
            }
        }
        // 设置 下一个请求的偏移量
        vo.setOffset(offset + syFolkList.size());

        List<FolkParamVO> dataFolkList = new ArrayList<>();
        constructFolkDataList(syFolkList, dataFolkList);
        vo.setFolkList(dataFolkList);
        return vo;
    }

    public FolkDetailVO folkDetail(JSONObject param) {
        FolkDetailVO vo = new FolkDetailVO();
        if (param == null) {
            logger.warn("FolkComponent#folkDetail_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }


        //民俗活动id
        Long id = param.getLong("id");
        String userId = param.getString("userId");
        if (null == id) {
            logger.warn("FolkComponent#folkDetail_folkId_is_null");
            return vo;
        }

        SyFolk syFolk = syFolkService.selectById(id);
        if (null == syFolk) {
            logger.warn("FolkComponent#folkDetail_syFolk_is_null");
            return vo;
        }

        vo.setBannerUrlList(CommonUtil.analyzePicUrl(syFolk.getPicUrl()));

        List<BaseLabel> labelList = new ArrayList<>();

        if (StringUtils.isNotBlank(syFolk.getAddress())) {
            labelList.add(new BaseLabel("地址", syFolk.getAddress()));
        }

        if ((syFolk.getStartTime() != null)) {
            labelList.add(new BaseLabel("开始时间", DateUtil.format(syFolk.getStartTime(), DateUtil.chineseDtFormat)));
        }

        if ((syFolk.getEndTime() != null)) {
            labelList.add(new BaseLabel("结束时间", DateUtil.format(syFolk.getEndTime(), DateUtil.chineseDtFormat)));
        }
        if (StringUtils.isNotBlank(syFolk.getChargeDesc())) {
            labelList.add(new BaseLabel("活动费用", syFolk.getChargeDesc()));
        }

        if (StringUtils.isNotBlank(syFolk.getPlayTips())) {
            labelList.add(new BaseLabel("注意事项", syFolk.getPlayTips()));
        }

        vo.setLabelList(labelList);
        vo.setTitle(syFolk.getTitle());
        vo.setLatitude(syFolk.getLatitude());
        vo.setLongitude(syFolk.getLongitude());
        vo.setTel(syFolk.getPhone());

        //bannerList
        vo.setBannerUrlList(CommonUtil.analyzePicUrl(syFolk.getBannerUrl()));


        if (StringUtils.isNotBlank(syFolk.getPicUrl()) || StringUtils.isNotBlank(syFolk.getDesc())) {
            vo.setFolkActDescTitle("活动详情");
            vo.setFolkActDesc(syFolk.getDesc());
            vo.setFolkActDescPicUrlList(CommonUtil.analyzePicUrl(syFolk.getPicUrl()));
        }


        if (StringUtils.isNotBlank(syFolk.getSpecialPicUrl()) || StringUtils.isNotBlank(syFolk.getSpecialDesc())) {
            vo.setSpecialDesc(syFolk.getSpecialDesc());
            vo.setSpecialDescTitle("特色活动");
            vo.setSpecialDescPicUrlList(CommonUtil.analyzePicUrl(syFolk.getSpecialPicUrl()));
        }
        // 是否预约状态1：预约过， 2：未预约  3:已结束
        if (StringUtils.isBlank(userId)) {
            vo.setReserveStatus(2);
            return vo;
        }

        SyFolkReserveOrderQuery query = new SyFolkReserveOrderQuery();
        query.setUserId(userId);
        query.setFolkId(id);
        Long count = syFolkReserveOrderService.queryCountByParam(query);
        if (null != count && count > 0) {
            vo.setReserveStatus(1);
            return vo;
        }
        vo.setReserveStatus(2);

        if (new Date().after(syFolk.getEndTime())) {
            vo.setReserveStatus(3);
        }
        return vo;
    }
}

