package com.moonsister.tcjy.main.widget;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ApplicationConfig;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.PayRedPacketPicsBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.find.widget.FindFragment;
import com.moonsister.tcjy.home.widget.HomeFragment;
import com.moonsister.tcjy.home.widget.HomeTopFragment;
import com.moonsister.tcjy.im.widget.IMHomeFragment;
import com.moonsister.tcjy.main.presenter.MainPresenter;
import com.moonsister.tcjy.main.presenter.MainPresenterImpl;
import com.moonsister.tcjy.main.view.MainView;
import com.moonsister.tcjy.manager.GaodeManager;
import com.moonsister.tcjy.manager.RecommendMananger;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.widget.HreatFragment;
import com.moonsister.tcjy.my.widget.MyFragment;
import com.moonsister.tcjy.update.UpdateManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.FragmentUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.IMManager;
import io.rong.imkit.RongyunManager;
import io.rong.imkit.provider.MyConversationBehaviorListener;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends BaseActivity implements MainView {
    @Bind(R.id.tv_home_page)
    TextView tvHomePage;
    @Bind(R.id.tv_im_page)
    TextView tvImPage;
    @Bind(R.id.tv_center_page)
    ImageView tvCenterPage;
    @Bind(R.id.tv_find_page)
    TextView tvFindPage;
    @Bind(R.id.tv_my_page)
    TextView tvMyPage;
    @Bind(R.id.main_content)
    FrameLayout mainContent;
    @Bind(R.id.appx_banner_container)
    RelativeLayout appx_banner_container;
    @Bind(R.id.tv_msg_number)
    TextView tvMsgNumber;
    //当前一级页面显示的Fragment
    private BaseFragment mCurrentFragment, homeFragment, imHomeFragment, findFragment, myFragment;

    private FragmentManager mFragmentManager;
    private MainPresenter mMainPresenter;

    @Override
    protected View setRootContentView() {
        mMainPresenter = new MainPresenterImpl();
        mMainPresenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        mMainPresenter.switchNavigation(R.id.tv_home_page);
        initRxBus();
        initNetMianData();
        new UpdateManager(MainActivity.this).checkUpdate();
        GaodeManager.getInstance().getLocLocation();

    }

    @Override
    public boolean isAddActivity() {

        return false;
    }

    /**
     * 初始化网络数据
     */
    private void initNetMianData() {

        if (!UserInfoManager.getInstance().isLogin())
            return;
        /**
         * 首页推荐会员
         */
        RecommendMananger.getInstance().start();
        /**
         * 监听消息未读数
         */
        RongyunManager.getInstance().setMsgNumber(new RongyunManager.onNotReadCallback() {
            @Override
            public void onSuccess(int number) {
                if (tvMsgNumber == null)
                    return;
                if (number < 1) {
                    number = 0;
                    tvMsgNumber.setText(number + "");
                    tvMsgNumber.setVisibility(View.GONE);
                } else {
                    tvMsgNumber.setText(number + "");
                    tvMsgNumber.setVisibility(View.VISIBLE);
                }
            }
        });
        /**
         *会话页面点击监听
         */
        RongyunManager.getInstance().setConversationBehaviorListener(new MyConversationBehaviorListener() {
            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
                ActivityUtils.startDynamicActivity(userInfo.getUserId());
                return super.onUserPortraitClick(context, conversationType, userInfo);
            }
        });
        /**
         * 融云设置Authcode
         */
        RongyunManager.getInstance().setAuthcode(UserInfoManager.getInstance().getAuthcode());
        /**
         * 登录融云
         */
        mMainPresenter.loginRongyun();
        /**
         *认证状态
         */
        mMainPresenter.getCertificationStatus();
        /**
         * 获取用户的权限
         */
        mMainPresenter.getUserPermission();
        /**
         * 开启推送会员机制
         */
        mMainPresenter.getUserFriendList();
        IMManager.getInstance().start(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);

    }


    @Override
    protected void onBaseCreate(Bundle savedInstanceState) {
        super.onBaseCreate(savedInstanceState);
        ConfigUtils.getInstance().setActivityContext(this);
    }

    @OnClick({R.id.tv_home_page, R.id.tv_im_page, R.id.tv_center_page, R.id.tv_find_page, R.id.tv_my_page})
    public void onClick(View view) {
        mMainPresenter.switchNavigation(
                view.getId());
    }

    @Override
    public void switch2Home() {
//        BaiduManager.getInstance(this).show(this, appx_banner_container);
        if (homeFragment == null)
//            homeFragment = new HomeFragment();
            homeFragment = new HomeTopFragment();
        enterPage(homeFragment);
    }

    @Override
    public void switch2IM() {
        if (!UserInfoManager.getInstance().isLogin()) {
            RxBus.getInstance().send(Events.EventEnum.LOGIN, null);
            return;
        }
        if (imHomeFragment == null)
            imHomeFragment = new IMHomeFragment();
        enterPage(imHomeFragment);
    }

    @Override
    public void switch2Center() {
        if (!UserInfoManager.getInstance().isLogin()) {
            RxBus.getInstance().send(Events.EventEnum.LOGIN, null);
            return;
        }
        ActivityUtils.startDynamicPublishActivity();
    }

    @Override
    public void switchFind() {
        if (findFragment == null)
            findFragment = new FindFragment();
        enterPage(findFragment);
    }

    @Override
    public void switch2My() {
        if (!UserInfoManager.getInstance().isLogin()) {
            RxBus.getInstance().send(Events.EventEnum.LOGIN, null);
            return;
        }
        if (myFragment == null)
//            myFragment = new MyFragment();
            myFragment = new HreatFragment();

        enterPage(myFragment);

    }

    @Override
    public void offline() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(UIUtils.getStringRes(R.string.Logoff_notification));
        builder.setMessage(UIUtils.getStringRes(R.string.connect_conflict));
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RxBus.getInstance().send(Events.EventEnum.LOGIN_CODE_TIMEOUT, null);
                dialog.dismiss();
