package com.songyang.tour.query;/**
 * Created by lenovo on 2017/9/3.
 */

/**
 * 菜馆 菜查询
 *
 * @author
 * @create 2017-09-03 10:37
 **/
public class SyRestaurantFoodQuery extends BaseQuery {

    /**
     * 数据库字段长度:20
     * 字段备注:主键
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Long id;

    /**
     * 数据库字段长度:20
     * 字段备注:餐馆ID
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Long restaurantId;

    /**
     * 数据库字段长度:64
     * 字段备注:菜名
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private String name;

    /**
     * 数据库字段长度:4
     * 字段备注:餐馆类型:1-杭帮菜、2-西餐、3-日韩料理、4-土家菜,5-湘菜，6-川菜，7-徽菜，8-粤菜，9-东北菜，99-其他
     * Sun Sep 03 16:51:09 CST 2017 AutoGenerate
     */
    private Integer type;

    /**
     * 记录 1：有效   -1：删除
     *
     * @see com.songyang.tour.constants.TourConstants.STATUS
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
