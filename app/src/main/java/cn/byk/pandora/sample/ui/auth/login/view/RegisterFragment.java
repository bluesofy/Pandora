package cn.byk.pandora.sample.ui.auth.login.view;

import android.app.Activity;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import cn.byk.pandora.libs.base.mvp.IBasePresenter;
import cn.byk.pandora.sample.R;
import cn.byk.pandora.sample.base.AppActivity;
import cn.byk.pandora.sample.base.AppFragment;
import cn.byk.pandora.sample.ui.auth.login.ILoginContract;
import cn.byk.pandora.sample.ui.auth.login.presenter.LoginPresenter;

/**
 * Created by Byk on 2018/8/21.
 **/
public class RegisterFragment extends AppFragment implements ILoginContract.RegisterView {

    private ILoginContract.Presenter mPresenter;

    @Override
    protected IBasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_auth_register;
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
        bindPresenter();
    }

    @Override
    public void onValid(boolean isSuccess, String code) {

    }

    @Override
    public void onRegister(boolean isSuccess, String username) {

    }

    @Override
    public Activity getMainContext() {
        return getActivity();
    }

    @Override
    public ILoginContract.Presenter bindPresenter() {
        return LoginPresenter.bind((AppActivity) getMainContext())
                             .attachRegView(this);
    }

    @Override
    public void setPresenter(ILoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
