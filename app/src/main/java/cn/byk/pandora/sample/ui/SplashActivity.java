package cn.byk.pandora.sample.ui;

import android.os.CountDownTimer;
import android.widget.TextView;

import cn.byk.pandora.sample.R;
import cn.byk.pandora.sample.base.AppActivity;
import cn.byk.pandora.sample.ui.auth.AuthActivity;

/**
 * Created by Byk on 2018/8/20.
 * <p>
 * 闪屏动画页，同时用于判断跳转登录还是跳转主页面
 **/
public class SplashActivity extends AppActivity {

    private TextView mTvCountdown;

    @Override
    protected void setView() {
        mTvCountdown = findViewById(R.id.tv_countdown);
    }

    @Override
    protected void setData() {
        startCountdown();
    }

    @Override
    protected void setWatcher() {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_splash;
    }

    private void startCountdown() {
        new CountDownTimer(3_000, 1_000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvCountdown.setText(String.valueOf(millisUntilFinished / 1_000));
            }

            @Override
            public void onFinish() {
                startNext(AuthActivity.class, true);
            }
        }.start();
    }
}
