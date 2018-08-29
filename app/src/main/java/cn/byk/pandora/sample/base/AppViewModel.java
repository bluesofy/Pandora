package cn.byk.pandora.sample.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import cn.byk.pandora.sample.AppApplication;

/**
 * Created by Byk on 2018/8/27.
 **/
public class AppViewModel extends AndroidViewModel {

    public AppViewModel(@NonNull Application application) {
        super(application);
    }

    public AppApplication getApp() {
        return getApplication();
    }
}
