package cn.byk.pandora.libs.base.view;

import androidx.annotation.CallSuper;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.mikepenz.fastadapter.listeners.OnLongClickListener;

import cn.byk.pandora.libs.base.widget.layout.MultiStateView;

/**
 * Created by Byk on 2018/8/9.
 **/
public abstract class BaseListFragment<T extends IItem> extends BaseFragment {

    protected RecyclerView iRvList;
    protected MultiStateView iStateView;

    public abstract int getStateViewId();

    public abstract int getListViewId();

    public abstract FastAdapter<T> getAdapter();

    protected boolean onListItemClick(View view, T item, int position) {
        return false;
    }

    protected boolean onListItemLongClick(View view, T item, int position) {
        return false;
    }

    @CallSuper
    @Override
    protected void setView(View rootView) {
        iStateView = (MultiStateView) rootView.findViewById(getStateViewId());
        iRvList = (RecyclerView) rootView.findViewById(getListViewId());
    }

    @CallSuper
    @Override
    protected void setWatcher() {
        FastAdapter<T> adapter = getAdapter();
        if (adapter != null) {
            adapter.withOnClickListener(new OnClickListener<T>() {
                @Override
                public boolean onClick(@Nullable View view, @Nullable IAdapter<T> adapter, T item, int position) {
                    return onListItemClick(view, item, position);
                }
            });
            adapter.withOnLongClickListener(new OnLongClickListener<T>() {
                @Override
                public boolean onLongClick(View view, IAdapter<T> adapter, T item, int position) {
                    return onListItemLongClick(view, item, position);
                }
            });
        }
    }

    public void showContent() {
        if (iStateView != null) {
            iStateView.showContent();
        }
    }

    public void showLoading() {
        if (iStateView != null) {
            iStateView.showLoading();
        }
    }

    public void showError() {
        if (iStateView != null) {
            iStateView.showError();
        }
    }

    public void showEmpty() {
        if (iStateView != null) {
            iStateView.showEmpty();
        }
    }

    public void setEmptyImage(@DrawableRes int resId) {
        if (iStateView != null) {
            iStateView.setEmptyImage(resId);
        }
    }

}
