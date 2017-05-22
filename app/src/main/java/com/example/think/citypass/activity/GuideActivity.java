package com.example.think.citypass.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.think.citypass.App;
import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;
import com.example.think.citypass.utils.sharepreferencesutil.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/5/15.
 */

public class GuideActivity extends BaseActivity {

    private ViewPager mPager;

    private int[] images = {R.drawable.main_intro1, R.drawable.main_intro2, R.drawable.main_intro3, R.drawable.main_intro4, R.drawable.main_intro5};

    private List<View> data;

    @Override
    protected int layoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        mPager = (ViewPager) findViewById(R.id.guidePager);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            View view = View.inflate(App.activity, R.layout.guide_pager_item, null);
            ImageView image = (ImageView) view.findViewById(R.id.pagerItemImage);
            image.setBackgroundResource(images[i]);
            data.add(view);
        }
        View view = View.inflate(App.activity, R.layout.guide_pager_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.pagerItemImage);
        image.setBackgroundResource(images[4]);
        data.add(view);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(App.activity, HomeActivity.class);
                //设置点击一次之后不进入导航页
                SharedPreferencesUtils.setParam(App.activity, "guideKey", true);
                finish();
            }
        });

        MyPagerAdapter mAdapter = new MyPagerAdapter();
        mPager.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(data.get(position));
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(data.get(position));
            return data.get(position);
        }
    }
}
