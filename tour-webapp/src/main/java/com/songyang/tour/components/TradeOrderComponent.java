package com.songyang.tour.components;/**
 * Created by lenovo on 2017/11/1.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.ShareTO;
import com.songyang.tour.pojo.*;
import com.songyang.tour.query.SyTradeOrderQuery;
import com.songyang.tour.service.*;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.SessionUtil;
import com.songyang.tour.vo.MailAddressVO;
import com.songyang.tour.vo.OrderListVO;
import com.songyang.tour.vo.OrderVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 购买门票/旅游商品组件
 *
 * @author
 * @create 2017-11-01 17:29
 **/
@Component
public class TradeOrderComponent {

    private static final Logger LOG = LoggerFactory.getLogger(TradeOrderComponent.class);

    @Autowired
    private SyTradeOrderService syTradeOrderService;

    @Autowired
    private SyProdService syProdService;

    @Autowired
    private SyConsumeFlowService syConsumeFlowService;

    @Autowired
    private SyMailingAddressService syMailingAddressService;

    @Resource
    private SyScenicSpotService syScenicSpotService;

    public static final String APP_ID = "2017122201070459";

    public static final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCngHS+FwzwKJ7ciOZbU4l1VgzT0j92I1ivYi9NWvgrXWp1mQhlyhZ08t+GPI867OVdCeBhyVJoHRz4zHwqT/I+8+FoaOS8ahujjwVYDMMYTt/4Q1SSJCmGGeqfJtMllewS+LJldwc4BHrfLUBnFDQYeprHjzO9kgwu6S/aIS7EZQhdu2r4g5ND4XCd0aKouWxg9Q/8DmhutMPJTollE0hahKq8cVN3iXkwutb9hr7beuHgOUud27iFouVsFwm5GjmnDu0GIdGqdRf0IFGKvPZ5c4Gsj27PU3/45iycYn7J/0YRCAQ+8REFNXKNBk9EI8hpbgs46vJeXg/9Xz2k9hv3AgMBAAECggEALB+6oRiRX/JTZuTpScWHwFEar/ICpaxKAGls7IO6PDIviSr2+1lw8P3JxCLP2EXvZcLkSN4vxx1kNGYpjzvgJJU6G7cGClwpS7yxO7Cg9BZTCaMy9Axyo9DP7XzcwlnsBlNdKz1fuXH0rDXPcVfxIUGq5Ebk6ls+ulzTLjLvjuxghknYsfN9wKNoa4EoTGSqUgofVwzzqrUIgY+f/wsFbRr8qnppRWVi48Opj8LZLHZ12b9KIRlOXDbGBzQRbvWxoL7z2xXxx1JTc5FrIghRJlK7VAGcAl+IfvEASZImMSorgv/r814MlCKdVpfmusKg9LqiwkZ04zN2DQmwodMngQKBgQDqfc35oRTzqFe8Q6g7rvr+j7SNMkllQntB+4ADDYxSKzBPuS3tmCn8mAhJktSZkhqzZ8Goda46XDV2ccQhRuMm1bOWd5mncpATUzeBbAxrEHiXirDktseCzPpgNJlvZY3w6h+YO9UUiEi6UPjVHx2JqE+HurpqQhMCE0PDEKO49QKBgQC23aNvCAH221e/C3zcN3nDIWD+nmgWCir8munJRJ7bupfUK33x21wK0JfINKeS6NFRlNymtcTe4XZqf/yn3g+uIB4OwaJPRVt8l9JK97p2UHwx69H28ArCl36r+1KsK37n0ZAtkJpx5c3avM4rVf9tn0HA/Pw6nyKVYXOJHllduwKBgFaNRP/FAzTEiE3HM0uEVMStSllrnEex+Ejsid2WnJqfsl9a3Kb8KVNA0p5Fgg+FLDHYNFT3yu1jp6AmcdxOi/2Dl5wMXTy/PorDhpNQzGygUcKtwIlLJe0Tt77W62OAupmX8T2BqC7B9wY+aPpRV9e9tk1FERWzecykS7iuZaAZAoGBAKuw1S3+Ornnny8EPisU+wG2aTcmy1tGGK4rVK1f/DcZrkvKuelGp9iPmLKknWFVrpIGTlVH/Ju23FOJ71I9MEOwbAg2zYnX+Nm2Qf+ZMDy9vJ2yZ+SN48xoKMqYoTp9bB2DqPbe+eNOfESPWJNfnv7xs+7amIcBupq+cJehIw7ZAoGBAJmzKvIQHLvCIcGwUoC/cY9oThlEw8DvwoDBqXtCUn/L+VMXeyGiEMAEufqRxGgmJxAwEunHO8oFwo0oe70X4CcViy6y7OItOT92ljSTllmdYRigSYoXy4lYOMJVQt9Lx9aIHdfEw1BaIRLvo4qbO5Vwks1CtLVsYDNIPiC/YftT";

