package com.moonsister.tcjy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;


/**
 * Created by jb on 2016/10/29.
 */

public class SquareLayout extends RelativeLayout {
    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        heightMeasureSpec = widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
