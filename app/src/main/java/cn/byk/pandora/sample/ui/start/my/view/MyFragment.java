package cn.byk.pandora.sample.ui.start.my.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import cn.byk.pandora.libs.base.mvp.IBasePresenter;
import cn.byk.pandora.sample.R;
import cn.byk.pandora.sample.base.AppFragment;
import cn.byk.pandora.sample.ui.start.my.IMyContract;

/**
 * Created by Byk on 2018/8/21.
 **/
public class MyFragment extends AppFragment implements IMyContract.View {

    private Button mBtnJump;

    private NavController mNavCtrlMain;

    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_start_my;
    }

    @Override
    protected void setView(View rootView) {
        mBtnJump = rootView.findViewById(R.id.btn_jump);
    }

    @Override
    protected void setValue() {
    }

    @Override
    protected void setWatcher() {
        mBtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMainNavCtrl(view).navigate(MyFragmentDirections.actionMyToAbout()
                                                                  .setIsVip(true));
            }
        });
    }

    @Override
    protected void init() {
    }

    private NavController getMainNavCtrl(View view) {
        if (mNavCtrlMain == null) {
            mNavCtrlMain = (view == null) ?
                           Navigation.findNavController(getMainContext(), R.id.nav_host_main) :
                           Navigation.findNavController(view);
        }
        return mNavCtrlMain;
    }

    @Override
    public Activity getMainContext() {
        return getActivity();
    }

    @Override
    public IMyContract.Presenter bindPresenter() {
        return null;
    }

    @Override
    public void setPresenter(IMyContract.Presenter presenter) {

    }
}
