package com.example.think.citypass.common.config;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.think.citypass.App;
import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseActivity;
import com.example.think.citypass.common.base.BaseFragment;

import static com.example.think.citypass.App.lastFragment;


public class FragmentBuilder {

    private static FragmentBuilder fragmentBuilder;

    private FragmentManager fragmentManager;

    private BaseFragment baseFragment;

    private FragmentTransaction transaction;


    private FragmentBuilder() {
        init(App.activity);
    }

    public synchronized static FragmentBuilder getInstance() {
        if (fragmentBuilder == null)
            fragmentBuilder = new FragmentBuilder();
        return fragmentBuilder;
    }

    private void init(BaseActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
    }

    public static void clean() {
        fragmentBuilder = null;
    }

    public FragmentBuilder show(Class<? extends BaseFragment> aClass) {
        transaction = fragmentManager.beginTransaction();
        String simpleName = aClass.getSimpleName();

        baseFragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);

        if (baseFragment == null) {
            try {
                baseFragment = aClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.Home_Frame1, baseFragment, simpleName);
        }

        if (lastFragment != null) {
            transaction.hide(lastFragment);
        }

        transaction.show(baseFragment);

        transaction.addToBackStack(null);

        return this;
    }

    public BaseFragment builder() {
        lastFragment = baseFragment;
        transaction.commit();
        return baseFragment;
    }

}
