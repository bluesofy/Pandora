package cn.byk.pandora.sample.ui;

import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import cn.byk.pandora.sample.R;
import cn.byk.pandora.sample.base.AppActivity;

/**
 * Created by Byk on 2018/8/20.
 * <p>
 * 主框架
 **/
public class MainActivity extends AppActivity {

    private BottomNavigationView mBnvView;

    private NavController mNavCtrl;

    @Override
    protected void setView() {
        mBnvView = findViewById(R.id.menu_bottom);
    }

    @Override
    protected void setData() {
        NavigationUI.setupWithNavController(mBnvView, getNavCtrl());
    }

    @Override
    protected void setWatcher() {
        getNavCtrl().addOnNavigatedListener(new NavController.OnNavigatedListener() {
            @Override
            public void onNavigated(@NonNull NavController controller,
                                    @NonNull NavDestination destination) {
                NavGraph navGraph = destination.getParent();
                if (navGraph != null) {
                    mBnvView.setVisibility(
                            navGraph.getId() == R.id.nav_start ? View.VISIBLE : View.GONE);
                }
            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return getNavCtrl().navigateUp();
    }

    private NavController getNavCtrl() {
        if (mNavCtrl == null) {
            mNavCtrl = Navigation.findNavController(this, R.id.nav_host_main);
        }
        return mNavCtrl;
    }
}
