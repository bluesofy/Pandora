package cn.byk.pandora.libs.base.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.lang.ref.WeakReference;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cn.byk.pandora.libs.base.BaseActivity;
import cn.byk.pandora.libs.base.mvp.IBasePresenter;
import cn.byk.pandora.libs.util.DeviceUtil;
import cn.byk.pandora.libs.util.ToastUtil;

/**
 * Created by Byk on 2018/8/8.
 **/
public abstract class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();

    protected Bundle iSavedInstanceState;

    private boolean mIsTablet;
    private WeakReference<View> mRootView;
    private boolean mNeedSet;
    private String mTitle;

    private OnActionWatcher mActWatcher;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mIsTablet = DeviceUtil.isTablet(getContext());

        iSavedInstanceState = savedInstanceState;

        if (mRootView == null || mRootView.get() == null) {
            loadLayout(inflater, container);
            mNeedSet = true;
        }

        ViewParent parent = mRootView.get()
                                     .getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(mRootView.get());
        }

        return mRootView.get();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (mNeedSet) {
            setView(mRootView.get());
            setValue();
            setWatcher();

            mNeedSet = false;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @CallSuper
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnActionWatcher) {
            mActWatcher = (OnActionWatcher) context;
        }
    }

    @CallSuper
    @Override
    public void onDetach() {
        super.onDetach();
        mActWatcher = null;
    }

    @CallSuper
    @Override
    public void onDestroy() {
        IBasePresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroy();
    }

    protected void loadLayout(LayoutInflater inflater, ViewGroup container) {
        mRootView = new WeakReference<>(inflater.inflate(getContentLayout(), container, false));
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public boolean onBackPressed() {
        return false;
    }

    public String getLogTag() {
        return TAG;
    }

    public void onAction(Uri uri) {
        if (mActWatcher != null) {
            mActWatcher.onAction(uri);
        }
    }

    public void showLoadingDialog(String msg) {
    }

    public void showLoadingDialog(int resId) {
    }

    public void hideLoadingDialog() {
    }

    public void showTips(String msg) {
        ToastUtil.showShort(this.getActivity(), msg);
    }

    public void showTips(int resId) {
        ToastUtil.showShort(this.getActivity(), resId);
    }

    public boolean isTablet() {
        return mIsTablet;
    }

    public void onMenuBtnClick(int type) {
    }

    protected abstract IBasePresenter getPresenter();

    protected abstract int getContentLayout();

    protected abstract void setView(View rootView);

    protected abstract void setValue();

    protected abstract void setWatcher();

    /**
     * View Loaded
     */
    protected abstract void init();

    public interface OnActionWatcher {
        void onAction(Uri uri);
    }
}
