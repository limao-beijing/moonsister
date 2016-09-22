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
    private  List<String> listSex = new ArrayList<>();
    private  List<String> listAge = new ArrayList<>();
    private  List<String> listCity = new ArrayList<>();
    private  List<String> listMore = new ArrayList<>();

    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener) {
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
        initDatas();
    }

    private void initDatas() {
        listSex.add("男");
        listSex.add("女");
        listAge.add("16-18岁");
        listAge.add("19-21岁");
        listAge.add("22-24岁");
        listAge.add("25-27岁");
        listAge.add("28-30岁");
        listAge.add("31-33岁");
        listAge.add("34-36岁");
        listAge.add("37-39岁");
        listCity.add("北京");
        listCity.add("江苏");
        listCity.add("安徽");
        listCity.add("四川");
        listCity.add("河南");
        listCity.add("江西");
        listCity.add("天津");
        listCity.add("贵州");


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
                view = createSingleListView(listSex);
                break;
            case 1:
                view = createSingleListView(listAge);
                break;
            case 2:
                view = createSingleListView(listCity);
                break;
            case 3:
                // view = createDoubleGrid();
                view =createDoubleListView(listMore);
                break;
        }

        return view;
    }

    private View createSingleListView(List<String> lists) {
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
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleListPosition = item;

                       // FilterUrl.instance().position = 0;
                       // FilterUrl.instance().positionTitle = item;

                        onFilterDone();
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

                            //FilterUrl.instance().position = 1;
                           // FilterUrl.instance().positionTitle = item.desc;
                            onFilterDone();
                        }

                        return child;
                    }
                })
                .onRightItemClickListener(new DoubleListView.OnRightItemClickListener<FilterType, String>() {
                    @Override
                    public void onRightItemClick(FilterType item, String string) {
                        FilterUrl.instance().doubleListLeft = item.desc;
                        FilterUrl.instance().doubleListRight = string;

                       // FilterUrl.instance().position = 1;
                       // FilterUrl.instance().positionTitle = string;

                        onFilterDone();
                    }
                });


        List<FilterType> list = new ArrayList<>();

        //第一项
        FilterType filterType = new FilterType();
        filterType.desc = "身高";
        List<String> childList = new ArrayList<>();
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
        for (int i = 0; i < 13; ++i) {
            childList.add("11" + i);
        }
        filterType.child = childList;
        list.add(filterType);

        //第三项
        filterType = new FilterType();
        filterType.desc = "微信号";
        childList = new ArrayList<>();
        childList.add("是");
        childList.add("否");
        filterType.child = childList;
        list.add(filterType);
//第四项
        filterType = new FilterType();
        filterType.desc = "手机号";
        childList = new ArrayList<>();
        childList.add("是");
        childList.add("否");
        filterType.child = childList;
        list.add(filterType);

        //第五项
        filterType = new FilterType();
        filterType.desc = "是否在线";
        childList = new ArrayList<>();
        childList.add("是");
        childList.add("否");
        filterType.child = childList;
        list.add(filterType);


        //第6项
        filterType = new FilterType();
        filterType.desc = "是否接受异地恋";
        childList = new ArrayList<>();
            childList.add("是");
            childList.add("否");
        filterType.child = childList;
        list.add(filterType);


        //初始化选中.
        comTypeDoubleListView.setLeftList(list, 1);
        comTypeDoubleListView.setRightList(list.get(1).child, -1);
        comTypeDoubleListView.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.b_c_fafafa));

        return comTypeDoubleListView;
    }

    private void onFilterDone() {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(0, "", "");
        }
    }

}
