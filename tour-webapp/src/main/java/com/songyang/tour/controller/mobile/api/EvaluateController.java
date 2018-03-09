package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/10/29.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.*;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyEvaluate;
import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.service.SyUserService;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.utils.SessionUtil;
import com.songyang.tour.vo.EvaluateListVO;
import com.songyang.tour.vo.OrderVO;
import com.songyang.tour.vo.ProdDetailVO;
import org.apache.velocity.runtime.directive.Evaluate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 评价controller
 *
 **/
@Controller
@RequestMapping("/mobile/api")
public class EvaluateController {

    private static final Logger LOG = LoggerFactory.getLogger(EvaluateController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private EvaluateComponent evaluateComponent;

    @ResponseBody
    @RequestMapping(value = "/member/evaluate/save", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<Boolean> save(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<Boolean>() {
            @Override
            public Model<Boolean> doExecute() throws IOException {
                Boolean flag = evaluateComponent.save(request);
                return Model.buidSucc(flag);
            }
        }, LOG, "保存评价");
    }


    @ResponseBody
    @RequestMapping(value = "/evaluate/more", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<EvaluateListVO> more(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<EvaluateListVO>() {
            @Override
            public Model<EvaluateListVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);

//                // 测试用
//                param = new JSONObject();
//                param.put("prodId",12);
//                param.put("offset",0);
//                param.put("rows",2);

                EvaluateListVO vo = evaluateComponent.more(param);
                return Model.buidSucc(vo);
            }
        }, LOG, "查看更多评价");
    }
}
