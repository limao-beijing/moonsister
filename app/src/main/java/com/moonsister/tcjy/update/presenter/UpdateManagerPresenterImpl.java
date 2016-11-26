package com.moonsister.tcjy.update.presenter;

import android.content.Context;
import android.content.pm.PackageManager;

import com.hickey.network.bean.VersionInfo;
import com.hickey.tool.ConfigUtils;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.file.PrefUtils;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.update.UpdateManager;
import com.moonsister.tcjy.update.model.UpdateManagerModel;
import com.moonsister.tcjy.update.model.UpdateManagerModelImpl;
import com.moonsister.tcjy.update.view.UpdateManagerView;
import com.moonsister.tcjy.utils.LogUtils;

/**
 * Created by jb on 2016/7/14.
 */
public class UpdateManagerPresenterImpl implements UpdateManagerPresenter, BaseIModel.onLoadDateSingleListener<VersionInfo>, BaseIModel.onDownFileleListener {

    private UpdateManagerView view;
    private UpdateManagerModel model;
    private String path;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(UpdateManagerView updateManagerView) {
        this.view = updateManagerView;
        model = new UpdateManagerModelImpl();
    }

    @Override
    public void loadVersionInfo(String apkPath) {
        path = apkPath;
        model.loadVersionInfo(this);
    }


    @Override
    public void onSuccess(VersionInfo versionInfo, BaseIModel.DataType dataType) {
        if (versionInfo == null || versionInfo.getData() == null || !StringUtis.equals(versionInfo.getCode(), AppConstant.code_request_success)) {
            return;
        }
        VersionInfo.DataBean data = versionInfo.getData();
        if (isUpdate(StringUtis.string2Int(data.getVersion()))) {
            PrefUtils.setString(ConfigUtils.getInstance().getApplicationContext(),UpdateManager.UPLOAD_DESC, data.getDesc());
            PrefUtils.setString(ConfigUtils.getInstance().getApplicationContext(),UpdateManager.UPLOAD_VNAME, data.getTitle());
            PrefUtils.setString(ConfigUtils.getInstance().getApplicationContext(),UpdateManager.UPLOAD_FSIZE, data.getSize());
            PrefUtils.setString(ConfigUtils.getInstance().getApplicationContext(),UpdateManager.UPLOAD_PDATE, data.getTime());
            model.downFile(data.getUrl(), path, this);
            this.versionInfo = versionInfo;

        }

    }



    private VersionInfo versionInfo;

    @Override
    public void onDownFileleSuccess() {
        view.transfePageMsg(UIUtils.getStringRes(R.string.down_success));
        view.downApkSuccess(versionInfo);
    }

    @Override
    public void onDownFileleFailure(String msg) {

    }
    @Override
    public void onFailure(String msg) {
        LogUtils.e(this,msg);
    }
    @Override
    public void onDownFileleProgress(long progress, long total, boolean done) {
        view.onProgress(progress, total, done);
    }

    /**
     * 检查软件是否有更新版本
     *
     * @param
     * @return
     */
    private boolean isUpdate(int version) {
        if (getVersionCode(ConfigUtils.getInstance().getApplicationContext()) >= version) {
            return false;
        }
        return true;
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private int getVersionCode(Context context) {
        int versionCode = 1;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    private String getVersionName(Context context) {
        String versionName = "";
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionName = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


}
