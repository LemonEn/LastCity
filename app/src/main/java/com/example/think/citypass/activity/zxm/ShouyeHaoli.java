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

public class ShouyeHaoli extends BaseActivity{
    TabLayout  tabLayout;
    ViewPager  viewPager;
    ShouyeZBActivity.ViewpagerAdapter  adapter;
    ArrayList<String>  stringList;
    ArrayList<BaseFragment>  fragmentList;
    @Override
    protected int layoutId() {
        return R.layout.shouye_haoli;
    }

    @Override
    protected void initView() {
     tabLayout= (TabLayout) findViewById(R.id.shouye_haoli_tablayout);
        viewPager= (ViewPager) findViewById(R.id.shouye_haoli_viewpager);

    }

    @Override
    protected void initData() {
   stringList=new ArrayList<>();
        fragmentList=new ArrayList<>();
        stringList.add("实物礼品");
        stringList.add("我的道具");
        stringList.add("抵用券");
        stringList.add("我的礼包");
        fragmentList.add(new ShouyeHLone());
        fragmentList.add(new ShouyeHLone());
        fragmentList.add(new ShouyeHLone());
        fragmentList.add(new ShouyeHLone());
        adapter=new  ShouyeZBActivity.ViewpagerAdapter(getSupportFragmentManager(),stringList,fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }
}
