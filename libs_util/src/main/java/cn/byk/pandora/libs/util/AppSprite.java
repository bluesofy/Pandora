package cn.byk.pandora.libs.util;

import android.app.Application;
import android.util.Log;

/**
 * Created by Byk on 2018/7/1.
 **/
public class AppSprite {

    private static final String TAG = AppSprite.class.getSimpleName();

    public static final Application INSTANCE;

    static {
        Application app = null;
        try {
            app = (Application) Class.forName("android.app.AppGlobals")
                                     .getMethod("getInitialApplication")
                                     .invoke(null);
            if (app == null) {
                throw new IllegalStateException("Static initialization of Applications must be on main thread.");
            }
        } catch (final Exception e) {
            Log.e(TAG, "Failed to get current application from AppGlobals." + e.getMessage());
            try {
                app = (Application) Class.forName("android.app.ActivityThread")
                                         .getMethod("currentApplication")
                                         .invoke(null);
            } catch (final Exception ex) {
                Log.e(TAG, "Failed to get current application from ActivityThread." + e.getMessage());
            }
        } finally {
            INSTANCE = app;
        }
    }
}
