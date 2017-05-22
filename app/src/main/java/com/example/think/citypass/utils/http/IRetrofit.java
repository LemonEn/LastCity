package com.example.think.citypass.utils.http;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface IRetrofit {

    /**
     * get请求
     * @param uri 网络端口 全网址
     * @param map  请求参数
     * @return
     */
    @GET
    Call<ResponseBody> get(@Url String uri, @QueryMap Map<String, String> map);
    /**
     * post请求
     * @param url 网络端口 全网址
     * @param map  请求参数
     * @return
     */

    @FormUrlEncoded
    @POST
    Call<ResponseBody> post(@Url String url, @FieldMap Map<String, String> map);
}
