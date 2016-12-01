package com.hickey.network;

import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.DefaultDataBean;
import com.hickey.network.bean.EngagemengOrderBean;
import com.hickey.network.bean.EngagemengRecommendBean;
import com.hickey.network.bean.EngagementDetailsBean;
import com.hickey.network.bean.EngagementManagerBean;
import com.hickey.network.bean.EngagementPermissTextBane;
import com.hickey.network.bean.EngagementTextBane;
import com.hickey.network.bean.PayBean;
import com.hickey.network.bean.StatusBean;
import com.hickey.network.bean.resposen.ChargeInitBean;
import com.hickey.network.bean.resposen.ChargeMessageBean;
import com.hickey.network.bean.resposen.ChargeMessagePayBean;
import com.hickey.network.bean.resposen.ChargeResBean;
import com.hickey.network.bean.resposen.InterestBean;
import com.hickey.network.gson.GsonConverterFactory;
import com.hickey.tool.base.BaseResponse;
import com.hickey.tool.security.UnicodeUtils;

import java.io.IOException;
import java.util.List;
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
 * Created by jb on 2016/11/9.
 */

public class ModuleServerApi {
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
        String baseUrl = "http://module.chuse.hk/index.php/index/";

        /**
         * 约会推荐用户列表
         *
         * @param userType
         * @param page
         * @param type
         * @param authcode
         * @param id
         * @return
         */
        @GET("dating/get_user_list")
        Observable<EngagemengRecommendBean> getEngagemengRecommen(@Query("type") String userType,
                                                                  @Query("page") int page,
                                                                  @Query("dating_type") int type,
                                                                  @Query("authcode") String authcode,
                                                                  @Query("channel") String id);

        /**
         * 发布约会
         *
         * @param type
         * @param uid
         * @param money
         * @param date
         * @param address
         * @param message
         * @param authcode
         * @param id
         * @return
         */
        @FormUrlEncoded
        @POST("dating/pub_dating")
        Observable<PayBean> getEngagementOreder(@Field("dating_count") String dating_count,
                                                @Field("type") int type,
                                                @Field("to_uid") String uid,
                                                @Field("money") String money,
                                                @Field("date") String date,
                                                @Field("address") String address,
                                                @Field("msg") String message,
                                                @Field("pay_type") String pay_type,
                                                @Field("pay_cash_type") String payCash,
                                                @Field("version_type") String version_type,
                                                @Field("is_pay_type") String payType,
                                                @Field("authcode") String authcode,
                                                @Field("channel") String id);

        /**
         * 发布的约会
         *
         * @param page
         * @param authcode
         * @param channel
         * @return
         */
        @GET("Dating/get_my_list_pub")
        Observable<EngagementManagerBean> getEngagementList(@Query("page") int page,
                                                            @Query("authcode") String authcode,
                                                            @Query("channel") String channel);

        /**
         * 被要请的约会的约会
         *
         * @param page
         * @param authcode
         * @param channel
         * @return
         */
        @GET("Dating/get_my_list")
        Observable<EngagementManagerBean> getEngagementPassivityList(@Query("page") int page,
                                                                     @Query("authcode") String authcode,
                                                                     @Query("channel") String channel);

        /**
         * 设置约会成功
         *
         * @param id
         * @param authcode
         * @param channel
         * @return
         */
        @FormUrlEncoded
        @POST("Dating/set_datting_success")
        Observable<StatusBean> getSubmitSuccess(@Field("dating_id") String id,
                                                @Field("authcode") String authcode,
                                                @Field("channel") String channel);

        /**
         * 约会状态操作
         *
         * @param id
         * @param authcode
         * @param channel
         * @return
         */
        @FormUrlEncoded
        @POST("Dating/set_dating_status")
        Observable<StatusBean> getsubmitInviteSuccess(@Field("dating_id") String id,
                                                      @Field("action_type") String type,
                                                      @Field("authcode") String authcode,
                                                      @Field("channel") String channel);

        /**
         * 约会申诉
         *
         * @param id
         * @param content
         * @param authcode
         * @param channel
         * @return
         */
        @FormUrlEncoded
        @POST("Dating/dating_appeal")
        Observable<DefaultDataBean> getSubmitEngagementAppeal(@Field("dating_id") String id,
                                                              @Field("msg") String content,
                                                              @Field("authcode") String authcode,
                                                              @Field("channel") String channel);

