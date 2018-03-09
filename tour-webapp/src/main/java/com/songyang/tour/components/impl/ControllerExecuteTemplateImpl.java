package com.songyang.tour.components.impl;/**
 * Created by lenovo on 2017/9/29.
 */

import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.constants.ModelStatusCodeConstants;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.Model;
import com.songyang.tour.utils.RequestUtils;
import com.songyang.tour.utils.StackTraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * controller执行模板
 *
 * @author
 * @create 2017-09-29 23:14
 **/
@Service
public class ControllerExecuteTemplateImpl implements ControllerExecuteTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExecuteTemplateImpl.class);

    /**
     * 执行callback回调对象的回调方法
     *
     * @param callback 回调
     * @param logger 日志
     * @param errorMsg 错误信息
     * @return 返回jsomModel对象
     */
    public <T> Model<T> execute(HttpServletRequest request, ControllerExecuteCallback<T> callback, Logger logger, String errorMsg) {
        try{
            try {

                Model<T> model = callback.doExecute();

                return model;
            } catch (TourBizException e) {
                return handleWarnException(request, callback, e, logger, e.getMessage());
            }
        } catch (IllegalArgumentException e){
            logger.warn(getRequestInfo(request) + " occurs error =[" + e.getMessage() + "]", e);
            Model<T> model = Model.buidFail(ModelStatusCodeConstants.BUSINESS_ERROR, e.getMessage());
            return model;
        }catch (Throwable e){
            logger.error(getRequestInfo(request) + "occurs error =[" + e.getMessage() + "]", e);
            Model<T> model = Model.buidFail(ModelStatusCodeConstants.SYSTEM_ERROR, errorMsg);
            return model;
        }

    }


    private <T> Model<T> handleWarnException(HttpServletRequest request, ControllerExecuteCallback<T> callback, TourBizException e, Logger logger, String msg) {
        String methodName = StackTraceUtil.getCallMethodName(2);
        logger.warn(getRequestInfo(request) +  log(methodName, msg), e);
        Model<T> result = new Model<T>();
        result.setCode(e.getCode());
        result.setErrorMsg(msg);
        return result;
    }


    private String log(String method, String errorMsg) {
        return method.concat(" occurs error=").concat(errorMsg);
    }

    private String getRequestInfo(HttpServletRequest request){

        StringBuilder errorStrBuilder = new StringBuilder();
        // RequestURL
        errorStrBuilder.append("RequestURL:[");
        errorStrBuilder.append(request.getRequestURL());
        errorStrBuilder.append("]");
        errorStrBuilder.append(", ");
        // RequestBody
        errorStrBuilder.append("RequestBody:[");
        errorStrBuilder.append(RequestUtils.getRequestBody(request));
        errorStrBuilder.append("]");
        errorStrBuilder.append(", ");
        return errorStrBuilder.toString();
    }

}
