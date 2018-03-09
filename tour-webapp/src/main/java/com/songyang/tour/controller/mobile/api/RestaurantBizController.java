package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/10/6.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.RestaurantComponent;
import com.songyang.tour.model.Model;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.vo.RestaurantDetailVO;
import com.songyang.tour.vo.RestaurantListVO;
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
 * 餐馆controller
 *
 * @author
 * @create 2017-10-06 23:22
 **/
@Controller
@RequestMapping("/mobile/api")
public class RestaurantBizController {

    private Logger logger = LoggerFactory.getLogger(RestaurantBizController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private RestaurantComponent restaurantBizComponent;

    @ResponseBody
    @RequestMapping(value = "/restaurantList", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<RestaurantListVO> restaurantList(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<RestaurantListVO>() {
            @Override
            public Model<RestaurantListVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                RestaurantListVO vo = restaurantBizComponent.restaurantList(param);
                return Model.buidSucc(vo);
            }
        }, logger, "餐馆List");
    }

    @ResponseBody
    @RequestMapping(value = "/restaurantDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<RestaurantDetailVO> restaurantDetail(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<RestaurantDetailVO>() {
            @Override
            public Model<RestaurantDetailVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                RestaurantDetailVO vo = restaurantBizComponent.restaurantDetail(param);
                return Model.buidSucc(vo);
            }
        }, logger, "餐馆List");
    }

}
