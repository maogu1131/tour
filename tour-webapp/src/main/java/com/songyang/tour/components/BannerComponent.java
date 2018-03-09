package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/2.
 */

import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyBanner;
import com.songyang.tour.query.SyBannerQuery;
import com.songyang.tour.service.SyBannerService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.IndexBaseParamVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * banner 组件
 *
 * @author
 * @create 2017-10-02 10:38
 **/
@Component
public class BannerComponent {

    @Resource
    private SyBannerService syBannerService;

    public List<IndexBaseParamVO> initBannerList() {
        List<IndexBaseParamVO> list = new ArrayList<>();
        List<SortColumn> sortColumns = new ArrayList<SortColumn>();
        SyBannerQuery query = new SyBannerQuery();
        query.setStatus(TourConstants.STATUS.NORMAL);
        query.setType(1);
        query.setOffset(0);
        query.setRows(100);
        sortColumns.add(new SortColumn("create_time", SortMode.DESC));
        query.setSorts(sortColumns);
        List<SyBanner> banners = syBannerService.queryListByParam(query);
        if (CollectionUtils.isEmpty(banners)) {
            return list;
        }

        IndexBaseParamVO vo = null;
        for (SyBanner sb : banners) {
            vo = new IndexBaseParamVO();
            vo.setPicUrl(CommonUtil.analyzeOnePicUrl(sb.getPicUrl()));
            vo.setType(sb.getBizType());
            vo.setId(sb.getId());
            vo.setJumpContent(sb.getContent());
            vo.setModuleType(sb.getModuleType());
            list.add(vo);
        }
        return list;
    }
}
