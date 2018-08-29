package cn.byk.pandora.libs.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * Created by Byk on 2018/7/1.
 **/
public class ActivityUtil {

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class clazz) {
        context.startActivity(new Intent().setClass(context, clazz));
    }

    public static void startActivity(Context context, String clsName) {
        try {
            context.startActivity(new Intent().setClass(context, Class.forName(clsName)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void startActivity(Context context, String pkgName, String clsName) {
        try {
            ComponentName cpName = new ComponentName(pkgName, clsName);
            Intent intent = new Intent();
            intent.setComponent(cpName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startActivity(Context context, String pkgName, String clsName, Bundle bundle) {
        try {

            ComponentName cpName = new ComponentName(pkgName, clsName);
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cpName);
            intent.putExtras(bundle);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getLauncherIntent(Context context, String pkgName) {
        PackageManager pkgMgr = context.getPackageManager();
        return pkgMgr.getLaunchIntentForPackage(pkgName);
    }

    public static void startLauncherActivity(Context context, String pkgName) {
        context.startActivity(getLauncherIntent(context, pkgName));
    }

    public static void startActivityForResult(Activity activity, Intent intent, int requestCode) {
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startService(Context context, Class cls) {
        context.startService(new Intent().setClass(context, cls));
    }

    public static void startService(Context context, Intent intent) {
        context.startService(intent);
    }

    public static void sendBroadcast(Context context, String action) {
        context.sendBroadcast(new Intent(action));
    }

    public static void sendBroadcast(Context context, Intent intent) {
        context.sendBroadcast(intent);
    }
}
