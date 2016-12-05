package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.LogUtils;
import com.hickey.network.ModuleServerApi;
import com.hickey.network.bean.resposen.CallMessageBean;
import com.hickey.tool.base.BaseResponse;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.hyphenate.easeui.mvp.model.ObservableMapUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.manager.CallLoopManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by jb on 2016/12/1.
 */
public class CallLoopViewHolder extends BaseHolder<CallMessageBean> {
    @Bind(R.id.riv_avater)
    RoundedImageView mRivAvater;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_cancel)
    TextView mTvCancel;
    @Bind(R.id.tv_ok)
    TextView mTvOk;
    private CallLoopManager.CallLoopManagerHandler mHandler;
    private CallMessageBean data;
    private String mAuthcode;

    @Override
    protected View initView() {
        return UIUtils.inflateLayout(R.layout.vh_call_loop);
    }

    @Override
    public void refreshView(CallMessageBean data) {
        if (data == null)
            return;
        this.data = data;
        ImageServerApi.showURLSamllImage(mRivAvater, data.getPic());
        mTvTitle.setText(data.getTitle());
        String type = data.getType();
        //1为打招呼模式，2为看动态模式
        if (StringUtis.equals(type, "1")) {
            mTvOk.setText("打个招呼");
        } else {
            mTvOk.setText("立即查看");
        }
//        mHandler.sendEmptyMessageDelayed(CallLoopManager.HANDLER_TYPE_GONE_VIEW, 5000);
    }

    @OnClick({R.id.tv_cancel, R.id.tv_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                mHandler.sendEmptyMessage(CallLoopManager.HANDLER_TYPE_GONE_VIEW);
                break;
            case R.id.tv_ok:
                if (data != null) {
                    String type = data.getType();
                    if (StringUtis.equals(type, "1")) {
                        upCall(data.getUid());
                        mHandler.sendEmptyMessage(CallLoopManager.HANDLER_TYPE_GONE_VIEW);
                    } else {
                        ActivityUtils.startHomePageActivity(data.getUid());
                        mHandler.sendEmptyMessage(CallLoopManager.HANDLER_TYPE_GONE_VIEW);
                    }
                }
                break;
        }
    }

    public void setHandler(CallLoopManager.CallLoopManagerHandler handler) {
        mHandler = handler;
    }

    private void upCall(String uid) {
        Observable<BaseResponse> observable = ModuleServerApi.getAppAPI().getUpCall(uid, mAuthcode, AppConstant.CHANNEL_ID);
        ObservableMapUtils.getObservable(observable).subscribe(new Subscriber<BaseResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(CallLoopViewHolder.this, "Throwable : " + e.getMessage());
            }

            @Override
            public void onNext(BaseResponse o) {
                LogUtils.e(CallLoopViewHolder.this, "code : " + o.getCode() + "  msg : " + o.getMsg());
                if (o != null && o.getCode() == 1) {
                    UIUtils.showToast(contentView.getContext(), o.getMsg());
                }

            }
        });
    }

    public void setAuthcode(String authcode) {
        mAuthcode = authcode;
    }
}
