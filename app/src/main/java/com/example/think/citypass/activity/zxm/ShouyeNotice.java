package com.example.think.citypass.activity.zxm;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;

/**
 * Created by 张萌 on 2017/5/19.
 */

public class ShouyeNotice   extends BaseActivity {
    ImageView  imageView;
    LinearLayout  push,notice,dongtai,visitor;

    @Override
    protected int layoutId() {
        return R.layout.shouye_notice;
    }

    @Override
    protected void initView() {
   imageView= (ImageView) findViewById(R.id.shouye_image);
        push= (LinearLayout) findViewById(R.id.shouye_notice_push);
        notice= (LinearLayout) findViewById(R.id.shouye_notice_systemnotice);
        dongtai= (LinearLayout) findViewById(R.id.shouye_notice_dongtai);
        visitor= (LinearLayout) findViewById(R.id.shouye_notice_visitor);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
    imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent  intent=new Intent(ShouyeNotice.this,ShouyeNoticePhone.class);
            startActivity(intent);
        }
    });
    }

    @Override
    protected void loadData() {

    }
}
