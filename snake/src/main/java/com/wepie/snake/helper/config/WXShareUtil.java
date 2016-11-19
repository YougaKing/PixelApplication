package com.wepie.snake.helper.config;

import android.content.Context;

import dalvik.system.DexClassLoader;

/**
 * Created by YougaKing on 2016/11/16.
 */

public class WXShareUtil {
    private static final String LOGIN_STATE = "wx_login";

    public static void doWXLogin(Context paramContext, DexClassLoader classLoader) {
        try {
            classLoader.loadClass("com.wepie.dyshare.share.ShareApi").getMethod("doWXLogin",
                    new Class[]{Context.class, String.class, String.class})
                    .invoke(null, new Object[]{paramContext, "wx9e4ce7f566e4b2ff", "wx_login"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
