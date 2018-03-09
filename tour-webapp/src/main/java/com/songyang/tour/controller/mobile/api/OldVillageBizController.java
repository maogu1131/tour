package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/10/14.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.OldVillageComponent;
import com.songyang.tour.model.Model;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.vo.OldVillageDetailVO;
import com.songyang.tour.vo.OldVillageListVO;
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
 * 古村落业务controller
 *
 * @author
 * @create 2017-10-14 16:05
 **/
@Controller
@RequestMapping("/mobile/api")
public class OldVillageBizController {

    private Logger logger = LoggerFactory.getLogger(OldVillageBizController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private OldVillageComponent oldVillageComponent;

    @ResponseBody
    @RequestMapping(value = "/oldVillageList", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<OldVillageListVO> oldVillageList(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<OldVillageListVO>() {
            @Override
            public Model<OldVillageListVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                OldVillageListVO vo = oldVillageComponent.oldVillageList(param);
                return Model.buidSucc(vo);
            }
        }, logger, "古村落业务");

    }

    @ResponseBody
    @RequestMapping(value = "/oldVillageDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<OldVillageDetailVO> oldVillageDetail(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<OldVillageDetailVO>() {
            @Override
            public Model<OldVillageDetailVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                OldVillageDetailVO vo = oldVillageComponent.oldVillageDetail(param);
                return Model.buidSucc(vo);
            }
        }, logger, "古村落业务");

    }
}
