package youga.snake.api;

import android.content.Context;
import android.util.Log;

import com.wepie.snake.helper.config.JustATest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import youga.snake.UrlConfig;

/**
 * Created by YougaKing on 2016/11/18.
 */

public class Api {
    static String TAG = "Api";
    static OkHttpClient mClient = new OkHttpClient();


    public static void post(HashMap<String, String> hashMap, String url, Context context, Callback responseCallback) {
        hashMap.put("platform", "2");
        hashMap.put("version", "2.0");
        hashMap.put("device_id", "imei_35436007025306_uuid_147887674745207807");
        hashMap.put("version_code", "2038");
        hashMap.put("market", "official");
        hashMap.put("push_channel", "2");
        doSign(context, url, hashMap);
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : hashMap.keySet()) {
            builder.add(key, hashMap.get(key));
            Log.i(TAG, key + ":" + hashMap.get(key));
        }
        Log.i(TAG, "url:" + url);
        Request request = new Request.Builder()
                .post(builder.build())
                .url(url)
                .build();
        mClient.newCall(request)
                .enqueue(responseCallback);
    }

    private static void doSign(Context context, String paramString, HashMap<String, String> paramHashMap) {
        paramString = paramString.substring(UrlConfig.K_API_URL.length());
        ArrayList<String> arrayList = new ArrayList<>();
        for (String key : paramHashMap.keySet()) {
            arrayList.add(key + "=" + paramHashMap.get(key));
        }
        Collections.sort(arrayList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        StringBuilder builder = new StringBuilder();
        if (arrayList.size() > 0) {
            for (String str : arrayList) {
                if (arrayList.indexOf(str) == 0) {
                    builder.append(str);
                } else {
                    builder.append("&").append(str);
                }
            }
        }
        String result = builder.toString();
        try {
            paramString = "POST&" + paramString + "&" + result;
            paramHashMap.put("snake_sign", new JustATest().getATestString(context, paramString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
