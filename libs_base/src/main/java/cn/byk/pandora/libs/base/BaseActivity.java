package cn.byk.pandora.libs.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.byk.pandora.libs.util.DeviceUtil;
import cn.byk.pandora.libs.util.ToastUtil;
import cn.byk.pandora.libs.util.log.LogMan;

/**
 * Created by Byk on 2018/8/3.
 **/
public abstract class BaseActivity extends AppCompatActivity {

    protected Bundle iSavedInstanceState;

    private boolean mIsTablet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Create
        iSavedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);

        if (blockOnCreate()) {
            finish();
            return;
        }

        mIsTablet = DeviceUtil.isTablet(this);

        // Apply Layout
        loadLayout();

        // Set Toolbar
        initToolbar();

        if (blockOnInit()) {
            return;
        }

        // Init
        setView();
        setData();
        setWatcher();
    }

    /**
     * Init View
     */
    protected abstract void setView();

    /**
     * Init Value
     */
    protected abstract void setData();

    /**
     * Init Listener
     */
    protected abstract void setWatcher();

    /**
     * Return Content Layout
     *
     * @return Layout Res Id
     */
    protected abstract int getContentLayout();

    protected void initToolbar() {
        initToolbar(R.menu.menu_toolbar);
    }

    protected void initToolbar(int menuResId) {

    }

    protected boolean blockOnInit() {
        return false;
    }

    protected boolean blockOnCreate() {
        return false;
    }

    protected void loadLayout() {
        setContentView(getContentLayout());
    }

    public boolean isTablet() {
        return mIsTablet;
    }

    protected void startNext(Intent intent) {
        startActivity(intent);
    }

    protected void startNext(Class<?> nextClass) {
        startNext(new Intent(this, nextClass));
    }

    protected void startNext(Class<?> nextClass, boolean doFinish) {
        startNext(nextClass);
        if (doFinish) {
            finish();
        }
    }

    public void showLoadingDialog(String msg) {

    }

    public void showLoadingDialog(int resId) {

    }

    public void hideLoadingDialog() {

    }

    public void showTips(String msg) {
        ToastUtil.showShort(this, msg);
    }

    public void showTips(int resId) {
        ToastUtil.showShort(this, resId);
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        LogMan.close();
        super.onDestroy();
    }
}
