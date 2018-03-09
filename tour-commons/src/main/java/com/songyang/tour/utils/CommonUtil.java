
package com.songyang.tour.utils;/**
 * Created by lenovo on 2017/10/19.
 */

import com.alibaba.fastjson.JSON;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.UrlPO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通用工具类
 *
 * @author
 * @create 2017-10-19 1:27
 **/
public class CommonUtil {

    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public static String analyzeOnePicUrl(String picUrl) {
        if (StringUtils.isBlank(picUrl)) {
            return "";
        }
        try {
            String[] picUrlArray = picUrl.split("\\|");
            if (picUrlArray.length > 0) {
                return TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath + URLEncoder.encode(picUrlArray[0], "UTF-8");
            }
        } catch (Exception e) {
            logger.error("CommonUtil#analyzeOnePicUrl_is_exception>>>", e);
        }
        return "";
    }

    public static List<String> analyzePicUrl(String picUrl) {
        List<String> picUrlList = new ArrayList<>();
        if (StringUtils.isBlank(picUrl)) {
            return picUrlList;
        }
        try {
            String[] picUrlArray = picUrl.split("\\|");
            if (picUrlArray.length > 0) {
                for (String tempUrl : picUrlArray) {
                    picUrlList.add(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath + URLEncoder.encode(tempUrl, "UTF-8"));
                }
            }

        } catch (Exception e) {
            logger.error("CommonUtil#analyzePicUrl_is_exception", e);
        }
        return picUrlList;
    }


    /**
     * 构建 图片地址：http前缀+实际地址
     *
     * @param picUrl
     */
    public static String buildPicUrl(String picUrl) {
        if (StringUtils.isNotBlank(picUrl)) {
            String[] urlArr = picUrl.split("\\|");
            List<UrlPO> urlList = new ArrayList<UrlPO>();
            for (String str : urlArr) {
                UrlPO url = new UrlPO();
                url.setUrl(TourConstants.PIC_URL_PREFIX + TourConstants.remoteFilePath + str);
                urlList.add(url);
            }
            return JSON.toJSONString(urlList);
        }

        return "";
    }

    /**
     * 构建 用户id
     * "7"+9位主键ID
     *
     * @param id
     */
    public static String buildUserId(Long id) {
        return TourConstants.USER_ID_PREFIX + StringUtils.leftPad(String.valueOf(id), TourConstants.LEN, "0");
    }

    public static String genOrderId(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new TourBizException("用户id不能为空");
        }
        // 17位 时间戳
        String timeStampSequence = DateFormatUtils.format(new Date(), "yyMMddHHmmssSSS");

        // 5位随机数
        double randomNo = Math.random() * 100000;
        String randomNoStr = String.valueOf((int) randomNo);
        randomNoStr = StringUtils.leftPad(randomNoStr, 5, "0");

        if (userId.length() > 10) {
            userId = StringUtils.substring(userId, 0, 10);
        }

        // 10位用户id
        userId = StringUtils.leftPad(userId, 10, "0");
        return timeStampSequence.concat(randomNoStr).concat(userId);
    }
}
