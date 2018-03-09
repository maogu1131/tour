package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/9.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.HotelType;
import com.songyang.tour.enums.ModuleType;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.BaseLabel;
import com.songyang.tour.pojo.SyHotel;
import com.songyang.tour.query.SyHotelQuery;
import com.songyang.tour.service.SyHotelService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.HotelDetailVO;
import com.songyang.tour.vo.HotelListVO;
import com.songyang.tour.vo.HotelVO;
import com.songyang.tour.vo.IndexBaseParamVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 酒店组件
 *
 * @author
 * @create 2017-10-09 16:30
 **/
@Component
public class HotelComponent {

    private Logger logger = LoggerFactory.getLogger(HotelComponent.class);

    @Resource
    private SyHotelService syHotelService;


    public HotelVO initHotelInfo() {
        HotelVO vo = new HotelVO();
        //民宿list
        List<IndexBaseParamVO> hotelList = new ArrayList<>();

        SyHotelQuery query = new SyHotelQuery();
        List<SortColumn> sortColumns = new ArrayList<>();
        query.setOffset(0);
        query.setRows(3);
        query.setStatus(TourConstants.STATUS.NORMAL);
        query.setType(HotelType.HOMESTAY.getCode());
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        query.setSorts(sortColumns);
        List<SyHotel> syHotels = syHotelService.queryListByParam(query);
        if (CollectionUtils.isEmpty(syHotels)) {
            //TODO
            return vo;
        }
        IndexBaseParamVO paramVO = null;
        for (SyHotel syHotel : syHotels) {
            paramVO = new IndexBaseParamVO();
            paramVO.setId(syHotel.getId());
            paramVO.setTitle(syHotel.getCnName());
            paramVO.setType(ModuleType.HOTEL.getCode());
            paramVO.setPicUrl(CommonUtil.analyzeOnePicUrl(syHotel.getPicUrl()));
            hotelList.add(paramVO);

        }
        vo.setHotelList(hotelList);
        return vo;
    }


    public HotelListVO hotelList(JSONObject param) {

        if (param == null) {
            logger.warn("HotelBizComponent#restaurantList_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //请求起始页
        Integer offset = param.getInteger("offset");
        //请求多少行
        Integer rows = param.getInteger("rows");
        //类型：1-酒店，2-民宿
        Integer type = param.getInteger("type");

        HotelListVO vo = new HotelListVO();
        int prePageSize = rows + 1;
        SyHotelQuery shq = new SyHotelQuery();
        shq.setOffset(offset);
        shq.setRows(prePageSize);
        shq.setStatus(TourConstants.STATUS.NORMAL);
        shq.setType(type);
        List<SortColumn> sortColumns = new ArrayList<>();
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        shq.setSorts(sortColumns);
        List<SyHotel> hotelList = syHotelService.queryListByParam(shq);
        if (CollectionUtils.isEmpty(hotelList)) {
            vo.setEndFlag(true);
            vo.setHotelList(new ArrayList<JSONObject>());
            return vo;
        }
        //结束标示
        vo.setEndFlag(false);

        if (hotelList.size() == prePageSize) {
            hotelList = hotelList.subList(0, prePageSize - 1);
        } else {
            vo.setEndFlag(true);
            if (offset != 0) {
                //到底标题
                vo.setEndTitle(TourConstants.END_TITLE_MSG);
            }
        }

        // 设置 下一个请求的偏移量
        vo.setOffset(offset + hotelList.size());

        List<JSONObject> jsonObjects = new ArrayList<>();
        JSONObject jo = null;
        for (SyHotel hotel : hotelList) {
            jo = new JSONObject();

            jo.put("id", hotel.getId());

            //图片
            jo.put("picUrl", CommonUtil.analyzeOnePicUrl(hotel.getPicUrl()));

            //图片标题
            jo.put("title", hotel.getCnName());

            //副标题支持html
            jo.put("subTitle", hotel.getSubTitle());

            //地址
            jo.put("address", hotel.getAddress());

            // 舒适整洁|#383838
            jo.put("label", hotel.getLabel());

            jsonObjects.add(jo);
        }
        vo.setHotelList(jsonObjects);
        return vo;
    }


    public HotelDetailVO hotelDetail(JSONObject param) {
        HotelDetailVO vo = new HotelDetailVO();
        if (param == null) {
            logger.warn("HotelComponent#hotelDetail_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //民宿&酒店id
        Long id = param.getLong("id");
        if (null == id) {
            logger.warn("HotelComponent#hotelDetail_hotelId_is_null");
            return vo;
        }

        SyHotel syHotel = syHotelService.selectById(id);
        if (null == syHotel) {
            logger.warn("HotelComponent#hotelDetail_syHotel_is_null>>>id:" + id);
            return vo;
        }

        vo.setBannerPicUrlList(CommonUtil.analyzePicUrl(syHotel.getBannerPicUrl()));
        vo.setType(syHotel.getType());
        vo.setTitle(syHotel.getCnName());
        List<BaseLabel> labelList = new ArrayList<>();
        labelList.add(new BaseLabel("类型", HotelType.getNameByCode(syHotel.getType())));

        if (StringUtils.isNotBlank(syHotel.getAddress())) {
            labelList.add(new BaseLabel("地址", syHotel.getAddress()));
        }

        if (StringUtils.isNotBlank(syHotel.getOpenTimeDesc())) {
            labelList.add(new BaseLabel("营业描述", syHotel.getOpenTimeDesc()));
        }

        if (syHotel.getType() == HotelType.HOTEL.getCode()) {
            if (null != syHotel.getLevel() && syHotel.getLevel() != 1) {
                labelList.add(new BaseLabel("评级", syHotel.getLevel().toString()));
            }
        }

        vo.setLabelList(labelList);

        vo.setLatitude(syHotel.getLatitude());
        vo.setLongitude(syHotel.getLongitude());
        vo.setTel(syHotel.getPhone());
        if (StringUtils.isNotBlank(syHotel.getPicUrl()) || StringUtils.isNotBlank(syHotel.getDesc())) {
            vo.setDescTitle("简介");
            vo.setDescPicUrlList(CommonUtil.analyzePicUrl(syHotel.getPicUrl()));
            vo.setDesc(syHotel.getDesc());
        }
        vo.setLabel(syHotel.getLabel());
        return vo;
    }
}
