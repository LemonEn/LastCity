package com.example.think.citypass.myview;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.think.citypass.R;

/**
 * Created by ASUS on 2017/5/13.
 */

public class XListViewHeaderGif extends LinearLayout {
    public ImageView playGifImageView;
    public AnimationDrawable animationDrawable;
    private LinearLayout mContainer;
    private int mState = STATE_NORMAL;
    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;

    public XListViewHeaderGif(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XListViewHeaderGif(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, 0);
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.xlistview_header_gif, null);
        addView(mContainer, lp);
        setGravity(Gravity.BOTTOM);
        playGifImageView = (ImageView) findViewById(R.id.playGifImageView);
        playGifImageView.setImageResource(R.drawable.fresh_loading);
        animationDrawable = (AnimationDrawable) playGifImageView.getDrawable();

    }

    public void setState(int state) {
        if (state == mState)
            return;
        if (state == STATE_REFRESHING) { // 显示进度
            animationDrawable.start();
        } else { // 显示箭头图片
            animationDrawable.stop();
        }
        mState = state;
    }

    public void setVisiableHeight(int height) {
        if (height < 0)
            height = 0;
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    public int getVisiableHeight() {
        return mContainer.getHeight();
    }
}
