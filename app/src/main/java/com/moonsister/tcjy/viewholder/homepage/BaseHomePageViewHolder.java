package com.moonsister.tcjy.viewholder.homepage;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.DefaultDataBean;
import com.hickey.network.bean.DynamicItemBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.time.TimeUtils;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.HomePageFragmentAdapter;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
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
    @Bind(R.id.iv_vip)
    ImageView iv_vip;
    @Bind(R.id.tv_job)
    TextView tv_job;
    @Bind(R.id.tv_home_page_vip)
    TextView tv_home_page_vip;
    @Bind(R.id.tv_home_page_vip_line)
    View tv_home_page_vip_line;

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
        String profession = dynamicItemBean.getProfession();
        if (StringUtis.isEmpty(profession)) {
            tv_job.setVisibility(View.GONE);
        } else {
            tv_job.setVisibility(View.VISIBLE);
            tv_job.setText(profession);
        }
        String level = dynamicItemBean.getVip_level();
        if (StringUtis.equals("1", level)) {
            iv_vip.setImageResource(R.mipmap.vipxiao);
        } else if (StringUtis.equals("3", level)) {
            iv_vip.setImageResource(R.mipmap.vipnext);
        } else if (StringUtis.equals("12", level)) {
            iv_vip.setImageResource(R.mipmap.vipmost);
        } else iv_vip.setImageBitmap(null);

        iv_add_v.setVisibility(StringUtis.equals(dynamicItemBean.getIsauth(), "1") ? View.VISIBLE : View.GONE);
        boolean isTop = StringUtis.equals(dynamicItemBean.getIstop(), "1");
        tv_time.setText(isTop ? UIUtils.getStringRes(R.string.stick) : TimeUtils.getDynamicTimeString(dynamicItemBean.getCreate_time()));
        tv_time.setTextColor(UIUtils.getResources().getColor(isTop ? R.color.yellow_ff8201 : R.color.text_gray_778998));
        if (isTop) {
            Drawable drawable = UIUtils.getResources().getDrawable(R.mipmap.dymiac_stcrk);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_time.setCompoundDrawables(drawable, null, null, null);
            tv_time.setCompoundDrawablePadding(10);
        } else {
            tv_time.setCompoundDrawables(null, null, null, null);

        }
        tvUserTag.setText(tags);
        dynamicContent.setText(dynamicItemBean.getTitle());
        tvHomePageComment.setText(dynamicItemBean.getLcomn());

        tvUserLike.setText(dynamicItemBean.getLupn());


        isShowRed(dynamicItemBean.getType(), dynamicItemBean);
    }


    @Override
    public void initViewClik(DynamicItemBean bean, int position) {
        tvHomePageControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(v, bean, position);
            }
        });
    }

    @Override
    protected void onItemclick(View view, DynamicItemBean bean, int position) {
        if (bean.getPageType() == 1) {
            ActivityUtils.startHomePageActivity(bean.getUid());
        } else
            ActivityUtils.startDynamicDatailsActivity(bean.getLatest_id(), bean.getType());
    }

    private void isShowRed(int type, DynamicItemBean dynamicItemBean) {
        switch (type) {
            case HomePageFragmentAdapter.TYPE_CHARGE_PIC:
            case HomePageFragmentAdapter.TYPE_CHARGE_VIDEO:
            case HomePageFragmentAdapter.TYPE_CHARGE_VOICE:
                vertical_line.setVisibility(View.VISIBLE);
                tvHomePagePay.setVisibility(View.VISIBLE);
                tv_home_page_vip.setVisibility(View.VISIBLE);
                tv_home_page_vip_line.setVisibility(View.VISIBLE);
                tvHomePagePay.setText(dynamicItemBean.getBuy_num());
                tv_home_page_vip.setText(dynamicItemBean.getVip_view_num());
                break;
            case HomePageFragmentAdapter.TYPE_FREE_PIC:
            case HomePageFragmentAdapter.TYPE_FREE_VIDEO:
            case HomePageFragmentAdapter.TYPE_FREE_VOICE:
                vertical_line.setVisibility(View.GONE);
                tvHomePagePay.setVisibility(View.GONE);
                tv_home_page_vip.setVisibility(View.GONE);
                tv_home_page_vip_line.setVisibility(View.GONE);
                break;
        }
    }


    private PopupWindow popupWindow;

    private void showPopwindow(View parent, DynamicItemBean bean, int position) {
        String uid = bean.getUid();

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
            tv_dynam_control_left.setText(StringUtis.equals(bean.getIstop(), "1") ? UIUtils.getStringRes(R.string.cancel) + UIUtils.getStringRes(R.string.stick) : UIUtils.getStringRes(R.string.stick));
            tv_dynam_control_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (popupWindow != null) {
                        upDynamic(StringUtis.equals(bean.getIstop(), "1") ? "1" : "2", bean, position);
                        popupWindow.dismiss();
                        popupWindow = null;
                    }
                }
            });
            tv_dynam_control_right.setText(UIUtils.getStringRes(R.string.delete));
            tv_dynam_control_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (popupWindow != null) {
                        delectDyanmic(bean.getLatest_id(), position);
                        popupWindow.dismiss();
                        popupWindow = null;
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

    private void delectDyanmic(String dynamicId, int position) {
        UserActionModelImpl model = new UserActionModelImpl();
        if (baseIView != null)
            baseIView.showLoading();
        model.deleteDynamic(dynamicId, new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
            @Override
            public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                if (baseIView != null) {
                    baseIView.hideLoading();
                }
                if (StringUtis.equals(bean.getCode(), "1")) {
                    if (baseRecyclerViewAdapter != null) {
                        baseRecyclerViewAdapter.delectSingleItme(position);
                    }
                }
                if (baseIView != null) {
                    baseIView.transfePageMsg(bean.getMsg());
                }

            }

            @Override
            public void onFailure(String msg) {
                if (baseIView != null) {
                    baseIView.transfePageMsg(msg);
                }
            }
        });
    }

    private void upDynamic(String type, DynamicItemBean itemBean, int position) {
        UserActionModelImpl model = new UserActionModelImpl();
        if (baseIView != null)
            baseIView.showLoading();
        model.upDynamic(StringUtis.equals("1", type) ? "2" : "1", itemBean.getLatest_id(), new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
            @Override
            public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                if (baseIView != null) {
                    baseIView.hideLoading();
                }
                if (StringUtis.equals(bean.getCode(), "1")) {
                    if (baseIView != null) {
                        if (StringUtis.equals(itemBean.getIstop(), "1")) {
                            itemBean.setIstop("2");
                            if (baseRecyclerViewAdapter != null)
                                ((HomePageFragmentAdapter) baseRecyclerViewAdapter).onDynamicItmeRefresh();
                        } else
                            ((HomePageFragmentAdapter) baseRecyclerViewAdapter).stickDynamicItme(position);

                    }
                }
                if (baseIView != null) {
                    baseIView.transfePageMsg(bean.getMsg());

                }
            }

            @Override
            public void onFailure(String msg) {
                if (baseIView != null) {
                    baseIView.transfePageMsg(msg);
                }
            }
        });
    }

}
