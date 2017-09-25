
package com.szmz.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    private static final String key="HYun1234";
    /**
     * 根据输入的字符串生成固定的32位MD5码
     *
     * @param str 输入的字符串
     * @return MD5码
     */
    public final static String getMd5(String str) {

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        str = str+key;
        messageDigest.update(str.getBytes());// 使用指定的字节更新摘要
        byte[] md = messageDigest.digest();// 获得密文
        return byteArrToHexStr(md).toUpperCase();
    }

    /**
     * 将byte数组转换为表示16进制值的字符串，如：byte[]{8,18}转换为：0813
     *
     * @param source 需要转换的byte数组
     * @return 转换后的字符串
     */
    public static String byteArrToHexStr(byte[] source) {
        int length = source.length;
        // 每个byte(8位)用两个(16进制)字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer stringBuffer = new StringBuffer(length * 2);
        for (int i = 0; i < length; i++) {
            int intTmp = source[i];
            while (intTmp < 0) {// 把负数转换为正数
                intTmp = intTmp + 256;
            }
            if (intTmp < 16) {// 小于0F的数需要在前面补0
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toString(intTmp, 16));
        }
        return stringBuffer.toString();
    }


}
