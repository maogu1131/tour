package com.songyang.tour.abstracts;/**
 * Created by lenovo on 2017/10/29.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.enums.RestaurantTypeEnum;
import com.songyang.tour.pojo.SyFolk;
import com.songyang.tour.pojo.SyOldVillage;
import com.songyang.tour.pojo.SyRestaurant;
import com.songyang.tour.pojo.SyScenicSpot;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.DateUtil;
import com.songyang.tour.vo.FolkParamVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 抽象业务组件
 *
 * @author
 * @create 2017-10-29 16:51
 **/

@Component
public abstract class AbstractBizComponent {

    //组装参数景区List
    protected void constructScenicSpotDataList(List<SyScenicSpot> SyScenicSpotList, List<JSONObject> scenicSpotList) {
        JSONObject jo = null;
        for (SyScenicSpot syScenicSpot : SyScenicSpotList) {
            jo = new JSONObject();
            //id
            jo.put("id", syScenicSpot.getId());

            //图片
            jo.put("picUrl", CommonUtil.analyzeOnePicUrl(syScenicSpot.getPicUrl()));

            //图片标题
            jo.put("title", syScenicSpot.getCnName());

            //简介描述
            jo.put("desc", syScenicSpot.getShortName());

            //价格
            jo.put("price", syScenicSpot.getPrice());

            scenicSpotList.add(jo);
        }
    }

    //组装古村落参数List
    protected void constructOldVillageDataList(List<SyOldVillage> syOldVillagetList, List<JSONObject> jsonObjectList) {
        JSONObject jo = null;
        for (SyOldVillage syOldVillage : syOldVillagetList) {
            jo = new JSONObject();
            //id
            jo.put("id", syOldVillage.getId());

            //图片
            jo.put("picUrl", CommonUtil.analyzeOnePicUrl(syOldVillage.getPicUrl()));

            //图片标题
            jo.put("title", syOldVillage.getCnName());

            //地址
            jo.put("address", syOldVillage.getAddress());

            //古村落称号
            jo.put("shortName", syOldVillage.getShortName());

            jsonObjectList.add(jo);
        }
    }

    //组装民俗活动参数List
    protected void constructFolkDataList(List<SyFolk> syFolkDBList, List<FolkParamVO> folkList) {
        FolkParamVO jo = null;
        for (SyFolk syFolk : syFolkDBList) {
            jo = new FolkParamVO();
            //id
            jo.setId(syFolk.getId());

            //图片
            jo.setPicUrl(CommonUtil.analyzeOnePicUrl(syFolk.getBannerUrl()));

            //图片标题
            jo.setTitle(syFolk.getTitle());

            //时间描述
            jo.setTimeDesc(DateUtil.format(syFolk.getStartTime(), DateUtil.shortChineseDtFormat_Mdd) + "-"
                    + DateUtil.format(syFolk.getEndTime(), DateUtil.shortChineseDtFormat_Mdd));

            //地址 TODO 字段截取
            jo.setAddress(syFolk.getAddress());

            folkList.add(jo);
        }

    }

    protected void construcRestaurantDataList(List<SyRestaurant> restaurantDBList, List<JSONObject> scenicSpotList) {

        JSONObject jo = null;
        for (SyRestaurant restaurant : restaurantDBList) {
            jo = new JSONObject();

            jo.put("id", restaurant.getId());

            //图片
            jo.put("picUrl", CommonUtil.analyzeOnePicUrl(restaurant.getPicUrl()));

            //图片标题
            jo.put("title", restaurant.getCnName());

            //人均消费
            jo.put("perPayDesc", restaurant.getPerPayDesc());

            //餐馆类型:1-中餐馆、2-西餐馆、3-日韩料理、4-土家菜,99-其他',
            //@see RestaurantTypeEnum
            jo.put("type", RestaurantTypeEnum.getEnumByCode(restaurant.getType()).getMessage());

            //地址
            jo.put("address", restaurant.getAddress());

            //标签
            jo.put("label", restaurant.getLabel());
            scenicSpotList.add(jo);
        }
    }
}