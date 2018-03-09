package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/11/19.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.FoodComponent;
import com.songyang.tour.components.RouteComponent;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.HotelType;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.enums.TourType;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyBanner;
import com.songyang.tour.pojo.SyCustomRoute;
import com.songyang.tour.pojo.SyCustomRouteDays;
import com.songyang.tour.query.SyBannerQuery;
import com.songyang.tour.query.SyCustomRouteDaysQuery;
import com.songyang.tour.query.SyCustomRouteQuery;
import com.songyang.tour.service.SyBannerService;
import com.songyang.tour.service.SyCustomRouteDaysService;
import com.songyang.tour.service.SyCustomRouteService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 定制路线业务Controller
 *
 * @author
 * @create 2017-11-19 17:03
 **/
@Controller
@RequestMapping("/mobile/api/customRoute")
public class CustomRouteBizController {

    private Logger logger = LoggerFactory.getLogger(CustomRouteBizController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private SyCustomRouteDaysService syCustomRouteDaysService;

    @Resource
    private SyCustomRouteService syCustomRouteService;

    @Resource
    private RouteComponent routeComponent;

    @Resource
    private SyBannerService syBannerService;

    public static final String SPLIT_MARK = "\\|\\|\\|\\|\\|\\|\\|\\|\\|\\|";

    @ResponseBody
    @RequestMapping(value = "/initData", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<JSONObject> initData(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject jo = new JSONObject();
                List<CodeName> fatherList = new ArrayList<CodeName>();

                CodeName codeName1 = new CodeName();
                codeName1.setCode(TourType.ONE_DAY.getCode());
                codeName1.setName(TourType.ONE_DAY.getName());

                CodeName codeName2 = new CodeName();
                codeName2.setCode(TourType.TWO_DAY.getCode());
                codeName2.setName(TourType.TWO_DAY.getName());

                CodeName codeName3 = new CodeName();
                codeName3.setCode(TourType.THREE_DAY.getCode());
                codeName3.setName(TourType.THREE_DAY.getName());

                CodeName codeName4 = new CodeName();
                codeName4.setCode(TourType.FOUR_DAY.getCode());
                codeName4.setName(TourType.FOUR_DAY.getName());

                CodeName codeName5 = new CodeName();
                codeName5.setCode(TourType.FIVE_DAY.getCode());
                codeName5.setName(TourType.FIVE_DAY.getName());

//                CodeName codeName6 = new CodeName();
//                codeName6.setCode(TourType.SIX_DAY.getCode());
//                codeName6.setName(TourType.SIX_DAY.getName());
//
//                CodeName codeName7 = new CodeName();
//                codeName7.setCode(TourType.SEVEN_DAY.getCode());
//                codeName7.setName(TourType.SEVEN_DAY.getName());

                fatherList.add(codeName1);
                fatherList.add(codeName2);
                fatherList.add(codeName3);
                fatherList.add(codeName4);
                fatherList.add(codeName5);
//                fatherList.add(codeName6);
//                fatherList.add(codeName7);

                List<CodeName> subList = new ArrayList<CodeName>();

                CodeName codeNameSub1 = new CodeName();
                codeNameSub1.setCode(String.valueOf(HotelType.HOTEL.getCode()));
                codeNameSub1.setName(HotelType.HOTEL.getName());

                CodeName codeNameSub2 = new CodeName();
                codeNameSub2.setCode(String.valueOf(HotelType.HOMESTAY.getCode()));
                codeNameSub2.setName(HotelType.HOMESTAY.getName());

                CodeName codeNameSub3 = new CodeName();
                codeNameSub3.setCode(String.valueOf(HotelType.MIX.getCode()));
                codeNameSub3.setName(HotelType.MIX.getName());

                subList.add(codeNameSub1);
                subList.add(codeNameSub2);
                subList.add(codeNameSub3);

                List<SortColumn> sortColumns = new ArrayList<SortColumn>();
                SyBannerQuery query = new SyBannerQuery();
                query.setStatus(TourConstants.STATUS.NORMAL);
                query.setOffset(0);
                query.setRows(1);
                query.setType(2);
                sortColumns.add(new SortColumn("create_time", SortMode.DESC));
                query.setSorts(sortColumns);
                List<SyBanner> banners = syBannerService.queryListByParam(query);

                JSONObject temp = new JSONObject();
                SyBanner sy = null;
                if (CollectionUtils.isNotEmpty(banners)) {
                    sy = banners.get(0);
                    temp.put("picUrl", CommonUtil.analyzeOnePicUrl(sy.getPicUrl()));
                    temp.put("type", sy.getType());
                    temp.put("id", sy.getId());
                    temp.put("jumpContent", sy.getContent());
                    temp.put("moduleType", sy.getModuleType());
                }

                jo.put("favour", subList);
                jo.put("playTime", fatherList);
                jo.put("banner", temp);
                return Model.buidSucc(jo);
            }
        }, logger, "定制路线init异常");
    }


    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public Model<RouteListVO> list(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<RouteListVO>() {
            @Override
            public Model<RouteListVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                RouteListVO vo = routeComponent.routeList(param);
                return Model.buidSucc(vo);
            }
        }, logger, "路线列表");

    }


    @ResponseBody
    @RequestMapping(value = "/detail", method = {RequestMethod.GET, RequestMethod.POST})
    public void detail(HttpServletRequest request, String callback, HttpServletResponse response) throws UnsupportedEncodingException {

        Model<CustomRouteDaysVO> model = new Model<CustomRouteDaysVO>();
        CustomRouteDaysVO daysVO = new CustomRouteDaysVO();
        PrintWriter out = null;
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            String routeId = request.getParameter("routeId");
            if (StringUtils.isBlank(routeId)) {
                throw new TourBizException("参数有误");
            }
            Long id = Long.valueOf(routeId);


            SyCustomRoute customRoute = syCustomRouteService.selectById(id);
            if (customRoute == null) {
                logger.warn("syCustomRouteService selectById is null>>>id:" + routeId);
                model.setData(daysVO);
                model.setCode(0);
                out.write(callback + "(" + JSONObject.toJSONString(model) + ")");
                return;
            }

            daysVO.setHeadTitle("行程共" + customRoute.getType() + "天");
            daysVO.setCustomRouteId(id);
            List<CustomRouteDetailVO> customRouteDaysVOList = new ArrayList<CustomRouteDetailVO>();

            SyCustomRouteDaysQuery daysQuery = new SyCustomRouteDaysQuery();
            daysQuery.setOffset(0);
            daysQuery.setRows(15);
            daysQuery.setCustomRouteId(id);
            List<SortColumn> daySortColumns = new ArrayList<>();
            daySortColumns.add(new SortColumn("day_num", SortMode.ASC));
            daysQuery.setSorts(daySortColumns);
            List<SyCustomRouteDays> routeDaysList = syCustomRouteDaysService.queryListByParam(daysQuery);
            if (CollectionUtils.isEmpty(routeDaysList)) {
                logger.warn("CustomRouteBizController#customRouteQuery_routeDaysList_is_null>>>type:" + customRoute.getType() + ",subType:" + customRoute.getSubType() + ",routeId:" + id);
                model.setData(daysVO);
                model.setCode(0);
                out.write(callback + "(" + JSONObject.toJSONString(model) + ")");
                return;
            }

            CustomRouteDetailVO detailVO = null;
            String type = customRoute.getType();
            for (int i = 1; i <= Integer.valueOf(type); i++) {
                detailVO = new CustomRouteDetailVO();
                detailVO.setDayDesc("第" + i + "天");
                for (SyCustomRouteDays days : routeDaysList) {
                    if (i == days.getDayNum()) {
                        String content[] = StringUtils.split(days.getContent(), SPLIT_MARK);
                        if(content != null && content.length > 0){
                            detailVO.setTourContent(content[0]);
                            if(content.length > 1){
                                detailVO.setLiveContent(content[1]);
                            }
                        }
                    }
                }
                customRouteDaysVOList.add(detailVO);
            }

            daysVO.setCustomRouteDaysVOList(customRouteDaysVOList);
            model.setData(daysVO);
            model.setCode(0);
            out.write(callback + "(" + JSONObject.toJSONString(model) + ")");
            return;
        } catch (Exception e) {
            logger.error("route detail is exception", e);
            model.setData(daysVO);
            model.setCode(1);
            model.setErrorMsg("网络异常，请稍候再试");
            out.write(callback + "(" + JSONObject.toJSONString(model) + ")");
        } finally {
            out.flush();
            out.close();
        }

    }
}
