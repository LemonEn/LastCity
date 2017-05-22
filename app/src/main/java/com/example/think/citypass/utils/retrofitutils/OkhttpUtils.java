package com.example.think.citypass.utils.retrofitutils;

import android.content.SharedPreferences;
import android.util.Log;


import com.example.think.citypass.App;
import com.example.think.citypass.model.http.callback.OkhttpCallback;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 张萌 on 2017/5/20.
 */
public class OkhttpUtils {
    private  SharedPreferences  preferences;
    private SharedPreferences.Editor  editor;

    /*
    * 单例模式,调用这个方法就会返回这个类的对象,接下来就能调用这里面的方法了
    * */
    public OkhttpUtils(){}
    public static OkhttpUtils requestHttp=new OkhttpUtils();
    public static OkhttpUtils getInstance(){
        return requestHttp;
    }


    /**
     * get的请求方法
     * @param url
     * @param map
     * @param callback
     */
    public void  GET(String  url, Map<String,String> map, final OkhttpCallback callback){
        OkHttpClient  okHttpClient=new OkHttpClient.Builder().build();
        int a=map.size();
        int num=0;
        if(map.size()>=1&&map!=null){
            StringBuffer sb=new StringBuffer();
            sb.append("?");
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                String value = map.get(key);
                sb.append(key);
                sb.append("=");
                sb.append(value);
                if(num<a){
                    sb.append("&");
                    num++;
                }
//                sb.append(key).append("=").append(value).append("&");
                url=url+sb.substring(0,map.size()-1).toString();
                Log.e("请求网址------>", "GET:"+ url.toString());
            }
//            url=sb.toString().substring(0,map.size()-1);
        }



        Request  request=new Request.Builder().url(url).build();
       okHttpClient.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(okhttp3.Call call, IOException e) {
               final String  error=e.getMessage();
               App.activity.runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       callback.onError(error);
                   }
               });
           }

           @Override
           public void onResponse(okhttp3.Call call, Response response) throws IOException {
             final String  msg=response.body().string();
               App.activity.runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       callback.onSuccess(msg);
                   }
               });
//               callback.onSuccess(msg);
           }
       });


        }

    /**
     * post请求方法
     * @param url
     * @param map
     * @param callback
     */

    public void POST(String  url, Map<String,String>  map, final OkhttpCallback callback){
        OkHttpClient  okHttpClient=new OkHttpClient.Builder().build();
        FormBody.Builder  builder=new FormBody.Builder();
        Set<String>  key=map.keySet();
        for(String  keys:key){
            String  values=map.get(keys);
            builder.add(keys,values);
        }

//        final Request  request=new Request.Builder().url(url).addHeader("Cookie",cookie).post(builder.build()).build();
        final Request  request=new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                final String  error=e.getMessage();
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(error);
                    }
                });
            }
            @Override
            public void onResponse(okhttp3.Call call,  Response response) throws IOException {
//                final String  msgs=response.body().string();
//                savaCookie(response);
                final String string = response.body().string();
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("这是我的内容",string );
                        callback.onSuccess(string);
                    }
                });
            }
        });
    }
















    public void POSTIMAGE(String  url, String  cookie, Map<String,String>  map, final OkhttpCallback callback){
        OkHttpClient  okHttpClient=new OkHttpClient.Builder().build();
        FormBody.Builder  builder=new FormBody.Builder();
        Set<String>  key=map.keySet();
        for(String  keys:key){
            String  values=map.get(keys);
            builder.add(keys,values);
        }

//        final Request  request=new Request.Builder().url(url).addHeader("Cookie",cookie).post(builder.build()).build();
        final Request  request=new Request.Builder().url(url).addHeader("Cookie",cookie).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                final String  error=e.getMessage();
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(error);
                    }
                });
            }
            @Override
            public void onResponse(okhttp3.Call call, final Response response) throws IOException {
//                final String  msgs=response.body().string();
//                savaCookie(response);
                final String string = response.body().string();
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("这是我的内容",string );
//                        savaCookie(response);
                        callback.onSuccess(string);
                    }
                });
            }
        });

    }





}




