package cn.byk.pandora.libs.base.adapter;

import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import cn.byk.pandora.libs.base.view.BaseFragment;

/**
 * Created by Byk on 2018/8/8.
 **/
public class FragmentAdapter extends FragmentPagerAdapter {

    protected FragmentManager iFragmentManager;
    protected List<BaseFragment> iFragments;

    public FragmentAdapter(FragmentManager fragmentMgr, List<BaseFragment> fragments) {
        super(fragmentMgr);
        this.iFragmentManager = fragmentMgr;
        this.iFragments = fragments;
    }

    public void setFragments(List<BaseFragment> fragments) {
        this.iFragments = fragments;
    }

    @Override
    public BaseFragment getItem(int position) {
        return iFragments.get(position);
    }

    @Override
    public int getCount() {
        return iFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getItem(position).getTitle();
    }
}
