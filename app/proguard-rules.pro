# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\pc\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#指定代码的压缩级别
-optimizationpasses 5
#包明不混合大小写
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
 #优化  不优化输入的类文件
-dontoptimize
 #预校验
-dontpreverify
 #混淆时是否记录日志
-verbose
 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#保护注解
-keepattributes *Annotation*
# 保持哪些类不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
# 解决warning: Ignoring InnerClasses attribute for an anonymous inner class
    -keepattributes Signature
    -keepattributes EnclosingMethod

#如果有引用v4包可以添加下面这行\
-keep public class * extends android.support.v4.app.Fragment
#忽略警告
-ignorewarning
#####################记录生成的日志数据,gradle build时在本项目根目录输出################
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt
#####################记录生成的日志数据，gradle build时 在本项目根目录输出-end################
################混淆保护自己项目的部分代码以及引用的第三方jar包library#########################
-keep class com.jcodecraeer.**{*;}
-keep class android.support.**{*;}
-keep class com.android.tools.**{*;}
-keep class com.moonsister.tcjy.progressdialog.**{*;}
-dontwarn com.moonsister.tcjy.progressdialog.**

-keep class retrofit.**{*;}
-keep class dagger.**{*;}
-keep class rx.**{*;}
-keep class com.squareup.**{*;}
-keep class javax.inject.**{*;}
-keep class org.**{*;}
-keep class com.trello.**{*;}
-keep class com.moonsister.tcjy.utils.*{*;}
-keep class com.moonsister.tcjy.AppConstant{*;}
-keep class com.moonsister.tcjy.utils.gson.**{*;}
-keep class com.moonsister.tcjy.event.**{*;}
-keep class com.moonsister.tcjy.bean.** { *; }
-keep class com.moonsister.tcjy.progressdialog.**{*;}
-dontwarn com.moonsister.tcjy.progressdialog.**
-keep class  com.moonsister.pay.** { *; }
-dontwarn com.moonsister.pay.**
-keep public class com.moonsister.tcjy.R$*{
    public static final int *;
}
 -keep public class com.moonsister.tcjy.js.JavaScriptObject {
    public void typePay(int , java.lang.String) ;
 }
#自己项目特殊处理代码
#忽略警告
-dontwarn com.veidy.mobile.common.**
#保留一个完整的包
-keep class com.veidy.mobile.common.** {*;}

#------忽略butterknife框架的混淆开始-----
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#---------忽略butterknife框架的混淆结束-------

#如果引用了v4或者v7包
-dontwarn android.support.**
############<span></span>混淆保护自己项目的部分代码以及引用的第三方jar包library-end##################
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
##保持 Serializable 不被混淆
#-keepnames class * implements java.io.Serializable
##保持 Serializable 不被混淆并且enum 类也不被混淆
#-keepclassmembers class * implements java.io.Serializable {
#    static final long serialVersionUID;
#    private static final java.io.ObjectStreamField[] serialPersistentFields;
#    !static !transient <fields>;
#    !private ;
#    !private <methods>;
#    private void writeObject(java.io.ObjectOutputStream);
#    private void readObject(java.io.ObjectInputStream);
#    java.lang.Object writeReplace();
#    java.lang.Object readResolve();
#}

-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
static final long serialVersionUID;
private static final java.io.ObjectStreamField[] serialPersistentFields;
!static !transient <fields>;
private void writeObject(java.io.ObjectOutputStream);
private void readObject(java.io.ObjectInputStream);
java.lang.Object writeReplace();
java.lang.Object readResolve();
}
#保持枚举 enum 类不被混淆 如果混淆报错，建议直接使用上面的 -keepclassmembers class * implements java.io.Serializable即可
#-keepclassmembers enum * {
#  public static **[] values();
#  public static ** valueOf(java.lang.String);
#}
-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
}
#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
    }
#    retrofit2
    -dontwarn retrofit2.**
    -keep class retrofit2.** { *; }
    -keepattributes Signature
    -keepattributes Exceptions

#   融云 rongyun  start

-keepclassmembers class fqcn.of.javascript.interface.for.webview {
 public *;
}

-keepattributes Exceptions,InnerClasses

-keep class io.rong.** {*;}

-keep class * implements io.rong.imlib.model.MessageContent{*;}

-keepattributes Signature

-keepattributes *Annotation*

-keep class sun.misc.Unsafe { *; }

-keep class com.google.gson.examples.android.model.** { *; }

-keepclassmembers class * extends com.sea_monster.dao.AbstractDao {
 public static java.lang.String TABLENAME;
}
-keep class **$Properties
-dontwarn org.eclipse.jdt.annotation.**

-keep class com.ultrapower.** {*;}

#  融云  rongyun end


       #Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions


    #RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#    #Gson
#-keepattributes Signature-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson 下面替换成自己的实体类

#
     #OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

#Jack is required to support java 8 language features.
-dontwarn java.lang.invoke.*
# 友盟
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#百度联盟 start
-keep public class com.baidu.appx.**

-keep public class com.baidu.appx.** { *; }

-keepattributes *Annotation*,*Exceptions*,Signature
#百度联盟 end


