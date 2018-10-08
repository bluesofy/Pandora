package cn.byk.pandora.sample;

import cn.byk.pandora.libs.base.BaseApplication;
import cn.byk.pandora.sample.data.entity.MyObjectBox;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

/**
 * Created by Byk on 2018/5/25.
 **/
public class AppApplication extends BaseApplication {

    private BoxStore mBoxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        initLibs();
    }

    public BoxStore getBoxStore() {
        return mBoxStore;
    }

    private void initLibs() {
        mBoxStore = MyObjectBox.builder()
                               .androidContext(AppApplication.this)
                               .build();
        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(mBoxStore).start(this);
        }
    }
}
