package cn.byk.pandora.libs.base.mvp;

import cn.byk.pandora.libs.base.BaseActivity;

/**
 * Created by Byk on 2018/8/9.
 **/
public interface IBaseView<T> extends IAbsView {

    BaseActivity getMainContext();

    T bindPresenter();

    void setPresenter(T presenter);
}
