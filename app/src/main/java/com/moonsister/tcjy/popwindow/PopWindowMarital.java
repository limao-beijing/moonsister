package com.moonsister.tcjy.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.moonsister.tcjy.R;

/**
 * Created by x on 2016/9/9.
 */
public class PopWindowMarital extends PopupWindow {
    private Button yi_marital, wei_marital,li_marital, si_marital,btn_cancel;
    private View mMenuViewmarital;
    public PopWindowMarital(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuViewmarital = inflater.inflate(R.layout.popwindowpersonalmarital, null);
        yi_marital = (Button) mMenuViewmarital.findViewById(R.id.yi_marital);
        wei_marital = (Button) mMenuViewmarital.findViewById(R.id.wei_marital);
        li_marital = (Button) mMenuViewmarital.findViewById(R.id.li_marital);
        si_marital= (Button) mMenuViewmarital.findViewById(R.id.si_marital);
        btn_cancel = (Button) mMenuViewmarital.findViewById(R.id.btn_cancel);
        //取消按钮
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置按钮监听
        yi_marital.setOnClickListener(itemsOnClick);
        wei_marital.setOnClickListener(itemsOnClick);
        li_marital.setOnClickListener(itemsOnClick);
        si_marital.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuViewmarital);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuViewmarital.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuViewmarital.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

}
