package com.songyang.tour.components;/**
 * Created by lenovo on 2017/12/7.
 */

import com.songyang.tour.component.SessionServiceComponent;
import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.query.SyUserQuery;
import com.songyang.tour.service.SyUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户登录服务组件
 *
 * @author
 * @create 2017-12-07 11:22
 **/
@Component
public class UserLoginComponent {

    private Logger logger = LoggerFactory.getLogger(UserLoginComponent.class);

    @Resource
    private SessionServiceComponent sessionServiceComponent;

    @Resource
    private SyUserService syUserService;

    public SyUser getMemberByJsessionIdForMobile(String jsessionId) {

        String userId = sessionServiceComponent.getUserIdBySessionId(jsessionId);
        if (StringUtils.isBlank(userId)) {
            logger.warn("UserLoginComponent#getMemberByJsessionIdForMobile_userId_is_null>>>param:" + jsessionId);
            return null;
        }
        SyUserQuery query = new SyUserQuery();
        query.setUserId(userId);
        query.setOffset(0);
        query.setRows(1);
        List<SyUser> userList = syUserService.queryListByParam(query);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }
        return userList.get(0);
    }
}
