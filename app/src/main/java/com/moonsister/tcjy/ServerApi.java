package com.moonsister.tcjy;


import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.CardInfoBean;
import com.moonsister.tcjy.bean.CertificationStatusBean;
import com.moonsister.tcjy.bean.CommentDataListBean;
import com.moonsister.tcjy.bean.DefaultDataBean;
import com.moonsister.tcjy.bean.FrientBaen;
import com.moonsister.tcjy.bean.GetMoneyBean;
import com.moonsister.tcjy.bean.GoodSelectBaen;
import com.moonsister.tcjy.bean.LableBean;
import com.moonsister.tcjy.bean.LoginBean;
import com.moonsister.pay.tencent.PayBean;
import com.moonsister.tcjy.bean.NearbyBean;
import com.moonsister.tcjy.bean.PayRedPacketPicsBean;
import com.moonsister.tcjy.bean.RankBean;
import com.moonsister.tcjy.bean.RecommendMemberFragmentBean;
import com.moonsister.tcjy.bean.RegiterBean;
import com.moonsister.tcjy.bean.RongyunBean;
import com.moonsister.tcjy.bean.SearchReasonBaen;
import com.moonsister.tcjy.bean.TiXinrRecordBean;
import com.moonsister.tcjy.bean.UserFriendListBean;
import com.moonsister.tcjy.bean.UserInfoChangeBean;
import com.moonsister.tcjy.bean.UserInfoDetailBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.bean.UserPermissionBean;
import com.moonsister.tcjy.bean.VersionInfo;
import com.moonsister.tcjy.main.widget.RecommendMemberFragment;
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

public class ServerApi {
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
        String baseUrl = "http://2.yytbzs.cn:88/index.php/index/";
//        String baseUrl = "http://mimei.cntttt.com:88/public/index.php/index/";

        /**
         * 登录
         *
         * @param username
         * @param password
         * @return
         */
        @FormUrlEncoded
        @POST("User/login")
        Observable<LoginBean> login(
                @Field("name") String username,
                @Field("pwd") String password,
                @Field("channel") String channel
        );

        /**
         * 获取验证码
         *
         * @param mobile
         * @return
         */
        @FormUrlEncoded
        @POST("User/register_send_mobile_code")
        Observable<BaseBean> sendSecurityCode(@Field("mobile") String mobile,
                                              @Field("channel") String channel);

        /**
         * 验证验证码
         *
         * @param mobile
         * @param code
         * @return
         */
        @FormUrlEncoded
        @POST("User/register_step1")
        Observable<RegiterBean> verifySecurityCode(@Field("mobile") String mobile,
                                                   @Field("code") String code,
                                                   @Field("channel") String channel);

        /**
         * 注册完成
         *
         * @param face
         * @param sex
         * @param pwd
         * @return
         */
        @FormUrlEncoded
        @POST("User/register_step2")
        Observable<BaseBean> regiterLogin(@Field("face") String face,
                                          @Field("sex") String sex,
                                          @Field("pwd") String pwd,
                                          @Field("channel") String channel,
                                          @Field("mobileauth") String mobileauth);

        /**
         * 精选数据
         *
         * @param type
         * @param page
         * @return
         */
        @GET("Userrec/get_list_jingxuan")
        Observable<GoodSelectBaen> getGoodSelect(@Query("type") String type,
                                                 @Query("page") int page,
                                                 @Query("authcode") String authcode,
                                                 @Query("channel") String channel);

        /**
         * 同城
         *
         * @param type
         * @param page
         * @param authcode
         * @return
         */
        @GET("Userrec/get_list_tongcheng")
        Observable<GoodSelectBaen> getSameCity(@Query("type") String type,
                                               @Query("page") int page,
                                               @Query("authcode") String authcode,
                                               @Query("channel") String channel);

        /**
         * 个人动态信息列表
         *
         * @param uid
         * @param authcode
         * @return
         */
        @GET("User/user_detail_addon1")
        Observable<UserInfoDetailBean> getUserInfoDetail(@Query("uid") String uid,
                                                         @Query("authcode") String authcode,
                                                         @Query("channel") String channel);

