package com.songyang.tour.components;/**
 * Created by lenovo on 2017/11/1.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.enums.SortColumn;
import com.songyang.tour.enums.SortMode;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.pojo.SyEvaluate;
import com.songyang.tour.pojo.SyUser;
import com.songyang.tour.query.SyEvaluateQuery;
import com.songyang.tour.service.SyEvaluateService;
import com.songyang.tour.service.SyUserService;
import com.songyang.tour.utils.CommonUtil;
import com.songyang.tour.utils.SessionUtil;
import com.songyang.tour.vo.EvaluateListVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 评价组件
 *
 * @author
 * @create 2017-11-01 17:29
 **/
@Component
public class EvaluateComponent {

    private static final Logger LOG = LoggerFactory.getLogger(EvaluateComponent.class);

    private static final String module = "evaluate";

    // 默认头像
    private static final String defaultImgUrl = "http://115.239.255.114:8888/img/showPic?imgPath=/data/www/upload_pic/other/default_head_portrait.jpg";

    @Autowired
    private SyEvaluateService syEvaluateService;

    @Autowired
    private SyUserService syUserService;

    @Resource
    private ImgComponent imgComponent;


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Boolean save(HttpServletRequest request) {


        // 获得userId
        String userId = SessionUtil.getCurrUserId();
        SyUser syUser = syUserService.selectByUserId(userId);
        String userName = syUser.getName();

        String urls = "";
        List<MultipartFile> files = null;
        if (request instanceof MultipartHttpServletRequest) {
            files = ((MultipartHttpServletRequest) request).getFiles("imgs");
        }

        try{
            if (files != null && files.size() > 0) {
                for (MultipartFile file : files) {
                    CommonsMultipartFile cf= (CommonsMultipartFile)file;
                        /* 3.1保存图片 */
                    JSONObject jsonObject = imgComponent.save(cf, module);
                    urls +="|"+jsonObject.get("url");
                }

                if(urls.length() > 1){
                    urls = urls.substring(1);
                }
            }
        }catch (Throwable e){
            LOG.error("imgComponent.save exception;module:"+module,e);
        }

        String effectId = request.getParameter("effectId");
        String effectName = request.getParameter("effectName");
        String effectLevel = request.getParameter("effectLevel");
        String effectContent = request.getParameter("effectContent");

        SyEvaluate syEvaluate = new SyEvaluate();
        syEvaluate.setaEffectType(TourConstants.EFFECT_TYPE.PROD);
        syEvaluate.setaEffectId(effectId);
        syEvaluate.setaEffectName(effectName);
        syEvaluate.setEffectLevel(new BigDecimal(effectLevel));
        syEvaluate.setEffectContent(effectContent);
        syEvaluate.setEffectPicUrl(urls);
        syEvaluate.setbEffectId(userId);
        syEvaluate.setbEffectName(userName);

       return syEvaluateService.insert(syEvaluate) > 0?true:false;

    }



    public EvaluateListVO more(JSONObject param) {
        if (param == null) {
            LOG.warn("EvaluateComponent#more param is null");
            throw new TourBizException("参数异常");
        }

        //请求起始页
        Long prodId = param.getLong("prodId");
        //请求起始页
        Integer offset = param.getInteger("offset");
        //请求多少行
        Integer rows = param.getInteger("rows");

        EvaluateListVO vo = new EvaluateListVO();

        int prePageSize = rows + 1;
        SyEvaluateQuery query = new SyEvaluateQuery();
        query.setaEffectType(TourConstants.EFFECT_TYPE.PROD);
        query.setaEffectId(String.valueOf(prodId));
        query.setStatus(TourConstants.STATUS.NORMAL);
        query.setOffset(offset);
        query.setRows(prePageSize);
        List<SortColumn> sortColumns = new ArrayList<>();
        sortColumns.add(new SortColumn("effect_time", SortMode.DESC));
        query.setSorts(sortColumns);
        List<SyEvaluate> list = syEvaluateService.queryListByParam(query);
        if (CollectionUtils.isEmpty(list)) {
            vo.setEndFlag(true);
            vo.setEvaluateList(new ArrayList<SyEvaluate>());
            return vo;
        }
        //结束标示
        vo.setEndFlag(false);

        if (list.size() == prePageSize) {
            list = list.subList(0, prePageSize - 1);
        } else {
            vo.setEndFlag(true);
            //到底标题
            vo.setEndTitle(TourConstants.END_TITLE_MSG);
        }

        // 设置 下一个请求的偏移量
        vo.setOffset(offset + list.size());

        // 查询用户头像
        if(CollectionUtils.isNotEmpty(list)){
            for (SyEvaluate temp:list) {
                String imgUrl = syUserService.selectByUserId(temp.getbEffectId()).getUserImg();
                // 空则用默认头像地址
                temp.setbEffectImg(StringUtils.isBlank(imgUrl) == true?defaultImgUrl: CommonUtil.analyzeOnePicUrl(imgUrl));
            }
        }

        vo.setEvaluateList(list);
        return vo;
    }

}
