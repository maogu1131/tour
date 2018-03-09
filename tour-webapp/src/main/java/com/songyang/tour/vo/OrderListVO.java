package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/10/6.
 */

import com.songyang.tour.pojo.SyEvaluate;

import java.util.List;

/**
 * 交易成功list
 *
 * @author
 * @create 2017-10-06 23:01
 **/
public class OrderListVO extends PageVO {

    private List<OrderVO> orderVOList;

    public List<OrderVO> getOrderVOList() {
        return orderVOList;
    }

    public void setOrderVOList(List<OrderVO> orderVOList) {
        this.orderVOList = orderVOList;
    }
}
