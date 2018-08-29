package cn.byk.pandora.sample;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import cn.byk.pandora.libs.util.log.LogMan;
import cn.byk.pandora.sample.base.AppActivity;
import cn.byk.pandora.sample.ui.SplashActivity;
import cn.byk.pandora.sample.util.NavCtrl;

/**
 * Created by Byk on 2018/8/18.
 * <p>
 * 入口页，解析启动参数
 **/
public class AppEntryActivity extends AppActivity {

    private static final String TAG = AppEntryActivity.class.getSimpleName();

    private String mChannel;
    private String mToken;

    @Override
    protected void setView() {
    }

    @Override
    protected void setData() {
    }

    @Override
    protected void setWatcher() {
    }

    @Override
    protected int getContentLayout() {
        return 0;
    }

    @Override
    protected boolean blockOnCreate() {
        init();
        return true;
    }

    private void init() {
        boolean isThirdParty = false;

        Intent extData = getIntent();
        if (extData != null) {
            LogMan.print(TAG, "Intent Data String:" + extData.getDataString(), true);

            String action = extData.getAction();
            if (Intent.ACTION_VIEW.equals(action)) {
                Uri uri = extData.getData();
                if (uri != null) {
                    mChannel = uri.getQueryParameter("channel");
                    mToken = uri.getQueryParameter("token");

                    LogMan.print(TAG, "ThirdParty Login Channel:" + mChannel, true);

                    if (TextUtils.isEmpty(mToken)) {
                        LogMan.print(TAG, "ThirdParty Login Token is Empty", true);
                    } else {
                        LogMan.print(TAG, "ThirdParty Login Token:" + mToken, true);
                        isThirdParty = true;
                    }
                } else {
                    LogMan.print(TAG, "ThirdParty Login Uri is Empty", true);
                }
            }
        }

        if (isThirdParty) {
            startViaOem();
        } else {
            startViaNormal();
        }

        finish();
    }

    private void startViaNormal() {
        startNext(SplashActivity.class);
    }

    private void startViaOem() {

    }
}
