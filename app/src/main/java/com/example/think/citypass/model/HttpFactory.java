package com.example.think.citypass.model;

import com.example.think.citypass.model.http.IHttp;
import com.example.think.citypass.utils.retrofitutils.RetrofitUtil;

/**
 * Created by ASUS on 2017/5/14.
 */

public class HttpFactory {
    private static final int OKHTTP = 0;

    private static int TYPE = OKHTTP;


    public static IHttp create() {
        IHttp iHttp = null;
        switch (TYPE) {
            case OKHTTP:
                iHttp = RetrofitUtil.getInstance();
                break;
        }
        return iHttp;
    }
}
