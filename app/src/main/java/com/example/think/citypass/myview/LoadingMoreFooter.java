package com.example.think.citypass.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.think.citypass.R;

public class LoadingMoreFooter extends RelativeLayout {

    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;
    private ProgressBar m_footer_refresh_pb;
    private TextView m_footer_refresh_tv;
    private String loadingHint;
    private String noMoreHint;
    private String loadingDoneHint;

    public LoadingMoreFooter(Context context) {
        super(context);
        initView();
    }

    /**
     * @param context
     * @param attrs
     */
    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setLoadingHint(String hint) {
        loadingHint = hint;
    }

    public void setNoMoreHint(String hint) {
        noMoreHint = hint;
    }

    public void setLoadingDoneHint(String hint) {
        loadingDoneHint = hint;
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.m_refresh_layout, this, true);
        m_footer_refresh_tv = (TextView) findViewById(R.id.m_footer_refresh_tv);
        m_footer_refresh_pb = (ProgressBar) findViewById(R.id.m_footer_refresh_pb);
    }

    public void setState(int state) {
        switch (state) {
            case STATE_LOADING:
                m_footer_refresh_pb.setVisibility(VISIBLE);
                m_footer_refresh_tv.setText("正在努力加载中...");
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                m_footer_refresh_pb.setVisibility(GONE);
                m_footer_refresh_tv.setText("正在努力加载中...");
                this.setVisibility(View.GONE);
                break;
            case STATE_NOMORE:
                Log.e("测试","没有更多");
                m_footer_refresh_pb.setVisibility(GONE);
                m_footer_refresh_tv.setText("没有更多内容啦~");
                this.setVisibility(View.VISIBLE);
                break;
        }
    }
}
