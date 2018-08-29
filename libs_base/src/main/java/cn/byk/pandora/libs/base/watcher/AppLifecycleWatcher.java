package cn.byk.pandora.libs.base.watcher;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Byk on 2018/8/1.
 **/
public class AppLifecycleWatcher implements Application.ActivityLifecycleCallbacks {

    public static final String TAG = AppLifecycleWatcher.class.getSimpleName();

    private static final int SWITCH_DELAY = 700;
    private static volatile AppLifecycleWatcher sInstance;

    private Handler mHandler = new Handler();
    private List<Watcher> mWatchers = new CopyOnWriteArrayList<>();
    private boolean mInForeground, mIsPaused;

    public interface Watcher {

        void onState(String name, AppLifecycleState state);

        void onSwitch(boolean toBack);
    }

    private AppLifecycleWatcher() {
    }

    public static synchronized AppLifecycleWatcher getInstance() {
        if (sInstance == null) {
            synchronized (AppLifecycleWatcher.class) {
                if (sInstance == null) {
                    sInstance = new AppLifecycleWatcher();
                }
            }
        }
        return sInstance;
    }

    public static void apply(Application application, Watcher watcher) {
        AppLifecycleWatcher appLifecycleWatcher = getInstance();
        application.registerActivityLifecycleCallbacks(appLifecycleWatcher);
        if (watcher != null) {
            appLifecycleWatcher.addWatcher(watcher);
        }
    }

    public static boolean isForeground() {
        return getInstance().mInForeground;
    }

    public static boolean isBackground() {
        return !getInstance().mInForeground;
    }

    public AppLifecycleWatcher addWatcher(Watcher watcher) {
        mWatchers.add(watcher);
        return this;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        for (Watcher watcher : mWatchers) {
            watcher.onState(activity.getClass()
                                    .getSimpleName(), AppLifecycleState.CREATE);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        for (Watcher watcher : mWatchers) {
            watcher.onState(activity.getClass()
                                    .getSimpleName(), AppLifecycleState.START);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        for (Watcher watcher : mWatchers) {
            watcher.onState(activity.getClass()
                                    .getSimpleName(), AppLifecycleState.RESUME);
        }

        mIsPaused = false;
        boolean fromBackground = !mInForeground;
        mHandler.removeCallbacks(mSwitchTask);
        if (fromBackground) {
            for (Watcher watcher : mWatchers) {
                watcher.onSwitch(false);
            }
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        for (Watcher watcher : mWatchers) {
            watcher.onState(activity.getClass()
                                    .getSimpleName(), AppLifecycleState.PAUSE);
        }

        mIsPaused = true;
        mHandler.removeCallbacks(mSwitchTask);
        mHandler.postDelayed(mSwitchTask, SWITCH_DELAY);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        for (Watcher watcher : mWatchers) {
            watcher.onState(activity.getClass()
                                    .getSimpleName(), AppLifecycleState.STOP);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        for (Watcher watcher : mWatchers) {
            watcher.onState(activity.getClass()
                                    .getSimpleName(), AppLifecycleState.SAVE);
        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        for (Watcher watcher : mWatchers) {
            watcher.onState(activity.getClass()
                                    .getSimpleName(), AppLifecycleState.DESTROY);
        }
    }

    private Runnable mSwitchTask = new Runnable() {
        @Override
        public void run() {
            if (mInForeground && mIsPaused) {
                mInForeground = false;
                for (Watcher watcher : mWatchers) {
                    watcher.onSwitch(true);
                }
            }
        }
    };
}
