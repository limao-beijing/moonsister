package com.moonsister.tcjy.viewholder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;
import com.moonsister.tool.lang.StringUtis;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/29.
 */
public class UserViewHolder extends BaseRecyclerViewHolder<DynamicItemBean> {
    @Bind(R.id.riv_friend_image)
    RoundedImageView rivFriendImage;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.textview_work)
    TextView textviewWork;
    @Bind(R.id.tv_wacth)
    TextView tvWacth;
    @Bind(R.id.iv_sex)
    ImageView iv_sex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_signature)
    TextView tv_signature;
    @Bind(R.id.iv_add_v)
    ImageView iv_add_v;

    public UserViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(DynamicItemBean dynamicItemBean) {
        if (dynamicItemBean == null)
            return;
        ImageServerApi.showURLSamllImage(rivFriendImage, dynamicItemBean.getFace());
        tvUserName.setText(dynamicItemBean.getNickname());
        textviewWork.setText(dynamicItemBean.getProfession());
        textviewWork.setVisibility(StringUtis.isEmpty(dynamicItemBean.getProfession()) ? View.GONE : View.VISIBLE);
        tvAge.setText(dynamicItemBean.getAge());
        tv_signature.setText(dynamicItemBean.getSignature());
        String sex = dynamicItemBean.getSex();
        if (StringUtis.equals(sex, "1")) {
            iv_sex.setImageResource(R.mipmap.boy);
        } else {
            iv_sex.setImageResource(R.mipmap.gril);
        }
        if (StringUtis.equals(dynamicItemBean.getIsauth(), "1")) {
            iv_add_v.setVisibility(View.VISIBLE);
        } else {
            iv_add_v.setVisibility(View.GONE);
        }
        if (StringUtis.equals(dynamicItemBean.getIsfollow(), "1")) {
            Drawable drawable = UIUtils.getResources().getDrawable(R.mipmap.delete_follow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvWacth.setCompoundDrawables(null, drawable, null, null);
            tvWacth.setCompoundDrawablePadding(5);
            tvWacth.setTextColor(UIUtils.getResources().getColor(R.color.text_gray_778998));
            tvWacth.setText(UIUtils.getStringRes(R.string.cancel) + UIUtils.getStringRes(R.string.wacth));
        } else {
            Drawable drawable = UIUtils.getResources().getDrawable(R.mipmap.search_user_add_wacth);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvWacth.setCompoundDrawables(null, drawable, null, null);
            tvWacth.setCompoundDrawablePadding(5);
            tvWacth.setTextColor(UIUtils.getResources().getColor(R.color.yellow_ff8201));
            tvWacth.setText(UIUtils.getStringRes(R.string.add) + UIUtils.getStringRes(R.string.wacth));
        }
        tvWacth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isfollow = dynamicItemBean.getIsfollow();
                UserActionModelImpl model = new UserActionModelImpl();
                isfollow = StringUtis.equals(isfollow, "1") ? "2" : "1";
                final String finalIsfollow = isfollow;
                model.wacthAction(dynamicItemBean.getUid(), isfollow, new BaseIModel.onLoadDateSingleListener<BaseBean>() {
                    @Override
                    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
                        if (StringUtis.equals(bean.getCode(), "1")) {
                            dynamicItemBean.setIsfollow(finalIsfollow);
                            baseRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });

            }
        });
    }

    @Override
    protected void onItemclick(View view, DynamicItemBean dynamicItemBean, int position) {
        ActivityUtils.startDynamicActivity(dynamicItemBean.getUid());
    }
}