        /**
         * 约会：发布约会时，获取免费约会信息
         *
         * @param authcode
         * @return
         */
        @GET("dating/get_limit_info")
        Observable<EngagemengOrderBean> getEngagemengOrder(@Query("authcode") String authcode,
                                                           @Query("channel") String channel);

        /**
         * 约会模块文字信息
         *
         * @param type
         * @param authcode
         * @return
         */
        @GET("dating/get_desc_info")
        Observable<EngagementTextBane> getEngagemengText(@Query("dating_id") String dating_id,
                                                         @Query("type") String type,
                                                         @Query("authcode") String authcode,
                                                         @Query("channel") String channel);

        /**
         * 约会信息接口
         *
         * @param id
         * @param authcode
         * @param id1
         * @return
         */
        @FormUrlEncoded
        @POST("dating/get_dating_detail")
        Observable<EngagementDetailsBean> getEngagemengDetails(@Field("id") String id,
                                                               @Field("authcode") String authcode,
                                                               @Field("channel") String id1);

        /**
         * 约会信息接口
         *
         * @param id
         * @param authcode
         * @param id1
         * @return
         */
        @FormUrlEncoded
        @POST("dating/get_dating_detail2")
        Observable<EngagementDetailsBean> getEngagemengDetails2(@Field("id") String id,
                                                                @Field("order_id") String order_id,
                                                                @Field("authcode") String authcode,
                                                                @Field("channel") String id1);

        /**
         * 约会状态操作
         *
         * @param id
         * @param type
         * @param authcode
         * @param channel
         * @return
         */
        @GET("dating/act_dating_status")
        Observable<BaseBean> getActionEngagement(@Query("dating_id") String id,
                                                 @Query("type") int type,
                                                 @Query("authcode") String authcode,
                                                 @Query("channel") String channel);

        /**
         * 约会权限文字
         *
         * @param authcode
         * @param channel
         * @return
         */
        @GET("msg/dating_auth_info")
        Observable<EngagementPermissTextBane> getTextMsg(@Query("authcode") String authcode,
                                                         @Query("channel") String channel);

        /**
         * 收费消息
         *
         * @param s
         * @param money
         * @param content
         * @param desc
         * @param type
         * @param uid
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("chat/pub")
        Observable<BaseResponse<ChargeMessageBean>> sendChargeMsg(@Field("send_friend") String send_friend,
                                                                  @Field("money") String money,
                                                                  @Field("contents") String content,
                                                                  @Field("desc") String desc,
                                                                  @Field("type") int type,
                                                                  @Field("to_uid") String uid,
                                                                  @Field("authcode") String authcode);

        /**
         * 获取 资源
         *
         * @param lid
         */
        @GET("chat/get2")
        Observable<BaseResponse<ChargeResBean>> getChargeMessage(@Query("source_id") String lid,
                                                                 @Query("authcode") String acthcode);

        /**
         * 购买操作
         *
         * @param lid
         * @param acthcode
         * @return
         */
        @GET("chat/buy")
        Observable<BaseResponse<ChargeMessagePayBean>> getChargePay(@Query("pay_cash_type") String pay_cash_type,
                                                                    @Query("version_type") String version_type,
                                                                    @Query("source_id") String lid,
                                                                    @Query("authcode") String acthcode);

        /**
         * 收费消息发布初始数据
         *
         * @param authcode
         * @return
         */
        @GET("chat/chat_init")
        Observable<BaseResponse<ChargeInitBean>> getChargeMessageInitData(@Query("authcode") String authcode);

        /**
         * 兴趣选项
         *
         * @param authcode
         * @param channel
         * @return
         */
        @GET("user/get_questions")
        Observable<BaseResponse<List<InterestBean>>> getInterstSelectInit(@Query("authcode") String authcode,
                                                                          @Query("channel") String channel);

        /**
         * 提交选项
         *
         * @param content
         * @param authcode
         * @param channel
         * @return
         */
        @FormUrlEncoded
        @POST("user/post_answers")
        Observable<BaseResponse> getInterstSubmit(@Field("contents") String content,
                                                  @Field("authcode") String authcode,
                                                  @Field("channel") String channel);
    }


}
