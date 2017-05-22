package com.example.think.citypass.activity.shezhi;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;

/**
 * Created by d on 2017/5/18.
 */

public class SheZhiActivity extends BaseActivity {
    private TextView mText, mTextMessage,mTiXing,mJianYiFanKui,mPassWorld;
    @Override
    protected int layoutId() {
        return R.layout.shezhi_activity;
    }

    @Override
    protected void initView() {
        mPassWorld= (TextView) findViewById(R.id.My_Passworld);
        mTextMessage= (TextView) findViewById(R.id.Message_TiXing);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mPassWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.My_Passworld:
                        Intent intent=new Intent(SheZhiActivity.this,MyPasswordActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });
        mTextMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.Message_TiXing:
                        Intent intent=new Intent(SheZhiActivity.this,MessageInfoActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });


    }

    @Override
    protected void loadData() {

    }
}
