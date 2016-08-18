package com.moonsister.tcjy.utils;

/**
 * Created by jb on 2016/6/17.
 */
public class NumberUtlis {
    /**
     * 获取一个不大于九位数的随机数
     *
     * @param digit
     * @return
     */
    public static String getRandomNumber(int digit) {
        if (digit > 9)
            throw new IllegalArgumentException("digit not  more than nine");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digit; i++) {
            int x = (int) (Math.random() * 10);
            sb.append(x);
        }

        return sb.toString();
    }
}
