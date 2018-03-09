package com.songyang.tour.service.impl;


import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.dao.SyEvaluateDao;
import com.songyang.tour.pojo.SyEvaluate;
import com.songyang.tour.query.SyEvaluateQuery;
import com.songyang.tour.service.SyEvaluateService;
import com.songyang.tour.service.SyUserService;
import com.songyang.tour.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by  小工具  on 2017/08/31.
 */
@Service
public class SyEvaluateServiceImpl implements SyEvaluateService {


    private static final String NAME_SPACE = SyEvaluate.class.getName();

    // 默认头像
    private static final String defaultImgUrl = "http://115.239.255.114:8888/img/showPic?imgPath=/data/www/upload_pic/other/default_head_portrait.jpg";


    @Resource
    private SyEvaluateDao syEvaluateDao;

    @Resource
    private SyUserService syUserService;




    /**
     * 返回mybatis 执行代码语句块定位字符串，是namespace.id 组成
     *
     * @param id id主键
     * @return namespace.id
     */
    public String generateStatement(String id) {
        return NAME_SPACE.concat(".").concat(id);
    }

    public int insert(SyEvaluate syEvaluate) {
        Assert.notNull(syEvaluate);
        return syEvaluateDao.insert(syEvaluate);
    }


    public int deleteById(Long id) {
        Assert.notNull(id);
        return syEvaluateDao.deleteById(id);
    }

    public int updateById(SyEvaluate syEvaluate) {
        Assert.notNull(syEvaluate);
        Assert.notNull(syEvaluate.getId());
        return syEvaluateDao.updateById(syEvaluate);
    }

    public SyEvaluate selectById(Long id) {
        Assert.notNull(id);
        return syEvaluateDao.selectById(id);
    }

    public SyEvaluate selectByIdForUpdate(Long id) {
        Assert.notNull(id);
        return syEvaluateDao.selectByIdForUpdate(id);
    }

    public List<SyEvaluate> queryListByParam(SyEvaluateQuery syEvaluateQuery) {
        Assert.notNull(syEvaluateQuery);
        List<SyEvaluate> list = syEvaluateDao.queryListByParam(syEvaluateQuery);
        if (!CollectionUtils.isEmpty(list)) {
            for (SyEvaluate temp : list) {
                String effectPicUrl = temp.getEffectPicUrl();
                if (StringUtils.isNotBlank(effectPicUrl)) {
                    temp.setPicUrlList(CommonUtil.analyzePicUrl(effectPicUrl));
                }
            }
        }

        return list;
    }

    public Long queryCountByParam(SyEvaluateQuery syEvaluateQuery) {
        Assert.notNull(syEvaluateQuery);
        return syEvaluateDao.queryCountByParam(syEvaluateQuery);
    }


    @Override
    public SyEvaluate selectLastestByProdId(Long prodId) {
        Assert.notNull(prodId);
        SyEvaluateQuery query = new SyEvaluateQuery();
        query.setaEffectId(String.valueOf(prodId));
        query.setaEffectType(TourConstants.EFFECT_TYPE.PROD);
        SyEvaluate temp = syEvaluateDao.selectLastestByProdId(query);

        if (null != temp) {
            String imgUrl = syUserService.selectByUserId(temp.getbEffectId()).getUserImg();
            // 空则用默认头像地址
            temp.setbEffectImg(StringUtils.isBlank(imgUrl) == true?defaultImgUrl: CommonUtil.analyzeOnePicUrl(imgUrl));

            String effectPicUrl = temp.getEffectPicUrl();
            if (effectPicUrl != null) {
                temp.setPicUrlList(CommonUtil.analyzePicUrl(effectPicUrl));
            }
        }
        return temp;
    }
}