        /**
         * 个人动态列表
         *
         * @param userId
         * @param authcode
         * @param page
         * @return
         */
        @GET("Latest/get_latest_list")
        Observable<UserInfoListBean> getPersonDynamincList(@Query("uid") String userId,
                                                           @Query("authcode") String authcode,
                                                           @Query("page") int page,
                                                           @Query("channel") String channel);

        /**
         * 动态评论列表
         *
         * @param id
         * @param page
         * @param authcode
         * @return
         */
        @GET("Latestcomment/get_latest_comment")
        Observable<CommentDataListBean> getCommentList(@Query("lid") String id,
                                                       @Query("page") int page,
                                                       @Query("authcode") String authcode,
                                                       @Query("channel") String channel);

        /**
         * 动态发布
         *
         * @param type
         * @param content
         * @param address
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("Latest/insert_latest")
        Observable<BaseBean> sendAllDefaultDynamic(@Field("type") int type,
                                                   @Field("title") String title,
                                                   @Field("contents") String content,
                                                   @Field("address") String address,
                                                   @Field("authcode") String authcode,
                                                   @Field("channel") String channel
        );

        /**
         * 打赏
         *
         * @param playType
         * @param uid
         * @param money
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("Reward/pub_reward")
        Observable<PayBean> getredPacketIndent(@Field("pay_type") String playType,
                                               @Field("to_uid") String uid,
                                               @Field("money") String money,
                                               @Field("authcode") String authcode,
                                               @Field("channel") String channel);

        /**
         * 送花
         *
         * @param type
         * @param uid
         * @param money
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("Flower/pub_flower")
        Observable<PayBean> getFlowerIndent(@Field("pay_type") String type,
                                            @Field("to_uid") String uid,
                                            @Field("money") String money,
                                            @Field("authcode") String authcode,
                                            @Field("channel") String channel);

        /**
         * 获取token
         *
         * @param authcode
         * @return
         */
        @GET("Rong/get_token")
        Observable<RongyunBean> getRongyunKey(@Query("authcode") String authcode,
                                              @Query("channel") String channel);

        /**
         * 朋友圈
         *
         * @param authcode
         * @param page
         * @return
         */
        @GET("Latest/get_latests_friends")
        Observable<UserInfoListBean> loadPersonDynamic(@Query("authcode") String authcode,
                                                       @Query("page") int page,
                                                       @Query("channel") String channel);

        /**
         * 获取个人的信息
         *
         * @param authcode
         * @return
         */
        @GET("User/user_detail_addon1_center")
        Observable<UserInfoDetailBean> loadPersonInfo(@Query("authcode") String authcode,
                                                      @Query("channel") String channel);

        /**
         * 动态红包支付
         *
         * @param id
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("Latestbuy/latest_buy")
        Observable<PayBean> redPacketPay(@Field("latest_id") String id,
                                         @Field("pay_type") String type,
                                         @Field("authcode") String authcode,
                                         @Field("channel") String channel);

        /**
         * 红包图片
         *
         * @param id
         * @param authcode
         * @return
         */
        @GET("Latest/get_latest_pay")
        Observable<PayRedPacketPicsBean> getPayDynamicPic(@Query("latest_id") String id,
                                                          @Query("authcode") String authcode,
                                                          @Query("channel") String channel);

        /**
         * @param address1
         * @param address2
         * @param height
         * @param sexid
         * @param nike
         * @param loadFile
         * @param serialize
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("Apply/pub")
        Observable<DefaultDataBean> sendAllRzInfo(@Field("province") String address1,
                                                  @Field("city") String address2,
                                                  @Field("height") String height,
                                                  @Field("sex") String sexid,
                                                  @Field("nickname") String nike,
                                                  @Field("face") String loadFile,
                                                  @Field("apply_image") String serialize,
                                                  @Field("order_id") String orderid,
                                                  @Field("authcode") String authcode,
                                                  @Field("channel") String channel);

        /**
         * 获取认证状态
         *
         * @param authcode
         * @return
         */
        @GET("Apply/get_apply_status")
        Observable<CertificationStatusBean> getCertificationStatus(@Query("authcode") String authcode,
                                                                   @Query("channel") String channel);

