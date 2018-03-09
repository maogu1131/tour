package com.songyang.tour.component;

import com.songyang.tour.type.TypeReference;

import java.util.List;

/**
 * Created by lenovo on 2017/12/7.
 */
public interface Serializer {

    byte[] serialzation(Object var1);

    <T> T deserialization(byte[] var1, Class<T> var2);

    <T> T deserialization(byte[] var1, TypeReference<T> var2);

    <E> List<E> deserializationList(byte[] var1, Class<E> var2);
}

