package Ultilities;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Lấy ra Resources ở những package con không sử dụng trực tiếp được
 * Get Resource form child package where can't call Resource directly
 */
public class AppResources extends Application {
    private static Context context = null;
    private static Resources res = null;

    /**
     * lấy ra Resources
     */
    public static Resources getResourses() {
        if (res == null) {
            res = context.getResources();
        }
        return res;
    }
    /**
     * lấy ra Resources
     */
    public static Context getContext() {
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
    }
}
