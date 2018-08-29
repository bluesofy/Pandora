package cn.byk.pandora.libs.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byk on 2018/7/1.
 **/
public class AppMgrUtil {

    public static ApplicationInfo getApplicationInfo(Context context, String packageName) {
        try {
            PackageInfo pInfo = context.getPackageManager()
                                       .getPackageInfo(packageName, 0);
            return pInfo.applicationInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isSystemAppOrUpdate(ApplicationInfo applicationInfo) {
        return isSystemApp(applicationInfo) || isSystemUpdateApp(applicationInfo);
    }

    /**
     * 是否是系统应用
     *
     * @param applicationInfo info
     * @return true - 为系统应用
     */
    public static boolean isSystemApp(ApplicationInfo applicationInfo) {
        return applicationInfo != null && ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    /**
     * 是否是系统应用更新版本
     *
     * @param applicationInfo applicationInfo
     * @return true - 为系统升级之后应用
     */
    public static boolean isSystemUpdateApp(ApplicationInfo applicationInfo) {
        return applicationInfo != null && ((applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0);
    }

    /**
     * 获取所有安装的浏览器
     */
    public static List<ResolveInfo> getAllInstalledBrowser(Context context) {
        Intent intent = new Intent(Intent.ACTION_DEFAULT);
        intent.setData(Uri.parse("http://"));
        return getAllInstalledApplication(context, intent);
    }


    /**
     * 根据提供的Intent获取所有匹配的应用程序列表
     */
    private static List<ResolveInfo> getAllInstalledApplication(Context context, Intent filter) {
        return context.getPackageManager()
                      .queryIntentActivities(filter, PackageManager.MATCH_DEFAULT_ONLY);
    }


    /***
     * 根据包名判断一个应用程序是否安装
     */
    public static boolean checkIsIntalledByPkgName(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }

        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pInfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<>();
        if (pInfo != null) {
            for (int i = 0; i < pInfo.size(); i++) {
                String pn = pInfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

    public static String getPackageNameByIntent(Context context, Intent intent) {
        String packageName = null;
        try {
            PackageManager mgr = context.getPackageManager();
            ResolveInfo res = mgr.resolveActivity(intent, 0);
            packageName = res.activityInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageName;
    }

    /**
     * 获取Apk包的packageName
     */
    public static String getApkPkgName(Context context, String apkPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            return info.packageName;
        }
        return null;
    }

    /**
     * 获取Apk包图标
     */
    public static Drawable getApkIcon(Context context, String apkPath) {
        Drawable icon = null;

        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            appInfo.sourceDir = apkPath;
            appInfo.publicSourceDir = apkPath;
            try {
                icon = appInfo.loadIcon(pm);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return icon;
    }

    /**
     * 获取Apk包的versionName
     */
    public static String getApkVersionName(Context context, String apkPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            return info.versionName;
        }
        return null;
    }

    /**
     * 获取Apk包的versionInt
     */
    public static int getApkVersionCode(Context context, String apkPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            return info.versionCode;
        }
        return -1;
    }

    /**
     * 验证apk包是否完整
     */
    public static boolean checkApk(Context context, String apkPath) {
        boolean result = false;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
            if (info != null) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取已安装应用的applicationName
     *
     * @param context context.getPackageName()
     * @return app
     */
    public static String getAppName(Context context, final String packageName) {
        String applicationName = null;
        try {
            PackageManager pm = context.getPackageManager();
            applicationName = pm.getApplicationLabel(pm.getApplicationInfo(packageName, 0))
                                .toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return applicationName;
    }

    /**
     * 获得指定包名版本名
     */
    public static String getVerName(Context context, String packageName) {
        String versionName = "";
        try {
            versionName = context.getPackageManager()
                                 .getPackageInfo(packageName, 0).versionName;
        } catch (Exception e) {
            System.out.println("Get Version Name Error:" + e.getMessage());
        }
        return versionName;
    }

    /**
     * 获得指定包名版本号
     */
    public static int getVerCode(Context context, String packageName) {
        int versionCode = -1;
        try {
            versionCode = context.getPackageManager()
                                 .getPackageInfo(packageName, 0).versionCode;
        } catch (Exception e) {
            System.out.println("Get Version Code Error:" + e.getMessage());
        }
        return versionCode;
    }

    /**
     * 获得版本名
     */
    public static String getVerName(Context context) {
        return getVerName(context, context.getPackageName());
    }

    /**
     * 获得版本号
     */
    public static int getVerCode(Context context) {
        return getVerCode(context, context.getPackageName());
    }

}
