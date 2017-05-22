package com.example.think.citypass.utils.retrofitutils;

import com.example.think.citypass.model.http.IHttp;
import com.example.think.citypass.model.http.callback.ResaultCallBack;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by think on 2017/5/14.
 */

public class RetrofitUtil implements IHttp {

    //公用的Url
    private String baseUrl = "";
    private Retrofit retrofit;
    private static RetrofitUtil retrofitUtil = null;
    private NetWork netWork;

    private RetrofitUtil() {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).build();
        netWork = retrofit.create(NetWork.class);
    }

    public synchronized static RetrofitUtil getInstance() {
        if (retrofitUtil == null) {
            retrofitUtil = new RetrofitUtil();
        }
        return retrofitUtil;
    }

    public <T> void getRetrofit(String url, Map<String, String> params, final ResaultCallBack<T> callBack) {
        if (params.size() == 0 || params == null) {
            callBack.onErrorParams("参数错误");
        } else {
            Call<ResponseBody> call = netWork.getLoad(url, params);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        T t = getGeneric(response.body().toString(), callBack);
                        callBack.onSuccess(t);
                    } else {
                        callBack.notNet("无网络连接");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    callBack.onError(t.getMessage());
                }
            });
        }
    }


    public <T> void postRetrofit(String url, Map<String, String> params, final ResaultCallBack<T> callBack) {
        if (params.size() == 0 || params == null) {
            callBack.onErrorParams("参数错误");
        } else {
            Call<ResponseBody> loadpost = netWork.getLoadpost(url, params);
            loadpost.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        T t = getGeneric(response.body().toString(), callBack);
                        callBack.onSuccess(t);
                    } else {
                        callBack.notNet("无网络连接");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    callBack.onError(t.getMessage());
                }
            });
        }
    }

    private <T> T getGeneric(String jsonData, ResaultCallBack<T> callBack) {
        Gson gson = new Gson();
        Type[] types = callBack.getClass().getGenericInterfaces();
        Type[] typeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        T t = gson.fromJson(jsonData, typeArguments[0]);
        return t;
    }
}
