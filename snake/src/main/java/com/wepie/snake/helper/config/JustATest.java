package com.wepie.snake.helper.config;

import android.content.Context;

/**
 * Created by YougaKing on 2016/11/15.
 */

public class JustATest {

    static {
        System.loadLibrary("JustATest");
    }

    public native String getATestString(Context context, String paramString);
}
