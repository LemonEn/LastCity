package com.example.think.citypass.model.http.callback;


import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by 张萌 on 2017/4/10.
 */
public interface OkhttpCallback {
    /**
     * 请求成功的回调
     * */
    void onSuccess(String msg);

    /*
    * 请求失败的回调
    * */
    void onError(String error);
}
