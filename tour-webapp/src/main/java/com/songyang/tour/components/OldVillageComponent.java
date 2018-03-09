package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/4.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.abstracts.AbstractBizComponent;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.ModuleType;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.BaseLabel;
import com.songyang.tour.pojo.SyOldVillage;
import com.songyang.tour.query.SyOldVillageQuery;
import com.songyang.tour.service.SyOldVillageService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.IndexBaseParamVO;
import com.songyang.tour.vo.OldVillageDetailVO;
import com.songyang.tour.vo.OldVillageListVO;
import com.songyang.tour.vo.OldVillageVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 古村落组件
 *
 * @author
 * @create 2017-10-04 22:57
 **/
@Component
public class OldVillageComponent extends AbstractBizComponent {

    private Logger logger = LoggerFactory.getLogger(OldVillageComponent.class);

    @Resource
    private SyOldVillageService syOldVillageService;

    /**
     * 首页初始化古村落
     *
     * @return
     */
    public OldVillageVO initOldVillage() {
        OldVillageVO vo = new OldVillageVO();
        //古村落list
        List<IndexBaseParamVO> villageList = new ArrayList<>();

        SyOldVillageQuery query = new SyOldVillageQuery();
        List<SortColumn> sortColumns = new ArrayList<>();
        query.setOffset(0);
        query.setRows(3);
        query.setStatus(TourConstants.STATUS.NORMAL);
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        query.setSorts(sortColumns);
        List<SyOldVillage> oldVillages = syOldVillageService.queryListByParam(query);
        if (CollectionUtils.isEmpty(oldVillages)) {
            //TODO
            return vo;
        }
        IndexBaseParamVO paramVO = null;
        for (SyOldVillage syOldVillage : oldVillages) {
            paramVO = new IndexBaseParamVO();
            paramVO.setId(syOldVillage.getId());
            paramVO.setTitle(syOldVillage.getCnName());
            paramVO.setType(ModuleType.OLD_VILLAGE.getCode());
            paramVO.setPicUrl(CommonUtil.analyzeOnePicUrl(syOldVillage.getPicUrl()));
            villageList.add(paramVO);

        }
        vo.setVillageList(villageList);
        return vo;
    }


