package com.moonsister.tcjy;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by jb on 2016/6/15.
 */
public class ImageServerApi {
    private static volatile Picasso mPicasso;
    private static int errorImage = R.mipmap.load_failure;
    private static int loadingSamllImage = R.mipmap.load_small;
    private static int loadingBigImage = R.mipmap.load_big;

    private static Picasso getPicasso() {
        if (mPicasso == null) {
            synchronized (ImageServerApi.class) {
                if (mPicasso == null) {
                    return Picasso.with(ConfigUtils.getInstance().getApplicationContext());
                }
                return mPicasso;

            }
        }
        return mPicasso;
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

            getPicasso().load(url).placeholder(loadingSamllImage).error(loadingSamllImage).into(imageView);
        else {
            getPicasso().load(new File(url)).placeholder(loadingSamllImage).error(loadingSamllImage).into(imageView);
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
            getPicasso().load(url).resize(200,200).centerCrop().placeholder(loadingSamllImage).error(loadingSamllImage).into(imageView);
        else {
            getPicasso().load(new File(url)).resize(200,200).centerCrop().placeholder(loadingSamllImage).error(loadingSamllImage).into(imageView);
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

            getPicasso().load(url).placeholder(loadingBigImage).error(loadingBigImage).into(imageView);
        else {
            getPicasso().load(new File(url)).placeholder(loadingBigImage).error(loadingBigImage).into(imageView);
        }
    }

    public static void showResourcesImage(@NonNull ImageView imageView, int resourceId) {

        getPicasso().load(resourceId).placeholder(loadingSamllImage).error(loadingSamllImage).into(imageView);
    }


}
