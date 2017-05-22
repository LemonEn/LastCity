package com.example.think.citypass.activity.zxm;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;
import com.example.think.citypass.common.base.BaseFragment;
import com.example.think.citypass.fragment.zxm.ShouyeHLone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张萌 on 2017/5/19.
 */

public class ShouyeNoticePhone extends BaseActivity{
TabLayout tabLayout;
    ViewPager  viewPager;
    ArrayList<String>  stringArrayList;
    ArrayList<BaseFragment>  fragmentArrayList;
    ShouyeZBActivity.ViewpagerAdapter  adapter;
    @Override
    protected int layoutId() {
        return R.layout.shouye_notice_msg;
    }

    @Override
    protected void initView() {
   tabLayout= (TabLayout) findViewById(R.id.shouye_notice_msg_tablayout);
        viewPager= (ViewPager) findViewById(R.id.shouye_notice_msg_viewpager);
        stringArrayList=new ArrayList<>();
        fragmentArrayList=new ArrayList<>();
        stringArrayList.add("粉丝");
        stringArrayList.add("关注");
        fragmentArrayList.add(new ShouyeHLone());
        fragmentArrayList.add(new ShouyeHLone());
        adapter=new ShouyeZBActivity.ViewpagerAdapter(getSupportFragmentManager(),stringArrayList,fragmentArrayList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }
}
