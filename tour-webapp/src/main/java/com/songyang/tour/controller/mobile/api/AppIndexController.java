package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/9/26.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.component.SearchKeyComponent;
import com.songyang.tour.components.*;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyTrafficWarn;
import com.songyang.tour.query.SyTrafficWarnQuery;
import com.songyang.tour.service.SyTrafficWarnService;
import com.songyang.tour.utils.DateUtil;
import com.songyang.tour.vo.IndexVO;
import com.songyang.tour.vo.TrafficWarnVO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * APP首页controller
 *
 * @author bi.yao
 * @create 2017-09-26 23:50
 **/
@Controller
@RequestMapping(value = "/mobile/api")
public class AppIndexController {

    private Logger logger = LoggerFactory.getLogger(AppIndexController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private SearchKeyComponent searchKeyComponent;

    @Resource
    private BannerComponent bannerComponent;

    @Resource
    private NoticeComponent noticeComponent;

    @Resource
    private TrafficWarnComponent trafficWarnComponent;

    @Resource
    private MainModuleComponent mainModuleComponent;

    @Resource
    private CustomRouteComponent customRouteComponent;

    @Resource
    private OldVillageComponent oldVillageComponent;

    @Resource
    private FolkComponent folkComponent;

    @Resource
    private HotelComponent hotelBizComponent;

    @Resource
    private SyTrafficWarnService syTrafficWarnService;


    @ResponseBody
    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public Model<IndexVO> indexInfo(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<IndexVO>() {
            @Override
            public Model<IndexVO> doExecute() throws IOException {
                IndexVO indexVO = new IndexVO();
                //首页搜索key
                indexVO.setSearchkey(searchKeyComponent.initIndexSearchKey());

                //首页搜索List
                indexVO.setKeyList(searchKeyComponent.initSearchKeyList());

                //Banner List
                indexVO.setBannerList(bannerComponent.initBannerList());

                // hot List
                indexVO.setHotList(noticeComponent.initNoticeList());

                //交通预警
                indexVO.setTrafficWarn(trafficWarnComponent.initTrafficWarnInfo());

                //六大主要模块
                indexVO.setMainModule(mainModuleComponent.initMainModule());

                //私人定制
                indexVO.setCustomRoute(customRouteComponent.initCustomRoute());

                //古村落
                indexVO.setOldVillageVO(oldVillageComponent.initOldVillage());

                //民俗活动
                indexVO.setFolk(folkComponent.initFolkVO());

                //民宿
                indexVO.setHotel(hotelBizComponent.initHotelInfo());

                return Model.buidSucc(indexVO);
            }
        }, logger, "app首页接口异常");
    }


    @ResponseBody
    @RequestMapping(value = "/trafficWarnList", method = {RequestMethod.POST, RequestMethod.GET})
    public Model<JSONObject> trafficWarnList(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject jo = new JSONObject();
                List<TrafficWarnVO> trafficWarnVOList = new ArrayList<TrafficWarnVO>();
                SyTrafficWarnQuery query = new SyTrafficWarnQuery();
                query.setStatus(TourConstants.STATUS.NORMAL);
                query.setOffset(0);
                query.setRows(5);
                List<SortColumn> list = new ArrayList<>();
                list.add(new SortColumn("create_time", SortMode.DESC));
                query.setSorts(list);
                List<SyTrafficWarn> warnList = syTrafficWarnService.queryListByParam(query);
                if (CollectionUtils.isEmpty(warnList)) {
                    return Model.buidSucc();
                }

                TrafficWarnVO vo = null;
                for (SyTrafficWarn warn : warnList) {
                    vo = new TrafficWarnVO();
                    vo.setContent(warn.getContent());
                    vo.setTrafficStatus(warn.getTrafficStatus());
                    vo.setJumpUrl(warn.getJumpUrl());
                    vo.setCreateTime(DateUtil.format(warn.getCreateTime(),DateUtil.DEFAULT_FORMAT));
                    trafficWarnVOList.add(vo);
                }
                jo.put("trafficWarnList", trafficWarnVOList);
                return Model.buidSucc(jo);
            }
        }, logger, "交通警告list异常");
    }
}
