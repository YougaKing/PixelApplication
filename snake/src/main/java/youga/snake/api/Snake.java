package youga.snake.api;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import youga.snake.UrlConfig;

/**
 * Created by YougaKing on 2016/11/15.
 */

public class Snake {

    public static void pushScore(Context context, HashMap<String, String> hashMap, final CallBack callBack) {
        Api.post(hashMap, UrlConfig.UPDATE_SCORE, context, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.call(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.call(response);
            }
        });
    }

    public static void getScoreInfo(Context context, HashMap<String, String> hashMap, final CallBack callBack) {
        Api.post(hashMap, UrlConfig.SCORE_API_GET_USER_SCORE_INFO, context, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.call(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.call(response);
            }
        });
    }


    public interface CallBack {
        void call(Response response);
    }


}
