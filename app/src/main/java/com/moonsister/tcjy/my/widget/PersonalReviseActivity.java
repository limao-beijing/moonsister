package com.moonsister.tcjy.my.widget;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonalReviseMessageBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.widget.SelectPicPopupActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.manager.aliyun.AliyunManager;
import com.moonsister.tcjy.my.persenter.PersonalReviseActivityPersenter;
import com.moonsister.tcjy.my.persenter.PersonalReviseActivityPersenterImpl;
import com.moonsister.tcjy.my.view.PersonalReviseActivityView;
import com.moonsister.tcjy.my.widget.info.SelectPlandWindowActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.FilePathUtlis;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.PersonDynamicViewholder;
import com.trello.rxlifecycle.ActivityEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/9/5.
 */
public class PersonalReviseActivity extends BaseActivity implements PersonalReviseActivityView {


    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.personalreviseactivity);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected String initTitleName() {
        return getString(R.string.change_user_info);
    }


    @Override
    public void success(PersonalReviseMessageBean personalReviseMessageBean) {

    }

    @Override
    public void submitSuccess() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void transfePageMsg(String msg) {

    }
}
