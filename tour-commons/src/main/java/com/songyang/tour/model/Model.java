package com.songyang.tour.model;/**
 * Created by lenovo on 2017/9/26.
 */

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 通用api接口Model类
 *
 * @author
 * @create 2017-09-26 23:58
 **/
@XmlRootElement
public class Model<E> implements Serializable{

    private int code = 0;
    private E data;
    private String errorMsg;

    public Model() {
    }

    public Model(E data) {
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public E getData() {
        return this.data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public static <T> Model<T> buidSucc(T data) {
        Model<T> model = new Model();
        model.setData(data);
        return model;
    }

    public static <T> Model<T> buidSucc() {
        Model<T> model = new Model();
        model.setData(null);
        return model;
    }

    public static <T> Model<T> buidFail(int code, String errorMsg) {
        Model<T> model = new Model();
        model.setCode(code);
        model.setErrorMsg(errorMsg);
        return model;
    }

    public static <T> Model<T> buidBusinessFail(String errorMsg) {
        Model<T> model = new Model();
        model.setCode(1);
        model.setErrorMsg(errorMsg);
        return model;
    }

    public String toString() {
        return JSONObject.toJSONString(this, new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
    }

}
