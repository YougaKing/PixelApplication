package youga.snake.api;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by YougaKing on 2016/11/18.
 */

public class LoginApi {


    private static final String TAG = "LoginApi";

    public static void getWXTokenByCode(String paramString, Context context) {
        HashMap<String, String> localHashMap = new HashMap<>();
        localHashMap.put("appid", "wx9e4ce7f566e4b2ff");
        localHashMap.put("secret", "be65b9ef9271e927d643b29a8b4812be");
        localHashMap.put("code", paramString);
        localHashMap.put("grant_type", "authorization_code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        Api.post(localHashMap, url, context, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String access_token = jsonObject.getString("access_token");
                    String unionid = jsonObject.getString("unionid");
                    Log.i(TAG,"access_token:"+access_token);
                    Log.i(TAG,"unionid:"+unionid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