        /**
         * 提现列表
         *
         * @param authcode
         * @return
         */
        @GET("Withdraw/get_withdraw_list")
        Observable<TiXinrRecordBean> getTixinRecord(@Query("authcode") String authcode,
                                                    @Query("channel") String channel);

        @GET("Withdraw/get_withdraw_money")
        Observable<DefaultDataBean> getEnableMoney(@Query("authcode") String authcode,
                                                   @Query("channel") String channel);

        /**
         * 提现基本信息
         *
         * @param authcode
         * @return
         */
        @GET("Account/get_latest_info")
        Observable<GetMoneyBean> getGetmoney(@Query("authcode") String authcode,
                                             @Query("channel") String channel);

        /**
         * 添加银行卡
         *
         * @param cardNumber
         * @param username
         * @param cardType
         * @param bankname
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("Account/add")
        Observable<DefaultDataBean> getsubmitAccount(@Field("bank_no") String cardNumber,
                                                     @Field("name") String username,
                                                     @Field("type") String cardType,
                                                     @Field("bank_name") String bankname,
                                                     @Field("authcode") String authcode,
                                                     @Field("channel") String channel);

        /**
         * 提现
         *
         * @param number
         * @param money
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("Withdraw/add")
        Observable<DefaultDataBean> getTiXianReason(@Field("account_id") String number,
                                                    @Field("money") int money,
                                                    @Field("authcode") String authcode,
                                                    @Field("channel") String channel);

        /**
         * 银行卡列表
         *
         * @param authcode
         * @return
         */
        @GET("Account/get_list")
        Observable<CardInfoBean> getCardInfo(@Query("authcode") String authcode,
                                             @Query("channel") String channel);

        /**
         * 关注
         *
         * @param uid
         * @param type     1关注操作，2取消关注操作
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("Follow/follow_act")
        Observable<DefaultDataBean> getWacthAction(@Field("touid") String uid,
                                                   @Field("type") String type,
                                                   @Field("authcode") String authcode,
                                                   @Field("channel") String channel);

        /**
         * 发布评论
         *
         * @param id
         * @param content
         * @param pid
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("Latestcomment/insert_latest_comment")
        Observable<DefaultDataBean> getSendComment(@Field("lid") String id,
                                                   @Field("title") String content,
                                                   @Field("pid") String pid,
                                                   @Field("authcode") String authcode,
                                                   @Field("channel") String channel);

        /**
         * 关注列表
         *
         * @param page
         * @param authcode
         * @return
         */
        @GET("Follow/get_list_follows")
        Observable<FrientBaen> getWacthList(@Query("page") int page,
                                            @Query("authcode") String authcode,
                                            @Query("channel") String channel);


        /**
         * 关注列表
         *
         * @param page
         * @param authcode
         * @return
         */
        @GET("Follow/get_list_fans")
        Observable<FrientBaen> getFenList(@Query("page") int page,
                                          @Query("authcode") String authcode,
                                          @Query("channel") String channel);

        /**
         * @param key
         * @param page
         * @param authcode @return
         */
        @GET("User/search")
        Observable<SearchReasonBaen> getSearchReason(@Query("name") String key,
                                                     @Query("page") int page,
                                                     @Query("authcode") String authcode,
                                                     @Query("channel") String channel);

        /**
         * 用户的信息
         *
         * @param uid
         * @return
         */
        @GET("User/user_detail")
        Observable<UserInfoChangeBean> getUserInfoBasic(@Query("uid") String uid,
                                                        @Query("channel") String channel);

        /**
         * 修正资料
         *
         * @return
         */
        @FormUrlEncoded
        @POST("User/edit_profile")
        Observable<DefaultDataBean> getChangeInfo(@Field("face") String face,
                                                  @Field("birthday") String birth,
                                                  @Field("nickname") String nickname,
                                                  @Field("height") String height,
                                                  @Field("prifession") String prifession,
                                                  @Field("residence") String addrss,
                                                  @Field("city") String city,
                                                  @Field("signature") String signature,
                                                  @Field("weight") String weight,
                                                  @Field("sex") String sex,
                                                  @Field("degree") String degree,
                                                  @Field("self_intro") String self_intro,
                                                  @Field("authcode") String authcode,
                                                  @Field("channel") String channel);

