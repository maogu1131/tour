package com.songyang.tour.components;/**
 * Created by lenovo on 2017/10/4.
 */

import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.SyModule;
import com.songyang.tour.query.SyModuleQuery;
import com.songyang.tour.service.SyModuleService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.vo.IndexBaseParamVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * app首页主要六大模块组件
 *
 * @author
 * @create 2017-10-04 9:19
 **/
@Component
public class MainModuleComponent {

    @Resource
    public SyModuleService syModuleService;

    /**
     * 初始化六大模块
     * @return
     */
    public List<IndexBaseParamVO> initMainModule() {
        List<IndexBaseParamVO> list = new ArrayList<>();
        SyModuleQuery query = new SyModuleQuery();
        query.setOffset(0);
        query.setRows(6);
        List<SortColumn> sortColumns = new ArrayList<>();
        sortColumns.add(new SortColumn("sort", SortMode.DESC));
        query.setSorts(sortColumns);
        List<SyModule> syModuleList = syModuleService.queryListByParam(query);
        if (CollectionUtils.isEmpty(syModuleList)) {
            return list;
        }
        IndexBaseParamVO vo = null;
        for (SyModule syModule : syModuleList) {
            vo = new IndexBaseParamVO();
            vo.setTitle(syModule.getTitle());
            vo.setSubTitle(syModule.getSubTitle());
            vo.setId(syModule.getId());
            vo.setPicUrl(CommonUtil.analyzeOnePicUrl(syModule.getTitlePicUrl()));
            vo.setType(syModule.getType());
            list.add(vo);
        }
        return list;
    }
}
