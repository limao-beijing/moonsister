package com.moonsister.tcjy.manager;

import android.text.TextUtils;

import com.hickey.network.bean.BaseBean;
import com.moonsister.tcjy.utils.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jb on 2016/7/29.
 */
public class RongServerAPI {
    public static String baseUrl = "http://chatapi.yytbzs.cn:88/mimei/";
    public static String authcode;
    private static RongAPI mRongAPI;

    public static RongAPI getRongAPI() {
        if (TextUtils.isEmpty(baseUrl)) {
            throw new RuntimeException("baseUrl is not null");
        }
        if (mRongAPI == null) {
            synchronized (RongServerAPI.class) {
                if (mRongAPI == null) {
                    if (LogUtils.getDeBugState()) {

                        Interceptor mTokenInterceptor = new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Response response = chain.proceed(chain.request());
                                LogUtils.d("RongServerAPI", "addNetworkInterceptor : Response  code: " + response.code());
                                BufferedSource source = response.body().source();
                                source.request(Long.MAX_VALUE);
                                Buffer clone = source.buffer().clone();
                                LogUtils.d("RongServerAPI", "addNetworkInterceptor : Response  content: " + clone.readUtf8());
                                return response;
                            }

                        };
                        // init okhttp 3 logger
                        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                LogUtils.e("RongServerAPI", "HttpLoggingInterceptor: " + message);
                            }

                        });
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                        OkHttpClient client = new OkHttpClient.Builder()
                                .retryOnConnectionFailure(true)
                                .connectTimeout(15, TimeUnit.SECONDS)
                                .addInterceptor(logging)
                                .addInterceptor(mTokenInterceptor)
                                .build();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(baseUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .client(client)
                                .build();
                        mRongAPI = retrofit.create(RongAPI.class);
                    } else {
                        OkHttpClient client = new OkHttpClient.Builder()
                                .retryOnConnectionFailure(true)
                                .connectTimeout(15, TimeUnit.SECONDS)
                                .build();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(baseUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .client(client)
                                .build();
                        mRongAPI = retrofit.create(RongAPI.class);
                    }

                }
            }
        }

        return mRongAPI;

    }

    public interface RongAPI {
        @FormUrlEncoded
        @POST("chat.php?action=chat")
        Observable<BaseBean> send(@Field("chat_type") String chatType,
                                  @Field("to_uid") String toUid,
                                  @Field("chat_content") String content,
                                  @Field("authcode") String authcode);

        /**
         * 启动APP信息
         *
         * @param
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("msg.php?action=get")
        Observable<BaseBean> sendAppStartMsg(@Field("authcode") String authcode,
                                            @Field("channel") String channel);
    }
}
