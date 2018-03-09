package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/12/22.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyFeedback;
import com.songyang.tour.service.SyFeedbackService;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.utils.SessionUtil;
import org.apache.commons.lang.StringUtils;
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
 * 意见反馈controller
 *
 * @author
 * @create 2017-12-22 22:40
 **/
@Controller
@RequestMapping("/mobile/api/")
public class SyFeedbackController {
   private Logger logger = LoggerFactory.getLogger(SyFeedbackController.class);

   @Resource
   private ControllerExecuteTemplate controllerExecuteTemplate;

   @Resource
   private SyFeedbackService syFeedbackService;


    @ResponseBody
    @RequestMapping(value = "/member/feedback/save", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<JSONObject> save(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject jo = new JSONObject();
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                if (param == null) {
                    logger.warn("SyFeedbackController#save_jsonObject_is_null");
                    throw new TourBizException("参数异常");
                }
                String userId = SessionUtil.getCurrUserId();
                String contactInfo = param.getString("contactInfo");
                String context = param.getString("context");


                if (StringUtils.isBlank(userId)) {
                    throw new TourBizException("用户参数异常");
                }
                if (StringUtils.isBlank(contactInfo)) {
                    throw new TourBizException("联系信息参数异常");
                }
                if (StringUtils.isBlank(context)) {
                    throw new TourBizException("内容参数异常");
                }

                SyFeedback feedback = new SyFeedback();
                feedback.setUserId(userId);
                feedback.setContactInfo("");
                feedback.setContext("");
                syFeedbackService.insert(feedback);
                return Model.buidSucc(jo);
            }
        }, logger, "意见反馈预约异常");
    }
}
