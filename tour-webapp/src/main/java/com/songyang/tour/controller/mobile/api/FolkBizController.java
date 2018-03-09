package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/10/22.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.FolkComponent;
import com.songyang.tour.components.UserLoginComponent;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.utils.SessionUtil;
import com.songyang.tour.vo.FolkDetailVO;
import com.songyang.tour.vo.FolkVO;
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
 * 民俗活动controller
 *
 * @author
 * @create 2017-10-22 15:35
 **/
@Controller
@RequestMapping("/mobile/api")
public class FolkBizController {

    private Logger logger = LoggerFactory.getLogger(FolkBizController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private FolkComponent folkComponent;


    @Resource
    private UserLoginComponent userLoginComponent;

    @ResponseBody
    @RequestMapping(value = "/folkList", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<FolkVO> folkList(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<FolkVO>() {
            @Override
            public Model<FolkVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);

                FolkVO vo = folkComponent.folkList(param);

                return Model.buidSucc(vo);
            }
        }, logger, "民俗活动");
    }

    @ResponseBody
    @RequestMapping(value = "/folkDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<FolkDetailVO> folkDetail(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<FolkDetailVO>() {
            @Override
            public Model<FolkDetailVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);

                String sessionId = SessionUtil.getSessionId(request);

                SyUser syUser = userLoginComponent.getMemberByJsessionIdForMobile(sessionId);

                if (null != syUser) {
                    param.put("userId", syUser.getUserId());
                }else {
                    param.put("userId", "");
                }
                FolkDetailVO vo = folkComponent.folkDetail(param);

                return Model.buidSucc(vo);
            }
        }, logger, "民俗活动");
    }

}
