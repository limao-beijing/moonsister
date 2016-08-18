package com.moonsister.tcjy.base;

/**
 * Created by pc on 2016/6/14.
 */
public interface BaseIPresenter<T extends BaseIView> {
    void onCreate();

    void attachView(T t);
}
