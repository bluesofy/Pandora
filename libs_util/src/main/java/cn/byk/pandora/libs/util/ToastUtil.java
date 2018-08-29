package cn.byk.pandora.libs.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Byk on 2018/7/3.
 **/
public class ToastUtil {

    public static void showShort(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT)
             .show();
    }

    public static void showShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
             .show();
    }

    public static void showLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG)
             .show();
    }

    public static void showLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG)
             .show();
    }
}
