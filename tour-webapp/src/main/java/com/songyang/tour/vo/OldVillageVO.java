package com.songyang.tour.vo;/**
 * Created by lenovo on 2017/9/27.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 古村落VO
 *
 * @author
 * @create 2017-09-27 1:05
 **/
public class OldVillageVO implements Serializable{
    //古村落List
   private List<IndexBaseParamVO> villageList;

    public List<IndexBaseParamVO> getVillageList() {
        return villageList;
    }

    public void setVillageList(List<IndexBaseParamVO> villageList) {
        this.villageList = villageList;
    }

}
