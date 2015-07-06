package com.qinziwanba.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wangzhiguo on 15/7/5.
 */
public class WanbaUtils {

    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = new String(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            WanbaLogger.warn("NoSuchAlgorithmException",ex);
        }
        return resultString;
    }
}
