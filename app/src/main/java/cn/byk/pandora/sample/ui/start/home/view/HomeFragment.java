package cn.byk.pandora.sample.ui.start.home.view;

import android.view.View;

import cn.byk.pandora.libs.base.BaseActivity;
import cn.byk.pandora.libs.base.mvp.IBasePresenter;
import cn.byk.pandora.sample.R;
import cn.byk.pandora.sample.base.AppActivity;
import cn.byk.pandora.sample.base.AppFragment;
import cn.byk.pandora.sample.ui.start.home.IHomeContract;

public class HomeFragment extends AppFragment implements IHomeContract.View {

    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_start_home;
    }

    @Override
    protected void setView(View rootView) {
    }

    @Override
    protected void setValue() {
    }

    @Override
    protected void setWatcher() {
    }

    @Override
    protected void init() {
    }

    @Override
    public BaseActivity getMainContext() {
        return (AppActivity) getActivity();
    }

    @Override
    public IHomeContract.Presenter bindPresenter() {
        return null;
    }

    @Override
    public void setPresenter(IHomeContract.Presenter presenter) {

    }
}
