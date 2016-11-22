package com.hickey.tool.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jb on 2016/6/17.
 */
public class MD5Util {
    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }
    public static  String test(String string){
        byte[] arrby = string.getBytes();
        String string2 = null;
        char[] arrc = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(arrby);
            byte[] arrby2 = messageDigest.digest();
            char[] arrc2 = new char[32];
            int n2 = 0;
            for (int i2 = 0; i2 < 16; ++i2) {
                byte by = arrby2[i2];
                arrc2[n2++] = arrc[by >>> 4 & 15];
                arrc2[n2++] = arrc[by & 15];
            }
            string2 = new String(arrc2);
        }
        catch (NoSuchAlgorithmException var5_6) {
//            m.a().f().e("AdUtil.getMD5", "", var5_6);
        }
        return string2;

    }
}
