package cn.byk.pandora.sample.ui.start.home.presenter;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import cn.byk.pandora.libs.base.mvp.BasePresenter;
import cn.byk.pandora.sample.base.AppActivity;
import cn.byk.pandora.sample.ui.start.home.IHomeContract;
import cn.byk.pandora.sample.ui.start.home.model.HomeModel;

/**
 * Created by Byk on 2018/5/31.
 **/
public class HomePresenter extends BasePresenter implements IHomeContract.Presenter {

    private AppActivity mActivity;

    private IHomeContract.View mView;

    private HomePresenter(AppActivity activity) {
        mActivity = activity;
    }

    public IHomeContract.Model getModel() {
        return ViewModelProviders.of(mActivity)
                                 .get(HomeModel.class);
    }

    public static HomePresenter bind(@NonNull AppActivity activity) {
        return new HomePresenter(activity);
    }

    public HomePresenter attachLoginView(IHomeContract.View view) {
        mView = view;
        view.setPresenter(this);
        return this;
    }

    @Override
    public void onStart() {

    }
}
