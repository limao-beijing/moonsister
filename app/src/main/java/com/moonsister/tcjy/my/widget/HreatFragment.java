package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.UserDetailBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.persenter.HreatFragmentPersenter;
import com.moonsister.tcjy.my.persenter.HreatFragmentPresenterImpl;
import com.moonsister.tcjy.my.view.HreatFragmentView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.HreatViewholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import im.gouyin.com.progressdialog.AlearDialog;

/**
 * Created by x on 2016/8/22.
 */
public class HreatFragment extends BaseFragment implements AdapterView.OnItemClickListener, HreatFragmentView {
    private HreatViewholder hreatViewholder;
    @Bind(R.id.gridView)//我页面中gridview
            GridView gridView;
    //gridview中的item数据与图片
    String uid;
    String my;
    @Bind(R.id.iv_user_icon)//用户头像
            ImageView iv_user_icon;
    @Bind(R.id.tv_user_all_income)//总收入
            TextView tv_user_all_income;
    @Bind(R.id.tv_user_day_income)//今日收入
            TextView tv_user_day_income;
    @Bind(R.id.tv_user_name)//用户名
            TextView tv_user_name;
    @Bind(R.id.tv_work)//用户职业
            ImageView tv_work;
    @Bind(R.id.tv_time)//时间
            TextView tv_time;
    @Bind(R.id.tv_age)//年龄
            TextView tv_age;
    @Bind(R.id.image_gril)//性别
            ImageView image_gril;
    @Bind(R.id.tv_address)//地址
            TextView tv_address;
    @Bind(R.id.tv_look_people)//预览个人主页
            TextView tv_look_people;
    @Bind(R.id.vip_money)//是否为VIP
            ImageView vip_money;
    String[] images_text = new String[]{"我关注的", "关注我的", "动态管理", "诚信认证", "兴趣修改", "悬赏管理", "约见管理", "修改资料", "财务中心", "屏蔽手机联系人", "设置"};
    //
    int[] images = new int[]{R.mipmap.mysee, R.mipmap.seemy, R.mipmap.makemessage, R.mipmap.vipmoney, R.mipmap.insert, R.mipmap.xuanshang, R.mipmap.yousee, R.mipmap.make, R.mipmap.money, R.mipmap.phone, R.mipmap.domake};
    //    ,,
    HreatFragmentPersenter persenter;
    UserDetailBean.DataBean.BaseinfoBean data;
    UserDetailBean.DataBean.AddonsBean addons;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        uid = UserInfoManager.getInstance().getUid();
        persenter = new HreatFragmentPresenterImpl();
        persenter.attachView(this);
        persenter.PaySubmit(uid);
        return UIUtils.inflateLayout(R.layout.my_zhuye);//加载主页
    }

    @Override
    protected void initData() {

        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        //循环加载数据到gridview中
        for (int i = 0; i < images_text.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("t", images[i]);
            //添加到List数组中
            listItem.put("m", images_text[i]);
            listItems.add(listItem);
            //设置SimpleAdapter属性
        }
        //利用simpleAdapter适配器适配数据
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItems,
                R.layout.gridviewitem, new String[]{"t", "m"}, new int[]{
                R.id.im, R.id.textview});
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);

//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                }
//            });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (images[i]) {
            case R.mipmap.mysee://我关注的
                ActivityUtils.startFollowActivity(UserInfoManager.getInstance().getUid(), 1);
                break;
            case R.mipmap.seemy://关注我的
                ActivityUtils.startFollowActivity(UserInfoManager.getInstance().getUid(), 2);
                break;
            case R.mipmap.makemessage://动态管理
                ActivityUtils.startMakeMessageActivity();
                break;
            case R.mipmap.vipmoney://VIP充值
                ActivityUtils.startBuyVipActivity();
                break;
            case R.mipmap.insert://兴趣选项
                ActivityUtils.startInsertActivity(my);
                break;
            case R.mipmap.viprenzheng://申请认证
                int status = UserInfoManager.getInstance().getCertificationStatus();

                if (status != 3) {
                    showToast("您已认证");
                    return;
                }
                ActivityUtils.startRenZhengActivity();
                break;
            case R.mipmap.make://修改资料
                ActivityUtils.startPersonalReviseActivity();
                break;
            case R.mipmap.money://财务中心
                String smobile = UserInfoManager.getInstance().getMemoryPersonInfoDetail().getSmobile();
                if (StringUtis.isEmpty(smobile)) {
                    AlearDialog dialog = new AlearDialog(AlearDialog.DialogType.bind_phone, getActivity());
                    dialog.setListenter(new AlearDialog.onClickListenter() {
                        @Override
                        public void clickType(AlearDialog.clickType type) {
                            if (type == AlearDialog.clickType.confirm_vip)
                                ActivityUtils.startRegActivity();
                            dialog.dismiss();
                        }
                    });
                } else
                    ActivityUtils.startMoneyActivity(uid);
                break;
            case R.mipmap.domake://设置
                ActivityUtils.startSettingActivity();
                break;
            case R.mipmap.xuanshang:
                showToast("敬请期待");

                break;
            case R.mipmap.yousee:
                showToast("敬请期待");
                break;

        }

    }

    @OnClick(R.id.tv_look_people)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_look_people://个人资料
                String uid = UserInfoManager.getInstance().getUid();
                ActivityUtils.startHomePageActivity(uid);
//                Intent intent=new Intent(getActivity(),PersonalActivity.class);
//                startActivity(intent);
                break;
        }
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

    @Override
    public void onStart() {
        super.onStart();
        if (!StringUtis.isEmpty(UserInfoManager.getInstance().getAuthcode()))
            persenter.PaySubmit(uid);
    }

    @Override
    public void success(UserDetailBean userDetailBean) {
        data = userDetailBean.getData().getBaseinfo();
        addons = userDetailBean.getData().getAddons();
        tv_user_name.setText(data.getNickname());//用户昵称
        int sex = data.getSex();//用户性别
        if (sex == 1) {
            image_gril.setImageResource(R.mipmap.nan);
        } else {
            image_gril.setImageResource(R.mipmap.gril);
        }
        ImageServerApi.showURLSamllImage(iv_user_icon, data.getFace());//用户头像
        String isverify = data.getVip_level();
        if (isverify.equals("0")) {
            tv_work.setVisibility(View.GONE);
        } else if (isverify.equals("1")) {
            tv_work.setImageResource(R.mipmap.vipxiao);
        } else if (isverify.equals("3")) {
            tv_work.setImageResource(R.mipmap.vipnext);
        } else if (isverify.equals("12")) {
            tv_work.setImageResource(R.mipmap.vipmost);
        }
        String birthday = data.getBirthday();//用户出生年月日
        if (birthday == "") {
            tv_time.setVisibility(View.INVISIBLE);
        } else {
            tv_time.setText(birthday);
        }
        //年龄
        tv_age.setText(data.getAge());
        tv_address.setText(data.getResidence());//用户地址
        //用户总收入
        tv_user_all_income.setText(addons.getIncome_all());

        //今日收入
        tv_user_day_income.setText(addons.getIncome_today());
        String vip_level = data.getVip_level();//判断是否为VIP
        if (vip_level.equals("1")) {
            vip_money.setVisibility(View.VISIBLE);
        } else {
            vip_money.setVisibility(View.GONE);

        }
    }

}
