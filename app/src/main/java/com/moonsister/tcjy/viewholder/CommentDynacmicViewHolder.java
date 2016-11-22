package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.TextView;

import com.hickey.network.bean.DynamicDatailsBean;
import com.hickey.network.bean.DynamicItemBean;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.DynamicAdapter;
import com.moonsister.tcjy.adapter.HomePageFragmentAdapter;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/6.
 */
public class CommentDynacmicViewHolder extends BaseHolder<DynamicDatailsBean> {
    @Bind(R.id.tv_comment_number)
    TextView mTvCommentNumber;
    @Bind(R.id.tv_wacth)
    TextView mTvWacth;
    @Bind(R.id.tv_not_like)
    TextView mTvNotLike;
    @Bind(R.id.tv_like)
    TextView mTvLike;
    @Bind(R.id.tv_home_page_vip)
    TextView tv_home_page_vip;

    @Override
    protected View initView() {
        return UIUtils.inflateLayout(R.layout.view_holder_comment_dynamic);
    }

    @Override
    public void refreshView(DynamicDatailsBean bean) {
        if (bean == null || bean.getData() == null)
            return;
        DynamicItemBean data = bean.getData();
        mTvCommentNumber.setText(UIUtils.getStringRes(R.string.comment) + "  " + data.getComment_count());

        mTvWacth.setText(getWacth(data.getType(), data));
        mTvNotLike.setText("踩 " + data.getLdon());
        mTvLike.setText("赞 " + data.getLupn());
        isShowRed(data.getType(), bean.getData());
    }

    private String getWacth(int type, DynamicItemBean bean) {
        String str = "";
        switch (type) {
            case DynamicAdapter.TYPE_CHARGE_PIC:
                str = "偷看" + "  " + bean.getBuy_num();
                break;
            case DynamicAdapter.TYPE_FREE_PIC:
                str = "查看"  + "  "+ bean.getView_num();
                break;
            case DynamicAdapter.TYPE_CHARGE_VIDEO:
                str = "偷窥" + "  " + bean.getBuy_num();
                break;
            case DynamicAdapter.TYPE_FREE_VIDEO:
                str = "观看"  + "  "+ "  " + bean.getView_num();
                break;
            case DynamicAdapter.TYPE_FREE_VOICE:
                str = "收听"  + "  "+ bean.getView_num();
                break;
            case DynamicAdapter.TYPE_CHARGE_VOICE:
                str = "偷听"  + "  "+ bean.getBuy_num();
                break;
        }
        return str;
    }

    private void isShowRed(int type, DynamicItemBean dynamicItemBean) {
        switch (type) {
            case HomePageFragmentAdapter.TYPE_CHARGE_PIC:
            case HomePageFragmentAdapter.TYPE_CHARGE_VIDEO:
            case HomePageFragmentAdapter.TYPE_CHARGE_VOICE:
                tv_home_page_vip.setVisibility(View.VISIBLE);
                tv_home_page_vip.setText(dynamicItemBean.getVip_view_num());
                break;
            case HomePageFragmentAdapter.TYPE_FREE_PIC:
            case HomePageFragmentAdapter.TYPE_FREE_VIDEO:
            case HomePageFragmentAdapter.TYPE_FREE_VOICE:
                tv_home_page_vip.setVisibility(View.GONE);
                break;
        }
    }
}
