package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/29.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.abstracts.AbstractBizComponent;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.ModuleType;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.pojo.SyFolk;
import com.songyang.tour.pojo.SyOldVillage;
import com.songyang.tour.pojo.SyScenicSpot;
import com.songyang.tour.query.SyFolkQuery;
import com.songyang.tour.query.SyOldVillageQuery;
import com.songyang.tour.query.SyScenicSpotQuery;
import com.songyang.tour.service.SyFolkService;
import com.songyang.tour.service.SyOldVillageService;
import com.songyang.tour.service.SyScenicSpotService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.DateUtil;
import com.songyang.tour.vo.SearchResultVO;
import com.songyang.tour.vo.SearchVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 景区搜索组件
 *
 * @author
 * @create 2017-10-29 16:40
 **/
@Component
public class SearchScenicSpotComponent extends AbstractBizComponent {
    private Logger logger = LoggerFactory.getLogger(SearchScenicSpotComponent.class);

    @Resource
    private SyScenicSpotService syScenicSpotService;

    @Resource
    private SyOldVillageService syOldVillageService;

    @Resource
    private SyFolkService syFolkService;


    public SearchResultVO searchScenicSpotResult(JSONObject param) {
        SearchResultVO vo = new SearchResultVO();
        List<SearchVO> searchList = new ArrayList<>();
        if (param == null) {
            logger.warn("SearchScenicSpotComponent#searchScenicSpotResult_jsonObject_is_null");
            throw new TourBizException("参数异常");
        }
        String searchKey = param.getString("searchKey");
        if (StringUtils.isBlank(searchKey)) {
            return vo;
        }

        //景区start
        SyScenicSpotQuery scenicSpotQuery = new SyScenicSpotQuery();
        scenicSpotQuery.setOffset(0);
        scenicSpotQuery.setRows(10);
        scenicSpotQuery.setStatus(TourConstants.STATUS.NORMAL);
        scenicSpotQuery.setCnName(searchKey);
        List<SortColumn> sortColumns1 = new ArrayList<>();
        sortColumns1.add(new SortColumn("create_time", SortMode.DESC));
        scenicSpotQuery.setSorts(sortColumns1);
        List<SyScenicSpot> hotScenicSpotDBList = syScenicSpotService.queryListByParam(scenicSpotQuery);
        List<JSONObject> scenicSpotList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(hotScenicSpotDBList)) {
            SearchVO scenicSpotSearchVO = new SearchVO();
            scenicSpotSearchVO.setModuleName(ModuleType.SCENIC_SPOT.getName());
            scenicSpotSearchVO.setModuleType(ModuleType.SCENIC_SPOT.getCode());
            constructScenicSpotDataList(hotScenicSpotDBList, scenicSpotList);
            scenicSpotSearchVO.setModuleDataList(scenicSpotList);
            searchList.add(scenicSpotSearchVO);
        }
        //景区end

        //古村落start
        SyOldVillageQuery oldVillageQuery = new SyOldVillageQuery();
        oldVillageQuery.setOffset(0);
        oldVillageQuery.setRows(10);
        oldVillageQuery.setCnName(searchKey);
        oldVillageQuery.setStatus(TourConstants.STATUS.NORMAL);
        List<SortColumn> sortColumns2 = new ArrayList<>();
        sortColumns2.add(new SortColumn("create_time", SortMode.DESC));
        oldVillageQuery.setSorts(sortColumns2);
        List<SyOldVillage> syOldVillageDBList = syOldVillageService.queryListByParam(oldVillageQuery);
        List<JSONObject> oldVillageList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(syOldVillageDBList)) {
            SearchVO oldVillageVO = new SearchVO();
            oldVillageVO.setModuleName(ModuleType.OLD_VILLAGE.getName());
            oldVillageVO.setModuleType(ModuleType.OLD_VILLAGE.getCode());
            constructOldVillageDataList(syOldVillageDBList, oldVillageList);
            oldVillageVO.setModuleDataList(oldVillageList);
            searchList.add(oldVillageVO);
        }
        //古村落end

        //民俗活动start
        SyFolkQuery syFolkQuery = new SyFolkQuery();
        syFolkQuery.setOffset(0);
        syFolkQuery.setRows(10);
        syFolkQuery.setTitle(searchKey);
        syFolkQuery.setStatus(TourConstants.STATUS.NORMAL);
        List<SortColumn> sortColumns3 = new ArrayList<>();
        sortColumns3.add(new SortColumn("create_time", SortMode.DESC));
        syFolkQuery.setSorts(sortColumns3);
        List<SyFolk> hotFolkDbList = syFolkService.queryListByParam(syFolkQuery);
        //设置hot List
        List<JSONObject> hotFolkList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(hotFolkDbList)) {
            SearchVO folkVO = new SearchVO();
            folkVO.setModuleName(ModuleType.FOLK.getName());
            folkVO.setModuleType(ModuleType.FOLK.getCode());
            JSONObject jo = null;
            for (SyFolk syFolk : hotFolkDbList) {
                jo = new JSONObject();
                //id
                jo.put("id", syFolk.getId());

                //图片
                jo.put("picUrl", CommonUtil.analyzeOnePicUrl(syFolk.getBannerUrl()));

                //图片标题
                jo.put("title", syFolk.getTitle());

                //时间描述
                jo.put("timeDesc", DateUtil.format(syFolk.getStartTime(), DateUtil.shortChineseDtFormat_Mdd) + "-"
                        + DateUtil.format(syFolk.getEndTime(), DateUtil.shortChineseDtFormat_Mdd));

                //地址 TODO 字段截取
                jo.put("address", syFolk.getAddress());

                hotFolkList.add(jo);
            }
            folkVO.setModuleDataList(hotFolkList);
            searchList.add(folkVO);
        }
        //民俗活动end

        vo.setSearchList(searchList);
        return vo;
    }
}
