package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/10/14.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.ScenicSpotComponent;
import com.songyang.tour.model.Model;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.vo.ScenicSpotDetailVO;
import com.songyang.tour.vo.ScenicSpotListVO;
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
 * 景区业务Controller
 *
 * @author
 * @create 2017-10-14 15:20
 **/
@Controller
@RequestMapping("/mobile/api")
public class ScenicSpotBizController {

    private Logger logger = LoggerFactory.getLogger(ScenicSpotBizController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private ScenicSpotComponent scenicSpotComponent;

    @ResponseBody
    @RequestMapping(value = "/scenicSpotList",method = {RequestMethod.GET, RequestMethod.POST})
    public Model<ScenicSpotListVO>  scenicSpotList(final HttpServletRequest request){

        return  controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<ScenicSpotListVO>() {
            @Override
            public Model<ScenicSpotListVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                ScenicSpotListVO vo = scenicSpotComponent.scenicSpotList(param);
                return Model.buidSucc(vo);
            }
        },logger,"景区list");

    }


    @ResponseBody
    @RequestMapping(value = "/scenicSpotDetail",method = {RequestMethod.GET, RequestMethod.POST})
    public Model<ScenicSpotDetailVO>  scenicSpotDetail(final HttpServletRequest request){

        return  controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<ScenicSpotDetailVO>() {
            @Override
            public Model<ScenicSpotDetailVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                ScenicSpotDetailVO vo = scenicSpotComponent.scenicSpotDetail(param);
                return Model.buidSucc(vo);
            }
        },logger,"景区详情");
    }
}