//                dialog = null;
            }
        });
        builder.show();


    }

    /**
     * 进入
     *
     * @param fragment
     */
    private void enterPage(BaseFragment fragment) {
        if (fragment == null)
            return;
        switchNatvigationSelect(fragment);
        if (mFragmentManager == null)
            mFragmentManager = getSupportFragmentManager();

        FragmentUtils.switchHideFragment(mFragmentManager, R.id.main_content, mCurrentFragment, fragment);
        mCurrentFragment = fragment;
    }

    private void switchNatvigationSelect(BaseFragment fragment) {
        if (fragment == null)
            return;
        tvHomePage.setSelected(fragment == homeFragment);
        tvImPage.setSelected(fragment == imHomeFragment);
        tvFindPage.setSelected(fragment == findFragment);
        tvMyPage.setSelected(fragment == myFragment);

    }

    private long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                showToast("再按一次退出程序");
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * rxbus
     */
    private void initRxBus() {
        /**
         * 进入登录
         */
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.LOGIN)
                .onNext(events -> {
                            ActivityUtils.startLoginMainActivity();
                        }
                )
                .create();
        /**
         * 登录成功
         */
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.LOGIN_SUCCSS)
                .onNext(events -> {
                            initNetMianData();
                        }
                )
                .create();
        /**
         * 身份失效
         */
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.LOGIN_CODE_TIMEOUT)
                .onNext(events -> {
                            ActivityUtils.startLoginMainActivity();
                            UserInfoManager.getInstance().logout();
                            showToast(UIUtils.getStringRes(R.string.login_code_timeout));
                            ((ApplicationConfig) ConfigUtils.getInstance().getApplicationContext()).logout();
                            imHomeFragment = null;
                            findFragment = null;
                            myFragment = null;
                            switch2Home();

                        }
                )
                .create();
        /**
         * 获取融云key
         */
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.GET_RONGYUN_KEY)
                .onNext(events -> {
                    mMainPresenter.getRongyunKey();
                })
                .create();
        /**
         * 更新红包数据
         */
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.PAY_SUCCESS_GET_DATA)
                .onNext(events -> {
                    LogUtils.e("MyFragment", "PAY_SUCCESS_GET_DATA 数据");
                    if (events != null && myFragment != null) {
                        Object message = events.message;
                        if (message instanceof PayRedPacketPicsBean) {
                            PayRedPacketPicsBean bean = (PayRedPacketPicsBean) message;
                            ((MyFragment) myFragment).updataPayData(bean);
                        }
                    }
                })
                .create();
    }

    @Override
    protected void onBaseDestroy() {
        IMManager.getInstance().stop();
        super.onBaseDestroy();
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }
}
