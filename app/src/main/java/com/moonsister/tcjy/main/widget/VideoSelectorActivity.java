package com.moonsister.tcjy.main.widget;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hickey.network.ImageServerApi;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.time.DateUtils;
import com.hickey.tool.time.TextFormater;
import com.hickey.tool.url.URIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.SDUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.utils.VideoUtils;
import com.moonsister.tcjy.widget.RecyclingImageView;
import com.hickey.tool.activity.video.VideoEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/8/9.
 */
public class VideoSelectorActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private List<VideoEntity> mList;
    private ImageAdapter mAdapter;

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.video);
    }

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_video_selector);
    }

    @Override
    protected void initView() {
        mList = new ArrayList<VideoEntity>();
        GridView mGridView = (GridView) findViewById(R.id.gridView);
        mAdapter = new ImageAdapter(getApplicationContext());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);

        UIUtils.sendDelayedOneMillis(new Runnable() {
            @Override
            public void run() {
                getVideoInfo();
            }
        });
    }

    @Override
    protected String initProgressDialogMsg() {
        return UIUtils.getStringRes(R.string.get_loction_video_fileing);
    }

    private void getVideoInfo() {
        showProgressDialog();
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                getVideoFile();
                subscriber.onNext("");
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        hideProgressDialog();
                        if (mAdapter != null)
                            mAdapter.notifyDataSetChanged();

                    }
                });
    }

    private void getVideoFile() {

        ContentResolver mContentResolver = getContentResolver();
        Cursor cursor = mContentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Video.DEFAULT_SORT_ORDER);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                // ID:MediaStore.Audio.Media._ID
                int id = cursor.getInt(cursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media._ID));

                // 名称：MediaStore.Audio.Media.TITLE
                String title = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                // 路径：MediaStore.Audio.Media.DATA
                String url = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.DATA));

                // 总播放时长：MediaStore.Audio.Media.DURATION
                long duration = cursor
                        .getInt(cursor
                                .getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));

                // 大小：MediaStore.Audio.Media.SIZE
                long size = (int) cursor.getLong(cursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
                if (StringUtis.isEmpty(url))
                    continue;

                if (url.endsWith(".mp4") || url.endsWith(".MP4")) {
                    VideoEntity entty = new VideoEntity();
                    entty.ID = id;
                    entty.title = title;
                    entty.filePath = url;
//                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//                    retriever.setDataSource(entty.filePath);
//                    Bitmap bitmap = retriever.getFrameAtTime();
//                    if (bitmap == null)
//                        continue;
                    entty.duration = duration;
                    entty.size = size;
                    String dataSize = TextFormater.getDataSize(entty.size);


                    if (!StringUtis.isEmpty(dataSize) && !StringUtis.equals(dataSize, "error") && !dataSize.startsWith("-")) {
                        mList.add(entty);
                    }
                }
            } while (cursor.moveToNext());

        }
        if (cursor != null) {
            cursor.close();
            cursor = null;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if (requestCode == 100) {
            Uri data1 = data.getData();
            setResult(Activity.RESULT_OK, data.putExtra("path", URIUtils.getRealFilePath(getApplicationContext(), data1)));
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Intent intent = new Intent();
            intent.setAction("android.media.action.VIDEO_CAPTURE");
            intent.addCategory("android.intent.category.DEFAULT");
            File file = new File(SDUtils.getRootFile(this) + File.separator + System.currentTimeMillis() + ".mp4");
            if (file.exists()) {
                file.delete();
            }
            Uri uri2 = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri2);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024 * 1024 * 50);
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 60);
//            Intent intent = new Intent();
//            intent.setClass(this, TakeVideoActivity.class);
            startActivityForResult(intent, 100);
        } else {
            VideoEntity vEntty = mList.get(position - 1);
            // 限制大小不能超过10M
            if (vEntty.size > 1024 * 1024 * 100) {
                String st = getResources().getString(R.string.temporary_does_not);
                Toast.makeText(this, st, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = getIntent().putExtra("path", vEntty.filePath).putExtra("dur", vEntty.duration);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    private class ImageAdapter extends BaseAdapter {

        private final Context mContext;
        private int mItemHeight = 0;
        private RelativeLayout.LayoutParams mImageViewLayoutParams;

        public ImageAdapter(Context context) {
            super();
            mContext = context;
            mImageViewLayoutParams = new RelativeLayout.LayoutParams(
                    AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
        }

        @Override
        public int getCount() {
            return mList.size() + 1;
        }

        @Override
        public Object getItem(int position) {
            return (position == 0) ? null : mList.get(position - 1);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_choose_gridi, container, false);
                holder.imageView = (RecyclingImageView) convertView.findViewById(R.id.imageView);
                holder.icon = (ImageView) convertView.findViewById(R.id.video_icon);
                holder.tvDur = (TextView) convertView.findViewById(R.id.chatting_length_iv);
                holder.tvSize = (TextView) convertView.findViewById(R.id.chatting_size_iv);
                holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                holder.imageView.setLayoutParams(mImageViewLayoutParams);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // Check the height matches our calculated column width
            if (holder.imageView.getLayoutParams().height != mItemHeight) {
                holder.imageView.setLayoutParams(mImageViewLayoutParams);
            }

            // Finally load the image asynchronously into the ImageView, this
            // also takes care of
            // setting a placeholder image while the background thread runs

            if (position == 0) {
                String st1 = getResources().getString(R.string.Video_footage);
                holder.icon.setVisibility(View.GONE);
                holder.tvDur.setVisibility(View.GONE);
                holder.tvSize.setText(st1);
                holder.imageView.setImageResource(R.mipmap.actionbar_camera_icon);
            } else {
                holder.icon.setVisibility(View.VISIBLE);
                VideoEntity entty = mList.get(position - 1);
                holder.tvDur.setVisibility(View.VISIBLE);

                holder.tvDur.setText(DateUtils.toTime(entty.duration));
                holder.tvSize.setText(TextFormater.getDataSize(entty.size));
                try {

                    ImageServerApi.showURLSamllImage(holder.imageView, VideoUtils.getInstance().getVideoThumbnail(entty.filePath));
//                    holder.imageView.setImageBitmap(ImageUtils.compressImage(ThumbnailUtils.createVideoThumbnail(entty.filePath, MediaStore.Images.Thumbnails.MICRO_KIND), 10));
//                    holder.imageView.setImageBitmap(BitmapFactory.decodeFile());z
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return convertView;
            // END_INCLUDE(load_gridview_item)
        }

        /**
         * Sets the item height. Useful for when we know the column width so the
         * height can be set to match.
         *
         * @param height
         */
        public void setItemHeight(int height) {
            if (height == mItemHeight) {
                return;
            }
            mItemHeight = height;
            mImageViewLayoutParams = new RelativeLayout.LayoutParams(
                    AbsListView.LayoutParams.MATCH_PARENT, mItemHeight);
            notifyDataSetChanged();
        }


        class ViewHolder {

            RecyclingImageView imageView;
            ImageView icon;
            TextView tvDur;
            TextView tvSize;


        }


    }

}
