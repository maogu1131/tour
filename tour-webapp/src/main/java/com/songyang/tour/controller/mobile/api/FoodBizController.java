package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/10/6.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.FoodComponent;
import com.songyang.tour.model.Model;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.vo.FoodDetailVO;
import com.songyang.tour.vo.FoodListVO;
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
 * 特色美食controller
 *
 * @author
 * @create 2017-10-06 22:06
 **/
@Controller
@RequestMapping("/mobile/api")
public class FoodBizController {
    private Logger logger = LoggerFactory.getLogger(FoodBizController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private FoodComponent foodBizComponent;


    @ResponseBody
    @RequestMapping(value = "/foodList", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<FoodListVO> foodList(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<FoodListVO>() {
            @Override
            public Model<FoodListVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                FoodListVO vo = foodBizComponent.foodList(param);
                return Model.buidSucc(vo);
            }
        }, logger, "美食列表");
    }

    @ResponseBody
    @RequestMapping(value = "/foodDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<FoodDetailVO> foodDetail(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<FoodDetailVO>() {
            @Override
            public Model<FoodDetailVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                FoodDetailVO vo = foodBizComponent.foodDetail(param);
                return Model.buidSucc(vo);
            }
        }, logger, "美食详情");
    }
}
