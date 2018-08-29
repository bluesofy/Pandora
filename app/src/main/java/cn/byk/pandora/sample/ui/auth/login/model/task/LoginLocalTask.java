package cn.byk.pandora.sample.ui.auth.login.model.task;

import cn.byk.pandora.sample.data.entity.User;
import cn.byk.pandora.sample.data.entity.User_;
import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Created by Byk on 2018/8/27.
 **/
public class LoginLocalTask {

    private BoxStore mBoxStore;

    private Box<User> mBoxUser;

    public LoginLocalTask(BoxStore boxStore) {
        mBoxStore = boxStore;
        mBoxUser = mBoxStore.boxFor(User.class);
    }

    public User getUser(String name) {
        return mBoxUser.query()
                       .equal(User_.name, name)
                       .build()
                       .findFirst();
    }

    public void addUser(User user) {
        mBoxUser.put(user);
    }
}
