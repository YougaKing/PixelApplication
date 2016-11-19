package com.wepie.snake.helper.update;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by YougaKing on 2016/11/15.
 */

public class QiniuUrlSafeBase64 {
    public static byte[] decode(String paramString) {
        return Base64.decode(paramString, 10);
    }

    public static String encodeToString(String paramString) {
        try {
            paramString = encodeToString(paramString.getBytes("utf-8"));
            return paramString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeToString(byte[] paramArrayOfByte) {
        return Base64.encodeToString(paramArrayOfByte, 10);
    }
}
