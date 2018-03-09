package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/4.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.CommonConfigConstants;
import com.songyang.tour.pojo.SyCommonConfig;
import com.songyang.tour.service.SyCommonConfigService;
import com.songyang.tour.vo.IndexBaseParamVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定制路线组件
 *
 * @author
 * @create 2017-10-04 10:40
 **/
@Component
public class CustomRouteComponent {

    @Resource
    private SyCommonConfigService syCommonConfigService;


    public IndexBaseParamVO initCustomRoute() {
        IndexBaseParamVO vo = new IndexBaseParamVO();
        SyCommonConfig config = syCommonConfigService.selectByKey(CommonConfigConstants.CUSTOM_ROUTE_KEY);
        if (null == config) {
            return vo;
        }
        JSONObject jo = JSONObject.parseObject(config.getValue());
        vo.setTitle(jo.getString("title"));
        vo.setSubTitle(jo.getString("subTitle"));
        return vo;
    }
}
