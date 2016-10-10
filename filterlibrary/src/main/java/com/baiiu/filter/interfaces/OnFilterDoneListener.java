package com.baiiu.filter.interfaces;

/**
 * author: baiiu
 * date: on 16/1/21 23:30
 * description:
 */
public interface OnFilterDoneListener {
    void onFilterDone(int listPosition, String positionTitle, String urlValue, int itemPosition);

    void onActionDone(int action);
}