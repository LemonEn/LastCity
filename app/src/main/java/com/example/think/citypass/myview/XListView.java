package com.example.think.citypass.myview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.example.think.citypass.R;

/**
 * Created by ASUS on 2017/5/16.
 */

public class XListView extends ListView {
    private float mLastY = -1; // save event y
    private Scroller mScroller; // used for scroll back
    private OnScrollListener mScrollListener; // user's scroll listener
    private IXListViewListener mListViewListener;
    private XListViewHeaderGif mHeaderView;
    private RelativeLayout head_gif;
    private int mHeaderViewHeight; // 头部view高度
    private boolean mEnablePullRefresh = true;
    private boolean mPullRefreshing = false; // is refreashing.
    private int mScrollBack;
    private final static int SCROLLBACK_HEADER = 0;
    private final static int SCROLL_DURATION = 400; // scroll back duration
    private final static float OFFSET_RADIO = 1.8f; // support iOS like pull


    public XListView(Context context) {
        super(context);
        initWithContext(context);
    }

    public XListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithContext(context);
    }

    public XListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWithContext(context);
    }

    private void initWithContext(Context context) {
        mScroller = new Scroller(context, new DecelerateInterpolator());
        mHeaderView = new XListViewHeaderGif(context);
        head_gif = (RelativeLayout) mHeaderView.findViewById(R.id.head_gif);
        addHeaderView(mHeaderView);
        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onGlobalLayout() {
                        mHeaderViewHeight = head_gif.getHeight();//mHeaderViewContent.getHeight();
                        getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                    }
                });

    }

    /**
     * stop refresh, reset header view.
     */
    public void stopRefresh() {
        mHandler.removeCallbacks(stoprunnable);
        mHandler.postDelayed(stoprunnable, 1000);
    }

    private Runnable stoprunnable = new Runnable() {
        @Override
        public void run() {
            if (mPullRefreshing == true) {
                mPullRefreshing = false;
                resetHeaderHeight();
            }
        }
    };
    private Handler mHandler = new Handler();

    private void invokeOnScrolling() {
        if (mScrollListener instanceof OnXScrollListener) {
            OnXScrollListener l = (OnXScrollListener) mScrollListener;
            l.onXScrolling(this);
        }
    }

    private void updateHeaderHeight(float delta) {
        mHeaderView.setVisiableHeight((int) delta
                + mHeaderView.getVisiableHeight());
        if (mEnablePullRefresh && !mPullRefreshing) {
            if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
                mHeaderView.setState(XListViewHeaderGif.STATE_READY);
            } else {
                mHeaderView.setState(XListViewHeaderGif.STATE_NORMAL);
            }
        }
        setSelection(0); // scroll to top each time
    }

    /**
     * reset header view's height.
     */
    private void resetHeaderHeight() {
        int height = mHeaderView.getVisiableHeight();
        if (height == 0) // not visible.
            return;
        if (mPullRefreshing && height <= mHeaderViewHeight) {
            return;
        }
        int finalHeight = 0; // default: scroll back to dismiss header.
        if (mPullRefreshing && height > mHeaderViewHeight) {
            finalHeight = mHeaderViewHeight;
        }
        mScrollBack = SCROLLBACK_HEADER;
        mScroller.startScroll(0, height, 0, finalHeight - height,
                SCROLL_DURATION);
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if (getFirstVisiblePosition() == 0
                        && (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
                    updateHeaderHeight(deltaY / OFFSET_RADIO);
                    invokeOnScrolling();
                }
                break;
            default:
                mLastY = -1; // reset
                if (getFirstVisiblePosition() == 0) {
                    if (mEnablePullRefresh
                            && mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
                        mPullRefreshing = true;
                        mHeaderView.setState(XListViewHeaderGif.STATE_REFRESHING);
                        if (mListViewListener != null) {
                            mListViewListener.onRefresh();
                        }
                    }
                    resetHeaderHeight();
                }
                break;
        }
        try {//友盟BUG
            return super.onTouchEvent(ev);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (mScrollBack == SCROLLBACK_HEADER) {
                mHeaderView.setVisiableHeight(mScroller.getCurrY());
            }
            postInvalidate();
            invokeOnScrolling();
        }
        super.computeScroll();
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mScrollListener = l;
    }

    public void setXListViewListener(IXListViewListener l) {
        mListViewListener = l;
    }

    public interface OnXScrollListener extends OnScrollListener {
        void onXScrolling(View view);
    }

    public interface IXListViewListener {
        void onRefresh();
    }

    private float xDistance, yDistance, xLast, yLast;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if (xDistance > yDistance) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }

}
