package com.moonsister.tcjy.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.moonsister.tcjy.manager.GaodeManager;

/**
 * Created by jb on 2016/7/18.
 */
public class PhoneInfoUtils {

    private String address;//地址
    private String brand;//手机品牌
    private String model; //型号
    private String imei;//手机识别码
    private String mac;
    private String screen; //屏幕大小
    private String width;
    private String height;
    private String simserial;
    private String tel; //手机号码
    private String tel2;
    private String imsi;//电话卡识别码

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public static PhoneInfoUtils newInstance() {

        PhoneInfoUtils phone = new PhoneInfoUtils();
        try {
            phone.setBrand(Build.MANUFACTURER);
            phone.setModel(Build.MODEL);
            TelephonyManager telephonyManager = getTelephonyManager();
            phone.setImei(telephonyManager.getDeviceId());
            phone.setImsi(telephonyManager.getSubscriberId());
            phone.setTel(telephonyManager.getLine1Number());
            phone.setMac(getLocalMacAddressFromWifiInfo());
            phone.setScreen(SystemUtils.getDisplayMetrics(ConfigUtils.getInstance().getActivityContext()));
            phone.setHeight(SystemUtils.getScreenHeight());
            phone.setWidth(SystemUtils.getScreenWeith());
            phone.setSimserial(getTelephonyManager().getSimSerialNumber());
            phone.setAddress(PrefUtils.getString(ConfigUtils.getInstance().getApplicationContext(), GaodeManager.class.getName(), ""));
        } catch (Exception e) {
            return phone;
        }
        return phone;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    //根据Wifi信息获取本地Mac
    public static String getLocalMacAddressFromWifiInfo() {
        WifiManager wifi = (WifiManager) ConfigUtils.getInstance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }


    private static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) ConfigUtils.getInstance().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getSimserial() {
        return simserial;
    }

    public void setSimserial(String simserial) {
        this.simserial = simserial;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
}
