package com.example.think.citypass.utils.http;


public interface HttpCallBack {
    //这个是成功的回调 成功的话返回一个带值的实体类或者集合
    void onSuccessful(Object success);
    //这个是失败的回调
    void onError(String errorMessage);

}