        /**
         * 修改密码
         *
         * @param oldpwd
         * @param newpwd
         * @return
         */
        @FormUrlEncoded
        @POST("User/edit_pwd")
        Observable<DefaultDataBean> getChangePassword(@Field("pwd1") String oldpwd,
                                                      @Field("pwd2") String newpwd,
                                                      @Field("authcode") String authcode,
                                                      @Field("channel") String channel);

        /**
         * 找回密码 验证码
         *
         * @param phoneNumber
         * @return
         */
        @FormUrlEncoded
        @POST("User/findpwd_send_mobile_code")
        Observable<BaseBean> getSecurityCode(@Field("mobile") String phoneNumber,
                                             @Field("channel") String channel);

        /**
         * 找回密码 验证验证码
         *
         * @param phone
         * @param code
         * @return
         */
        @FormUrlEncoded
        @POST("User/findpwd_step1")
        Observable<RegiterBean> getFindPasswordSecurity(@Field("mobile") String phone,
                                                        @Field("code") String code,
                                                        @Field("channel") String channel);

        /**
         * 找回密码 输入新密码
         *
         * @param newpwd
         * @param code
         * @return
         */
        @FormUrlEncoded
        @POST("User/findpwd_step2")
        Observable<BaseBean> getFindPasswordNext(@Field("pwd") String newpwd,
                                                 @Field("mobileauth") String code,
                                                 @Field("channel") String channel);

        /**
         * 更新背景图
         *
         * @param path
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("User/edit_like_image")
        Observable<DefaultDataBean> getUploadBackground(@Field("like_image") String path,
                                                        @Field("authcode") String authcode,
                                                        @Field("channel") String channel);

        /**
         * 点赞
         *
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("Latest/updown")
        Observable<DefaultDataBean> getLikeAction(@Field("latest_id") String dynamicId,
                                                  @Field("type") String type,
                                                  @Field("authcode") String authcode,
                                                  @Field("channel") String channel);

        /**
         * 删除动态
         *
         * @param id
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("Latest/del_latest")
        Observable<DefaultDataBean> getDelectDynamic(@Field("latest_id") String id,
                                                     @Field("authcode") String authcode,
                                                     @Field("channel") String channel);

        /**
         * 置顶
         *
         * @param id
         * @param authcode
         * @param channel
         * @return
         */
        @FormUrlEncoded
        @POST("Latesttop/ins")
        Observable<DefaultDataBean> getupDynamic(@Field("latest_id") String id,
                                                 @Field("authcode") String authcode,
                                                 @Field("channel") String channel);

        /**
         * 取消置顶
         *
         * @param id
         * @param authcode
         * @param channel
         * @return
         */
        @FormUrlEncoded
        @POST("Latesttop/del")
        Observable<DefaultDataBean> getdelUpDynamic(@Field("latest_id") String id,
                                                    @Field("authcode") String authcode,
                                                    @Field("channel") String channel);

        /**
         * 获取更新信息
         *
         * @param
         * @return
         */
        @GET("update/index")
        Observable<VersionInfo> getLoadVersonInfo(@Query("channel") String channel);

        /**
         * 上传手机信息
         *
         * @param serialize
         * @return
         */
        @FormUrlEncoded
        @POST("Location/update_data")
        Observable<DefaultDataBean> getuploadPhoneInfo(@Field("address") String serialize);

        /**
         * 获取关注列表
         *
         * @param page
         * @param uid
         * @param authcode @return
         */

        @GET("Follow/get_list_follows_other")
        Observable<FrientBaen> getWacthRelation(@Query("page") int page,
                                                @Query("ouid") String uid,
                                                @Query("authcode") String authcode,
                                                @Query("channel") String channel);

