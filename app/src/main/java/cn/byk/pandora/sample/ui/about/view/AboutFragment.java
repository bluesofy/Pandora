package cn.byk.pandora.sample.ui.about.view;

import android.view.View;
import android.widget.TextView;

import cn.byk.pandora.libs.base.mvp.IBasePresenter;
import cn.byk.pandora.sample.R;
import cn.byk.pandora.sample.base.AppFragment;

/**
 * Created by Byk on 2018/8/21.
 **/
public class AboutFragment extends AppFragment {

    private TextView mTvContent;

    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_about;
    }

    @Override
    protected void setView(View rootView) {
        mTvContent = rootView.findViewById(R.id.tv_content);
    }

    @Override
    protected void setValue() {
        boolean isVip = AboutFragmentArgs.fromBundle(getArguments())
                                         .getIsVip();
        mTvContent.setText(isVip ? R.string.txt_vip_yes : R.string.txt_vip_no);
    }

    @Override
    protected void setWatcher() {

    }

    @Override
    protected void init() {

    }
}
