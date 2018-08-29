package cn.byk.pandora.sample.ui.start.home;

import cn.byk.pandora.libs.base.mvp.IBasePresenter;
import cn.byk.pandora.libs.base.mvp.IBaseView;

/**
 * Created by Byk on 2018/5/30.
 **/
public interface IHomeContract {

    interface View extends IBaseView<Presenter> {
    }

    interface Presenter extends IBasePresenter {
    }

    interface Model {
    }
}
