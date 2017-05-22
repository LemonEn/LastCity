package com.example.think.citypass.fragment.zzh;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ASUS on 2017/5/16.
 */

public class ZzhPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> data;

    private String[] titles;


    public ZzhPagerAdapter(FragmentManager fm, List<Fragment> data, String[] titles) {
        super(fm);
        this.data = data;
        this.titles = titles;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