    /**
     * 古村落List
     *
     * @param param
     * @return
     */
    public OldVillageListVO oldVillageList(JSONObject param) {
        OldVillageListVO vo = new OldVillageListVO();

        if (param == null) {
            logger.warn("OldVillageComponent#oldVillageList_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //请求起始页
        Integer offset = param.getInteger("offset");
        //请求多少行
        Integer rows = param.getInteger("rows");

        SyOldVillageQuery stsq2 = new SyOldVillageQuery();
        stsq2.setOffset(0);
        stsq2.setRows(10);
        stsq2.setHot(TourConstants.HOT.YES);
        stsq2.setStatus(TourConstants.STATUS.NORMAL);
        stsq2.setHot(TourConstants.HOT.YES);
        List<SortColumn> sortColumns2 = new ArrayList<>();
        sortColumns2.add(new SortColumn("create_time", SortMode.DESC));
        stsq2.setSorts(sortColumns2);
        List<SyOldVillage> syOldVillages = syOldVillageService.queryListByParam(stsq2);

        //设置hot List
        List<JSONObject> hotOldVillageList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(syOldVillages)) {
            constructOldVillageDataList(syOldVillages, hotOldVillageList);
            vo.setHotList(hotOldVillageList);
        }


        //分页数据
        int prePageSize = rows + 1;
        SyOldVillageQuery stsq1 = new SyOldVillageQuery();
        stsq1.setOffset(offset);
        stsq1.setRows(prePageSize);
        stsq1.setStatus(TourConstants.STATUS.NORMAL);
//        stsq1.setHot(TourConstants.HOT.NO);
        List<SortColumn> sortColumns1 = new ArrayList<>();
        sortColumns1.add(new SortColumn("create_time", SortMode.DESC));
        stsq1.setSorts(sortColumns1);
        List<SyOldVillage> syOldVillageList = syOldVillageService.queryListByParam(stsq1);

        if (CollectionUtils.isEmpty(syOldVillageList)) {
            vo.setEndFlag(true);
            vo.setOldVillageList(new ArrayList<JSONObject>());
            return vo;
        }
        //结束标示
        vo.setEndFlag(false);

        if (syOldVillageList.size() == prePageSize) {
            syOldVillageList = syOldVillageList.subList(0, prePageSize - 1);
        } else {
            vo.setEndFlag(true);
            if (offset != 0) {
                //到底标题
                vo.setEndTitle(TourConstants.END_TITLE_MSG);
            }
        }
        // 设置 下一个请求的偏移量
        vo.setOffset(offset + syOldVillageList.size());

        List<JSONObject> jsonObjectList = new ArrayList<>();
        constructOldVillageDataList(syOldVillageList, jsonObjectList);
        vo.setOldVillageList(jsonObjectList);
        return vo;

    }


    public OldVillageDetailVO oldVillageDetail(JSONObject param) {
        OldVillageDetailVO vo = new OldVillageDetailVO();
        if (param == null) {
            logger.warn("OldVillageComponent#oldVillageDetail_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //古村落id
        Long id = param.getLong("id");
        if (null == id) {
            logger.warn("OldVillageComponent#oldVillageDetail_prodId_is_null");
            return vo;
        }
        SyOldVillage syOldVillage = syOldVillageService.selectById(id);
        if (null == syOldVillage) {
            logger.warn("OldVillageComponent#oldVillageDetail_syProd_is_null>>>id:" + id);
            return vo;
        }

        //bannerList
        vo.setBannerList(CommonUtil.analyzePicUrl(syOldVillage.getPicUrl()));

        vo.setCnName(syOldVillage.getCnName());
        vo.setEnName(syOldVillage.getEnName());

        vo.setLongitude(syOldVillage.getLongitude());
        vo.setLatitude(syOldVillage.getLatitude());

        if (StringUtils.isNotBlank(syOldVillage.getVillageDesc())) {
            vo.setVillageDescTitle("古村落简介");
            vo.setVillageDesc(syOldVillage.getVillageDesc());
            vo.setVillageDescPicUrlList(CommonUtil.analyzePicUrl(syOldVillage.getPicUrl()));
        }

        if (StringUtils.isNotBlank(syOldVillage.getFolkActPicUrl()) ||
                StringUtils.isNotBlank(syOldVillage.getFolkActDesc())) {
            vo.setFolkDescTitle("民俗活动");
            vo.setFolkActDescPicUrlList(CommonUtil.analyzePicUrl(syOldVillage.getFolkActPicUrl()));
            vo.setFolkActDesc(syOldVillage.getFolkActDesc());
        }

        if (StringUtils.isNotBlank(syOldVillage.getHistoryDesc()) ||
                StringUtils.isNotBlank(syOldVillage.getHistoryPicUrl())) {
            vo.setHistoryDescTitle("历史文化");
            vo.setHistoryDesc(syOldVillage.getHistoryDesc());
            vo.setHistoryDescPicUrlList(CommonUtil.analyzePicUrl(syOldVillage.getHistoryPicUrl()));
        }

        if (StringUtils.isNotBlank(syOldVillage.getSpecialDesc()) ||
                StringUtils.isNotBlank(syOldVillage.getSpecialPicUrl())) {
            vo.setSpecialDescTitle("特色描述");
            vo.setSpecialDesc(syOldVillage.getSpecialDesc());
            vo.setSpecialDescPicUrlList(CommonUtil.analyzePicUrl(syOldVillage.getSpecialPicUrl()));
        }

        List<BaseLabel> baseLabelList = new ArrayList<>();
        if (StringUtils.isNotBlank(syOldVillage.getAddress()))

        {
            baseLabelList.add(new BaseLabel("地址", syOldVillage.getAddress()));
        }
        if (StringUtils.isNotBlank(syOldVillage.getArea()))

        {
            baseLabelList.add(new BaseLabel("村落面积", syOldVillage.getArea()));
        }
        if (StringUtils.isNotBlank(syOldVillage.getPopulationDesc()))

        {
            baseLabelList.add(new BaseLabel("常住人口", syOldVillage.getPopulationDesc()));
        }
        if (StringUtils.isNotBlank(syOldVillage.getServices()))

        {
            baseLabelList.add(new BaseLabel("服务设施", syOldVillage.getServices()));
        }

        vo.setLabelList(baseLabelList);
        return vo;

    }
}
