package com.songyang.tour.components;/**
 * Created by lenovo on 2017/11/1.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.ProdTypeEnum;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.pojo.SyEvaluate;
import com.songyang.tour.pojo.SyProd;
import com.songyang.tour.query.SyProdQuery;
import com.songyang.tour.query.SyTradeOrderQuery;
import com.songyang.tour.service.SyEvaluateService;
import com.songyang.tour.service.SyProdService;
import com.songyang.tour.service.SyTradeOrderService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.SessionUtil;
import com.songyang.tour.vo.ProdDetailVO;
import com.songyang.tour.vo.ProdListVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品组件
 *
 * @author
 * @create 2017-11-01 17:29
 **/
@Component
public class ProdComponent {

    private Logger logger = LoggerFactory.getLogger(ProdComponent.class);

    @Resource
    private SyProdService syProdService;

    @Resource
    private SyEvaluateService syEvaluateService;

    @Resource
    private SyTradeOrderService syTradeOrderService;


    public ProdListVO prodList(JSONObject param) {
        if (param == null) {
            logger.warn("ProdComponent#prodList_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //请求起始页
        Integer offset = param.getInteger("offset");
        //请求多少行
        Integer rows = param.getInteger("rows");

        ProdListVO vo = new ProdListVO();

        int prePageSize = rows + 1;
        SyProdQuery prodQuery = new SyProdQuery();
        prodQuery.setOffset(offset);
        prodQuery.setRows(prePageSize);
        prodQuery.setStatus(TourConstants.STATUS.NORMAL);
        List<SortColumn> sortColumns = new ArrayList<>();
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        prodQuery.setSorts(sortColumns);
        List<SyProd> foodList = syProdService.queryListByParam(prodQuery);
        if (CollectionUtils.isEmpty(foodList)) {
            vo.setEndFlag(true);
            vo.setProdList(new ArrayList<JSONObject>());
            return vo;
        }
        //结束标示
        vo.setEndFlag(false);

        if (foodList.size() == prePageSize) {
            foodList = foodList.subList(0, prePageSize - 1);
        } else {
            vo.setEndFlag(true);
            if (offset != 0) {
                //到底标题
                vo.setEndTitle(TourConstants.END_TITLE_MSG);
            }
        }

        // 设置 下一个请求的偏移量
        vo.setOffset(offset + foodList.size());

        List<JSONObject> jsonObjects = new ArrayList<>();
        JSONObject jo = null;
        for (SyProd syProd : foodList) {
            jo = new JSONObject();
            jo.put("id", syProd.getId());
            jo.put("title", syProd.getName());
            jo.put("picUrl", CommonUtil.analyzeOnePicUrl(syProd.getPicUrl()));
            jo.put("level", syProd.getLevel());
            jo.put("price", syProd.getPrice());
            jo.put("payNum", syProd.getSendNum());
            jsonObjects.add(jo);
        }
        vo.setProdList(jsonObjects);
        return vo;
    }

    public ProdDetailVO prodDetail(JSONObject param) {
        ProdDetailVO vo = new ProdDetailVO();

        if (param == null) {
            logger.warn("ProdComponent#prodDetail_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }

        //产品id
        Long id = param.getLong("id");
        if (null == id) {
            logger.warn("ProdComponent#prodDetail_prodId_is_null");
            return vo;
        }
        SyProd syProd = syProdService.selectById(id);
        if (null == syProd) {
            logger.warn("HotelComponent#prodDetail_syProd_is_null>>>id:" + id);
            return vo;
        }

        vo.setPicUrlList(CommonUtil.analyzePicUrl(syProd.getPicUrl()));
        vo.setTitle(syProd.getName());
        vo.setDesc(syProd.getDesc());
        vo.setCommentTitle("用户评论");
        vo.setPrice(syProd.getPrice());
        vo.setRemainNum(syProd.getRemainNum());
        vo.setSendNum(syProd.getSendNum());
        vo.setType(ProdTypeEnum.getNameByCode(syProd.getType()));

        if(syProd.getTotalNum() != null && syProd.getTotalNum().equals(syProd.getSendNum())){
            vo.setStatus(TourConstants.PROD_STATUS.SOLD_OUT);
        }else{
            vo.setStatus(syProd.getStatus());
        }

        try {
            // 查询最新的评价
            SyEvaluate syEvaluate = syEvaluateService.selectLastestByProdId(id);
            vo.setSyEvaluate(syEvaluate);
        } catch (Throwable e) {
            logger.error("syEvaluateService.selectLastestByProdId exception;prodId:" + id);
        }

        // 从cookie中取userId
        String userId = SessionUtil.getCurrUserId();
        if (userId != null) {
            SyTradeOrderQuery query = new SyTradeOrderQuery();
            query.setStatus(TourConstants.ORDER_STATUS.SUCCESS);
            query.setSrc(TourConstants.PROD_SRC.FARM_PROD);
            query.setProdId(String.valueOf(id));
            query.setUserId(userId);
            query.setOffset(0);
            query.setRows(1);
            Long count = syTradeOrderService.queryCountByParam(query);
            if (Long.valueOf(1).equals(count)) {
                vo.setCanComment(true);
            }
        }

        return vo;
    }
}
