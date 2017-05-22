package com.example.think.citypass.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ASUS on 2017/5/17.
 */

public class SquareItemImageView extends ImageView {
    public SquareItemImageView(Context context) {
        super(context);
    }

    public SquareItemImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        //高度和宽度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec + 5, heightMeasureSpec + 5);
    }
}
