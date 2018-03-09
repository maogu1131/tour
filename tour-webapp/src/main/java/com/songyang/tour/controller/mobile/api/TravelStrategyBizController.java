package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/10/9.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.TravelStrategyComponent;
import com.songyang.tour.model.Model;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.vo.TravelStrategyDetailVO;
import com.songyang.tour.vo.TravelStrategyListVO;
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
 * 旅游攻略controller
 *
 * @author
 * @create 2017-10-09 19:58
 **/
@Controller
@RequestMapping("/mobile/api")
public class TravelStrategyBizController {

    private Logger logger = LoggerFactory.getLogger(TravelStrategyBizController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private TravelStrategyComponent travelStrategyComponent;

    @ResponseBody
    @RequestMapping(value = "/travelStrategyList", method = {RequestMethod.POST, RequestMethod.GET})
    public Model<TravelStrategyListVO> travelStrategyList(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<TravelStrategyListVO>() {
            @Override
            public Model<TravelStrategyListVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                TravelStrategyListVO vo = travelStrategyComponent.travelStrategyList(param);
                return Model.buidSucc(vo);
            }
        }, logger, "旅游攻略List");

    }

    @ResponseBody
    @RequestMapping(value = "/travelStrategyDetail", method = {RequestMethod.POST, RequestMethod.GET})
    public Model<TravelStrategyDetailVO> travelStrategyDetail(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<TravelStrategyDetailVO>() {
            @Override
            public Model<TravelStrategyDetailVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                TravelStrategyDetailVO vo = travelStrategyComponent.travelStrategyDetail(param);
                return Model.buidSucc(vo);
            }
        }, logger, "旅游攻略详情");

    }
}
