package com.hickey.tool.activity.pic;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.moonsister.tool.R;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 单张图片显示Fragment
 */
public class ImageDetailFragment extends Fragment {
	private String mImageUrl;
	private ImageView mImageView;
	private ProgressBar progressBar;
	private PhotoViewAttacher mAttacher;

	public static ImageDetailFragment newInstance(String imageUrl) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putString("url", imageUrl);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
		mImageView = (ImageView) v.findViewById(R.id.image);
		mAttacher = new PhotoViewAttacher(mImageView);
		mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
			@Override
			public void onPhotoTap(View view, float x, float y) {
				getActivity().finish();
			}

			@Override
			public void onOutsidePhotoTap() {

			}
		});

		progressBar = (ProgressBar) v.findViewById(R.id.loading);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		progressBar.setVisibility(View.VISIBLE);
		/**
		 * 设置加载错误时的默认图片
		 */
		Picasso.with(getContext()).load(mImageUrl).config(Bitmap.Config.ARGB_8888).placeholder(R.mipmap.load_big).error(R.mipmap.load_failure).into(mImageView,
				new com.squareup.picasso.Callback() {
					@Override
					public void onSuccess() {
						progressBar.setVisibility(View.GONE);
						mAttacher.update();
					}

					@Override
					public void onError() {
						progressBar.setVisibility(View.GONE);
					}
				});

//        ImageLoader.getInstance().displayImage(mImageUrl, mImageView, new SimpleImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String imageUri, View view) {
//                progressBar.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                String message = null;
//                switch (failReason.getType()) {
//                    case IO_ERROR:
//                        message = "下载错误";
//                        break;
//                    case DECODING_ERROR:
//                        message = "图片无法显示";
//                        break;
//                    case NETWORK_DENIED:
//                        message = "网络有问题，无法下载";
//                        break;
//                    case OUT_OF_MEMORY:
//                        message = "图片太大无法显示";
//                        break;
//                    case UNKNOWN:
//                        message = "未知的错误";
//                        break;
//                }
//                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                progressBar.setVisibility(View.GONE);
//                mAttacher.update();
//            }
//        });
	}
}