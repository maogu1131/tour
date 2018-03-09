package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/10/13.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyScenicSpotModule;
import com.songyang.tour.query.SyScenicSpotModuleQuery;
import com.songyang.tour.service.SyScenicSpotModuleService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.ScenicSpotModuleVO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 景区模块controller
 *
 * @author
 * @create 2017-10-13 10:47
 **/
@Controller
@RequestMapping("/mobile/api")
public class ScenicSpotModuleBizController {

    private Logger logger = LoggerFactory.getLogger(ScenicSpotModuleBizController.class);

    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private SyScenicSpotModuleService syScenicSpotModuleService;

    @ResponseBody
    @RequestMapping(value = "/spotModuleList")
    public Model<ScenicSpotModuleVO> initSpotModuleList(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<ScenicSpotModuleVO>() {
            @Override
            public Model<ScenicSpotModuleVO> doExecute() throws IOException {
                ScenicSpotModuleVO vo = new ScenicSpotModuleVO();
                SyScenicSpotModuleQuery query = new SyScenicSpotModuleQuery();
                query.setStatus(TourConstants.STATUS.NORMAL);
                query.setOffset(0);
                query.setRows(50);
                List<SortColumn> sortColumns = new ArrayList<>();
                sortColumns.add(new SortColumn("priority", SortMode.DESC));
                query.setSorts(sortColumns);
                List<SyScenicSpotModule> spotModules = syScenicSpotModuleService.queryListByParam(query);
                if (CollectionUtils.isEmpty(spotModules)) {
                    return Model.buidSucc(vo);
                }
                List<JSONObject> spotModuleList = new ArrayList<JSONObject>();
                JSONObject moduleJo = null;
                for (SyScenicSpotModule module : spotModules) {
                    moduleJo = new JSONObject();
                    moduleJo.put("id", module.getId());
                    moduleJo.put("title", module.getTitle());
                    moduleJo.put("type",module.getType());
                    moduleJo.put("picUrl", CommonUtil.analyzeOnePicUrl(module.getPicUrl()));
                    spotModuleList.add(moduleJo);
                }
                vo.setSpotModuleList(spotModuleList);
                return Model.buidSucc(vo);
            }
        }, logger, "景区模块");

    }
}
