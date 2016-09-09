package com.moonsister.tcjy.manager;

import com.moonsister.tcjy.CacheManager;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.StringUtis;

import java.util.List;

import io.rong.imkit.RongyunManager;


/**
 * Created by jb on 2016/6/30.
 */
public class UserInfoManager {
    private volatile static UserInfoManager instance;
    private static PersonInfoDetail info;


    public static UserInfoManager getInstance() {
        if (instance == null) {
            synchronized (UserInfoManager.class) {
                if (instance == null) {
                    initFilePersonInfoDetail();
                    instance = new UserInfoManager();
                }

            }
        }
        return instance;
    }

    /**
     * 初始化对象
     */
    private static void initFilePersonInfoDetail() {
        info = getFilePersonInfoDetail();
    }

    /**
     * 从本地获取信息对象
     *
     * @return
     */
    private static PersonInfoDetail getFilePersonInfoDetail() {
        PersonInfoDetail persinInfo = null;
        if (CacheManager.isExist4DataCache(ConfigUtils.getInstance().getApplicationContext(), CacheManager.CachePath.PERSON_INFODETAIL_INSTANCE)) {
            Object o = CacheManager.readObject(ConfigUtils.getInstance().getApplicationContext(), CacheManager.CachePath.PERSON_INFODETAIL_INSTANCE);
            if (o != null && o instanceof PersonInfoDetail) {
                persinInfo = (PersonInfoDetail) o;
            }
        } else {
            persinInfo = new PersonInfoDetail();
            CacheManager.saveObject(ConfigUtils.getInstance().getApplicationContext(), persinInfo, CacheManager.CachePath.PERSON_INFODETAIL_INSTANCE);
        }
        if (persinInfo == null) {
            persinInfo = new PersonInfoDetail();
            CacheManager.saveObject(ConfigUtils.getInstance().getApplicationContext(), persinInfo, CacheManager.CachePath.PERSON_INFODETAIL_INSTANCE);
        }
        return persinInfo;
    }

    /**
     * 保存到文件
     *
     * @param instance
     */
    private void saveFileInstance(PersonInfoDetail instance) {
        CacheManager.saveObject(ConfigUtils.getInstance().getApplicationContext(), instance, CacheManager.CachePath.PERSON_INFODETAIL_INSTANCE);
    }


    /**
     * 从内存获取信息对象
     *
     * @return
     */
    public PersonInfoDetail getMemoryPersonInfoDetail() {

        return info;
    }

    /**
     * 是否登录
     *
     * @return
     */
    public boolean isLogin() {
        return info.isLogin();
    }

    /**
     * 获取authcode
     *
     * @return
     */
    public String getAuthcode() {
        String authcode = info.getAuthcode();
        if (StringUtis.isEmpty(authcode))
            return "";
        return authcode;
    }


    /**
     * 获取融云的key
     *
     * @return
     */
    public String getRongyunKey() {
        String rongyunkey = info.getRongyunkey();
        if (StringUtis.isEmpty(rongyunkey))
            return "";
        return rongyunkey;
    }

    /**
     * 下线
     */
    public void logout() {
        saveFileInstance(null);
        initFilePersonInfoDetail();
        RongyunManager.getInstance().offline();
    }

    /**
     * 用户头像
     *
     * @return
     */
    public String getAvater() {
        return info.getFace();
    }

    /**
     * 保存在内存
     *
     * @param instance
     */
    public void saveMemoryInstance(PersonInfoDetail instance) {
        info = instance;
        saveFileInstance(info);

    }

    /**
     * 认证状态
     *
     * @return
     */

    public int getCertificationStatus() {
        return info.getAttestation();
    }

    /**
     * 性别
     *
     * @return
     */
    public String getUserSex() {
        return info.getSex();
    }

    /**
     * 名字
     *
     * @return
     */
    public String getNickeName() {
        return info.getNickname();
    }

    /**
     * id
     *
     * @return
     */
    public String getUid() {
        return info.getId();
    }


    /**
     * 获取好友列表
     *
     * @return
     */
    public List<String> getUserFrientList() {
        return info.getUserFriendList();
    }

    //总收入
    public String getAll_Income(){
        return info.getIncome_all();
    }
    //今日收入
    public String getDay_Income(){
        return info.getIncome_today();
    }
    //用户年龄
    public String getAge(){
        return info.getAge();
    }
    //用户出生年月
    public String getBrith(){
        return info.getBrith();
    }
    //用户地址
    public String getAddress(){
        return info.getAddress();
    }
    //用户职业
    public String getProfession(){
        return info.getProfession();
    }
    /**
     * 获取用户手机号
     *
     * @return
     */
    public String getSmobile() {
        String smobile = info.getSmobile();
        if (StringUtis.isEmpty(smobile))
            return "";
        return smobile;
    }
}
