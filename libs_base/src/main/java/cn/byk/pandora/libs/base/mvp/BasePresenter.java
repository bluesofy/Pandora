package cn.byk.pandora.libs.base.mvp;

import androidx.annotation.CallSuper;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Byk on 2018/8/9.
 **/
public class BasePresenter implements IBaseLifecycle {

    private CompositeDisposable mTaskMgr;

    public void applyTask(Disposable disposableTask) {
        if (disposableTask == null) {
            return;
        }

        if (mTaskMgr == null) {
            mTaskMgr = new CompositeDisposable();
        }

        mTaskMgr.add(disposableTask);
    }

    public void clearTask() {
        if (mTaskMgr != null) {
            mTaskMgr.clear();
        }
    }

    @CallSuper
    @Override
    public void onDestroy() {
        clearTask();
    }
}
