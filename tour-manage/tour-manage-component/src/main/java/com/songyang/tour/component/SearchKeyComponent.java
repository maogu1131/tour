package com.songyang.tour.component;/**
 * Created by lenovo on 2017/9/29.
 */

import com.songyang.tour.constants.CommonConfigConstants;
import com.songyang.tour.pojo.SyCommonConfig;
import com.songyang.tour.service.SyCommonConfigService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 搜索key相关组件
 *
 * @author
 * @create 2017-09-29 23:39
 **/
@Component
public class SearchKeyComponent {
    private Logger logger = LoggerFactory.getLogger(SearchKeyComponent.class);
    @Resource
    private SyCommonConfigService syCommonConfigService;


    // private

    public String initIndexSearchKey() {
        SyCommonConfig config = syCommonConfigService.selectByKey(CommonConfigConstants.INDEX_SEARCH_KEY);
        return null == config ? "" : config.getValue();
    }

    public List<String> initSearchKeyList() {
        List<String> searchKeyList = new ArrayList<String>();
        SyCommonConfig config = syCommonConfigService.selectByKey(CommonConfigConstants.SEARCH_LIST_KEY);
        if (null == config) {
            return new ArrayList<String>();
        }
        String[] searchKeyArray = StringUtils.split(config.getValue(), "\\|", 8);
        return Arrays.asList(searchKeyArray);
    }
}
