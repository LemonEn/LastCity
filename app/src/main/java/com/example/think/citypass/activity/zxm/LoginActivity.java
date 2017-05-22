package com.example.think.citypass.activity.zxm;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;

/**
 * Created by 张萌 on 2017/5/22.
 */

public class LoginActivity  extends BaseActivity {
    TextView  registerText;
    @Override
    protected int layoutId() {
        return R.layout.username_login;
    }

    @Override
    protected void initView() {
   registerText= (TextView) findViewById(R.id.textView_user_register);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
   registerText.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent  intent=new Intent(LoginActivity.this,RegisterActivity.class);
           startActivity(intent);
       }
   });
    }

    @Override
    protected void loadData() {

    }
}
