package cn.byk.pandora.sample.ui.start.my;

import cn.byk.pandora.libs.base.mvp.IBasePresenter;
import cn.byk.pandora.libs.base.mvp.IBaseView;

/**
 * Created by Byk on 2018/8/21.
 **/
public interface IMyContract {

    interface View extends IBaseView<Presenter> {
    }

    interface Presenter extends IBasePresenter {
    }
}
