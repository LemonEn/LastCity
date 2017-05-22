package com.example.think.citypass.activity.zxm;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;
import com.example.think.citypass.common.base.BaseFragment;
import com.example.think.citypass.fragment.zxm.ShouYeFragment;
import com.example.think.citypass.fragment.zxm.ShouyeHBxiaoliang;
import com.example.think.citypass.fragment.zxm.ShouyeHBzhonghe;
import com.example.think.citypass.fragment.zxm.ShouyeZBDaysTask;
import com.example.think.citypass.fragment.zxm.ShouyeZBSpecialTask;
import com.example.think.citypass.fragment.zxm.Shouye_huabi_jiage;
import com.example.think.citypass.myview.TipView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张萌 on 2017/5/18.
 */

public class ShouyeHuabi  extends BaseActivity {
    TabLayout tabLayout;
    private TipView tipView;
    RollPagerView  rollPagerView;
    ViewPager viewPager;
   ViewpagerAdapter adapter;
    ArrayList<String> titleList;
    ArrayList<BaseFragment>  fragmentArrayList;

    @Override
    protected int layoutId() {
        return R.layout.shouye_huabi;
    }

    @Override
    protected void initView() {
        tabLayout= (TabLayout) findViewById(R.id.shouye_huabi_tablayout);
        viewPager= (ViewPager) findViewById(R.id.shouye_huabi_viewpager);
        rollPagerView= (RollPagerView) findViewById(R.id.shouye_huabi_rollpageview);
        //设置播放时间间隔
        rollPagerView.setPlayDelay(2000);
        //设置透明度
        rollPagerView.setAnimationDurtion(500);
        //设置适配器
        rollPagerView.setAdapter(new Banner_Adapter());
        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        rollPagerView.setHintView(new ColorPointHintView(ShouyeHuabi.this, Color.YELLOW, Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);
        tipView = (TipView) findViewById(R.id.looptextview);
        tipView.setTipList(generateTips());
    }

    private List<String> generateTips() {
        List<String> tips = new ArrayList<>();
        for (int i = 100; i < 120; i++) {
            tips.add("我怀念的是无话不说");
            tips.add("我怀念的是一起做梦");
            tips.add("我怀念的是争吵以后还是想要");
            tips.add("爱你的冲动");
        }
        return tips;
    }




    @Override
    protected void initData() {
        titleList=new ArrayList<>();
        fragmentArrayList=new ArrayList<>();
        titleList.add("综合");
        titleList.add("销量");
        titleList.add("价格");
        fragmentArrayList.add(new ShouyeHBzhonghe());
        fragmentArrayList.add(new ShouyeHBxiaoliang());
        fragmentArrayList.add(new Shouye_huabi_jiage());
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
    class  ViewpagerAdapter  extends FragmentPagerAdapter {
        ArrayList<String>  titleList;
        ArrayList<BaseFragment>  fragmentArrayList;

        public ViewpagerAdapter(FragmentManager fm, ArrayList<String>  titleList, ArrayList<BaseFragment>  fragmentArrayList) {
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


    /***
     * 轮播图的适配器
     */
    class Banner_Adapter extends StaticPagerAdapter {


        private int[] imgs ={
                R.drawable.my_main_advice1,
                R.drawable.my_main_advice2,
                R.drawable.my_main_advice3,
                R.drawable.my_main_advice4,

        };
        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }



}
