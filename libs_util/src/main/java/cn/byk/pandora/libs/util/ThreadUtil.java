package cn.byk.pandora.libs.util;

import android.os.Looper;
import android.text.TextUtils;

/**
 * Created by Byk on 2018/7/3.
 **/
public class ThreadUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * Get Current Invoke Method Name
     */
    public static String getSelfMethodName(String tag, int invokeTimes) {
        StringBuilder sBuilder = new StringBuilder();

        if (!TextUtils.isEmpty(tag)) {
            sBuilder.append(tag)
                    .append(".");
        }

        try {
            StackTraceElement[] Elements = Thread.currentThread()
                                                 .getStackTrace();
            int count = invokeTimes;
            for (StackTraceElement element : Elements) {
                if (count == 0) {
                    sBuilder.append(element.getMethodName());
                    break;
                }
                if (count < invokeTimes || element.getMethodName()
                                                  .equals("getCurrentMethodName")) {
                    count--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sBuilder.toString();
    }

    public static String getSelfMethodName(String tag) {
        return getSelfMethodName(tag, 1);
    }
}
