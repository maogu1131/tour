package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/9/27.
 */

import java.util.List;

/**
 * 民俗活动VO
 *
 * @author
 * @create 2017-09-27 1:11
 **/
public class FolkVO extends PageVO {
    //banner 热hot  List
    private List<FolkParamVO> hotList;
    //民俗活动List
    private List<FolkParamVO> folkList;

    public List<FolkParamVO> getFolkList() {
        return folkList;
    }

    public void setFolkList(List<FolkParamVO> folkList) {
        this.folkList = folkList;
    }

    public List<FolkParamVO> getHotList() {
        return hotList;
    }

    public void setHotList(List<FolkParamVO> hotList) {
        this.hotList = hotList;
    }
}
