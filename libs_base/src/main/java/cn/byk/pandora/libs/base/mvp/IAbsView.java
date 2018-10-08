package cn.byk.pandora.libs.base.mvp;

/**
 * Created by Byk on 2018/9/26.
 **/
public interface IAbsView {

    void showLoadingDialog(String msg);

    void showLoadingDialog(int resId);

    void hideLoadingDialog();

    void showTips(String msg);

    void showTips(int resId);
}
