package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/11/1.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.ProdComponent;
import com.songyang.tour.model.Model;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.vo.ProdDetailVO;
import com.songyang.tour.vo.ProdListVO;
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
 * 产品列表Controller
 *
 * @author
 * @create 2017-11-01 17:23
 **/
@Controller
@RequestMapping("/mobile/api")
public class ProdBizController {

    private Logger logger = LoggerFactory.getLogger(ProdBizController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private ProdComponent prodComponent;


    @ResponseBody
    @RequestMapping(value = "/prodList", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<ProdListVO> restaurantList(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<ProdListVO>() {
            @Override
            public Model<ProdListVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                ProdListVO vo = prodComponent.prodList(param);
                return Model.buidSucc(vo);
            }
        }, logger, "产品List");
    }

    @ResponseBody
    @RequestMapping(value = "/prodDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<ProdDetailVO> prodDetail(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<ProdDetailVO>() {
            @Override
            public Model<ProdDetailVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                ProdDetailVO vo = prodComponent.prodDetail(param);
                return Model.buidSucc(vo);
            }
        }, logger, "产品详情");
    }
}
