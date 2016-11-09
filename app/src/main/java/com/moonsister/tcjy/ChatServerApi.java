package com.moonsister.tcjy;


import com.moonsister.tcjy.bean.MsgBean;
import com.moonsister.tcjy.bean.PermissionBean;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UnicodeUtils;
import com.moonsister.tcjy.utils.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * * Created by pc on 2016/6/12.
 */

public class ChatServerApi {
    private static AppAPI mAppApi;
    private static String TAG = "ServerApi";

    public static AppAPI getAppAPI() {
        if (mAppApi == null) {
            if (LogUtils.getDeBugState()) {
                Interceptor mTokenInterceptor = new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        LogUtils.d(TAG, "addNetworkInterceptor : Response  code: " + response.code());
                        BufferedSource source = response.body().source();
                        source.request(Long.MAX_VALUE);
                        Buffer clone = source.buffer().clone();
                        LogUtils.d(TAG, "addNetworkInterceptor : Response  content: " + UnicodeUtils.decodeUnicode(clone.readUtf8()));
                        return response;
                    }

                };
                // init okhttp 3 logger
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtils.e(TAG, (message.startsWith("{") ? UnicodeUtils.decodeUnicode(message) : message));
                    }

                });
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                //401 Not Authorised
                Authenticator mAuthenticator = new Authenticator() {

                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        Request request = response.request();
                        LogUtils.d(TAG, "Authenticator : The Cookie is " + request.header("Cookie"));
                        LogUtils.e(TAG, "Authenticator : 访问网络地址: " + request.url().toString());
                        LogUtils.d(TAG, "Authenticator : 访问body : " + request.body().toString());
                        return request;
                    }
                };

                Interceptor interceptor = new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        LogUtils.d(TAG, "addInterceptor : 访问request : " + chain.request().toString());
                        Response response = chain.proceed(chain.request());

                        LogUtils.d(TAG, "addInterceptor : Response  code: " + response.code());
                        BufferedSource source = response.body().source();
                        source.request(Long.MAX_VALUE);
                        Buffer clone = source.buffer().clone();
                        LogUtils.d(TAG, "addInterceptor : Response  content: " + UnicodeUtils.decodeUnicode(clone.readUtf8()));
                        return response;
                    }
                };

                //OkHttpClient
                OkHttpClient httpClient = new OkHttpClient.Builder()
                        .addInterceptor(logging)
                        .addInterceptor(interceptor)
                        .retryOnConnectionFailure(true)
//                        .authenticator(mAuthenticator)
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .addNetworkInterceptor(mTokenInterceptor)
                        .build();
                //Retrofit.
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(AppAPI.baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(httpClient)
                        .build();

                mAppApi = retrofit.create(AppAPI.class);
            } else {
                //OkHttpClient
                OkHttpClient httpClient = new OkHttpClient.Builder()
                        .retryOnConnectionFailure(true)
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .build();
                //Retrofit.
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(AppAPI.baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(httpClient)
                        .build();

                mAppApi = retrofit.create(AppAPI.class);
            }
        }
        return mAppApi;
    }


    public interface AppAPI {
        String baseUrl = "http://chatapi.yytbzs.cn:88/";
//        String baseUrl = "http://2.yytbzs.cn:88/index.php/index/";
//        String baseUrl = "http://mimei.cntttt.com:88/public/index.php/index/";


        /**
         * 权限检查
         *
         * @param toUid
         * @param authcode
         * @param type
         * @param id
         * @return
         */
        @GET("mimei/auth.php?action=get")
        Observable<PermissionBean> checkVip(@Query("to_uid") String toUid,
                                            @Query("authcode") String authcode,
                                            @Query("auth_type") String type,
                                            @Query("channel") String id);

        @FormUrlEncoded
        @POST("mimei/chat.php?action=chat")
        Observable<MsgBean> send(@Field("chat_type") String chatType,
                                 @Field("to_uid") String toUid,
                                 @Field("chat_content") String content,
                                 @Field("authcode") String authcode);

    }
}

