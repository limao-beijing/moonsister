package com.moonsister.tcjy.center.widget;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragmentActivity;
import com.moonsister.tcjy.center.presenter.DynamicPublishPresenter;
import com.moonsister.tcjy.center.presenter.DynamicPublishPresenterImpl;
import com.moonsister.tcjy.center.view.DefaultDynamicView;
import com.moonsister.tcjy.dialogFragment.widget.BaseDialogFragment;
import com.moonsister.tcjy.dialogFragment.DialogMannager;
import com.moonsister.tcjy.dialogFragment.widget.ImPermissionDialog;
import com.moonsister.tcjy.manager.GaodeManager;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.permission.UserPermissionManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tool.lang.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;

/**
 * Created by jb on 2016/8/8.
 */
public class DynamicPublishActivity extends BaseFragmentActivity implements View.OnClickListener, DefaultDynamicView {
    private TextView tv_title_right;
    private DynamicPublishFragment dyf;
    private DynamicPublishPresenter presenter;

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.dynamic);
    }

    @Override
    protected Fragment initFragment() {
        tv_title_right = (TextView) findViewById(R.id.tv_title_right);
        tv_title_right.setText(UIUtils.getStringRes(R.string.publish));
        dyf = DynamicPublishFragment.newInstance();

        tv_title_right.setOnClickListener(this);
        presenter = new DynamicPublishPresenterImpl();
        presenter.attachView(this);

        return dyf;
    }


    private void checkPermsission() {
        UserPermissionManager.getInstance().checkVip(EnumConstant.PermissionType.LATEST_PUB, new UserPermissionManager.PermissionCallback() {
            @Override
            public void onStatus(EnumConstant.PermissionReasult reasult, int imCount, String sex) {
                if (reasult == EnumConstant.PermissionReasult.NOT_PERSSION) {
                    DialogMannager.getInstance().showImPermission(sex, getSupportFragmentManager(), new ImPermissionDialog.OnCallBack() {
                        @Override
                        public void onStatus(BaseDialogFragment dialogFragment, EnumConstant.DialogCallBack statusCode) {
                            if (statusCode == EnumConstant.DialogCallBack.CONFIRM) {
                                if (StringUtis.equals("1", sex)) {
                                    ActivityUtils.startBuyVipActivity();
                                } else{
                                    if (UserInfoManager.getInstance().getMemoryPersonInfoDetail().getAttestation() == 2) {
                                        UIUtils.showToast(getApplicationContext(), "认证中!");
                                        return;
                                    }
                                    ActivityUtils.startRenZhengThreeActivity();
                                }

                                dialogFragment.dismissDialogFragment();
                            } else if (statusCode == EnumConstant.DialogCallBack.CANCEL) {
                                finish();
                            }

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermsission();
    }

//    public void certificationStatus() {
//        int certificationStatus = UserInfoManager.getInstance().getCertificationStatus();
//        if (certificationStatus == 3) {
//            AlearDialog alearDialog = new AlearDialog(AlearDialog.DialogType.Certification_publish_1002, this);
//            alearDialog.setListenter(new AlearDialog.onClickListenter() {
//                @Override
//                public void clickType(AlearDialog.clickType type) {
//                    switch (type) {
//                        case cancel:
//                            finish();
//                            break;
//                        case confirm_vip:
//                            ActivityUtils.startBuyVipActivity();
//                            break;
//                        case confirm:
//                            ActivityUtils.startCertificationActivity();
//                            break;
//
//                    }
//                    alearDialog.dismiss();
//                }
//            });
//        }
//    }

    @Override
    public boolean isBaseonActivityResult() {
        return false;
    }

    @Override
    public void onClick(View v) {
        if (dyf == null)
            return;
        boolean charge = dyf.isCharge();
        List<String> lables = dyf.getLables();
        if (lables == null || lables.size() == 0) {
            showToast(UIUtils.getStringRes(R.string.lable_is_empty));
            return;
        }
        List<String> dynamicContent = dyf.getDynamicContent();
        if (dynamicContent == null || dynamicContent.size() == 0) {
            showToast(UIUtils.getStringRes(R.string.dynamic_content_is_empty));
            return;
        }

        DynamicContentFragment.DynamicType dynamicType = dyf.getDynamicType();
        if (dynamicType == DynamicContentFragment.DynamicType.PIC) {
            if (charge) {
                if (dynamicContent.size() < 6) {
                    showToast(UIUtils.getStringRes(R.string.dynamic_pic_not_more_6));
                    return;
                }
            } else {
                if (dynamicContent.size() < 1) {
                    showToast(UIUtils.getStringRes(R.string.dynamic_pic_not_more_1));
                    return;
                }
            }


        }
        String txtContent = dyf.getTXTContent();
        if (StringUtis.isEmpty(txtContent)) {
            showToast(UIUtils.getStringRes(R.string.input_not_empty));
            return;
        }

        EnumConstant.DynamicType type = null;
        if (dynamicType == DynamicContentFragment.DynamicType.PIC) {
            if (charge)
                type = EnumConstant.DynamicType.CHARGE_PIC;
            else {
                type = EnumConstant.DynamicType.FREE_PIC;
            }
        } else if (dynamicType == DynamicContentFragment.DynamicType.VOICE) {
            if (charge) {
                type = EnumConstant.DynamicType.CHARGE_VOICE;
            } else
                type = EnumConstant.DynamicType.FREE_VOICE;
        } else if (dynamicType == DynamicContentFragment.DynamicType.VIDEO) {
            if (charge)
                type = EnumConstant.DynamicType.CHARGE_VIDEO;
            else
                type = EnumConstant.DynamicType.FREE_VIDEO;
        }
        if (type == null) {
            showToast(UIUtils.getStringRes(R.string.publish_failure));
            return;
        }
        String adress = "";
        if (dyf.isShowAdress())
            adress = GaodeManager.getInstance().getStringAdress();
        presenter.sendDynamic(type, txtContent, dynamicContent, splice(lables, "|||"), adress);
    }

    @Override
    public void finishPage() {
        ActivityUtils.startDynamicActivity(UserInfoManager.getInstance().getUid());
        finish();
    }

    private String splice(List<String> arrays, String tag) {
        if (arrays == null || StringUtis.isEmpty(tag))
            return "";

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arrays.size(); i++) {
            sb.append(arrays.get(i));
            if (i != arrays.size() - 1) {
                sb.append(tag);
            }
        }
        return sb.toString();

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
    protected String initProgressDialogMsg() {
        return UIUtils.getStringRes(R.string.dynamic_uploading);
    }
}
