package com.example.think.citypass.fragment.zzh.naonao;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.think.citypass.App;
import com.example.think.citypass.R;
import com.example.think.citypass.common.base.BaseFragment;
import com.example.think.citypass.utils.recyclerviewutils.MRefreshUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/5/17.
 */

public class SquareFragment extends BaseFragment {

    private RelativeLayout layout;
    private MRefreshUtils mRefreshUtils;

    private List<String> data;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                mRefreshUtils.requestData();
            }
        }
    };
    private SquareAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.naonao_refreshlist;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();

        mRefreshUtils = new MRefreshUtils(getActivity(), (RelativeLayout) getFragmentLayoutView(),  onListStateListener);

        mRefreshUtils.setAdapter(1,new SquareAdapter());
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    private MRefreshUtils.OnListStateListener onListStateListener = new MRefreshUtils.OnListStateListener() {
        @Override
        public void onLoadMore() {
            if (data.size() == 0 || data.isEmpty()) {
                mRefreshUtils.setNoMore(true);
            } else {
                handler.sendEmptyMessageDelayed(1, 2000);
            }
//            if (data.size() >= 60) {
//                mRefreshUtils.setNoMore(true);
//            } else
        }

        @Override
        public void onRefresh() {
            handler.sendEmptyMessageDelayed(1, 2000);
        }

        @Override
        public void onSuccess(boolean Refresh, String result) {
//            if (Refresh) {
//                data.clear();
//            }
//            data.addAll(data1);
//            mRefreshUtils.setPageNums(data.size() / 10 + 1);
//
            Toast.makeText(App.activity, result, Toast.LENGTH_SHORT).show();
            mRefreshUtils.showNormal();
            if (Refresh) {
                mRefreshUtils.startUpdate(10);
            }

        }

        @Override
        public void onFailure(boolean Refresh, String result) {

        }

        @Override
        public void onFirstFailure() {

        }
    };
}
