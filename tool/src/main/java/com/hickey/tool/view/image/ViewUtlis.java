package com.hickey.tool.view.image;

import android.view.View;

/**
 * Created by jb on 2016/11/12.
 */

public class ViewUtlis {
    /**
     * 动态测量控件的宽高
     *
     * @param view
     * @return
     */
    public static View measureView(View view) {
        int intw = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int inth = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(intw, inth);
        return view;
    }
}
