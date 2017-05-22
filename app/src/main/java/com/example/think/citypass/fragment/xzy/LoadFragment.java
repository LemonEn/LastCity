package com.example.think.citypass.fragment.xzy;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;

import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseFragment;

public class LoadFragment extends BaseFragment {
    private LinearLayout linearLayout;
    @Override
    protected int layoutId() {
        return R.layout.find_main;
    }
    //发送空消息2秒后显示界面  即  隐藏Load布局
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == 200){
                linearLayout.setVisibility(View.GONE);
            }
            return true;
        }
    });
    @Override
    protected void initView(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_loading);
    }

    @Override
    protected void initData() {
        handler.sendEmptyMessageDelayed(200,2000);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }
}
