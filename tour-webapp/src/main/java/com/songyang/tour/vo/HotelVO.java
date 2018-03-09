package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/9/27.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 酒店民俗VO
 *
 * @author
 * @create 2017-09-27 1:16
 **/
public class HotelVO implements Serializable{
    //酒店民宿List
    private List<IndexBaseParamVO> hotelList;

    public List<IndexBaseParamVO> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<IndexBaseParamVO> hotelList) {
        this.hotelList = hotelList;
    }
}
