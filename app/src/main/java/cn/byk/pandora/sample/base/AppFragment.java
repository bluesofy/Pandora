package cn.byk.pandora.sample.base;

import android.app.Activity;

import cn.byk.pandora.libs.base.view.BaseFragment;
import cn.byk.pandora.sample.AppApplication;
import io.objectbox.BoxStore;

/**
 * Created by Byk on 2018/8/26.
 **/
public abstract class AppFragment extends BaseFragment {

    private BoxStore mBoxStore;

    public BoxStore getBoxStore() {
        Activity activity = getActivity();
        if (activity != null && mBoxStore == null) {
            mBoxStore = ((AppApplication) activity.getApplication()).getBoxStore();
        }
        return mBoxStore;
    }
}
