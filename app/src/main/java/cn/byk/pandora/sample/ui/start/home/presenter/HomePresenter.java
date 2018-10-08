package cn.byk.pandora.sample.ui.start.home.presenter;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import cn.byk.pandora.libs.base.mvp.BasePresenter;
import cn.byk.pandora.libs.base.mvp.IBaseView;
import cn.byk.pandora.sample.ui.start.home.IHomeContract;
import cn.byk.pandora.sample.ui.start.home.model.HomeModel;

/**
 * Created by Byk on 2018/5/31.
 **/
public class HomePresenter extends BasePresenter implements IHomeContract.Presenter {

    private IBaseView mBinder;

    private IHomeContract.View mView;

    private HomePresenter(IBaseView binder) {
        mBinder = binder;
    }

    public IHomeContract.Model getModel() {
        return ViewModelProviders.of(mBinder.getMainContext())
                                 .get(HomeModel.class);
    }

    public static HomePresenter bind(@NonNull IBaseView binder) {
        return new HomePresenter(binder);
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
