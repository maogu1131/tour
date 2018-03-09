package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyCustomRouteDao;
import com.songyang.tour.pojo.SyCustomRoute;
import com.songyang.tour.pojo.SyCustomRouteDays;
import com.songyang.tour.query.SyCustomRouteQuery;
import com.songyang.tour.service.SyCustomRouteDaysService;
import com.songyang.tour.service.SyCustomRouteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Service
public class SyCustomRouteServiceImpl implements SyCustomRouteService {


    private static final String NAME_SPACE = SyCustomRoute.class.getName();


    @Resource
    private SyCustomRouteDao syCustomRouteDao;

    @Resource
    private SyCustomRouteDaysService syCustomRouteDaysService;


    /**
     * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
     *
     * @param id id主键
     * @return namespace.id
     */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int insert(SyCustomRoute syCustomRoute) {
        Assert.notNull(syCustomRoute);
        syCustomRoute.setStatus(TourConstants.STATUS.NORMAL);
        return syCustomRouteDao.insert(syCustomRoute);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int deleteById(Long id) {
        Assert.notNull(id);
        return syCustomRouteDao.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int updateById(SyCustomRoute syCustomRoute) {
        Assert.notNull(syCustomRoute);
        Assert.notNull(syCustomRoute.getId());
        return syCustomRouteDao.updateById(syCustomRoute);
    }

    public SyCustomRoute selectById(Long id) {
        Assert.notNull(id);
        return syCustomRouteDao.selectById(id);
    }

    public SyCustomRoute selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syCustomRouteDao.selectByIdForUpdate(id);
    }

    public List<SyCustomRoute> queryListByParam(SyCustomRouteQuery syCustomRouteQuery) {
        Assert.notNull(syCustomRouteQuery);
        return syCustomRouteDao.queryListByParam(syCustomRouteQuery);
    }

    public Long queryCountByParam(SyCustomRouteQuery syCustomRouteQuery) {
        Assert.notNull(syCustomRouteQuery);
        return syCustomRouteDao.queryCountByParam(syCustomRouteQuery);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Boolean save(SyCustomRoute syCustomRoute) {
        int count = 0;
        List<SyCustomRouteDays> customRouteDaysList = syCustomRoute.getCustomRouteDaysList();
        if (syCustomRoute.getId() == null) {
            // TODO 登录用户
            syCustomRoute.setCreator("SYSTEM");
            count = insert(syCustomRoute);
            if (count > 0 && !CollectionUtils.isEmpty(customRouteDaysList)) {
                for (SyCustomRouteDays temp : customRouteDaysList) {
                    temp.setCustomRouteId(syCustomRoute.getId());
                    int insertCount = syCustomRouteDaysService.insert(temp);
                    if (insertCount < 1) {
                        throw new RuntimeException("insert custom_route_day error");
                    }
                }
            }
        } else {
            // TODO 登录用户
            syCustomRoute.setModifier("SYSTEM");
            count = updateById(syCustomRoute);
            List<SyCustomRouteDays> oldCustomRouteDays = syCustomRouteDaysService.selectByCustomRouteId(syCustomRoute.getId());
            if (count > 0) {
                for (SyCustomRouteDays temp : customRouteDaysList) {
                    if (TourConstants.OPERATE_TYPE.UPDATE == temp.getOperateType()) {
                        int updateCount = syCustomRouteDaysService.updateById(temp);
                        if (updateCount < 1) {
                            throw new RuntimeException("update custom_route_day error");
                        }
                    } else if (TourConstants.OPERATE_TYPE.ADD == temp.getOperateType()) {
                        temp.setCustomRouteId(syCustomRoute.getId());
                        int insertCount = syCustomRouteDaysService.insert(temp);
                        if (insertCount < 1) {
                            throw new RuntimeException("insert custom_route_day error");
                        }
                    }
                }

                // 老列表中对象不在新列表中，代表需要删除
                for (SyCustomRouteDays oldTemp : oldCustomRouteDays) {
                    Long oldId = oldTemp.getId();
                    boolean flag = false;
                    for (SyCustomRouteDays newTemp : customRouteDaysList) {
                        Long newId = newTemp.getId();
                        if (oldId.equals(newId)) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        int deleteCount = syCustomRouteDaysService.deleteById(oldId);
                        if (deleteCount < 1) {
                            throw new RuntimeException("delete custom_route_day error");
                        }
                    }
                }
            }
        }
        return count == 0 ? false : true;
    }
}