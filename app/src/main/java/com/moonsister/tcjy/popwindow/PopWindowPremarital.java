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
public class PopWindowPremarital extends PopupWindow {
    private Button can_can, nono_no,can_no,btn_cancel;
    private View mMenuViewPremarital;
    public PopWindowPremarital(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuViewPremarital = inflater.inflate(R.layout.popwindowpremarital, null);
        can_can = (Button) mMenuViewPremarital.findViewById(R.id.can_can);
        nono_no = (Button) mMenuViewPremarital.findViewById(R.id.nono_no);
        can_no = (Button) mMenuViewPremarital.findViewById(R.id.can_no);
        btn_cancel = (Button) mMenuViewPremarital.findViewById(R.id.btn_cancel);
        //取消按钮
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置按钮监听
        can_can.setOnClickListener(itemsOnClick);
        nono_no.setOnClickListener(itemsOnClick);
        can_no.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuViewPremarital);
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
        mMenuViewPremarital.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuViewPremarital.findViewById(R.id.pop_layout).getTop();
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
