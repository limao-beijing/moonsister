package com.hickey.network;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.hickey.tool.lang.StringUtis;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by jb on 2016/6/15.
 */
public class ImageServerApi {
    private static volatile Picasso mPicasso;
    private static int mErrorImage;
    private static int mLoadingSamllImage;
    private static int mLoadingBigImage;
    private static Context mContext;

    private static Picasso getPicasso() {
        if (mPicasso == null) {
            synchronized (ImageServerApi.class) {
                if (mPicasso == null) {
                    return Picasso.with(mContext);
                }
                return mPicasso;

            }
        }
        return mPicasso;
    }

    public static void initImageServerApi(Context context, @DrawableRes int image, @DrawableRes int loadingSamllImage, @DrawableRes int loadingBigImage) {
        mContext = context;
        mErrorImage = image;
        mLoadingSamllImage = loadingSamllImage;
        mLoadingBigImage = loadingBigImage;
    }

    /**
     * 显示url
     *
     * @param imageView
     * @param url
     */
    public static void showURLImage(@NonNull ImageView imageView, @NonNull String url) {

        if (StringUtis.isEmpty(url))
            return;
        if (url.startsWith("http"))

            getPicasso().load(url).placeholder(mLoadingSamllImage).error(mLoadingSamllImage).into(imageView);
        else {
            getPicasso().load(new File(url)).placeholder(mLoadingSamllImage).error(mLoadingSamllImage).into(imageView);
        }
    }

    /**
     * 显示url小图片
     *
     * @param imageView
     * @param url
     */
    public static void showURLSamllImage(@NonNull ImageView imageView, @NonNull String url) {
        if (StringUtis.isEmpty(url))
            return;
        if (url.startsWith("http"))
            getPicasso().load(url).resize(200, 200).centerCrop().placeholder(mLoadingSamllImage).error(mLoadingSamllImage).into(imageView);
        else {
            getPicasso().load(new File(url)).resize(200, 200).centerCrop().placeholder(mLoadingSamllImage).error(mLoadingSamllImage).into(imageView);
        }
    }

    /**
     * 显示url大图片
     *
     * @param imageView
     * @param url
     */
    public static void showURLBigImage(@NonNull ImageView imageView, @NonNull String url) {
        if (StringUtis.isEmpty(url))
            return;
        if (url.startsWith("http"))

            getPicasso().load(url).placeholder(mLoadingBigImage).error(mLoadingBigImage).into(imageView);
        else {
            getPicasso().load(new File(url)).placeholder(mLoadingBigImage).error(mLoadingBigImage).into(imageView);
        }
    }

    public static void showResourcesImage(@NonNull ImageView imageView, int resourceId) {

        getPicasso().load(resourceId).placeholder(mLoadingSamllImage).error(mErrorImage).into(imageView);
    }


}
