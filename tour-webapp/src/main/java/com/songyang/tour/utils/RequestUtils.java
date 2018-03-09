package com.songyang.tour.utils;/**
 * Created by lenovo on 2017/9/29.
 */

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * http请求工具类
 *
 * @author
 * @create 2017-09-29 23:22
 **/
public class RequestUtils {
    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);
    public static final String REQUEST_BODY_KEY = "request_body";

    public RequestUtils() {
    }

    public static String getRequestBody(HttpServletRequest req) {
        return getRequestBody(req, "UTF-8");
    }

    public static String getRequestBody(HttpServletRequest req, String code) {
        String requestBody = "";

        try {
            Object attribute = req.getAttribute("request_body");
            if(attribute == null) {
                requestBody = IOUtils.toString(req.getInputStream(), code);
                req.setAttribute("request_body", requestBody);
            } else {
                requestBody = attribute.toString();
            }
        } catch (Exception var4) {
            logger.error("Req body: " + requestBody, var4);
        }

        return requestBody;
    }

    public static String getRequestParameters(HttpServletRequest req) {
        StringBuilder parameters = new StringBuilder("");
        Enumeration enums = req.getParameterNames();

        while(enums.hasMoreElements()) {
            String name = (String)enums.nextElement();
            String value = req.getParameter(name);
            parameters.append(name).append("=").append(value).append("&");
        }

        return parameters.toString();
    }
}
