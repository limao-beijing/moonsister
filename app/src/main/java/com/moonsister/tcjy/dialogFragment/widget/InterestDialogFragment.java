package com.moonsister.tcjy.dialogFragment.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.resposen.InterestBean;
import com.hickey.tool.ConfigUtils;
import com.hickey.tool.base.BaseDialogFragment;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.dialogFragment.presenter.InterestDialoPresenterImpl;
import com.moonsister.tcjy.dialogFragment.presenter.InterestDialogPresenter;
import com.moonsister.tcjy.dialogFragment.view.InterestDialogView;
import com.moonsister.tcjy.main.widget.MainActivity;
import com.moonsister.tcjy.widget.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/11/30.
 */

public class InterestDialogFragment extends BaseDialogFragment implements InterestDialogView, View.OnClickListener {
    @Bind(R.id.riv_avater)
    RoundedImageView mRivAvater;
    @Bind(R.id.tv_problem)
    TextView mTvProblem;
    @Bind(R.id.tv_progress_text)
    TextView mTvProgressText;
    @Bind(R.id.pb)
    ProgressBar mPb;
    @Bind(R.id.ll_content)
    LinearLayout mLlContent;
    private InterestDialogPresenter interestDialogresenter;
    private int count = 0;
    private List<InterestBean> datas;

    @NonNull
    @Override
    protected int initViewId() {
        return R.layout.df_initerest_select;
    }

    public static InterestDialogFragment newInstance() {
        return new InterestDialogFragment();
    }


    @Override
    protected void initData() {
        interestDialogresenter = new InterestDialoPresenterImpl();
        interestDialogresenter.attachView(this);
        interestDialogresenter.loadInitData();
    }


    @Override
    public void setInitData(List<InterestBean> beans) {
        this.datas = beans;
        setView();
    }

    @Override
    public void submitSuccess() {
        Activity context = ConfigUtils.getInstance().getActivityContext();
        if (context instanceof MainActivity) {
            ((MainActivity) context).bindPhoneDialog();
        }
        dismissDialogFragment();
    }

    private void setView() {
        InterestBean bean = datas.get(count);
        if (bean == null) {
            return;
        }

        mTvProgressText.setText("进度: " + (count + 1) + "/" + datas.size() + "   选择如下选项");
        ImageServerApi.showURLSamllImage(mRivAvater, bean.getFace());
        mTvProblem.setText(bean.getQuestion());
        int dimension = (int) getResources().getDimension(R.dimen.x96);
        Drawable drawable = getResources().getDrawable(R.mipmap.df_interest_point);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

//        Drawable lineBg = getResources().getDrawable(R.drawable.shape_imaginary_line);
        mLlContent.removeAllViews();
        int size = bean.getAnswer().size();
        for (int i = 0; i < size; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dimension);
            params.gravity = Gravity.CENTER_VERTICAL;
            TextView tv = new TextView(getContext());
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setText(bean.getAnswer().get(i));
            tv.setCompoundDrawables(drawable, null, null, null);
            tv.setCompoundDrawablePadding(20);
            tv.setOnClickListener(this);
            tv.setTextColor(getResources().getColor(R.color.black));
            mLlContent.addView(tv, params);
//            if (i != size - 1) {
//                ImageView line = new ImageView(getContext());
//                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                line.setBackground(lineBg);
//                line.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//                mLlContent.addView(line, params1);
//            }
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

    private JSONObject mJSONObject;
    private JSONArray mJSONArray;

    @Override
    public void onClick(View v) {

        try {
            if (v != null && v instanceof TextView) {
                if (mJSONArray == null) {
                    mJSONArray = new JSONArray();
                }
//                if (mJSONObject == null) {
                mJSONObject = new JSONObject();
//                }
                if (count < datas.size()) {
                    InterestBean bean = datas.get(count);
                    mJSONObject.put("key", bean.getKey());
                    mJSONObject.put("cont", ((TextView) v).getText().toString());
                    mJSONArray.put(mJSONObject);
                    if (count == datas.size() - 1) {
                        interestDialogresenter.submitService(mJSONArray.toString());
//                        transfePageMsg("完成 :" + mJSONArray != null ? mJSONArray.toString() : "");
//                        dismissDialogFragment();
                    } else {
                        count++;
                        setView();
                    }
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
