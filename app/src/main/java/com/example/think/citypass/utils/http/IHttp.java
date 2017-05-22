package com.example.think.citypass.utils.http;


import java.util.Map;



public interface IHttp {

    /**
     * Get请求
     * @param classDemo 需要返回集合或者对象的class对象
     * @param url     请求网络的全网址
     * @param map     请求参数
     * @param httpCallBack 网络回调
     */
    void get(Class classDemo, String url, Map<String, String> map, HttpCallBack httpCallBack);

    /**
     * Post请求
     * @param classDemo 需要返回集合或者对象的class对象
     * @param url     请求网络的全网址
     * @param map     请求参数
     * @param httpCallBack 网络回调
     */
    void Post(Class classDemo, String url, Map<String, String> map, HttpCallBack httpCallBack);
}
