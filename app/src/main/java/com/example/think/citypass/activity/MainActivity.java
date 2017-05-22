package com.example.think.citypass.activity;

import android.os.Handler;

import com.example.think.citypass.App;
import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;
import com.example.think.citypass.utils.sharepreferencesutil.SharedPreferencesUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //是否进入引导页
                if (!(Boolean) SharedPreferencesUtils.getParam(App.activity, "guideKey", false)) {
                    setIntent(App.activity, GuideActivity.class);
                } else {
                    setIntent(App.activity, HomeActivity.class);
                }
                finish();
            }
        }, 2000);
    }

}
