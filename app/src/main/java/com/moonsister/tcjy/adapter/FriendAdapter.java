package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.DefaultDataBean;
import com.hickey.network.bean.FrientBaen;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.base.BaseRecyclerViewAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.my.widget.ChatFollowFragment;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.FriendViewHoler;

import java.util.List;

/**
 * Created by x on 2016/8/25.
 */
public class FriendAdapter extends BaseRecyclerViewAdapter<FrientBaen.DataBean> {
    private FriendViewHoler viewHoler;
    private BaseIView view;
    private int pageType;
//    @Bind(R.id.delete_text)
//    TextView delete;

    public FriendAdapter(List<FrientBaen.DataBean> list, BaseIView view) {
        super(list);
        this.view = view;
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        View view = UIUtils.inflateLayout(R.layout.myfollow);
        viewHoler = new FriendViewHoler(view);
        viewHoler.setAdapter(this);

        return viewHoler.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return viewHoler;
    }
    @Override
    public void onItemclick(View view, int position) {
//        if (datas != null && datas.get(position) != null) {
//            FrientBaen.DataBean dataBean = datas.get(position);
//            if (!StringUtis.isEmpty(dataBean.getUid())) {
//                ActivityUtils.startDynamicActivity(dataBean.getUid());
//            }
//
//        }
    }
    public void setClick(Integer position) {
        if (datas.size() - 1 >= position) {
            FrientBaen.DataBean dataBean = datas.get(position);
            if (dataBean == null)
                return;

            view.showLoading();
            UserActionModelImpl userActionModel = new UserActionModelImpl();
            userActionModel.wacthAction(dataBean.getUid(), pageType == ChatFollowFragment.PAGE_FEN ? "1" : "2", new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
                @Override
                public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                    if (bean != null) {
                        if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                            if (StringUtis.equals(dataBean.getIsfollow(), "2")) {
                                dataBean.setIsfollow("1");
                            } else
                                datas.remove(dataBean);
                            onRefresh();
//                            Events<FrientBaen.DataBean> events = new Events<>();
//                            events.what = Events.EventEnum.FRIEND_CHANGE;
//                            events.message = dataBean;
                            RxBus.getInstance().send(Events.EventEnum.FRIEND_CHANGE,null);
                        }
                        view.transfePageMsg(bean.getMsg());
                    } else {
                        view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
                    }
                    view.hideLoading();
                }

                @Override
                public void onFailure(String msg) {
                    view.hideLoading();
                    view.transfePageMsg(msg);
                }
            });

        }
    }

    public void setPageType(int pageType) {
        this.pageType = pageType;
    }

}
