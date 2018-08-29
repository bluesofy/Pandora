package cn.byk.pandora.libs.base;

import androidx.annotation.CallSuper;
import androidx.multidex.MultiDexApplication;

import cn.byk.pandora.libs.base.watcher.AppLifecycleState;
import cn.byk.pandora.libs.base.watcher.AppLifecycleWatcher;
import cn.byk.pandora.libs.util.image.GlideMgr;
import cn.byk.pandora.libs.util.log.LogMan;

/**
 * Created by Byk on 2018/7/13.
 **/
public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // Init Log
        initLog(true);

        // Add Activity Lifecycle Listener
        initLifecycleWatcher();
    }

    private void initLifecycleWatcher() {
        AppLifecycleWatcher.apply(this, new AppLifecycleWatcher.Watcher() {
            @Override
            public void onState(String name, AppLifecycleState state) {
                log(AppLifecycleWatcher.TAG, name + "-" + state, true);
            }

            @Override
            public void onSwitch(boolean toBack) {
                log(AppLifecycleWatcher.TAG, "App is in Background:" + toBack, true);
            }
        });
    }

    protected boolean initLog(boolean logEnable) {
        LogMan.init(this, logEnable);
        return true;
    }

    protected void log(String tag, String msg, boolean toFile) {
        LogMan.print(tag, msg, toFile);
    }

    @CallSuper
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        GlideMgr.trimMemory(this, level);
    }

    @CallSuper
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        GlideMgr.cleanMemory(this);
    }
}
