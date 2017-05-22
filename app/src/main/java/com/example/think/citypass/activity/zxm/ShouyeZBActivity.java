package com.example.think.citypass.activity.zxm;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;
import com.example.think.citypass.common.base.BaseFragment;
import com.example.think.citypass.fragment.zxm.ShouyeZBDaysTask;
import com.example.think.citypass.fragment.zxm.ShouyeZBSpecialTask;

import java.util.ArrayList;

/**
 * Created by 张萌 on 2017/5/18.
 */

public class ShouyeZBActivity extends BaseActivity {
    TabLayout  tabLayout;
    ViewPager  viewPager;
    ViewpagerAdapter  adapter;
    ArrayList<String>  titleList;
    ArrayList<BaseFragment>  fragmentArrayList;
    @Override
    protected int layoutId() {
        return R.layout.shouye_zhuanbi;
    }

    @Override
    protected void initView() {
    tabLayout= (TabLayout) findViewById(R.id.shouye_zhuanbi_tablayout);
        viewPager= (ViewPager) findViewById(R.id.shouye_zhuanbi_viewpager);
    }

    @Override
    protected void initData() {
     titleList=new ArrayList<>();
        fragmentArrayList=new ArrayList<>();
        titleList.add("特殊任务");
        titleList.add("日常任务");
        fragmentArrayList.add(new ShouyeZBSpecialTask());
        fragmentArrayList.add(new ShouyeZBDaysTask());
        adapter=new ViewpagerAdapter(getSupportFragmentManager(),titleList,fragmentArrayList);
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


    /***
     * 用于viewpager的适配器
     */
    static class  ViewpagerAdapter  extends FragmentPagerAdapter{
        ArrayList<String>  titleList;
        ArrayList<BaseFragment>  fragmentArrayList;

        public ViewpagerAdapter(FragmentManager fm,ArrayList<String>  titleList,ArrayList<BaseFragment>  fragmentArrayList) {
            super(fm);
            this.titleList=titleList;
            this.fragmentArrayList=fragmentArrayList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }



}
