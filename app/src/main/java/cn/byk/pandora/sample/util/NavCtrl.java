package cn.byk.pandora.sample.util;

import android.content.Context;

import cn.byk.pandora.libs.util.ActivityUtil;
import cn.byk.pandora.sample.ui.MainActivity;

/**
 * Created by Byk on 2018/8/18.
 **/
public class NavCtrl {

    public static void startMain(Context context) {
        ActivityUtil.startActivity(context, MainActivity.class);
    }

}
