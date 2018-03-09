package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/3.
 */

import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyTrafficWarn;
import com.songyang.tour.query.SyTrafficWarnQuery;
import com.songyang.tour.service.SyTrafficWarnService;
import com.songyang.tour.vo.TrafficWarnVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 交通预警组件
 *
 * @author
 * @create 2017-10-03 22:15
 **/
@Component
public class TrafficWarnComponent {

    @Resource
    private SyTrafficWarnService syTrafficWarnService;

    public TrafficWarnVO initTrafficWarnInfo() {
        TrafficWarnVO vo = new TrafficWarnVO();
        SyTrafficWarnQuery query = new SyTrafficWarnQuery();
        query.setStatus(TourConstants.STATUS.NORMAL);
        query.setOffset(0);
        query.setRows(1);
        List<SortColumn> list = new ArrayList<>();
        list.add(new SortColumn("create_time", SortMode.DESC));
        query.setSorts(list);
        List<SyTrafficWarn> warnList = syTrafficWarnService.queryListByParam(query);
        if (CollectionUtils.isEmpty(warnList)) {
            return vo;
        }

        SyTrafficWarn warn = warnList.get(0);
        vo.setContent(warn.getContent());
        vo.setTrafficStatus(warn.getTrafficStatus());
        vo.setJumpUrl(warn.getJumpUrl());
        return vo;
    }
}