        /**
         * @param page
         * @param authcode
         * @param channel
         * @return
         */
        @GET("Follow/get_list_fans_other")
        Observable<FrientBaen> getFenRelation(@Query("page") int page,
                                              @Query("ouid") String uid,
                                              @Query("authcode") String authcode,
                                              @Query("channel") String channel);

        /**
         * 更新位置信息
         *
         * @param address
         * @param authcode
         * @param channel
         * @return
         */
        @FormUrlEncoded
        @POST("Location/update_location")
        Observable<DefaultDataBean> uploadLoation(@Field("address") String address,
                                                  @Field("authcode") String authcode,
                                                  @Field("channel") String channel);

        /**
         * 排行
         *
         * @param page
         * @param authcode
         * @return
         */
        @GET("rank/fx")
        Observable<RankBean> getRankData(@Query("type") int type,
                                         @Query("page") int page,
                                         @Query("pagesize") int pagesize,
                                         @Query("authcode") String authcode,
                                         @Query("channel") String channel);

        /**
         * //        1、authcode 用户身份验证码
         * //        2、sex性别（1男，2女），默认为0，为不限
         * //        3、page 分页，默认为1
         * //        4、lat 维度
         * //        5、lng 经度
         *
         * @param longitude
         * @param latitude
         * @param authcode
         * @return
         */
        @GET("user/find_near")
        Observable<NearbyBean> getNearby(@Query("sex") String sex,
                                         @Query("page") int page,
                                         @Query("lng") double longitude,
                                         @Query("lat") double latitude,
                                         @Query("authcode") String authcode,
                                         @Query("channel") String channel);

        /**
         * 动态标签获取
         *
         * @param page
         * @param type
         * @param catid
         * @param pagesize
         * @param authcode
         * @return
         */
        @GET("tags/get")
        Observable<LableBean> getDynamicLable(@Query("page") int page,
                                              @Query("type") int type,
                                              @Query("catid") int catid,
                                              @Query("pagesize") int pagesize,
                                              @Query("authcode") String authcode,
                                              @Query("channel") String channel);

        /**
         * 购买VIP
         *
         * @param payType
         * @param opentype
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("mmvip/openvip")
        Observable<PayBean> getBuyVIP(@Field("pay_type") String payType,
                                      @Field("opentype") int opentype,
                                      @Field("authcode") String authcode,
                                      @Field("channel") String channel);

        /**
         * 认证押金
         *
         * @param authcode
         * @return
         */
        @FormUrlEncoded
        @POST("apply/gobuy")
        Observable<PayBean> getCertificationPay(@Field("authcode") String authcode);

        /**
         * 推送用户信息
         *
         * @param pagesize
         * @param authcode
         * @param channel
         * @return
         */
        @GET("push/index")
        Observable<RecommendMemberFragmentBean> getRecommendMemberBean(@Query("pagesize") int pagesize,
                                                                       @Query("authcode") String authcode,
                                                                       @Query("channel") String channel);

        /**
         * 推送用户信息：点击喜欢，不喜欢
         *
         * @param uid
         * @param
         * @return
         */
        @GET("push/ilike")
        Observable<DefaultDataBean> getRecommend(@Query("type") int type,
                                                 @Query("like_uid") String uid,
                                                 @Query("authcode") String authcode,
                                                 @Query("channel") String channel);

        /**
         * 获取好友列表
         *
         * @param page
         * @param authcode
         * @param
         * @return
         */
        @GET("follow/get_list_friends")
        Observable<FrientBaen> getFriendList(@Query("page") int page,
                                             @Query("authcode") String authcode,
//                                             @Query("pagesize") int pagesize,
                                             @Query("channel") String channel);

        /**
         * @param authcode
         * @param channelId
         */
        @GET("user/get_auth")
        Observable<UserPermissionBean> getUserPermission(@Query("authcode") String authcode,
                                                         @Query("channel") String channelId);

        /**
         * 获取全部好友uid：
         *
         * @param authcode
         * @return
         */
        @GET("follow/get_list_friends_all")
        Observable<UserFriendListBean> getUserFriendList(@Query("authcode") String authcode,
                                                         @Query("channel") String channelId);
    }
}

