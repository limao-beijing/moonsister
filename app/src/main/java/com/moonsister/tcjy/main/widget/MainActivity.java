package com.moonsister.tcjy.main.widget;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hickey.network.bean.PayRedPacketPicsBean;
import com.hickey.tool.ConfigUtils;
import com.hickey.tool.activity.FragmentUtils;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.base.BaseFragment;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ApplicationConfig;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.dialogFragment.DialogMannager;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.find.widget.FindFragment;
import com.moonsister.tcjy.home.widget.HomeTopFragment;
import com.moonsister.tcjy.im.widget.IMHomeFragment;
import com.moonsister.tcjy.main.presenter.MainPresenter;
import com.moonsister.tcjy.main.presenter.MainPresenterImpl;
import com.moonsister.tcjy.main.view.MainView;
import com.moonsister.tcjy.manager.GaodeManager;
import com.moonsister.tcjy.manager.IMLoopManager;
import com.moonsister.tcjy.manager.IMManager;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.widget.HreatFragment;
import com.moonsister.tcjy.my.widget.MyFragment;
import com.moonsister.tcjy.update.UpdateManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.Bind;
import butterknife.OnClick;

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
        UIUtils.sendDelayedOneMillis(new Runnable() {
            @Override
            public void run() {
                new UpdateManager(MainActivity.this).checkUpdate();
                GaodeManager.getInstance().getLocLocation();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (StringUtis.isEmpty(UserInfoManager.getInstance().getAuthcode()))
            shwoSelectSexDialog();
    }

    /**
     * 选择男女
     */
    public void shwoSelectSexDialog() {
        DialogMannager.getInstance().showSelectSexDialog(getSupportFragmentManager());
    }

    public void bindPhoneDialog() {
        DialogMannager.getInstance().showBindPhoneDialog(getSupportFragmentManager());
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
         * 监听消息未读数
         */
        if (tvMsgNumber != null) {
            IMManager.getInstance().setMsgNumber(new IMManager.onNotReadCallback() {
                @Override
                public void onSuccess(int number) {
                    if (tvMsgNumber == null)
                        return;
                    if (number <= 0) {
                        number = 0;
                        tvMsgNumber.setText(number + "");
                        tvMsgNumber.setVisibility(View.GONE);
                    } else {
                        tvMsgNumber.setText(number + "");
                        tvMsgNumber.setVisibility(View.VISIBLE);
                    }
                }
            });
            IMManager.getInstance().uploadUnreadMsgCountTotal();
        }
//        /**
//         *会话页面点击监听
//         */
//        RongyunManager.getInstance().setConversationBehaviorListener(new MyConversationBehaviorListener() {
//            @Override
//            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
//                ActivityUtils.startDynamicActivity(userInfo.getUserId());
//                return super.onUserPortraitClick(context, conversationType, userInfo);
//            }
//        });
        /**
         * 融云设置Authcode
         */
//        RongyunManager.getInstance().setAuthcode(UserInfoManager.getInstance().getAuthcode());

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


        /**
         * 轮询消息
         */
        IMLoopManager.getInstance().start(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        /**
         * 登录融云
         */
        mMainPresenter.loginRongyun();
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
        ActivityUtils.startDynamicSendActivity();
//        ActivityUtils.startDynamicPublishActivity();
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
    public void offline(String msg) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(UIUtils.getStringRes(R.string.Logoff_notification));
        builder.setMessage(msg);
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


                            IMManager.getInstance().logoutIMService(UserInfoManager.getInstance().getUid());
                            UserInfoManager.getInstance().logout();
                            showToast(UIUtils.getStringRes(R.string.login_code_timeout));
                            ConfigUtils.getInstance().logout();
                            ActivityUtils.startLoginMainActivity();
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
                .setEvent(Events.EventEnum.GET_IM_SERVICE_KEY)
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
        IMLoopManager.getInstance().stop();
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
