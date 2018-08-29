package cn.byk.pandora.libs.base.mvp;

import android.app.Activity;

/**
 * Created by Byk on 2018/8/9.
 **/
public interface IBaseView<T> {

    Activity getMainContext();

    T bindPresenter();

    void setPresenter(T presenter);
}
