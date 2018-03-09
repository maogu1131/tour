package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/10/9.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.HotelComponent;
import com.songyang.tour.model.Model;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.vo.HotelDetailVO;
import com.songyang.tour.vo.HotelListVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 民宿&酒店controller
 *
 * @author
 * @create 2017-10-09 16:18
 **/
@Controller
@RequestMapping("/mobile/api/")
public class HotelBizController {

    private Logger logger = LoggerFactory.getLogger(HotelBizController.class);

    @Resource
    private HotelComponent hotelBizComponent;

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @ResponseBody
    @RequestMapping(value = "/hotelList", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<HotelListVO> hotelList(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<HotelListVO>() {
            @Override
            public Model<HotelListVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                HotelListVO vo = hotelBizComponent.hotelList(param);
                return Model.buidSucc(vo);
            }
        }, logger, "酒店民宿list");
    }

    @ResponseBody
    @RequestMapping(value = "/hotelDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<HotelDetailVO> hotelDetail(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<HotelDetailVO>() {
            @Override
            public Model<HotelDetailVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                HotelDetailVO vo = hotelBizComponent.hotelDetail(param);
                return Model.buidSucc(vo);
            }
        }, logger, "酒店民宿详情");
    }
}
