package com.example.think.citypass.utils.recyclerviewutils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.think.citypass.R;
import com.example.think.citypass.myview.MRecyclerView;

/**
 * Created by zhangzl
 * 描述:
 * 日期: 2017/2/24 0024.
 */

public class MRefreshUtils {
    private Context context;
    private RelativeLayout mainLayout;
    private TextView update, moveTextview;
    private ImageView moveImageview;
    private LinearLayout moveLayout, loadLayout, failureLayout;
    public MRecyclerView mRecyclerView;

    private boolean isTran = false;
    private int loadNum = 0;
    private boolean isAll = false;
    private int tagAll = 1;
    protected boolean isRefresh = false;
    protected boolean isLoadMore = true;

    public void setPageNums(int count) {
        tagAll = count;
        if (tagAll < 1) {
            moveLayout.setVisibility(View.GONE);
        } else {
            moveLayout.setVisibility(View.VISIBLE);
        }
    }

    public int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            update.setVisibility(View.GONE);
            slideviewTop(update, dip2px(35));
            isTran = false;
        }
    };

    public static void slideviewTop(View view, int heighet) {
        TranslateAnimation animation = new TranslateAnimation(view.getX(), view.getX(), view.getY(), view.getY() - heighet);
        animation.setDuration(300);
        view.startAnimation(animation);
    }

    public void addHeadView(View view) {
        mRecyclerView.addHeaderView(view);
    }


    public MRefreshUtils(Context context, RelativeLayout layout, OnListStateListener stateListener) {
        this.mainLayout = layout;
        this.context = context;
        this.onListStateListener = stateListener;
        mRecyclerView = (MRecyclerView) mainLayout.findViewById(R.id.m_refresh_view);
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setLoadingListener(new MRecyclerView.LoadingListener() {

            @Override
            public void onRvScrolled(int dx, int dy) {
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) mRecyclerView.getLayoutManager();
                int[] ss = layoutManager.findLastVisibleItemPositions(null);
                if (ss != null && ss.length > 0) {
                    int p = ss[ss.length - 1] / 10 + 1;
                    if (p > tagAll)
                        p = tagAll;
                    moveTextview.setText(p + "/" + tagAll);
                }
            }

            @Override
            public void onRefresh() {
                isRefresh = true;
                isLoadMore = false;
                if (onListStateListener != null) {
                    onListStateListener.onRefresh();
                }
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                isLoadMore = true;
                if (onListStateListener != null) {
                    onListStateListener.onLoadMore();
                }
            }
        });
        update = (TextView) mainLayout.findViewById(R.id.update);
        moveTextview = (TextView) mainLayout.findViewById(R.id.move_textview);
        moveLayout = (LinearLayout) mainLayout.findViewById(R.id.move_layout);
        moveLayout.setVisibility(View.VISIBLE);
        loadLayout = (LinearLayout) mainLayout.findViewById(R.id.load_layout);
        failureLayout = (LinearLayout) mainLayout
                .findViewById(R.id.failure_layout);

        failureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                failureLayout.setVisibility(View.GONE);
                loadLayout.setVisibility(View.VISIBLE);
                mRecyclerView.refresh();
            }
        });
    }

    public void requestData() {
        isLoadMore = false;
        if (isRefresh) {
            mRecyclerView.refreshComplete();
            isRefresh = false;
            isAll = false;
            if (onListStateListener != null) {
                onListStateListener.onSuccess(true, "刷新成功");
            }
        } else {
            mRecyclerView.loadMoreComplete();
            if (onListStateListener != null) {
                onListStateListener.onSuccess(false, "加载成功");
            }
        }
    }

    public void setItemDecoration(int decoration) {
        mRecyclerView.addItemDecoration(new StaggeredGridSpaceItemDecoration(context, decoration));
    }

    public void setAdapter(int type, RecyclerView.Adapter adapter) {
        adapter.setHasStableIds(true);
        mRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(type, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    public void showNormal() {
        failureLayout.setVisibility(View.GONE);
        loadLayout.setVisibility(View.GONE);
    }

    /**
     * 没有更多数据了
     *
     * @param isAll
     */
    public void setNoMore(boolean isAll) {
        mRecyclerView.setNoMore(isAll);
    }

    /**
     * 加载更多加载完毕
     */
    public void loadMoreComplete() {
        mRecyclerView.loadMoreComplete();
    }

    /**
     * 显示更新信息
     */
    public void startUpdate(final int num) {
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                update(num);
            }
        }, 1200);
    }

    private void update(int num) {
        if (isTran) {
            return;
        }
        if (num == 0) {
            update.setText("没有最新信息");
        } else {
            update.setText("已更新" + num + "条信息");
        }
        update.setVisibility(View.VISIBLE);
        isTran = true;
        // 动画添加
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    mHandler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private OnListStateListener onListStateListener;

    public void setOnListStateListener(OnListStateListener onListStateListener) {
        this.onListStateListener = onListStateListener;
    }

    /**
     * 提示信息
     */
    private void showToast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }

    public interface OnListStateListener {
        public void onLoadMore();

        public void onRefresh();

        public void onSuccess(boolean Refresh, String result);

        public void onFailure(boolean Refresh, String result);

        public void onFirstFailure();
    }


}
