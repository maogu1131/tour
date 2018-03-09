package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/9/27.
 */

import java.io.Serializable;
import java.util.List;

/**
 * app首页VO
 *
 * @author
 * @create 2017-09-27 0:16
 **/
public class IndexVO implements Serializable {
    //首页搜索框显示key
    private String searchkey;

    //搜索keyList
    private List<String> keyList;

    //首页轮播图片bannerList
    private List<IndexBaseParamVO> bannerList;

    //公告List
    private List<IndexBaseParamVO> hotList;

    //交通预警
    private TrafficWarnVO trafficWarn;

    //8大模块
    private List<IndexBaseParamVO> mainModule;

    //私人订制内容
    private IndexBaseParamVO customRoute;

    //古村落相关
    private OldVillageVO oldVillageVO;

    //民俗活动
    private FolkVO folk;

    //酒店民宿内容
    private HotelVO hotel;

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public List<String> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<String> keyList) {
        this.keyList = keyList;
    }

    public List<IndexBaseParamVO> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<IndexBaseParamVO> bannerList) {
        this.bannerList = bannerList;
    }

    public List<IndexBaseParamVO> getHotList() {
        return hotList;
    }

    public void setHotList(List<IndexBaseParamVO> hotList) {
        this.hotList = hotList;
    }

    public TrafficWarnVO getTrafficWarn() {
        return trafficWarn;
    }

    public void setTrafficWarn(TrafficWarnVO trafficWarn) {
        this.trafficWarn = trafficWarn;
    }

    public List<IndexBaseParamVO> getMainModule() {
        return mainModule;
    }

    public void setMainModule(List<IndexBaseParamVO> mainModule) {
        this.mainModule = mainModule;
    }

    public IndexBaseParamVO getCustomRoute() {
        return customRoute;
    }

    public void setCustomRoute(IndexBaseParamVO customRoute) {
        this.customRoute = customRoute;
    }

    public OldVillageVO getOldVillageVO() {
        return oldVillageVO;
    }

    public void setOldVillageVO(OldVillageVO oldVillageVO) {
        this.oldVillageVO = oldVillageVO;
    }

    public FolkVO getFolk() {
        return folk;
    }

    public void setFolk(FolkVO folk) {
        this.folk = folk;
    }

    public HotelVO getHotel() {
        return hotel;
    }

    public void setHotel(HotelVO hotel) {
        this.hotel = hotel;
    }
}
