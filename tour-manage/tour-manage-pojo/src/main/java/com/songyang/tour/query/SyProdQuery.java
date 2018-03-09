package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/3.
 */

/**
 * 产品query
 *
 * @author
 * @create 2017-09-03 10:32
 **/
public class SyProdQuery extends BaseQuery {

    /**
     * 主键
     */
    private Long id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品类型
     * @see com.songyang.tour.enums.ProdTypeEnum
     */
    private Integer type;

    /**
     * 销售状态-1-不可售,1-待售,3-销售中，5-售罄',
     * @see com.songyang.tour.enums.ProdSaleStatusEnum
     */
    private Integer saleStatus;

    /**
     * 记录状态  1：有效   -1：删除
     * @see com.songyang.tour.constants.TourConstants.STATUS
     */
    private Integer  status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
