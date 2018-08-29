package cn.byk.pandora.sample.ui.start.home.model;

import android.app.Application;

import androidx.annotation.NonNull;
import cn.byk.pandora.sample.base.AppViewModel;
import cn.byk.pandora.sample.ui.start.home.IHomeContract;

public class HomeModel extends AppViewModel implements IHomeContract.Model {

    public HomeModel(@NonNull Application application) {
        super(application);
    }
}
