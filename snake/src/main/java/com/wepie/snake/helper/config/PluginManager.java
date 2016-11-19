package com.wepie.snake.helper.config;

import android.content.Context;
import android.util.Log;

import java.io.File;

import dalvik.system.DexClassLoader;
import youga.snake.SkApplication;

/**
 * Created by YougaKing on 2016/11/16.
 */

public class PluginManager {

    static final String LIB_DIR = SkApplication.getInstance().getFilesDir().getParent() + "/lib";
    static final String FILE_DIR = SkApplication.getInstance().getFilesDir().getAbsolutePath();
    static final String PATH_SHARE = FILE_DIR + "/plugin/share/wpshare.jar";
    private static final String TAG = "PluginManager";
    private static PluginManager mInstance;
    private DexClassLoader mDexLoaderShare;

    public static PluginManager getInstance() {
        if (mInstance == null) {
            mInstance = new PluginManager();
        }
        return mInstance;
    }


    private void initDexLoader(String paramString) {
        Log.d(TAG, "paramString:" + paramString);
        try {
            File file = new File(paramString);
            String str = file.getParentFile().getAbsolutePath();
            this.mDexLoaderShare = new DexClassLoader(paramString, str, LIB_DIR, ClassLoader.getSystemClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void wxLogin(Context context) {

        WXShareUtil.doWXLogin(context, this.mDexLoaderShare);
    }
}
