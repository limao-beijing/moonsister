package com.moonsister.tcjy.center.widget;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.DynamicContent;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.widget.PictureSelectorActivity;
import com.moonsister.tcjy.main.widget.VideoSelectorActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.SDUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.utils.VideoUtils;
import com.moonsister.tcjy.widget.NoScrollGridView;
import com.moonsister.tcjy.widget.RoundedImageView;
import com.moonsister.tcjy.widget.speak.VoicePlay;
import com.moonsister.tcjy.widget.takevideo.TakeVideoActivity;
import com.trello.rxlifecycle.FragmentEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/8/9.
 */
public class DynamicContentFragment extends BaseFragment {
    @Bind(R.id.iv_add_content)
    ImageView ivAddContent;
    @Bind(R.id.rl_root_content)
    RelativeLayout root;
    private NoScrollGridView noScrollGridView;
    private ArrayList pics;
    private String videoPath;
    private String voicePath;
    private DynamicType dynamicType;

    public enum DynamicType {
        PIC, VIDEO, VOICE
    }

    /**
     * 获取动态类型
     *
     * @return
     */
    public DynamicType getDynamicType() {
        return dynamicType;
    }

    /**
     * 获取动态类型
     *
     * @return
     */
    public List<String> getDynamicContent() {
        List<String> dynamicContents = new ArrayList<String>();
        switch (dynamicType) {
            case PIC:
                if (pics != null) {
                    for (Object path : pics) {
                        if (path instanceof String) {
                            dynamicContents.add((String) path);
                        } else if (path instanceof Uri) {
                            dynamicContents.add(((Uri) path).getPath());
                        }
                    }

                }
                break;
            case VIDEO:
                dynamicContents.add(videoPath);
                break;
            case VOICE:
                dynamicContents.add(voicePath);
                break;
        }

        return dynamicContents;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return UIUtils.inflateLayout(R.layout.fragment_dynamic_content);
    }

    public static DynamicContentFragment newInsatance() {
        return new DynamicContentFragment();
    }

