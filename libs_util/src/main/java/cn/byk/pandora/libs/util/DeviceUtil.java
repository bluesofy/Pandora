package cn.byk.pandora.libs.util;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Byk on 2018/7/1.
 **/
public class DeviceUtil {

    private static final int LOLLIPOP = 21;

    public static boolean isTablet(Context context) {
        return (context.getResources()
                       .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static String getSDKVerName() {
        return Build.VERSION.RELEASE;
    }

    public static int getSDKVerCode() {
        return Build.VERSION.SDK_INT;
    }

    public static String model() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim()
                         .replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    public static String display() {
        return Build.DISPLAY;
    }

    public static String version() {
        return Build.VERSION.RELEASE;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String[] getABIs() {
        if (Build.VERSION.SDK_INT >= 21) {
            // Build.VERSION_CODES.LOLLIPOP
            return Build.SUPPORTED_ABIS;
        } else {
            if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
                return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
            }
            return new String[]{Build.CPU_ABI};
        }
    }

    public static String cpuName() {
        String result = "unknown";

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/proc/cpuinfo"));
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            if (Build.VERSION.SDK_INT >= LOLLIPOP) {
                result = Build.SUPPORTED_ABIS[0] + " " + array[1];
            } else {
                result = Build.CPU_ABI + " " + array[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static boolean isDeviceRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/",
                "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }
}
