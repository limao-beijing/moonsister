package com.moonsister.tcjy.update;


import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;


import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.VersionInfo;
import com.moonsister.tcjy.main.widget.MainActivity;
import com.moonsister.tcjy.update.presenter.UpdateManagerPresenter;
import com.moonsister.tcjy.update.presenter.UpdateManagerPresenterImpl;
import com.moonsister.tcjy.update.view.UpdateManagerView;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.NetWorkUtil;
import com.moonsister.tcjy.utils.PackageUtils;
import com.moonsister.tcjy.utils.PrefUtils;
import com.moonsister.tcjy.utils.SDUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.umeng.analytics.MobclickAgent;

import java.io.File;


public class UpdateManager implements UpdateManagerView, View.OnClickListener {

    /**
     * 下载的升级包软件版本号
     */
    public static final String UPLOAD_VERSIONCODE = "UPLOADVERSIONCODE";
    /**
     * 升级包的主要信息
     */
    public static final String UPLOAD_DESC = "UPLOAD_DESC";
    /**
     * 升级包的名字
     */
    public static final String UPLOAD_VNAME = "UPLOAD_VNAME";
    /**
     * 升级包的大小
     */
    public static final String UPLOAD_FSIZE = "UPLOAD_FSIZE";
    /**
     * 升级包的时间
     */
    public static final String UPLOAD_PDATE = "UPLOAD_PDATE";
    private String SAVE_APK_PATH;
    private int notification_id = 123486;
    private Context mContext;
    private TextView umeng_update_content;
    private Dialog mNoticeDialog;
    private NotificationManager nm;
    private Notification notification;

    private UpdateManagerPresenter presenter;

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate() {
//        if (!NetWorkUtil.isWifiAvailable(mContext)) {// wifi情况下下载更新包;
//            return;
//        }
        //本都存储的升级号与当前软件的版本号3种情况：
        //a:大于,说明本地已有下载好的升级包，用户没有安装
        //b:等于,说明当前版本相同，需要进行网络检查是否有更新版本
        //c:小于，说明当前存储错误，需要进行网络检查是否有更新版本
        int currentCode = PrefUtils.getInt(UPLOAD_VERSIONCODE, 0);
        int versionCode = PackageUtils.getVersionCode(mContext);
        LogUtils.e(UpdateManager.class, "currentCode ：" + currentCode);
        LogUtils.e(UpdateManager.class, "versionCode ：" + versionCode);
        if (currentCode > versionCode) {  //情况a
            File apk_file = new File(getApkPath(mContext));
            if (apk_file.exists()) {//当前路径下有升级版本包给出升级弹框
                // 显示提示对话框
                showNoticeDialog(null);
            } else {//当前路径下没有升级升级包，重新网络下载
                checkUpdate_net();
            }
        } else { //情况b,c;
            checkUpdate_net();
        }
    }

    public String getApkPath(Context context) {
        String packageName = context.getPackageName();
        if (packageName.contains(".")) {
            packageName = packageName.substring(packageName.lastIndexOf(".") + 1);
        }
        String path = SDUtils.getRootFile(context) + File.separator + "apks";
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        return File.separator + path + File.separator + packageName + ".apk";
    }

    private void checkUpdate_net() {
        presenter = new UpdateManagerPresenterImpl();
        presenter.attachView(this);
        presenter.loadVersionInfo(getApkPath(mContext));

    }


    @Override
    public void onProgress(long progress, long total, boolean done) {
        int pro = (int) ((100 * progress) / total);

        if (notification != null) {
            notification.contentView.setProgressBar(R.id.pb,
                    (int) total, (int) progress, false);
            // 设置当前值为count
            showNotification();// 这里是更新notification,就是更新进度条
        } else {
            showNotifi();
        }

    }

    @Override
    public void downApkSuccess(VersionInfo bean) {
        if (nm != null) {
            nm.cancel(notification_id);
        }
        //本地记录当前apk的版本号
        if (bean != null && bean.getData() != null && bean.getData().getVersion() != null) {

            PrefUtils.setInt(mContext, UPLOAD_VERSIONCODE, StringUtis.string2Int(bean.getData().getVersion()));
        }
        // 显示提示对话框
        showNoticeDialog(bean);
    }

    /**
     * 检查软件是否有更新版本
     *
     * @param
     * @return
     */
    private boolean isUpdate(int version) {
        if (PackageUtils.getVersionCode(mContext) >= version) {
            return false;
        }
        return true;
    }


