package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/10/29.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.components.SearchScenicSpotComponent;
import com.songyang.tour.model.Model;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.vo.SearchResultVO;
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
 * 景区搜索controller
 *
 * @create 2017-10-29 12:19
 **/
@Controller
@RequestMapping("/mobile/api")
public class SearchScenicSpotController {

    private Logger logger = LoggerFactory.getLogger(SearchScenicSpotController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private SearchScenicSpotComponent searchScenicSpotComponent;

    @ResponseBody
    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public Model<SearchResultVO> searchScenicSpot(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<SearchResultVO>() {
            @Override
            public Model<SearchResultVO> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                SearchResultVO vo = searchScenicSpotComponent.searchScenicSpotResult(param);
                return Model.buidSucc(vo);
            }
        }, logger, "景区搜索");
    }
}
