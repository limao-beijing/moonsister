package im.gouyin.com.progressdialog;

import android.app.Activity;
import android.app.Dialog;
import android.widget.TextView;

/**
 * Created by pc on 2016/6/4.
 */
public class ProgressDialog {
    private Dialog progressDialog;
    private boolean isShow;
    private TextView tv;
    private String msg;

    public ProgressDialog(Activity activity) {
        progressDialog = new Dialog(activity, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        msg = getProgressDialogMsg();
        tv = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
//        progressDialog.show();
    }


    public void show() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            if (tv != null)
                tv.setText(msg);
            progressDialog.show();
            isShow = true;
        }
    }

    public void dismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            isShow = false;
        }
    }

    public boolean isShowing() {
        return isShow;
    }

    public String getProgressDialogMsg() {
        return "玩命加载中...";
    }
}