    public static final String CHARSET = "utf-8";

    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjAJ8fmS82FYATdDvk7a86R6nRRuuwfgPvcI0SQy3JQ2h6qvbz7vI4O/fwqteQEpfrwor12A9a6U6L3BDwuUG1Zg7WLG0iCjo9Ddit6zW0UynjbuuFm0UcW9/e+Dbn9CsCo2EKJs6DELjouc2n1PzYaB9MqEwJWAwu/Ty469C12OeONi7ekeZpKed8skSPbJNWTNIkwW+/yiwShpK5u/Vn14klwER1JO5ygEp7bNVpAAblcFRWHB5C1sSZcQ/pdcwy3oMOVHTYovHTb9Dx+FE3gSQGLNloHEeDTnXRdbv64vI1IDtbLbcHMFA6e2ca1juwapqjiQ3rxB7quniV055cwIDAQAB";


    public String order(JSONObject param) {

        if (param == null) {
            LOG.warn("TradeOrderComponent#buy_is_null");
            throw new TourBizException("参数异常");
        }

        // 从cookie中取userId
        String userId = SessionUtil.getCurrUserId();

        // 来源
        Integer src = param.getInteger("src");
        //  产品ID
        Long prodId = param.getLong("prodId");
        // 数量
        Integer num = param.getInteger("num");
        // 金额
        BigDecimal amount = param.getBigDecimal("amount");

        // 收货地址id  mail_address_id
        Long mailAddressId = param.getLong("mailAddressId");

        //支付方式:1.支付宝 2.微信'
        Integer payWay = param.getInteger("payWay");

        //参数校验
        if (null == src) {
            throw new TourBizException("购买来源不能为空");
        }
        if (src != TourConstants.PROD_SRC.FARM_PROD && src != TourConstants.PROD_SRC.TICKET) {
            throw new TourBizException("购买来源必须是农产品或门票类型");
        }
        if (null == num || num <= 0) {
            throw new TourBizException("购买数量必须大于0");
        }
        if (null == amount || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new TourBizException("购买金额必须大于0");
        }

        if (payWay != TourConstants.PAY_WAY.ALIPAY && payWay != TourConstants.PAY_WAY.WEIXIN_PAY) {
            throw new TourBizException("购买方式仅支持支付宝或微信支付");
        }

        //判断购买产品是否存在
        String prodName = "";
        BigDecimal prodAmount = BigDecimal.ZERO;
        if (src == TourConstants.PROD_SRC.FARM_PROD) {
            SyProd syProd = syProdService.selectById(prodId);
            if (null == mailAddressId) {
                throw new TourBizException("收货地址id不能为空");
            }
            //验证收货地址是否存在
            SyMailingAddress address = syMailingAddressService.selectById(mailAddressId);
            if (null == address) {
                throw new TourBizException("收货地址不能为空");
            }
            if (null == syProd) {
                throw new TourBizException("农产品不存在");
            }
            if (syProd.getStatus() == TourConstants.STATUS.DELETE) {
                throw new TourBizException("农产品状态已删除");
            }
            prodName = syProd.getName();
            prodAmount = syProd.getPrice().multiply(new BigDecimal(num)).setScale(2, RoundingMode.DOWN);
        } else if (src == TourConstants.PROD_SRC.TICKET) {
            SyScenicSpot syScenicSpot = syScenicSpotService.selectById(prodId);
            if (null == syScenicSpot) {
                throw new TourBizException("景区信息不存在");
            }
            if (syScenicSpot.getStatus() == TourConstants.STATUS.DELETE) {
                throw new TourBizException("景区状态已删除");
            }
            prodName = syScenicSpot.getCnName();
            prodAmount = syScenicSpot.getPrice().multiply(new BigDecimal(num)).setScale(2, RoundingMode.DOWN);

        }
        //校验产品单价*请求数量  是否等于请求金额
        if (amount.compareTo(prodAmount) != 0) {
            throw new TourBizException("购买金额不正确");
        }

        SyTradeOrder order = new SyTradeOrder();
        order.setUserId(userId);
        order.setSrc(src);
        order.setOrderId(CommonUtil.genOrderId(userId));
        order.setProdId(String.valueOf(prodId));
        order.setProdName(prodName);
        order.setAmount(amount);
        order.setNum(num);
        order.setRealPayAmount(amount);
        order.setStatus(TourConstants.ORDER_STATUS.INIT);
        order.setPayStatus(TourConstants.PAY_STATUS.INIT);
        order.setPayStatus(payWay);

        // 农产品-收货地址
        if (TourConstants.PROD_SRC.FARM_PROD == src) {
            //判断收货地址是否存在
            order.setMailAddressId(mailAddressId);
        }

        Integer count = syTradeOrderService.insert(order);
        if (count < 1) {
            LOG.error("syTradeOrderService.insert error;order:" + JSON.toJSONString(order));
            throw new TourBizException("下单失败");
        }

        Boolean flag = dealShare(order, num);
        if (!flag) {
            LOG.error("dealShare error;order:" + JSON.toJSONString(order) + ",num:" + num);
            throw new TourBizException("下单失败");
        }

        /***
         * 支付宝创建订单
         */
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(prodName + "_body");
        model.setSubject(prodName);
        model.setOutTradeNo(order.getOrderId());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(String.valueOf(0.01));
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("http://app.sylyfz.cn:8888/mobile/api/alipay/callback");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                return response.getBody();
            } else {
                order.setStatus(TourConstants.ORDER_STATUS.FAIL);
                dealShare(order, num);
                order.setRemark("调用支付宝失败，订单关闭");
                syTradeOrderService.updateById(order);
                LOG.error("alipayClient_sdkExecute_is_alipyException_result:" + JSONObject.toJSONString(response) + "param:" + JSON.toJSONString(model) + ",userId:" + userId);
                throw new TourBizException("下单失败");
            }
        } catch (AlipayApiException e) {
            order.setStatus(TourConstants.ORDER_STATUS.FAIL);
            dealShare(order, num);
            order.setRemark("调用支付宝异常，订单关闭");
            syTradeOrderService.updateById(order);
            LOG.error("alipayClient_sdkExecute_is_alipyException" + JSON.toJSONString(model) + ",userId:" + userId);
            throw new TourBizException("下单失败");
        }
    }


    /**
     * 查询成功订单
     *
     * @param param
     * @return
     */
    public OrderListVO queryOrder(JSONObject param) {

        OrderListVO vo = new OrderListVO();

        if (param == null) {
            LOG.warn("TradeOrderComponent#queryOrder_is_null");
            throw new TourBizException("参数异常");
        }

        // 从cookie中取userId
        String userId = SessionUtil.getCurrUserId();

        if (param == null) {
            LOG.warn("EvaluateComponent#more param is null");
            throw new TourBizException("参数异常");
        }

        // 来源
        Integer src = param.getInteger("src");
        //请求起始页
        Integer offset = param.getInteger("offset");
        //请求多少行
        Integer rows = param.getInteger("rows");


        SyTradeOrderQuery query = new SyTradeOrderQuery();
        query.setUserId(userId);
        query.setSrc(src);
        query.setStatus(TourConstants.ORDER_STATUS.SUCCESS);


        int prePageSize = rows + 1;
        query.setOffset(offset);
        query.setRows(prePageSize);
        List<SortColumn> sortColumns = new ArrayList<>();
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        query.setSorts(sortColumns);
        List<SyTradeOrder> list = syTradeOrderService.queryListByParam(query);
        if (CollectionUtils.isEmpty(list)) {
            vo.setEndFlag(true);
            vo.setOrderVOList(new ArrayList<OrderVO>());
            return vo;
        }
        //结束标示
        vo.setEndFlag(false);

        if (list.size() == prePageSize) {
            list = list.subList(0, prePageSize - 1);
        } else {
            vo.setEndFlag(true);
            if (offset != 0) {
                //到底标题
                vo.setEndTitle(TourConstants.END_TITLE_MSG);
            }
        }

        // 设置 下一个请求的偏移量
        vo.setOffset(offset + list.size());

        // 构建订单列表
        List<OrderVO> orderVOList = new ArrayList<OrderVO>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (SyTradeOrder temp : list) {
                OrderVO orderVO = new OrderVO();
                SyConsumeFlow flow = syConsumeFlowService.selectByReqId(temp.getOrderId() + "#" + TourConstants.ORDER_STATUS.SUCCESS);
                orderVO.setOrderId(temp.getOrderId());
                orderVO.setCreateTime(temp.getCreateTime());
                orderVO.setProdName(flow.getProdName());
                // 图片地址
                if (TourConstants.PROD_SRC.FARM_PROD == src) {
                    SyProd syProd = syProdService.selectById(Long.valueOf(flow.getProdId()));
                    // 图片地址转换
                    if (StringUtils.isNotBlank(syProd.getPicUrl())) {
                        orderVO.setPicUrl(CommonUtil.analyzeOnePicUrl(syProd.getPicUrl()));
                    }
                    // 单价
                    orderVO.setPrice(syProd.getPrice());

                    // 构建收货地址
                    SyMailingAddress syMailingAddress = syMailingAddressService.selectById(temp.getMailAddressId());
                    MailAddressVO mailAddressVO = new MailAddressVO();
                    mailAddressVO.setUserName(syMailingAddress.getUserName());
                    mailAddressVO.setPhone(syMailingAddress.getPhone());
                    mailAddressVO.setAddress(syMailingAddress.getAddress());
                    orderVO.setMailAddressVO(mailAddressVO);
                } else {
                    SyScenicSpot syScenicSpot = syScenicSpotService.selectById(Long.valueOf(flow.getProdId()));
                    // 图片地址转换
                    if (StringUtils.isNotBlank(syScenicSpot.getPicUrl())) {
                        orderVO.setPicUrl(CommonUtil.analyzeOnePicUrl(syScenicSpot.getPicUrl()));
                    }
                    // 单价
                    orderVO.setPrice(syScenicSpot.getPrice());
                }

                // 数量
                orderVO.setNum(flow.getNum());
                // 总金额
                orderVO.setPayAmount(temp.getRealPayAmount());
                orderVOList.add(orderVO);
            }
        }

        vo.setOrderVOList(orderVOList);
        return vo;
    }

    /**
     * 处理份额：
     * 1-插入流水
     * 2-更新份额
     *
     * @param order
     * @param num
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Boolean dealShare(SyTradeOrder order, Integer num) {

        ShareTO shareTO = new ShareTO();
        shareTO.setOperator(order.getUserId());

        SyConsumeFlow flow;
        if (TourConstants.ORDER_STATUS.FAIL == order.getStatus()) {
            flow = syConsumeFlowService.selectByReqId(order.getOrderId() + "#" + TourConstants.ORDER_STATUS.SUCCESS);
            flow.setReqId(order.getOrderId() + "#" + TourConstants.ORDER_STATUS.FAIL);
            flow.setStatus(TourConstants.FLOW_STATUS.FAIL);
            num = flow.getNum();
            shareTO.setDirection(TourConstants.DIRECTION.PLUS);
        } else {
            flow = new SyConsumeFlow();
            flow.setUserId(order.getUserId());
            flow.setSrc(order.getSrc());
            flow.setProdId(order.getProdId());
            flow.setProdName(order.getProdName());
            flow.setNum(num);
            flow.setOrderId(order.getOrderId());
            flow.setReqId(order.getOrderId() + "#" + TourConstants.ORDER_STATUS.SUCCESS);
            flow.setStatus(TourConstants.FLOW_STATUS.SUCCESS);
            shareTO.setDirection(TourConstants.DIRECTION.DEDUCT);
        }
        shareTO.setNum(num);
        Integer flowCount = syConsumeFlowService.insert(flow);
        if (flowCount < 1) {
            LOG.error("syTradeOrderService.insert error;flow:" + JSON.toJSONString(flow));
            throw new RuntimeException("syTradeOrderService.insert error");
        }

        shareTO.setProdId(order.getProdId());
        Boolean flag;
        if (TourConstants.DIRECTION.DEDUCT == shareTO.getDirection()) {
            if (TourConstants.PROD_SRC.TICKET == order.getSrc()) {
                // 扣减门票份额
                flag = syScenicSpotService.deductByShareTO(shareTO);

            } else {
                // 扣减农产品份额
                flag = syProdService.deductByShareTO(shareTO);
            }
        } else {
            if (TourConstants.PROD_SRC.TICKET == order.getSrc()) {
                // 回滚门票份额
                flag = syScenicSpotService.plusByShareTO(shareTO);

            } else {
                //  回滚农产品份额
                flag = syProdService.plusByShareTO(shareTO);
            }
        }
        if (!flag) {
            LOG.error("syProdService/syTradeOrderService.plusByShareTO/plusByShareTO error;shareTO:" + JSON.toJSONString(shareTO));
            throw new RuntimeException("syProdService/syTradeOrderService.plusByShareTO/plusByShareTO error");
        }

        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Boolean update(SyTradeOrder param) {

        Boolean flag;

        Assert.notNull(param, "syTrade is null");
        Assert.notNull(param.getOrderId(), "orderId is null");
        Assert.notNull(param.getStatus(), "status is null");
        Assert.notNull(param.getPayStatus(), "playStatus is null");

        int count = syTradeOrderService.updateById(param);
        if (count < 1) {
            LOG.error("syTradeOrderService.updateById error;param:" + JSON.toJSONString(param));
            throw new RuntimeException("syTradeOrderService.updateById error");
        } else {
            flag = true;
        }

        // 支付失败---》回滚份额
        if (TourConstants.ORDER_STATUS.FAIL == param.getStatus()) {
            // 回滚需要查询之前扣减的流水，无需数量参数
            flag = dealShare(param, null);
        }

        return flag;

    }
}
