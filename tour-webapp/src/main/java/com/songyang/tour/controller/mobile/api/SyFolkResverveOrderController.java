package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/12/22.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyFolk;
import com.songyang.tour.pojo.SyFolkReserveOrder;
import com.songyang.tour.query.SyFolkReserveOrderQuery;
import com.songyang.tour.service.SyFolkReserveOrderService;
import com.songyang.tour.service.SyFolkService;
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
 * 民俗预约订单controller
 *
 * @author
 * @create 2017-12-22 22:41
 **/
@Controller
@RequestMapping("/mobile/api")
public class SyFolkResverveOrderController {

    private Logger logger = LoggerFactory.getLogger(SyFolkResverveOrderController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private SyFolkReserveOrderService syFolkReserveOrderService;

    @Resource
    private SyFolkService syFolkService;


    @ResponseBody
    @RequestMapping(value = "/member/folk/order/resverve", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<JSONObject> resverve(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject jo = new JSONObject();
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                if (param == null) {
                    logger.warn("SyFolkResverveOrderController#resverve_jsonObject_is_null");
                    throw new TourBizException("参数异常");
                }
                String userId = SessionUtil.getCurrUserId();

                String folkId = param.getString("folkId");

                String resvervePhone = param.getString("resvervePhone");


                if (StringUtils.isBlank(userId)) {
                    throw new TourBizException("用户不为空");
                }
                if (StringUtils.isBlank(resvervePhone)) {
                    throw new TourBizException("预约手机号不为空");
                }
                if (StringUtils.isBlank(folkId)) {
                    throw new TourBizException("预约民俗活动不为空");
                }


                SyFolkReserveOrder order = new SyFolkReserveOrder();
                order.setFolkId(Long.valueOf(folkId));
                order.setUserId(userId);
                order.setRentPhone(resvervePhone);
                syFolkReserveOrderService.insert(order);
                return Model.buidSucc(jo);
            }
        }, logger, "预约民俗活动服务预约异常");
    }


    @ResponseBody
    @RequestMapping(value = "/member/folk/reserveOrder", method = {RequestMethod.GET, RequestMethod.POST})
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
                SyFolkReserveOrderQuery orderQuery = new SyFolkReserveOrderQuery();
                orderQuery.setUserId(userId);
                orderQuery.setOffset(offset);
                orderQuery.setRows(prePageSize);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                orderQuery.setSorts(sortColumns);
                List<SyFolkReserveOrder> folkOrderList = syFolkReserveOrderService.queryListByParam(orderQuery);
                if (CollectionUtils.isEmpty(folkOrderList)) {
                    vo.setEndFlag(true);
                    vo.setReserveOrderList(new ArrayList<JSONObject>());
                    return Model.buidSucc(vo);
                }
                //结束标示
                vo.setEndFlag(false);

                if (folkOrderList.size() == prePageSize) {
                    folkOrderList = folkOrderList.subList(0, prePageSize - 1);
                } else {
                    vo.setEndFlag(true);
                    //到底标题
                    vo.setEndTitle(TourConstants.END_TITLE_MSG);
                }

                // 设置 下一个请求的偏移量
                vo.setOffset(offset + folkOrderList.size());


                List<JSONObject> jsonObjects = new ArrayList<>();
                JSONObject jo = null;
                for (SyFolkReserveOrder folkOrder : folkOrderList) {
                    SyFolk folk = syFolkService.selectById(folkOrder.getFolkId());
                    jo = new JSONObject();
                    jo.put("title", "民俗活动预约");
                    jo.put("folkId", folkOrder.getFolkId());
                    if (null != folk) {
                        jo.put("actTime", DateUtil.format(folk.getStartTime(), DateUtil.shortChineseDtFormat_Mdd) + "-"
                                + DateUtil.format(folk.getEndTime(), DateUtil.shortChineseDtFormat_Mdd));
                        jo.put("folkName", folk.getTitle());
                    }
                    jo.put("createTime", DateUtil.format(folkOrder.getCreateTime(), DateUtil.noSecondFormat));
                    jsonObjects.add(jo);
                }
                vo.setReserveOrderList(jsonObjects);
                return Model.buidSucc(vo);
            }
        }, logger, "民俗活动预约订单异常");
    }

}
