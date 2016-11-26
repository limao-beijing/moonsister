package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.viewholder.UserViewHolder;
import com.moonsister.tcjy.viewholder.dynamic.PicViewHolder;
import com.moonsister.tcjy.viewholder.dynamic.VideoViewHolder;
import com.moonsister.tcjy.viewholder.dynamic.VoiceViewHolder;

import java.util.List;

/**
 * Created by jb on 2016/8/29.
 */
public class SearchReasonFragmentAdapter extends BaseRecyclerViewAdapter<DynamicItemBean> {
    /**
     * type 动态类型 1红包图集，2普通图文，3普通小视频动态，4免费语音，5付费语音，6付费视频
     */
    //1 红包图集
    public static final int TYPE_CHARGE_PIC = 1;

    //2 普通图片
    public static final int TYPE_FREE_PIC = 2;
    //3 视频
    public static final int TYPE_FREE_VIDEO = 3;

    public static final int TYPE_FREE_VOICE = 4;

    public static final int TYPE_CHARGE_VOICE = 5;

    public static final int TYPE_CHARGE_VIDEO = 6;
    public static final int TYPE_USER = 100;

    private BaseRecyclerViewHolder holder;

    public SearchReasonFragmentAdapter(List<DynamicItemBean> list) {
        super(list);
    }

    @Override
    public int getItemViewType(int position) {
        if (datas.size() >= position) {
            return datas.get(position).getType();
        }
        return super.getItemViewType(position);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CHARGE_PIC:
                holder = new PicViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_pic, parent));
                break;
            case TYPE_FREE_PIC:
                holder = new PicViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_pic, parent));
                break;
            case TYPE_CHARGE_VIDEO:
                holder = new VideoViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_video, parent));
                break;
            case TYPE_FREE_VIDEO:
                holder = new VideoViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_video, parent));
                break;
            case TYPE_CHARGE_VOICE:
                holder = new VoiceViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_voice, parent));
                break;
            case TYPE_FREE_VOICE:
                holder = new VoiceViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_voice, parent));
                break;
            case TYPE_USER:
                holder = new UserViewHolder(UIUtils.inflateLayout(R.layout.follow_listview_item, parent));
                break;
        }
        return holder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return holder;
    }
}
