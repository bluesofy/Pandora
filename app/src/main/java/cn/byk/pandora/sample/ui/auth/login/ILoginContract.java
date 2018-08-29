package cn.byk.pandora.sample.ui.auth.login;

import androidx.lifecycle.MutableLiveData;
import cn.byk.pandora.libs.base.mvp.IBasePresenter;
import cn.byk.pandora.libs.base.mvp.IBaseView;
import cn.byk.pandora.sample.data.entity.User;
import io.reactivex.disposables.Disposable;

/**
 * Created by Byk on 2018/8/21.
 **/
public interface ILoginContract {

    interface LoginView extends IBaseView<Presenter> {

        void update(User userInfo);

        void onLogin(boolean isSuccess);
    }

    interface RegisterView extends IBaseView<Presenter> {

        void onValid(boolean isSuccess, String code);

        void onRegister(boolean isSuccess, String username);
    }

    interface Presenter extends IBasePresenter {

        void login(String username, String pwd);

        void validPhone(String phoneNum);
    }

    interface Model {

        MutableLiveData<User> getUser(String name);

        Disposable login(String name, String pwd);
    }
}
