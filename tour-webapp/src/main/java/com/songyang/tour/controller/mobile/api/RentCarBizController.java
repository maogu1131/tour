package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/11/19.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.constants.CommonConfigConstants;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyCommonConfig;
import com.songyang.tour.pojo.SyReserveRentCarOrder;
import com.songyang.tour.query.SyReserveRentCarOrderQuery;
import com.songyang.tour.service.SyCommonConfigService;
import com.songyang.tour.service.SyReserveRentCarOrderService;
import com.songyang.tour.utils.DateUtil;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.utils.SessionUtil;
import com.songyang.tour.vo.ReserveOrderVO;
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
 * 租车服务controller
 *
 * @author
 * @create 2017-11-19 17:53
 **/
@Controller
@RequestMapping("/mobile/api")
public class RentCarBizController {

    private Logger logger = LoggerFactory.getLogger(RentCarBizController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private SyCommonConfigService syCommonConfigService;

    @Resource
    private SyReserveRentCarOrderService syReserveRentCarOrderService;

    @ResponseBody
    @RequestMapping(value = "/rentCar/initData", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<JSONObject> initData(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject jo = new JSONObject();
                SyCommonConfig config = syCommonConfigService.selectByKey(CommonConfigConstants.RENT_CAR_KEY);

                List<String> rentTypeList = new ArrayList<String>();
                List<String> rentNumList = new ArrayList<String>();
                if (null != config) {
                    String value = config.getValue();
                    JSONObject jsonObject = JSONObject.parseObject(value);
                    String[] rentTypeArray = jsonObject.getObject("rentTypeList", String[].class);
                    String[] rentNumArray = jsonObject.getObject("rentNumList", String[].class);
                    for (int i = 0; i < rentTypeArray.length; i++) {
                        rentTypeList.add(rentTypeArray[i]);
                    }

                    for (int i = 0; i < rentNumArray.length; i++) {
                        rentNumList.add(rentNumArray[i]);
                    }
                    jo.put("rentTypeList", rentTypeList);
                    jo.put("rentNumList", rentNumList);
                }
                return Model.buidSucc(jo);
            }

            ;
        }, logger, "租车服务初始化异常");
    }

    @ResponseBody
    @RequestMapping(value = "/member/rentCar/reserve", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<JSONObject> reserve(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject jo = new JSONObject();
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                if (param == null) {
                    logger.warn("RentCarBizController#reserve_jsonObject_is_null");
                    throw new TourBizException("参数异常");
                }
                String userId = SessionUtil.getCurrUserId();
                String rentType = param.getString("rentType");
                String rentNum = param.getString("rentNum");
                String rentTime = param.getString("rentTime");

                if (StringUtils.isBlank(userId)) {
                    throw new TourBizException("用户参数异常");
                }
                if (StringUtils.isBlank(rentType)) {
                    throw new TourBizException("类型参数异常");
                }
                if (StringUtils.isBlank(rentNum)) {
                    throw new TourBizException("人数参数异常");
                }
                if (StringUtils.isBlank(rentTime)) {
                    throw new TourBizException("时间参数异常");
                }

                SyReserveRentCarOrder order = new SyReserveRentCarOrder();
                order.setUserId(userId);
                order.setType(rentType);
                order.setRentNum(rentNum);
                order.setRentTime(DateUtil.parseDate(rentTime, DateUtil.yyyy_MM_dd));
                syReserveRentCarOrderService.insert(order);
                return Model.buidSucc(jo);
            }
        }, logger, "租车服务预约异常");
    }

    @ResponseBody
    @RequestMapping(value = "/member/rentCar/reserveOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<ReserveOrderVO> reserveOrder(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<ReserveOrderVO>() {
            @Override
            public Model<ReserveOrderVO> doExecute() throws IOException {
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

                ReserveOrderVO vo = new ReserveOrderVO();
                int prePageSize = rows + 1;
                SyReserveRentCarOrderQuery orderQuery = new SyReserveRentCarOrderQuery();
                orderQuery.setUserId(userId);
                orderQuery.setOffset(offset);
                orderQuery.setRows(prePageSize);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                orderQuery.setSorts(sortColumns);
                List<SyReserveRentCarOrder> carOrderList = syReserveRentCarOrderService.queryListByParam(orderQuery);
                if (CollectionUtils.isEmpty(carOrderList)) {
                    vo.setEndFlag(true);
                    vo.setReserveOrderList(new ArrayList<JSONObject>());
                    return Model.buidSucc(vo);
                }
                //结束标示
                vo.setEndFlag(false);

                if (carOrderList.size() == prePageSize) {
                    carOrderList = carOrderList.subList(0, prePageSize - 1);
                } else {
                    vo.setEndFlag(true);
                    //到底标题
                    vo.setEndTitle(TourConstants.END_TITLE_MSG);
                }

                // 设置 下一个请求的偏移量
                vo.setOffset(offset + carOrderList.size());


                List<JSONObject> jsonObjects = new ArrayList<>();
                JSONObject jo = null;
                for (SyReserveRentCarOrder carOrder : carOrderList) {
                    jo = new JSONObject();
                    jo.put("title", "租车预约");
                    jo.put("id", carOrder.getId());
                    jo.put("type", carOrder.getType());
                    jo.put("rentNum", carOrder.getRentNum());
                    jo.put("rentTime", DateUtil.format(carOrder.getRentTime(),DateUtil.yyyy_MM_dd));
                    jo.put("createTime", DateUtil.format(carOrder.getCreateTime(),DateUtil.noSecondFormat));
                    jsonObjects.add(jo);
                }
                vo.setReserveOrderList(jsonObjects);
                return Model.buidSucc(vo);
            }
        }, logger, "租车服务订单异常");
    }
};