#爱贝支付 start

#-----------keep httpclient -------------------
-keep class org.apache.** {
    public <fields>;
    public <methods>;
}

#--------------unionpay 3.3.0--------------
-keep class com.unionpay.** {*;}
-keep class com.UCMobile.PayPlugin.** {*;}
-keep class cn.gov.pbc.tsm.client.mobile.android.bank.service.** {*;}


-keepattributes *Annotation*
-keepattributes *JavascriptInterface*


#--------------sms--------------
-keep class com.iapppay.sms.** {*;}

#--------------alipay-------------
-keep class com.ta.utdid2.** {
    public <fields>;
    public <methods>;
}
-keep class com.ut.device.** {
    public <fields>;
    public <methods>;
}
-keep class com.alipay.android.app.** {
    public <fields>;
    public <methods>;
}
-keep class com.alipay.sdk.** {
    public <fields>;
    public <methods>;
}
-keep class com.alipay.mobilesecuritysdk.** {
    public <fields>;
    public <methods>;
}
-keep class HttpUtils.** {
    public <fields>;
    public <methods>;
}
-keep class org.json.alipay.** {
    public <fields>;
    public <methods>;
}

#-----------keep iapppay -------------------
-keep class com.iapppay.tool {*;}
-keep class com.iapppay.service {*;}
-keep class com.iapppay.provider {*;}
-keep class com.iapppay.apppaysystem {*;}
-keep class com.iapppay.account.channel.ipay.IpayAccountApi {*;}
-keep class com.iapppay.account.channel.ipay.IpayOpenidApi {*;}
-keep class com.iapppay.account.channel.ipay.view.** {
    public <fields>;
    public <methods>;
}
-keep public class com.iapppay.fastpay.ui.** {
    public <fields>;
    public <methods>;
}
-keep public class com.iapppay.fastpay.view.** {
    public <fields>;
    public <methods>;
}
#-----------keep iapppay -------------------
-keep class com.iapppay.utils.RSAHelper {*;}
-keep class com.iapppay.network.** {
    public <fields>;
    public <methods>;
}
-keep class com.iapppay.sdk.main.** {
    public <fields>;
    public <methods>;
}
#iapppay_sub_pay
-keep public class com.iapppay.pay.channel.** {
    public <fields>;
    public <methods>;
}
-keep class com.iapppay.interfaces.callback.** {*;}
-keep class com.iapppay.interfaces.** {
    public <fields>;
    public <methods>;
}

#iapppay UI
-keep public class com.iapppay.ui.activity.** {
    public <fields>;
    public <methods>;
}
# View
-keep public class com.iapppay.ui.widget.**{
    public <fields>;
    public <methods>;
}
-keep public class com.iapppay.ui.view.**{
    public <fields>;
    public <methods>;
}

#爱贝支付  end

##ease start
#-keep class com.easemob.** {*;}
#-keep class org.jivesoftware.** {*;}
#-keep class org.apache.** {*;}
#-dontwarn  com.easemob.**
##2.0.9后的不需要加下面这个keep
##-keep class org.xbill.DNS.** {*;}
##另外，demo中发送表情的时候使用到反射，需要keep SmileUtils
#-keep class com.easemob.chatuidemo.utils.SmileUtils {*;}
##注意前面的包名，如果把这个类复制到自己的项目底下，比如放在com.example.utils底下，应该这么写（实际要去掉#）
##-keep class com.example.utils.SmileUtils {*;}
##如果使用EaseUI库，需要这么写
#-keep class com.easemob.com.im.easeui.easeui.utils.EaseSmileUtils {*;}
#
##2.0.9后加入语音通话功能，如需使用此功能的API，加入以下keep
#-dontwarn ch.imvs.**
#-dontwarn org.slf4j.**
#-keep class org.ice4j.** {*;}
#-keep class net.java.sip.** {*;}
#-keep class org.webrtc.voiceengine.** {*;}
#-keep class org.bitlet.** {*;}
#-keep class org.slf4j.** {*;}
#-keep class ch.imvs.** {*;}
-keep class com.hyphenate.** {*;}
-dontwarn  com.hyphenate.**





-keep class org.xmlpull.** {*;}
-keep class com.baidu.** {*;}
-keep public class * extends com.umeng.**
-keep class com.umeng.** { *; }
-keep class com.squareup.picasso.* {*;}

-keep class com.hyphenate.* {*;}
-keep class com.hyphenate.chat.** {*;}
-keep class org.jivesoftware.** {*;}
-keep class org.apache.** {*;}
#另外，demo中发送表情的时候使用到反射，需要keep SmileUtils,注意前面的包名，
#不要SmileUtils复制到自己的项目下keep的时候还是写的demo里的包名
-keep class com.hyphenate.easeui.utils.SmileUtils {*;}

#2.0.9后加入语音通话功能，如需使用此功能的api，加入以下keep
-keep class net.java.sip.** {*;}
-keep class org.webrtc.voiceengine.** {*;}
-keep class org.bitlet.** {*;}
-keep class org.slf4j.** {*;}
-keep class ch.imvs.** {*;}

#ease end

-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**