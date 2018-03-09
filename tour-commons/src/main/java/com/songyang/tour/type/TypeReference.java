package com.songyang.tour.type;/**
 * Created by lenovo on 2017/12/6.
 */

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author
 * @create 2017-12-06 18:27
 **/
public class TypeReference<T> {

    private final Type type;

    protected TypeReference() {
        Type superClass = this.getClass().getGenericSuperclass();
        this.type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return this.type;
    }
}
