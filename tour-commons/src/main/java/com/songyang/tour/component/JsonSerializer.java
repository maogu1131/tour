package com.songyang.tour.component;/**
 * Created by lenovo on 2017/12/6.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.songyang.tour.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.util.SafeEncoder;
import java.util.List;

/**
 * @author
 * @create 2017-12-06 18:31
 **/
public class JsonSerializer implements Serializer {
    private Logger logger = LoggerFactory.getLogger(JsonSerializer.class);

    public JsonSerializer() {
    }

    public byte[] serialzation(Object object) {
        if (object == null) {
            return null;
        } else {
            try {
                return SafeEncoder.encode(JSONObject.toJSONString(object));
            } catch (Exception var3) {
                this.logger.error("", var3);
                return null;
            }
        }
    }

    public <T> T deserialization(byte[] byteArray, Class<T> c) {
        if (byteArray != null && byteArray.length != 0) {
            try {
                return JSON.parseObject(SafeEncoder.encode(byteArray), c);
            } catch (Exception var4) {
                this.logger.error("", var4);
                return null;
            }
        } else {
            return null;
        }
    }

    public <T> T deserialization(byte[] byteArray, TypeReference<T> type) {
        if (byteArray != null && byteArray.length != 0) {
            try {
                return JSON.parseObject(SafeEncoder.encode(byteArray), type.getType(), new Feature[0]);
            } catch (Exception var4) {
                this.logger.error("", var4);
                return null;
            }
        } else {
            return null;
        }
    }

    public <E> List<E> deserializationList(byte[] byteArray, Class<E> elementC) {
        if (byteArray != null && byteArray.length != 0) {
            try {
                return JSON.parseArray(SafeEncoder.encode(byteArray), elementC);
            } catch (Exception var4) {
                this.logger.error("", var4);
                return null;
            }
        } else {
            return null;
        }
    }
}
