package com.moonsister.tcjy.viewholder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.SearchReasonBaen;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.SearchAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/7/10.
 */
public class SearchViewHolder extends BaseRecyclerViewHolder<SearchReasonBaen.DataBean> {
    @Bind(R.id.riv_user_image)
    RoundedImageView rivUserImage;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_user_fen)
    TextView tv_user_fen;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;

    private SearchAdapter adapter;

    public SearchViewHolder(View view) {
        super(view);
    }


    @Override
    public void onBindData(SearchReasonBaen.DataBean dataBean, int position) {
        ImageServerApi.showURLSamllImage(rivUserImage, dataBean.getFace());
        tvContent.setText(dataBean.getSignature());
        tvUserName.setText(dataBean.getNickname());
        String isfollow = dataBean.getIsfollow();
        if (StringUtis.equals(isfollow, "1")) {
            tvSubmit.setCompoundDrawables(null, null, null, null);
            tvSubmit.setText(UIUtils.getStringRes(R.string.already_wacth));
            tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_gred));
        } else {
            Drawable drawable = UIUtils.getResources().getDrawable(R.mipmap.add_wacth_icon);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvSubmit.setCompoundDrawables(drawable, null, null, null);
            tvSubmit.setText(UIUtils.getStringRes(R.string.wacth));
            tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_red));
        }

        tv_user_fen.setText(UIUtils.getStringRes(R.string.str_fen) + " : " + dataBean.getFansnum());
        tvSubmit.setTag(position);
        rivUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startUserinfoActivity(dataBean.getUid());
            }
        });
    }

    @Override
    public void onBindData(SearchReasonBaen.DataBean dataBean) {

    }

    @Override
    protected void onItemclick(View view, SearchReasonBaen.DataBean dataBean, int position) {

    }

    @OnClick(R.id.tv_submit)
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof Integer) {
            int positin = (int) tag;

            adapter.setClick(positin);
        }
    }

    public void setAdapter(SearchAdapter adapter) {
        this.adapter = adapter;
    }
}
