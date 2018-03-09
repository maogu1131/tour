package com.songyang.tour.utils;/**
 * Created by lenovo on 2017/10/6.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.Reader;

/**
 * JSON 工具类
 *
 * @author
 * @create 2017-10-06 22:21
 **/
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public JsonUtil() {
    }

    public static JSONObject getJSONObjectFromRequestStream(HttpServletRequest req) {
        JSONObject json = null;
        String jsonStr = null;

        try {
            jsonStr = RequestUtils.getRequestBody(req);
            json = JSONObject.parseObject(jsonStr);
        } catch (Exception var4) {
            logger.error("Req body: " + jsonStr, var4);
        }

        return json;
    }

    public static JSONArray getJSONArrayFromRequestStream(HttpServletRequest req) {
        JSONArray json = null;
        BufferedReader reader = null;
        String jsonStr = null;

        try {
            jsonStr = RequestUtils.getRequestBody(req);
            json = JSONArray.parseArray(jsonStr);
        } catch (Exception var8) {
            logger.error("Req body: " + jsonStr, var8);
        } finally {
            IOUtils.closeQuietly((Reader)reader);
        }

        return json;
    }

    public static boolean isEmptyJsonArray(Object obj) throws JSONException {
        if(obj == null) {
            return true;
        } else if(!(obj instanceof JSONArray)) {
            throw new JSONException("param does not instanceof JSONArray");
        } else {
            return ((JSONArray)obj).isEmpty();
        }
    }

    public static boolean isEmpty(Object obj) {
        if(obj == null) {
            return true;
        } else if(!(obj instanceof JSONObject)) {
            throw new JSONException("param does not instanceof JSONObject");
        } else {
            return ((JSONObject)obj).isEmpty();
        }
    }

    public static <T> T getObjectFromRequest(HttpServletRequest req, Class<T> cls) {
        JSONObject jsonObject = getJSONObjectFromRequestStream(req);
        return JSONObject.toJavaObject(jsonObject, cls);
    }
}
