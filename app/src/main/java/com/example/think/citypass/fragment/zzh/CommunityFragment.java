package com.example.think.citypass.fragment.zzh;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseFragment;
import com.example.think.citypass.myview.ChildViewPager;

import java.util.ArrayList;
import java.util.List;

public class CommunityFragment extends BaseFragment {

    private TabLayout tabLayout;

    private ChildViewPager viewPager;

    private String[] titles = {"闹闹帝", "闹闹王", "闹闹星", "广场", "话题", "推荐", "晒图", "网友自荐"};

    private List<Fragment> data;

    private ZzhPagerAdapter zzhPagerAdapter;


    @Override
    protected int layoutId() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.community_tablayout);
        viewPager = (ChildViewPager) view.findViewById(R.id.community_viewpager);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        initFragment();
        zzhPagerAdapter = new ZzhPagerAdapter(getFragmentManager(), data, titles);
        viewPager.setAdapter(zzhPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initFragment() {


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }
}
