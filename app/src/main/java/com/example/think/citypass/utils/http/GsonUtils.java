package com.example.think.citypass.utils.http;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;



public class GsonUtils {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }


    private GsonUtils() {
    }

    //bean
    public static <T> T gsonBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    //集合
    public static <T> List<T> gsonList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }
}
