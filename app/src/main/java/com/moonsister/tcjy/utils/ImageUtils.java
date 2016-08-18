package com.moonsister.tcjy.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by jb on 2016/6/28.
 */
public class ImageUtils {

//    /**
//     * 模糊效果
//     * @param bmp
//     * @return
//     */
//    private Bitmap blurImage(Bitmap bmp)
//    {
//        int width = bmp.getWidth();
//        int height = bmp.getHeight();
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//
//        int pixColor = 0;
//
//        int newR = 0;
//        int newG = 0;
//        int newB = 0;
//
//        int newColor = 0;
//
//        int[][] colors = new int[9][3];
//        for (int i = 1, length = width - 1; i < length; i++)
//        {
//            for (int k = 1, len = height - 1; k < len; k++)
//            {
//                for (int m = 0; m < 9; m++)
//                {
//                    int s = 0;
//                    int p = 0;
//                    switch(m)
//                    {
//                        case 0:
//                            s = i - 1;
//                            p = k - 1;
//                            break;
//                        case 1:
//                            s = i;
//                            p = k - 1;
//                            break;
//                        case 2:
//                            s = i + 1;
//                            p = k - 1;
//                            break;
//                        case 3:
//                            s = i + 1;
//                            p = k;
//                            break;
//                        case 4:
//                            s = i + 1;
//                            p = k + 1;
//                            break;
//                        case 5:
//                            s = i;
//                            p = k + 1;
//                            break;
//                        case 6:
//                            s = i - 1;
//                            p = k + 1;
//                            break;
//                        case 7:
//                            s = i - 1;
//                            p = k;
//                            break;
//                        case 8:
//                            s = i;
//                            p = k;
//                    }
//                    pixColor = bmp.getPixel(s, p);
//                    colors[m][0] = Color.red(pixColor);
//                    colors[m][1] = Color.green(pixColor);
//                    colors[m][2] = Color.blue(pixColor);
//                }
//
//                for (int m = 0; m < 9; m++)
//                {
//                    newR += colors[m][0];
//                    newG += colors[m][1];
//                    newB += colors[m][2];
//                }
//
//                newR = (int) (newR / 9F);
//                newG = (int) (newG / 9F);
//                newB = (int) (newB / 9F);
//
//                newR = Math.min(255, Math.max(0, newR));
//                newG = Math.min(255, Math.max(0, newG));
//                newB = Math.min(255, Math.max(0, newB));
//
//                newColor = Color.argb(255, newR, newG, newB);
//                bitmap.setPixel(i, k, newColor);
//
//                newR = 0;
//                newG = 0;
//                newB = 0;
//            }
//        }
//
//        return bitmap;
//    }

    /**
     * 柔化效果(高斯模糊)(优化后比上面快三倍)
     *
     * @param bmp
     * @return
     */
    public static Bitmap blurImageAmeliorate(Bitmap bmp) {
        long start = System.currentTimeMillis();
        // 高斯矩阵
        int[] gauss = new int[]{1, 2, 1, 2, 4, 2, 1, 2, 1};

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int delta = 16; // 值越小图片会越亮，越大则越暗

        int idx = 0;
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                idx = 0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        pixColor = pixels[(i + m) * width + k + n];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + (int) (pixR * gauss[idx]);
                        newG = newG + (int) (pixG * gauss[idx]);
                        newB = newB + (int) (pixB * gauss[idx]);
                        idx++;
                    }
                }

                newR /= delta;
                newG /= delta;
                newB /= delta;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);

                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        long end = System.currentTimeMillis();
        Log.d("may", "used time=" + (end - start));
        return bitmap;
    }

