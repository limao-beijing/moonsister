package com.moonsister.tcjy.viewholder.homepage;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.HomePageFragmentAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.TimeUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/11.
 */
public class BaseHomePageViewHolder extends BaseRecyclerViewHolder<DynamicItemBean> {
    @Bind(R.id.riv_user_image)
    RoundedImageView rivUserImage;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.iv_sex_icon)
    ImageView ivSexIcon;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_user_tag)
    TextView tvUserTag;
    @Bind(R.id.dynamic_content)
    TextView dynamicContent;
    @Bind(R.id.home_page_pay_line)
    View vertical_line;
    @Bind(R.id.tv_home_page_comment)
    TextView tvHomePageComment;
    @Bind(R.id.tv_home_page_pay)
    TextView tvHomePagePay;
    @Bind(R.id.tv_home_page_control)
    TextView tvHomePageControl;
    @Bind(R.id.tv_user_like)
    TextView tvUserLike;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.iv_add_v)
    ImageView iv_add_v;

    public BaseHomePageViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(DynamicItemBean dynamicItemBean) {
        if (dynamicItemBean == null)
            return;
        String uid = dynamicItemBean.getUid();
        ImageServerApi.showURLSamllImage(rivUserImage, dynamicItemBean.getFace());
        tvUserName.setText(dynamicItemBean.getNickname());
        String sex = dynamicItemBean.getSex();
        if (StringUtis.equals(sex, "1")) {
            ivSexIcon.setImageResource(R.mipmap.boy);
        } else
            ivSexIcon.setImageResource(R.mipmap.gril);
        tvSex.setText(dynamicItemBean.getAge());
        String tags = dynamicItemBean.getTags();
        if (!StringUtis.isEmpty(tags)) {
            tags = tags.replace("|||", "  ");
        }
        iv_add_v.setVisibility(StringUtis.equals(dynamicItemBean.getIsauth(), "1") ? View.VISIBLE : View.GONE);
        tv_time.setText(TimeUtils.getDynamicTimeString(dynamicItemBean.getCreate_time()));
        tvUserTag.setText(tags);
        dynamicContent.setText(dynamicItemBean.getTitle());
        tvHomePageComment.setText(dynamicItemBean.getLcomn());
        tvHomePagePay.setText(dynamicItemBean.getMoney());
        tvUserLike.setText(dynamicItemBean.getLupn());

        tvHomePageControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(v, uid);
            }
        });
        isShowRed(dynamicItemBean.getType());
    }


    @Override
    public void initViewClik(DynamicItemBean bean, int position) {

    }

    @Override
    protected void onItemclick(View view, DynamicItemBean bean, int position) {
        ActivityUtils.startDynamicDatailsActivity(bean.getLatest_id(), bean.getType());
    }

    private void isShowRed(int type) {
        switch (type) {
            case HomePageFragmentAdapter.TYPE_CHARGE_PIC:
            case HomePageFragmentAdapter.TYPE_CHARGE_VIDEO:
            case HomePageFragmentAdapter.TYPE_CHARGE_VOICE:
                vertical_line.setVisibility(View.VISIBLE);
                tvHomePagePay.setVisibility(View.VISIBLE);
                break;
            case HomePageFragmentAdapter.TYPE_FREE_PIC:
            case HomePageFragmentAdapter.TYPE_FREE_VIDEO:
            case HomePageFragmentAdapter.TYPE_FREE_VOICE:
                vertical_line.setVisibility(View.GONE);
                tvHomePagePay.setVisibility(View.GONE);
                break;
        }
    }


    private PopupWindow popupWindow;

    private void showPopwindow(View parent, String uid) {

        View view = UIUtils.inflateLayout(R.layout.pop_dynamic_control);


        View line = view.findViewById(R.id.line);
        TextView tv_dynam_control_left = (TextView) view.findViewById(R.id.tv_dynam_control_left);
        TextView tv_dynam_control_right = (TextView) view.findViewById(R.id.tv_dynam_control_right);
        if (!StringUtis.equals(UserInfoManager.getInstance().getUid(), uid)) {
            line.setVisibility(View.GONE);
            tv_dynam_control_right.setVisibility(View.GONE);
            tv_dynam_control_left.setText(UIUtils.getStringRes(R.string.report));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                        popupWindow = null;
                        if (baseIView != null) {
                            baseIView.transfePageMsg(UIUtils.getStringRes(R.string.report) + UIUtils.getStringRes(R.string.success));
                        }
                    }

                }
            });
        } else {
            tv_dynam_control_left.setText(UIUtils.getStringRes(R.string.stick));
            tv_dynam_control_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                        popupWindow = null;
                        if (baseIView != null) {
                            baseIView.transfePageMsg(UIUtils.getStringRes(R.string.stick) + UIUtils.getStringRes(R.string.success));
                        }
                    }
                }
            });
            tv_dynam_control_right.setText(UIUtils.getStringRes(R.string.delete));
            tv_dynam_control_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                        popupWindow = null;
                        if (baseIView != null) {
                            baseIView.transfePageMsg(UIUtils.getStringRes(R.string.delete) + UIUtils.getStringRes(R.string.success));
                        }
                    }
                }
            });
        }


        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();


        popupWindow = new PopupWindow(view, width, height);

//        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        int[] location = new int[2];
        parent.getLocationOnScreen(location);

        popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, location[0] - popupWindow.getWidth(), location[1]);


    }

}
