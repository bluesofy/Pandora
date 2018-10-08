package cn.byk.pandora.sample.ui.auth.login.presenter;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import cn.byk.pandora.libs.base.mvp.BasePresenter;
import cn.byk.pandora.libs.base.mvp.IBaseView;
import cn.byk.pandora.sample.data.entity.User;
import cn.byk.pandora.sample.ui.auth.login.ILoginContract;
import cn.byk.pandora.sample.ui.auth.login.model.LoginModel;

/**
 * Created by Byk on 2018/8/26.
 **/
public class LoginPresenter extends BasePresenter implements ILoginContract.Presenter {

    private IBaseView mBinder;

    private ILoginContract.LoginView mLoginView;
    private ILoginContract.RegisterView mRegView;

    private LoginPresenter(IBaseView binder) {
        mBinder = binder;
    }

    public ILoginContract.Model getModel() {
        return ViewModelProviders.of(mBinder.getMainContext())
                                 .get(LoginModel.class);
    }

    public static LoginPresenter bind(@NonNull IBaseView binder) {
        return new LoginPresenter(binder);
    }

    public LoginPresenter attachLoginView(ILoginContract.LoginView view) {
        mLoginView = view;
        view.setPresenter(this);
        return this;
    }

    public LoginPresenter attachRegView(ILoginContract.RegisterView view) {
        mRegView = view;
        view.setPresenter(this);
        return this;
    }

    @Override
    public void onStart() {
        // Init Ui
    }

    @Override
    public void login(String username, String pwd) {
        ILoginContract.Model model = getModel();
        model.getUser(username)
             .observe(mBinder.getMainContext(), new Observer<User>() {
                 @Override
                 public void onChanged(User user) {
                     mLoginView.update(user);
                 }
             });

        // TODO: 2018/8/26 Add Listener
        applyTask(model.login(username, pwd));
        showTips("Login Succeed");
        mLoginView.onLogin(true);
    }

    @Override
    public void validPhone(String phoneNum) {

    }

    private void showTips(String msg) {
        if (mBinder != null) {
            mBinder.showTips(msg);
        }
    }
}
