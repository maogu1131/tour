package com.songyang.tour.controller.manager.datacenter;/**
 * Created by lenovo on 2017/12/24.
 */

import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.pojo.*;
import com.songyang.tour.query.PageQuery;
import com.songyang.tour.query.SyFolkReserveOrderQuery;
import com.songyang.tour.query.SyGuideOrderQuery;
import com.songyang.tour.query.SyReserveRentCarOrderQuery;
import com.songyang.tour.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据中心controller
 *
 * @author
 * @create 2017-12-24 21:19
 **/
@Controller
@RequestMapping("/manage/data")
public class DataCenterController {

    private Logger logger = LoggerFactory.getLogger(DataCenterController.class);

    @Resource
    private SyReserveRentCarOrderService syReserveRentCarOrderService;

    @Resource
    private SyFolkReserveOrderService syFolkReserveOrderService;

    @Resource
    private SyGuideOrderService syGuideOrderService;

    @Resource
    private SyFolkService syFolkService;

    @Resource
    private SyGuideService syGuideService;

    @RequestMapping(value = "/rentCarOrder/query")
    public String getRentCarOrderList(SyReserveRentCarOrderQuery qo, PageQuery query, ModelMap model) {
        // 1、准备参数
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
        try {

            // 2、查询条数
            Long count = syReserveRentCarOrderService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyReserveRentCarOrder> list = syReserveRentCarOrderService.queryListByParam(qo);

            model.addAttribute("list", list);
            model.addAttribute("syReserveRentCarOrder", qo);
        } catch (Throwable e) {
            logger.error("DataCenterController query is exception", e);
        }

        return "/web/datacenter/rentcarorder";
    }

    @RequestMapping(value = "/guideOrder/query")
    public String getGuideOrderList(SyGuideOrderQuery qo, PageQuery query, ModelMap model) {
        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
        try {

            // 2、查询条数
            Long count = syGuideOrderService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyGuideOrder> list = syGuideOrderService.queryListByParam(qo);

            for (SyGuideOrder order : list) {
                SyGuide syGuide = syGuideService.selectById(order.getGuideId());
                if (null != syGuide) {
                    order.setGuideName(syGuide.getName());
                }
            }

            model.addAttribute("list", list);
            model.addAttribute("syGuideOrder", qo);
        } catch (Throwable e) {
            logger.error("DataCenterController query is exception", e);
        }
        return "/web/datacenter/guideorder";
    }

    /**
     * <th>预约id</th>
     * <th>民俗活动id</th>
     * <th>民俗活动名称</th>
     * <th>用户id</th>
     * <th>预约手机号</th>
     * <th>创建时间</th>
     */
    @RequestMapping(value = "/folkResverOrder/query")
    public String getFolkResverOrderList(SyFolkReserveOrderQuery qo, PageQuery query, ModelMap model) {

        model.addAttribute("qo", qo);
        model.addAttribute("query", query);
        try {

            // 2、查询条数
            Long count = syFolkReserveOrderService.queryCountByParam(qo);
            if (count > 0) {
                query.doPage(count.intValue());
            }

            // 3、条件查询
            List<SortColumn> sorts = new ArrayList<SortColumn>();
            sorts.add(new SortColumn("create_time", SortMode.DESC));
            qo.setSorts(sorts);
            qo.setOffset(query.getStartRow());
            qo.setRows(query.getPageSize());
            List<SyFolkReserveOrder> orders = syFolkReserveOrderService.queryListByParam(qo);

            for (SyFolkReserveOrder order : orders) {
                SyFolk folk = syFolkService.selectById(order.getFolkId());
                if (null != folk) {
                    order.setFolkName(folk.getTitle());
                }
            }

            model.addAttribute("list", orders);
            model.addAttribute("folkResverOrder", qo);
        } catch (Throwable e) {
            logger.error("DataCenterController query is exception", e);
        }
        return "/web/datacenter/folkorder";
    }
}
