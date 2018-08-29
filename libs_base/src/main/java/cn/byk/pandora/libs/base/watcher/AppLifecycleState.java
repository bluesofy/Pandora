package cn.byk.pandora.libs.base.watcher;

/**
 * Created by Byk on 2018/8/3.
 **/
public enum AppLifecycleState {

    CREATE("onCreated"),
    START("onStarted"),
    RESUME("onResumed"),
    PAUSE("onPaused"),
    STOP("onStopped"),
    SAVE("onSaveInstanceState"),
    DESTROY("onDestroyed");

    private String name;

    AppLifecycleState(String desc) {
        this.name = desc;
    }

    public String getName() {
        return name;
    }
}
