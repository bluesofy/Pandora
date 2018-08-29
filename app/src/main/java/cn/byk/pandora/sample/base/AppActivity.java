package cn.byk.pandora.sample.base;

import cn.byk.pandora.libs.base.BaseActivity;
import cn.byk.pandora.sample.AppApplication;
import io.objectbox.BoxStore;

/**
 * Created by Byk on 2018/8/20.
 **/
public abstract class AppActivity extends BaseActivity {

    private BoxStore mBoxStore;

    public BoxStore getBoxStore() {
        if (mBoxStore == null) {
            mBoxStore = ((AppApplication) getApplication()).getBoxStore();
        }
        return mBoxStore;
    }

}
