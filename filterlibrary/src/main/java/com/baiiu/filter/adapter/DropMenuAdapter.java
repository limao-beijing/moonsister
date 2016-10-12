package com.baiiu.filter.adapter;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.baiiu.filter.R;
import com.baiiu.filter.entity.FilterType;
import com.baiiu.filter.entity.FilterUrl;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.baiiu.filter.interfaces.OnFilterItemClickListener;
import com.baiiu.filter.typeview.DoubleListView;
import com.baiiu.filter.typeview.SingleListView;
import com.baiiu.filter.util.CommonUtil;
import com.baiiu.filter.util.UIUtil;
import com.baiiu.filter.view.FilterCheckedTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * author: baiiu
 * date: on 16/1/17 21:14
 * description:
 */
public class DropMenuAdapter implements MenuAdapter {
    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private String[] titles;
    private List<String> listSex = new ArrayList<>();
    private List<String> listAge = new ArrayList<>();
    private List<String> listCity = new ArrayList<>();
    private List<String> listMore = new ArrayList<>();

    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener) {
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
        initDatas();
    }

    private void initDatas() {
        listSex.add("不限");
        listSex.add("男");
        listSex.add("女");
        listAge.add("不限");
        listAge.add("16-25岁");
        listAge.add("26-29岁");
        listAge.add("29-35岁");
        listAge.add("36岁以上");

        listCity.add("不限");
        listCity.add("同城");

//        listCity.add("安徽");
//        listCity.add("四川");
//        listCity.add("河南");
//        listCity.add("江西");
//        listCity.add("天津");
//        listCity.add("贵州");


    }

    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        if (position == 3) {
            return 0;
        }

        return UIUtil.dp(mContext, 140);
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        View view = parentContainer.getChildAt(position);

        switch (position) {
            case 0:
                view = createSingleListView(listSex, position);
                break;
            case 1:
                view = createSingleListView(listAge, position);
                break;
            case 2:
                view = createSingleListView(listCity, position);
                break;
            case 3:
                // view = createDoubleGrid();
                view = createDoubleListView(listMore);
                break;
        }

        return view;
    }

    private View createSingleListView(List<String> lists, final int listPosition) {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item, int position) {
                        FilterUrl.instance().singleListPosition = item;

                        FilterUrl.instance().position = 0;
                        FilterUrl.instance().positionTitle = item;

                        onFilterDone(listPosition, titles[listPosition], item, position);
                    }
                });
        singleListView.setList(lists, -1);
        return singleListView;
    }


    private View createDoubleListView(List<String> lists) {
        DoubleListView<FilterType, String> comTypeDoubleListView = new DoubleListView<FilterType, String>(mContext)
                .leftAdapter(new SimpleTextAdapter<FilterType>(null, mContext) {
                    @Override
                    public String provideText(FilterType filterType) {
                        return filterType.desc;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(mContext, 44), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                    }
                })
                .rightAdapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String s) {
                        return s;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(mContext, 30), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                        checkedTextView.setBackgroundResource(android.R.color.white);
                    }
                })
                .onLeftItemClickListener(new DoubleListView.OnLeftItemClickListener<FilterType, String>() {
                    @Override
                    public List<String> provideRightList(FilterType item, int position) {
                        List<String> child = item.child;
                        if (CommonUtil.isEmpty(child)) {
                            FilterUrl.instance().doubleListLeft = item.desc;
                            FilterUrl.instance().doubleListRight = "";

                            FilterUrl.instance().position = 1;
                            FilterUrl.instance().positionTitle = item.desc;
//                            onFilterDone(position,item.desc,"");
                        }

                        return child;
                    }
                })
                .onRightItemClickListener(new DoubleListView.OnRightItemClickListener<FilterType, String>() {
                    @Override
                    public void onRightItemClick(FilterType item, String string, int positon) {
                        FilterUrl.instance().doubleListLeft = item.desc;
                        FilterUrl.instance().doubleListRight = string;
                        onFilterDone(3, item.desc, string, item.position);
                    }
                })
                .onActionItemClickListener(new DoubleListView.OnActionItemClickListener() {
                    @Override
                    public void onActionClick(View v) {
                        if (onFilterDoneListener != null) {
                            int action = -1;
                            int i = v.getId();
                            if (i == R.id.tv_all_clean) {
                                action = 1;

                            } else if (i == R.id.tv_ok) {
                                action = 2;
                            }
                            if (onFilterDoneListener != null)
                                onFilterDoneListener.onActionDone(action);
                        }
                    }
                });


        List<FilterType> list = new ArrayList<>();

        //第一项
        FilterType filterType = new FilterType();
        filterType.desc = "身高";
        List<String> childList = new ArrayList<>();
        childList.add("不限");
        childList.add("150-155cm");
        childList.add("155-160cm");
        childList.add("160-165cm");
        childList.add("165-170cm");
        childList.add("170-175cm");
        childList.add("175-180cm");
        filterType.child = childList;
        list.add(filterType);

        //第二项
        filterType = new FilterType();
        filterType.desc = "体重";
        childList = new ArrayList<>();
        childList.add("不限");
        childList.add("45kg以下");
        childList.add("45-50kg");
        childList.add("50-55kg");
        childList.add("55-60kg");
        childList.add("60-65kg");
        childList.add("65-70kg");
        childList.add("70-75kg");
        childList.add("75kg以上");
        filterType.child = childList;
        list.add(filterType);

        //第三项
        filterType = new FilterType();
        filterType.desc = "是否绑定微信";
        childList = new ArrayList<>();
        childList.add("不限");
        childList.add("是");
        childList.add("否");
        filterType.child = childList;
        list.add(filterType);
//第四项
        filterType = new FilterType();
        filterType.desc = "是否绑定手机";
        childList = new ArrayList<>();
        childList.add("不限");
        childList.add("是");
        childList.add("否");
        filterType.child = childList;
        list.add(filterType);

        //第五项
        filterType = new FilterType();
        filterType.desc = "是否在线";
        childList = new ArrayList<>();
        childList.add("不限");
        childList.add("是");
        childList.add("否");
        filterType.child = childList;
        list.add(filterType);


        //第6项
        filterType = new FilterType();
        filterType.desc = "是否接受异地恋";
        childList = new ArrayList<>();
        childList.add("不限");
        childList.add("是");
        childList.add("否");
        filterType.child = childList;
        list.add(filterType);
        //第6项
        filterType = new FilterType();
        filterType.desc = "选择";


        //初始化选中.
        comTypeDoubleListView.setLeftList(list, 0);
        comTypeDoubleListView.setRightList(list.get(0).child, 0);
        comTypeDoubleListView.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.b_c_fafafa));


        return comTypeDoubleListView;
    }

    private void onFilterDone(int listPosition, String positionTitle, String urlValue, int itemPosition) {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(listPosition, positionTitle, urlValue, itemPosition);
        }
    }

}
