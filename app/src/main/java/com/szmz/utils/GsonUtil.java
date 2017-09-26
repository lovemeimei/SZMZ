package com.szmz.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonUtil {

    private GsonUtil() {
    }

    private static Gson getGsonObject() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson;
    }

    /**
     * 对象转Gson字符串
     *
     * @param object
     * @return
     */
    public static <T extends Object> String ser(T object) {
        Gson gson = getGsonObject();
        return gson.toJson(object);
    }

    /**
     * Gson字符串转可序列化对象
     *
     * @param object
     * @param clazz
     * @return
     */
    public static <T extends Object> T deser(String object, Class<T> clazz) {
        Gson gson = getGsonObject();
        return gson.fromJson(object, clazz);
    }

}