    @Override
    protected void initData() {
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.SWITCH_ITEM)
                .onNext(events -> {
                    if (events != null) {
                        Object message = events.message;
                        if (message != null && message instanceof Bundle) {
                            Bundle bundle = (Bundle) message;
                            String tag = bundle.getString("tag");
                            if (StringUtis.equals(tag, this.getClass().getName())) {
                                String id = bundle.getString("id");
                                doClick(id);
                            }
                        }
                    }
                })
                .create();
    }

    private void doClick(String id) {
        switch (id) {
            case "1":
                dynamicType = DynamicType.PIC;
                startPicActivityForResult();
                noScrollGridView = new NoScrollGridView(getActivity());
                noScrollGridView.setNumColumns(3);
                noScrollGridView.setHorizontalSpacing(20);
                noScrollGridView.setVerticalSpacing(20);
                root.addView(noScrollGridView);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) noScrollGridView.getLayoutParams();
                int dimension = (int) getResources().getDimension(R.dimen.x30);
                params.topMargin = dimension;
                params.bottomMargin = dimension;
                params.leftMargin = dimension;
                params.rightMargin = dimension;
                noScrollGridView.setLayoutParams(params);
                break;
            case "2":
                dynamicType = DynamicType.VIDEO;
                startVideoActivityForResult();
                break;
            case "3":
                dynamicType = DynamicType.VOICE;
                startVoiceActivityForResult();
                break;
        }

    }

    @OnClick(R.id.iv_add_content)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_content:
                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
                map.put("1", UIUtils.getStringRes(R.string.pic));
                map.put("2", UIUtils.getStringRes(R.string.video));
                map.put("3", UIUtils.getStringRes(R.string.voice));
                ActivityUtils.startSwitchItemActivity(map, this.getClass().getName());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if (requestCode == 1) {
            ArrayList listExtra = data.getStringArrayListExtra("android.intent.extra.RETURN_RESULT");
            if (listExtra == null)
                return;
            if (pics == null)
                pics = new ArrayList();
            if (pics.size() > 0) {
                Object o = pics.get(pics.size() - 1);
                if (o instanceof Integer)
                    pics.remove(pics.size() - 1);
            }
            if (pics.size() + listExtra.size() > 9) {
                pics.add(R.mipmap.add_images);
                showToast(UIUtils.getStringRes(R.string.pic_more_nine));
                return;
            }
            pics.addAll(listExtra);
            if (pics.size() < 9 && pics.size() > 0) {
                if (pics.get(pics.size() - 1) instanceof Integer) {
                } else {
                    pics.add(R.mipmap.add_images);
                }

            }
            ivAddContent.setVisibility(View.GONE);
            ShowPicAdapter showPicAdapter = new ShowPicAdapter(pics);
            noScrollGridView.setAdapter(showPicAdapter);
        } else if (requestCode == 2) {
            String video_path = data.getStringExtra("path");
//            String video_path = data.getData().getPath();
            videoPath = video_path;
            ivAddContent.setVisibility(View.GONE);
            String videoThumbnail = VideoUtils.getInstance().getVideoThumbnail(video_path);
            ImageView imageView = new ImageView(getActivity());
            ImageServerApi.showURLBigImage(imageView, videoThumbnail);
            root.addView(imageView);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            int dimension = (int) getResources().getDimension(R.dimen.x240);
            params.width = dimension;
            params.height = dimension;
            imageView.setLayoutParams(params);

        } else if (requestCode == 3) {
            String video_path = data.getStringExtra("path");

            voicePath = video_path;
            if (StringUtis.isEmpty(video_path))
                return;
            int dimension = (int) getResources().getDimension(R.dimen.x240);
            ImageView bg = new ImageView(getActivity());
            ImageServerApi.showURLBigImage(bg, UserInfoManager.getInstance().getAvater());
            root.addView(bg);
            RelativeLayout.LayoutParams bgparams = (RelativeLayout.LayoutParams) bg.getLayoutParams();
            bgparams.height = dimension;
            bgparams.width = dimension;
            bgparams.addRule(RelativeLayout.CENTER_IN_PARENT);
            bg.setLayoutParams(bgparams);

            ivAddContent.setVisibility(View.GONE);


            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.mipmap.dynamic_publish_voice);
            root.addView(imageView);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            imageView.setLayoutParams(params);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new VoicePlay().playVoice(getActivity(), video_path);
                }
            });
        }

    }

    private void startPicActivityForResult() {
        Intent intent = new Intent(getActivity(), PictureSelectorActivity.class);
        int munber = 0;
        if (pics != null) {
            munber = pics.size();
            if (pics.size() > 0) {
                Object o = pics.get(pics.size() - 1);
                if (o instanceof Integer)
                    munber = munber - 1;
            }
        }
        intent.putExtra("max", munber);
        startActivityForResult(intent, 1);
    }

    private void startVideoActivityForResult() {

        Intent intent = new Intent(getActivity(), VideoSelectorActivity.class);

        startActivityForResult(intent, 2);
    }


    private void startVoiceActivityForResult() {
        Intent intent = new Intent(getActivity(), VoiceActivity.class);
        startActivityForResult(intent, 3);
    }


    private class ShowPicAdapter extends BaseAdapter {
        private List datas;

        public ShowPicAdapter(List datas) {
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = UIUtils.inflateLayout(R.layout.item_dynamic_publish);
            ImageView pic = (ImageView) view.findViewById(R.id.iv_pic);
            View delete = view.findViewById(R.id.iv_delete_pic);
            delete.setVisibility(View.VISIBLE);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pics.remove(position);
//                    datas.remove(position);
                    if (pics.size() == 0)
                        ivAddContent.setVisibility(View.VISIBLE);
                    notifyDataSetChanged();
                }
            });
            Object o = datas.get(position);
            if (o != null) {
                if (o instanceof String) {
                    ImageServerApi.showURLSamllImage(pic, (String) o);

                } else if (o instanceof Integer) {
                    delete.setVisibility(View.GONE);
                    ImageServerApi.showResourcesImage(pic, (Integer) o);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startPicActivityForResult();
                        }
                    });
                } else if (o instanceof Uri) {
                    ImageServerApi.showURLBigImage(pic, ((Uri) o).getPath());
                }
            }
            return view;
        }
    }
}
