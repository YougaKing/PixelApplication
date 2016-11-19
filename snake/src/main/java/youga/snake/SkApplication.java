package youga.snake;

import android.app.Application;

/**
 * Created by YougaKing on 2016/11/18.
 */

public class SkApplication extends Application {

    private static SkApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }


    public static SkApplication getInstance() {
        return mApplication;
    }

}
