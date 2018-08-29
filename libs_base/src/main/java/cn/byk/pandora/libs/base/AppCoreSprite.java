package cn.byk.pandora.libs.base;

import android.app.Application;

import cn.byk.pandora.libs.util.AppSprite;
import cn.byk.pandora.libs.util.ToastUtil;

/**
 * Created by Byk on 2018/8/1.
 **/
public class AppCoreSprite extends AppSprite {

    private static final int INTERVAL = 2_000;

    private static String sLastTips = "";
    private static long sLastTipsTime;

    public static Application getApp() {
        return INSTANCE;
    }

    public static void showTips(int resId, Object... formatArgs) {
        showTips(INSTANCE.getString(resId, formatArgs));
    }

    public static void showTips(int resId) {
        showTips(INSTANCE.getString(resId));
    }

    public static void showTips(String msg) {
        if (INSTANCE == null) {
            return;
        }

        long now = System.currentTimeMillis();
        if (now - sLastTipsTime < INTERVAL) {
            if (msg.equals(sLastTips)) {
                return;
            }
        }
        sLastTipsTime = now;
        sLastTips = msg;
        ToastUtil.showShort(INSTANCE, msg);
    }
}
