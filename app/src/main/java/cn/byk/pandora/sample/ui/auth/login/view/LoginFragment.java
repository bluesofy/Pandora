package cn.byk.pandora.sample.ui.auth.login.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.ViewModelProviders;
import cn.byk.pandora.libs.base.mvp.IBasePresenter;
import cn.byk.pandora.sample.R;
import cn.byk.pandora.sample.base.AppActivity;
import cn.byk.pandora.sample.base.AppFragment;
import cn.byk.pandora.sample.data.entity.User;
import cn.byk.pandora.sample.ui.auth.login.ILoginContract;
import cn.byk.pandora.sample.ui.auth.login.presenter.LoginPresenter;
import cn.byk.pandora.sample.util.NavCtrl;

/**
 * Created by Byk on 2018/8/21.
 **/
public class LoginFragment extends AppFragment implements ILoginContract.LoginView {

    private Button mBtnLogin;

    private ILoginContract.Presenter mPresenter;

    @Override
    protected IBasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_auth_login;
    }

    @Override
    protected void setView(View rootView) {
        mBtnLogin = rootView.findViewById(R.id.btn_login);
    }

    @Override
    protected void setValue() {

    }

    @Override
    protected void setWatcher() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login("test", "");
            }
        });
    }

    @Override
    protected void init() {
        bindPresenter();
    }

    @Override
    public void update(User userInfo) {

    }

    @Override
    public void onLogin(boolean isSuccess) {
        if (isSuccess) {
            NavCtrl.startMain(getMainContext());
            getMainContext().finish();
        }
    }

    @Override
    public Activity getMainContext() {
        return getActivity();
    }

    @Override
    public ILoginContract.Presenter bindPresenter() {
        return LoginPresenter.bind((AppActivity) getMainContext())
                             .attachLoginView(this);
    }

    @Override
    public void setPresenter(ILoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