//    [java] view plain copy
//
//    /**
//     * 模糊效果
//     * @param bmp
//     * @return
//     */
//    private Bitmap blurImage(Bitmap bmp)
//    {
//        int width = bmp.getWidth();
//        int height = bmp.getHeight();
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//
//        int pixColor = 0;
//
//        int newR = 0;
//        int newG = 0;
//        int newB = 0;
//
//        int newColor = 0;
//
//        int[][] colors = new int[9][3];
//        for (int i = 1, length = width - 1; i < length; i++)
//        {
//            for (int k = 1, len = height - 1; k < len; k++)
//            {
//                for (int m = 0; m < 9; m++)
//                {
//                    int s = 0;
//                    int p = 0;
//                    switch(m)
//                    {
//                        case 0:
//                            s = i - 1;
//                            p = k - 1;
//                            break;
//                        case 1:
//                            s = i;
//                            p = k - 1;
//                            break;
//                        case 2:
//                            s = i + 1;
//                            p = k - 1;
//                            break;
//                        case 3:
//                            s = i + 1;
//                            p = k;
//                            break;
//                        case 4:
//                            s = i + 1;
//                            p = k + 1;
//                            break;
//                        case 5:
//                            s = i;
//                            p = k + 1;
//                            break;
//                        case 6:
//                            s = i - 1;
//                            p = k + 1;
//                            break;
//                        case 7:
//                            s = i - 1;
//                            p = k;
//                            break;
//                        case 8:
//                            s = i;
//                            p = k;
//                    }
//                    pixColor = bmp.getPixel(s, p);
//                    colors[m][0] = Color.red(pixColor);
//                    colors[m][1] = Color.green(pixColor);
//                    colors[m][2] = Color.blue(pixColor);
//                }
//
//                for (int m = 0; m < 9; m++)
//                {
//                    newR += colors[m][0];
//                    newG += colors[m][1];
//                    newB += colors[m][2];
//                }
//
//                newR = (int) (newR / 9F);
//                newG = (int) (newG / 9F);
//                newB = (int) (newB / 9F);
//
//                newR = Math.min(255, Math.max(0, newR));
//                newG = Math.min(255, Math.max(0, newG));
//                newB = Math.min(255, Math.max(0, newB));
//
//                newColor = Color.argb(255, newR, newG, newB);
//                bitmap.setPixel(i, k, newColor);
//
//                newR = 0;
//                newG = 0;
//                newB = 0;
//            }
//        }
//
//        return bitmap;
//    }

//    /**
//     * 柔化效果(高斯模糊)(优化后比上面快三倍)
//     *
//     * @param bmp
//     * @return
//     */
//    private Bitmap blurImageAmeliorate(Bitmap bmp) {
//        long start = System.currentTimeMillis();
//        // 高斯矩阵
//        int[] gauss = new int[]{1, 2, 1, 2, 4, 2, 1, 2, 1};
//
//        int width = bmp.getWidth();
//        int height = bmp.getHeight();
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//
//        int pixR = 0;
//        int pixG = 0;
//        int pixB = 0;
//
//        int pixColor = 0;
//
//        int newR = 0;
//        int newG = 0;
//        int newB = 0;
//
//        int delta = 16; // 值越小图片会越亮，越大则越暗
//
//        int idx = 0;
//        int[] pixels = new int[width * height];
//        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
//        for (int i = 1, length = height - 1; i < length; i++) {
//            for (int k = 1, len = width - 1; k < len; k++) {
//                idx = 0;
//                for (int m = -1; m <= 1; m++) {
//                    for (int n = -1; n <= 1; n++) {
//                        pixColor = pixels[(i + m) * width + k + n];
//                        pixR = Color.red(pixColor);
//                        pixG = Color.green(pixColor);
//                        pixB = Color.blue(pixColor);
//
//                        newR = newR + (int) (pixR * gauss[idx]);
//                        newG = newG + (int) (pixG * gauss[idx]);
//                        newB = newB + (int) (pixB * gauss[idx]);
//                        idx++;
//                    }
//                }
//
//                newR /= delta;
//                newG /= delta;
//                newB /= delta;
//
//                newR = Math.min(255, Math.max(0, newR));
//                newG = Math.min(255, Math.max(0, newG));
//                newB = Math.min(255, Math.max(0, newB));
//
//                pixels[i * width + k] = Color.argb(255, newR, newG, newB);
//
//                newR = 0;
//                newG = 0;
//                newB = 0;
//            }
//        }
//
//        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//        long end = System.currentTimeMillis();
//        Log.d("may", "used time=" + (end - start));
//        return bitmap;
//    }

    /**
     * 质量压缩
     *
     * @param image
     * @return size kb
     */
    public static Bitmap compressImage(Bitmap image, int size) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        int options = 100;
        //循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > size) {
            baos.reset();//重置baos即清空baos
            //这里压缩options%，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            //每次都减少10
            options -= 10;
        }
        //把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        //把ByteArrayInputStream数据生成图片
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    /**
     * 长宽压缩
     *
     * @param image
     * @return
     */
    public static Bitmap compressImageSzie(Bitmap image, float height, float width) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        //判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
        if (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = height;//这里设置高度为800f
        float ww = width;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }else if (w==h){//长宽一样
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return bitmap;//压缩好比例大小后再进行质量压缩
    }


    /**
     * 压缩大小
     *
     * @param srcPath
     * @return
     */
    public static Bitmap compressImageWithPathSzie(String srcPath, float height, float width) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        //此时返回bm为空
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = height;
        float ww = width;
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        //如果宽度大的话根据宽度固定大小缩放
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }else if (w==h){//长宽一样
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;//压缩好比例大小后再进行质量压缩
    }

    public static boolean saveBitmap2file(Bitmap bmp, String filename) {

        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        int quality = 100;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bmp.compress(format, quality, stream);
    }

    /**
     * bitmap 转二进制
     *
     * @param bitmap
     * @return
     */
    public static byte[] getBitmapByte(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * 二进制转bitmap
     *
     * @param temp
     * @return
     */
    public static Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }
    }


}