    /**
     * 显示软件更新对话框
     *
     * @param bean
     */
    private void showNoticeDialog(VersionInfo bean) {
        if (mNoticeDialog != null) {
            return;
        }
        // 构造对话框
        Builder builder = new Builder(mContext);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.update_dialog, null);
        mNoticeDialog = builder.create();
        mNoticeDialog.show();
        mNoticeDialog.getWindow().setContentView(v);
        v.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
//         String forcedUpdating = mVersionInfo.cont.forcedUpdating;
//         // 强制更新显示
//         if ("1".equals(forcedUpdating)) {
//         MyApplication.getMainActivity().finish();
//         }
                return false;
            }
        });
        initDialogView(v, bean);

    }

    /**
     * 初始化dialog布局
     *
     * @param v
     * @param bean
     */
    private void initDialogView(View v, VersionInfo bean) {
        umeng_update_content = (TextView) v
                .findViewById(R.id.umeng_update_content);

        v.findViewById(R.id.umeng_update_id_cancel).setOnClickListener(this);
        v.findViewById(R.id.umeng_update_id_ok).setOnClickListener(this);

        initVersionInfo(bean);
    }

    private void initVersionInfo(VersionInfo bean) {
        String desc = null;
        String vname = null;
        String fsize = null;
        String pdate = null;
        if (bean == null) {
            desc = PrefUtils.getString(UPLOAD_DESC, "正在获取……");
            vname = PrefUtils.getString(UPLOAD_VNAME, "正在获取……");
            fsize = PrefUtils.getString(UPLOAD_FSIZE, "正在获取……");
            pdate = PrefUtils.getString(UPLOAD_PDATE, "正在获取……");
        } else {
            desc = bean.getData().getDesc();
            vname = bean.getData().getTitle();
            fsize = bean.getData().getSize();
            pdate = bean.getData().getTime();
        }

        if (!"".equals(desc)) {
            desc = "\n" + desc;
        }
        if (!"".equals(pdate)) {
            pdate = "\n更新时间  ：" + pdate;
        }
        if (!"".equals(fsize)) {
            fsize = "\n文件大小  ： " + fsize;
        }
        if (!"".equals(vname)) {
            vname = "\n版本号  ： " + vname;
        }
        umeng_update_content.setText(vname + fsize + pdate + desc);
    }

    //
//    /**
//     * 下载apk文件
//     *
//     * @param bean
//     */
//    private void downloadApk(final VersionInfo bean) {
//        if (httpHelp == null) {
//            httpHelp = new HttpHelp();
//        }
//        File apkfile = new File(GlobalConstant.SAVE_APK_PATH);
//        if (apkfile.exists()) {
//            apkfile.delete();
//        }
//        httpHelp.downLoad(bean.cont.durl, GlobalConstant.SAVE_APK_PATH,
//                new LoadRequestCallBack() {
//
//                    @Override
//                    public void onLoading(long total, long current,
//                                          boolean isUploading) {
//                        if (notification != null) {
//                            notification.contentView.setProgressBar(R.id.pb,
//                                    (int) total, (int) current, false);
//                            // 设置当前值为count
//                            showNotification();// 这里是更新notification,就是更新进度条
//                        } else {
//                            showNotifi();
//                        }
//                    }
//
//                    @Override
//                    public void onSucceed(ResponseInfo mAdapter) {
//                        if (nm != null) {
//                            nm.cancel(notification_id);
//                        }
//                        //本地记录当前apk的版本号
//                        if (bean != null && bean.cont != null && bean.cont.vcode != null) {
//                            int code = 0;
//                            try {
//                                code = Integer.parseInt(bean.cont.vcode);
//                            } catch (Exception e) {
//                                code = 0;
//                            }
//                            PrefUtils.setInt(mContext, GlobalConstant.UPLOADVERSIONCODE, code);
//                        }
//                        // 显示提示对话框
//                        showNoticeDialog(bean);
//                    }
//
//                    @Override
//                    public void onFailure(HttpException arg0, String arg1) {
//                        UIUtils.showToast(mContext, "更新包下载失败！");
//
//                    }
//                });
//    }
//
    private void showNotifi() {
        String appName = UIUtils.getResources()
                .getString(R.string.app_name)
                + "正在升级";
        nm = (NotificationManager) mContext.getSystemService(
                Context.NOTIFICATION_SERVICE);
        notification = new Notification(R.mipmap.ic_launcher, appName,
                System.currentTimeMillis());
        notification.contentView = new RemoteViews(ConfigUtils.getInstance().getApplicationContext()
                .getPackageName(), R.layout.up_notification);
        notification.contentView.setTextViewText(R.id.down_tv, appName);
        notification.flags = Notification.FLAG_ONGOING_EVENT
                | Notification.FLAG_AUTO_CANCEL;
        notification.icon = android.R.drawable.stat_sys_download;
        // 使用notification.xml文件作VIEW
        // 设置进度条，最大值 为100,当前值为0，最后一个参数为true时显示条纹
        // （就是在Android Market下载软件，点击下载但还没获取到目标大小时的状态）
        // Intent notificationIntent = new Intent(UIUtils.getContext(),
        // MainActivity.class);
        // PendingIntent contentIntent =
        // PendingIntent.getActivity(UIUtils.getContext(),0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        // notification.contentIntent = contentIntent;
    }

    public void showNotification() {
        nm.notify(notification_id, notification);
    }
//

    /**
     * 安装APK文件
     */
    private void installApk() {
        final File apkfile = new File(getApkPath(mContext));
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        mContext.startActivity(i);
        ((MainActivity) (mContext)).finish();
        MobclickAgent.onKillProcess(mContext);
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.umeng_update_id_ok:
                if (mNoticeDialog != null) {
                    mNoticeDialog.dismiss();
                }
                installApk();
                break;
            case R.id.umeng_update_id_cancel:
                if (mNoticeDialog != null) {
                    mNoticeDialog.dismiss();
                }
                break;
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void transfePageMsg(String msg) {
//        UIUtils.showToast(ConfigUtils.getInstance().getActivityContext(), msg);
        LogUtils.e(this, msg);
    }


}
