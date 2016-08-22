package io.rong.imkit.provider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.RongContext;
import io.rong.imkit.widget.InputView;
import io.rong.imkit.widget.provider.InputProvider;

/**
 * Created by jb on 2016/7/29.
 */
class RongInputProvider extends InputProvider.MainInputProvider {
    public RongInputProvider(RongContext context) {
        super(context);
    }

    @Override
    public Drawable obtainSwitchDrawable(Context context) {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, InputView inputView) {
        return null;
    }

    @Override
    public void onActive(Context context) {

    }

    @Override
    public void onInactive(Context context) {

    }

    @Override
    public void onSwitch(Context context) {

    }
}
