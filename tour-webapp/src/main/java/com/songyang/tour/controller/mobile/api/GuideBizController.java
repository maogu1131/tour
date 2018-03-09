package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/11/23.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyGuide;
import com.songyang.tour.pojo.SyGuideOrder;
import com.songyang.tour.query.SyGuideOrderQuery;
import com.songyang.tour.query.SyGuideQuery;
import com.songyang.tour.service.SyGuideOrderService;
import com.songyang.tour.service.SyGuideService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.DateUtil;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.utils.SessionUtil;
import com.songyang.tour.vo.GuideListVO;
import com.songyang.tour.vo.GuideReserveOrderVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 导游业务接口controller
 *
 * @author
 * @create 2017-11-23 19:47
 **/
@Controller
@RequestMapping("/mobile/api")
public class GuideBizController {

    private Logger logger = LoggerFactory.getLogger(FoodBizController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private SyGuideService syGuideService;

    @Resource
    private SyGuideOrderService syGuideOrderService;

    @ResponseBody
    @RequestMapping(value = "/guide/list", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<GuideListVO> guideList(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<GuideListVO>() {
            @Override
            public Model<GuideListVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);

                if (param == null) {
                    logger.warn("GuideBizController#guideList_jsonObject_is_null");
                    throw new TourBizException("参数异常");
                }
                //请求起始页
                Integer offset = param.getInteger("offset");
                //请求多少行
                Integer rows = param.getInteger("rows");

                GuideListVO vo = new GuideListVO();
                int prePageSize = rows + 1;
                SyGuideQuery srfq = new SyGuideQuery();
                srfq.setOffset(offset);
                srfq.setRows(prePageSize);
                srfq.setStatus(TourConstants.STATUS.NORMAL);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                srfq.setSorts(sortColumns);
                List<SyGuide> guideList = syGuideService.queryListByParam(srfq);
                if (CollectionUtils.isEmpty(guideList)) {
                    vo.setEndFlag(true);
                    vo.setGuideList(new ArrayList<JSONObject>());
                    return Model.buidSucc(vo);
                }
                //结束标示
                vo.setEndFlag(false);

                if (guideList.size() == prePageSize) {
                    guideList = guideList.subList(0, prePageSize - 1);
                } else {
                    vo.setEndFlag(true);
                    if (offset != 0) {
                        //到底标题
                        vo.setEndTitle(TourConstants.END_TITLE_MSG);
                    }
                }

                // 设置 下一个请求的偏移量
                vo.setOffset(offset + guideList.size());

                List<JSONObject> jsonObjects = new ArrayList<>();
                JSONObject jo = null;
                for (SyGuide guide : guideList) {
                    jo = new JSONObject();
                    jo.put("guideId", guide.getId());
                    jo.put("guideHeadImgUrl", CommonUtil.analyzeOnePicUrl(guide.getPicUrl()));
                    jo.put("guideName", guide.getName());
                    jo.put("guideDesc", guide.getDesc());
                    jo.put("guideVoiceUrl", guide.getAudioUrl());
                    jsonObjects.add(jo);
                }
                vo.setGuideList(jsonObjects);
                return Model.buidSucc(vo);
            }
        }, logger, "导游业务列表");
    }

    @ResponseBody
    @RequestMapping(value = "/member/guide/reserve", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<Void> guideReserve(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<Void>() {
            @Override
            public Model<Void> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                if (param == null) {
                    logger.warn("GuideBizController#guideList_jsonObject_is_null");
                    throw new TourBizException("参数异常");
                }
                String userId = SessionUtil.getCurrUserId();
                String phone = param.getString("phone");
                Long guideId = param.getLong("guideId");
                if (StringUtils.isBlank(userId) && StringUtils.isBlank(phone) && null == guideId) {
                    throw new TourBizException("请求参数异常");
                }
                SyGuideOrder order = new SyGuideOrder();
                order.setUserId(userId);
                order.setPhone(phone);
                order.setGuideId(guideId);
                syGuideOrderService.insert(order);
                return Model.buidSucc();
            }
        }, logger, "导游预约异常");
    }


    @ResponseBody
    @RequestMapping(value = "/member/guide/reserveOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<GuideReserveOrderVO> reserveOrder(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<GuideReserveOrderVO>() {
            @Override
            public Model<GuideReserveOrderVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                if (param == null) {
                    logger.warn("RentCarBizController#reserveOrder_jsonObject_is_null");
                    throw new TourBizException("参数异常");
                }

                String userId = SessionUtil.getCurrUserId();

                //请求起始页
                Integer offset = param.getInteger("offset");
                //请求多少行
                Integer rows = param.getInteger("rows");

                GuideReserveOrderVO vo = new GuideReserveOrderVO();
                int prePageSize = rows + 1;
                SyGuideOrderQuery orderQuery = new SyGuideOrderQuery();
                orderQuery.setUserId(userId);
                orderQuery.setOffset(offset);
                orderQuery.setRows(prePageSize);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                orderQuery.setSorts(sortColumns);
                List<SyGuideOrder> guideOrderList = syGuideOrderService.queryListByParam(orderQuery);
                if (CollectionUtils.isEmpty(guideOrderList)) {
                    vo.setEndFlag(true);
                    vo.setReserveOrderList(new ArrayList<JSONObject>());
                    return Model.buidSucc(vo);
                }
                //结束标示
                vo.setEndFlag(false);

                if (guideOrderList.size() == prePageSize) {
                    guideOrderList = guideOrderList.subList(0, prePageSize - 1);
                } else {
                    vo.setEndFlag(true);
                    //到底标题
                    vo.setEndTitle("到底了");
                }

                // 设置 下一个请求的偏移量
                vo.setOffset(offset + guideOrderList.size());


                List<JSONObject> jsonObjects = new ArrayList<>();
                JSONObject jo = null;
                for (SyGuideOrder guideOrder : guideOrderList) {
                    SyGuide guide = syGuideService.selectById(guideOrder.getId());
                    jo.put("guideName", "暂无姓名");
                    jo.put("tel", "暂无电话");
                    if (null != guide) {
                        jo.put("guideName", guide.getName());
                        jo.put("tel", guide.getPhone());
                    }
                    jo = new JSONObject();
                    jo.put("title", "导游预约");
                    ;
                    jo.put("rentTime", DateUtil.format(guideOrder.getCreateTime(), DateUtil.yyyy_MM_dd));
                    jo.put("createTime", DateUtil.format(guideOrder.getCreateTime(), DateUtil.noSecondFormat));
                    jsonObjects.add(jo);
                }
                vo.setReserveOrderList(jsonObjects);
                return Model.buidSucc(vo);
            }
        }, logger, "导游预约订单异常");
    }
}
