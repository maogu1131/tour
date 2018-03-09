package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/3.
 */

import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyNotice;
import com.songyang.tour.query.SyNoticeQuery;
import com.songyang.tour.service.SyNoticeService;
import com.songyang.tour.vo.IndexBaseParamVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 公告组件
 *
 * @author
 * @create 2017-10-03 21:59
 **/
@Component
public class NoticeComponent {

    @Resource
    private SyNoticeService syNoticeService;

    public List<IndexBaseParamVO> initNoticeList() {
        List<IndexBaseParamVO> voList = new ArrayList<>();
        List<SortColumn> sortColumns = new ArrayList<SortColumn>();
        SyNoticeQuery query = new SyNoticeQuery();
        query.setStatus(TourConstants.STATUS.NORMAL);
        query.setOffset(0);
        query.setRows(100);
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        query.setSorts(sortColumns);
        List<SyNotice> list = syNoticeService.queryListByParam(query);
        if (CollectionUtils.isEmpty(list)) {
            return voList;
        }

        IndexBaseParamVO vo = null;
        for (SyNotice syNotice : list) {
            vo = new IndexBaseParamVO();
            vo.setDesc(syNotice.getContent());
            vo.setJumpContent(syNotice.getJumpUrl());
            voList.add(vo);
        }
        return voList;
    }

}
