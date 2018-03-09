package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/10/29.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.TradeOrderComponent;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyTradeOrder;
import com.songyang.tour.query.SyTradeOrderQuery;
import com.songyang.tour.service.SyTradeOrderService;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.utils.SessionUtil;
import com.songyang.tour.vo.OrderListVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 景区搜索controller
 * * @create 2017-10-29 12:19
 **/
@Controller
@RequestMapping("/mobile/api")
public class TradeOrderController {

    private Logger logger = LoggerFactory.getLogger(TradeOrderController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private TradeOrderComponent tradeOrderComponent;

    @Resource
    private SyTradeOrderService syTradeOrderService;

    @ResponseBody
    @RequestMapping(value = "/member/createOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<String> submitOrder(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<String>() {
            @Override
            public Model<String> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                String vo = tradeOrderComponent.order(param);
                return Model.buidSucc(vo);
            }
        }, logger, "下单失败");
    }

    @ResponseBody
    @RequestMapping(value = "/member/queryOrderStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<JSONObject> queryOrderStatus(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                if (param == null) {
                    logger.warn("TradeOrderComponent#buy_is_null");
                    throw new TourBizException("参数异常");
                }
                // 从cookie中取userId
                String userId = SessionUtil.getCurrUserId();
                // 来源
                String tradeNo = param.getString("tradeNo");
                if (StringUtils.isBlank(tradeNo)) {
                    throw new TourBizException("查询的订单id不能为空");
                }
                SyTradeOrderQuery query = new SyTradeOrderQuery();
                query.setOrderId(tradeNo);
                query.setUserId(userId);
                List<SyTradeOrder> orderList = syTradeOrderService.queryListByParam(query);
                if (CollectionUtils.isNotEmpty(orderList)) {
                    SyTradeOrder order = orderList.get(0);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("orderStatus", order.getStatus());
                    return Model.buidSucc(jsonObject);
                }
                throw new TourBizException("查询的订单不存在");
            }
        }, logger, "查询支付订单失败");
    }


    @ResponseBody
    @RequestMapping(value = "/alipay/callback", method = {RequestMethod.GET, RequestMethod.POST})
    public String callback(final HttpServletRequest request) {

        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        String tradeNo = params.get("out_trade_no");
        logger.info("aplipay_callback_trade_order>>>callbackParam:" + JSONObject.toJSONString(params));
        try {
            boolean flag = AlipaySignature.rsaCheckV1(params, TradeOrderComponent.ALIPAY_PUBLIC_KEY, TradeOrderComponent.CHARSET, "RSA2");
            if (flag) {
                //调用支付宝查询接口更新订单状态
                tradeNo = params.get("out_trade_no");
                String aplipayTradeNo = params.get("trade_no");
                String trade_status = params.get("trade_status");
                if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                    //业务处理
                    SyTradeOrderQuery order = new SyTradeOrderQuery();
                    order.setOrderId(tradeNo);
                    List<SyTradeOrder> queryList = syTradeOrderService.queryListByParam(order);
                    if (CollectionUtils.isNotEmpty(queryList)) {
                        SyTradeOrder queryTradeOrder = queryList.get(0);
                        if (queryTradeOrder.getStatus() == TourConstants.PAY_STATUS.SUCCESS) {
                            return "success";
                        } else if (queryTradeOrder.getStatus() == TourConstants.PAY_STATUS.INIT) {
                            queryTradeOrder.setStatus(TourConstants.ORDER_STATUS.SUCCESS);
                            queryTradeOrder.setPayStatus(TourConstants.PAY_STATUS.SUCCESS);
                            queryTradeOrder.setRemark("支付宝回调成功，订单成功");
                            queryTradeOrder.setThirdOrderId(aplipayTradeNo);
                            syTradeOrderService.updateById(queryTradeOrder);
                            //订单完成时间
                            return "success";
                        } else {
                            logger.warn("aplipay_callback_success_trade_order_is_not_init>>>order:" + JSONObject.toJSONString(queryTradeOrder) + "callbackParam:" + JSONObject.toJSONString(params));
                        }
                    }
                } else {
                    //回滚份额
                    rollbackShare(tradeNo, "支付宝回调交易失败，回滚份额，订单失败");
                    return "response fail";
                }
            } else {
                //回滚份额
                rollbackShare(tradeNo, "支付宝回调交易验签失败，回滚份额，订单失败");
                return "sign fail";
            }
        } catch (AlipayApiException e) {
            logger.error("alipayClient_callback_is_alipyException" + JSON.toJSONString(params));
        }
        return "response fail";
    }

    private void rollbackShare(String tradeNo, String remark) {
        SyTradeOrderQuery order = new SyTradeOrderQuery();
        order.setOrderId(tradeNo);
        List<SyTradeOrder> queryList = syTradeOrderService.queryListByParam(order);
        if (CollectionUtils.isNotEmpty(queryList)) {
            SyTradeOrder queryTradeOrder = queryList.get(0);
            queryTradeOrder.setStatus(TourConstants.ORDER_STATUS.FAIL);
            queryTradeOrder.setPayStatus(TourConstants.ORDER_STATUS.FAIL);
            tradeOrderComponent.dealShare(queryTradeOrder, queryTradeOrder.getNum());
            queryTradeOrder.setRemark(remark);
            syTradeOrderService.updateById(queryTradeOrder);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/member/myOrder", method = {RequestMethod.GET, RequestMethod.POST})
//    @RequestMapping(value = "/myOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<OrderListVO> myOrder(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<OrderListVO>() {
            @Override
            public Model<OrderListVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                OrderListVO vo = tradeOrderComponent.queryOrder(param);
                return Model.buidSucc(vo);
            }
        }, logger, "我的订单（门票/商品）");
    }

}
