package cn.byk.pandora.sample.ui.auth.login.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import cn.byk.pandora.sample.base.AppViewModel;
import cn.byk.pandora.sample.data.entity.User;
import cn.byk.pandora.sample.ui.auth.login.ILoginContract;
import cn.byk.pandora.sample.ui.auth.login.model.task.LoginLocalTask;
import cn.byk.pandora.sample.ui.auth.login.model.task.LoginRemoteTask;
import io.reactivex.disposables.Disposable;

/**
 * Created by Byk on 2018/8/26.
 **/
public class LoginModel extends AppViewModel implements ILoginContract.Model {

    private LoginLocalTask mLocalTask;
    private LoginRemoteTask mRemoteTask;

    private MutableLiveData<User> mUserData = new MutableLiveData<>();

    public LoginModel(@NonNull Application application) {
        super(application);

        mLocalTask = new LoginLocalTask(getApp().getBoxStore());
        mRemoteTask = new LoginRemoteTask();
    }

    @Override
    public MutableLiveData<User> getUser(String name) {
        mUserData.setValue(mLocalTask.getUser(name));
        return mUserData;
    }

    @Override
    public Disposable login(String name, String pwd) {
        // Sample
        User user = new User(1, name);
        mLocalTask.addUser(user);
        mUserData.setValue(user);

        return mRemoteTask.login(name, pwd);
    }

}
