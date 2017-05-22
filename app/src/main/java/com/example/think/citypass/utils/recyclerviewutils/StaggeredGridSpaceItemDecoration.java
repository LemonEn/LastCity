package com.example.think.citypass.utils.recyclerviewutils;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhangzl
 * 描述:
 * 日期: 2017/2/24 0024.
 */

public class StaggeredGridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public StaggeredGridSpaceItemDecoration(Context context,int space) {
        this.space = dip2px(context, space);
    }

    public int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;
    }
}
