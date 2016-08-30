package com.moonsister.tcjy.my.widget;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.RZGridViewAdapter;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.persenter.RZSecondPersenter;
import com.moonsister.tcjy.my.persenter.RZSecondPersenterImpl;
import com.moonsister.tcjy.my.view.RZSecondView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.NoScrollGridView;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/27.
 */
public class RZSecondActivity extends BaseActivity implements RZSecondView {

    @Bind(R.id.grid_view)
    NoScrollGridView gridView;
    @Bind(R.id.tv_add_pic)
    ImageView tv_add_pic;
    private ArrayList<String> pics;
    private RZSecondPersenter persenter;

    @Override
    protected View setRootContentView() {
        persenter = new RZSecondPersenterImpl();
        persenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_rzsecond);
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.apply_renzheng);
    }

    @Override
    protected void initView() {


        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.CERTIFICATION_PAGE_FINISH)
                .onNext(events -> pageFinish())
                .create();

    }

    private void pageFinish() {

        finish();
    }


    private void setRxBus() {
        RxBus.with(this).setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.GET_PHOTO_LIST)
                .onNext(events ->
                        {

                            if (events != null) {
                                Object message = events.getMessage();
                                if (message != null && message instanceof ArrayList) {
                                    ArrayList ls = (ArrayList) message;
                                    addPic(ls);

                                }
                            }
                        }
                ).create();
    }

    public void addPic(ArrayList<String> ls) {
        if (ls == null || ls.size() == 0)
            return;
        if (pics == null) {
            pics = new ArrayList<String>();
        }
        if ((pics.size() + ls.size()) > 9) {
            showToast(UIUtils.getStringRes(R.string.pic_more_nine));
            return;
        }
        if ((pics.size() + ls.size()) == 9) {
            tv_add_pic.setVisibility(View.INVISIBLE);
        }
        for (String s : ls) {
            if (!pics.contains(s)) {
                pics.add(s);
            }
        }
        gridView.setAdapter(new RZGridViewAdapter(pics));


    }

    @OnClick({R.id.tv_add_pic, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_pic:
                ActivityUtils.startPhonePicActivity();
                setRxBus();
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    private void submit() {
        if (pics == null || pics.size() == 0) {
            showToast(UIUtils.getStringRes(R.string.not_empty));
            return;
        }
        if (pics.size() < 1) {
            showToast(UIUtils.getStringRes(R.string.pic_size_not_six));
            return;
        }
        //点击提交审核后弹出dialog提醒用户是否继续
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String s = getResources().getString(R.string.tv_ren);
        builder.setMessage(s);
        builder.setTitle("提示");
        //dialog确认监听，用户点击确认则提交审核，得到并且判断之前的数据，跳转页面及带参数
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                　　    Main.this.finish();
                Intent intent = getIntent();//跳转
                if (intent == null)
                    return;
                //得到之前数据
                String address = intent.getStringExtra("address");
                String height = intent.getStringExtra("height");
                String sex = intent.getStringExtra("sex");
                String nike = intent.getStringExtra("nike");
                String avaterpath = intent.getStringExtra("path");
                String sexid = null;
                if (StringUtis.equals(UIUtils.getStringRes(R.string.boy), sex)) {
                    sexid = "1";
                } else {
                    sexid = "2";
                }
                String[] splits = null;
                if (address.contains(".")) {
                    splits = address.split("\\.");
                }
                String address1 = "";//判断之前数据是否存在
                String address2 = "";
                if (splits != null) {
                    if (splits.length >= 2) {
                        address1 = splits[0];
                        address2 = splits[1];
                    }

                }

                persenter.submit(address1, address2, height, sexid, nike, avaterpath, pics);
            }
        });
        //dialog取消监听
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

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
    public void success() {
        ActivityUtils.startRZThidActivity();
        RxBus.getInstance().send(Events.EventEnum.CERTIFICATION_PAGE_FINISH, null);
        finish();
    }
}
