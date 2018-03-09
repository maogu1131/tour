package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/11/1.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 产品listVO
 *
 * @author
 * @create 2017-11-01 17:25
 **/
public class ProdListVO extends PageVO {

    /**
     * //产品标题
     * private String title;
     * <p>
     * //产品图片地址
     * private String picUrl;
     * <p>
     * //评星级别
     * private Integer level;
     * <p>
     * //价格
     * private BigDecimal price;
     * <p>
     * //付款人数
     * private Integer payNum;
     */

    private List<JSONObject> prodList;

    public List<JSONObject> getProdList() {
        return prodList;
    }

    public void setProdList(List<JSONObject> prodList) {
        this.prodList = prodList;
    }
}
