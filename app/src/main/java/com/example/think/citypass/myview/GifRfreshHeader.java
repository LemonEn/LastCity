package com.example.think.citypass.myview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.think.citypass.R;


/**
 * Created by zhangzl
 * 描述:
 * 日期: 2017/2/23 0023.
 */

public class GifRfreshHeader extends LinearLayout implements BaseRefreshHeader {

    private LinearLayout mContainer;
    private ImageView playGifImageView;
    private AnimationDrawable animationDrawable;
    public int mMeasuredHeight;
    private int mState = STATE_NORMAL;

    public GifRfreshHeader(Context context) {
        super(context);
        initView();
    }

    public GifRfreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GifRfreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        // 初始情况，设置下拉刷新view高度为0
        mContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(
                R.layout.gif_refresh_header, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);

        addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        setGravity(Gravity.BOTTOM);

        playGifImageView = (ImageView) mContainer.findViewById(R.id.playGifImageView);
        playGifImageView.setImageResource(R.drawable.fresh_loading);
        animationDrawable = (AnimationDrawable) playGifImageView.getDrawable();

        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMeasuredHeight = getMeasuredHeight();
    }

    public void setState(int state) {
        if (state == mState) return;

        if (state == STATE_REFRESHING) {    // 显示进度
            animationDrawable.start();
            smoothScrollTo(mMeasuredHeight);
        } else {    // 显示箭头图片
            animationDrawable.stop();
        }

        mState = state;
    }

    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        animator.setDuration(300).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisibleHeight((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    public void setVisibleHeight(int height) {
        if (height < 0) height = 0;
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    public int getVisibleHeight() {
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        return lp.height;
    }

    public int getState() {
        return mState;
    }

    @Override
    public void onMove(float delta) {
        if (getVisibleHeight() > 0 || delta > 0) {
            setVisibleHeight((int) delta + getVisibleHeight());
            if (mState <= STATE_RELEASE_TO_REFRESH) { // 未处于刷新状态，更新箭头
                if (getVisibleHeight() > mMeasuredHeight) {
                    setState(STATE_RELEASE_TO_REFRESH);
                } else {
                    setState(STATE_NORMAL);
                }
            }
        }
    }

    @Override
    public boolean releaseAction() {
        boolean isOnRefresh = false;
        int height = getVisibleHeight();
        if (height == 0) // not visible.
            isOnRefresh = false;

        if (getVisibleHeight() > mMeasuredHeight && mState < STATE_REFRESHING) {
            setState(STATE_REFRESHING);
            isOnRefresh = true;
        }
        // refreshing and header isn't shown fully. do nothing.
        if (mState == STATE_REFRESHING && height <= mMeasuredHeight) {
            //return;
        }
        if (mState != STATE_REFRESHING) {
            smoothScrollTo(0);
        }

        if (mState == STATE_REFRESHING) {
            int destHeight = mMeasuredHeight;
            smoothScrollTo(destHeight);
        }

        return isOnRefresh;
    }

    @Override
    public void refreshComplete() {
        setState(STATE_DONE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                reset();
            }
        }, 300);
    }

    public void reset() {
        smoothScrollTo(0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                setState(STATE_NORMAL);
            }
        }, 500);
    }
}
