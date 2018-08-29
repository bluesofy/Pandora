package cn.byk.pandora.libs.util.log;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Byk on 2018/7/12.
 **/
public class LogMan {

    private static String mClassName = LogMan.class.getName();
    private static ArrayList<String> mMethods = new ArrayList<>();

    private static boolean sLogEnable = true;
    private static boolean sFileLogEnable = true;

    static {
        Method[] ms = LogMan.class.getDeclaredMethods();
        for (Method m : ms) {
            mMethods.add(m.getName());
        }
    }

    public static void print(String tag, String msg) {
        print(tag, msg, false, false);
    }

    public static void print(String tag, String msg, boolean toFile) {
        print(tag, msg, false, toFile);
    }

    public static void print(String tag, String msg, boolean withLine, boolean toFile) {
        i(tag, msg, withLine);
        if (sLogEnable && toFile) {
            file(tag, msg, false);
        }
    }

    public static void file(String tag, String msg, boolean toConsole) {
        if (sFileLogEnable) {
            LogFileMan.write(tag, msg, toConsole);
        }
    }

    public static void setEnable(boolean logEnable) {
        sLogEnable = logEnable;
    }

    public static void init(Context context, boolean logEnable) {
        setEnable(logEnable);
        if (logEnable) {
            LogFileMan.init(context);
        }
    }

    /**
     * Close to Save the Log
     */
    public static void close() {
        if (sLogEnable) {
            LogFileMan.close();
        }
    }

    public static void d(String tag, String msg) {
        d(tag, msg, true);
    }

    public static void d(String tag, String msg, boolean withLine) {
        if (sLogEnable) {
            Log.d(tag, withLine ? getMsgWithLineNumber(msg) : msg);
        }
    }

    public static void e(String tag, String msg) {
        e(tag, msg, true);
    }

    public static void e(String tag, String msg, boolean withLine) {
        if (sLogEnable) {
            Log.e(tag, withLine ? getMsgWithLineNumber(msg) : msg);
        }
    }

    public static void i(String tag, String msg) {
        i(tag, msg, true);
    }

    public static void i(String tag, String msg, boolean withLine) {
        if (sLogEnable) {
            Log.i(tag, withLine ? getMsgWithLineNumber(msg) : msg);
        }
    }

    public static void w(String tag, String msg) {
        w(tag, msg, true);
    }

    public static void w(String tag, String msg, boolean withLine) {
        if (sLogEnable) {
            Log.w(tag, withLine ? getMsgWithLineNumber(msg) : msg);
        }
    }

    public static void v(String tag, String msg) {
        v(tag, msg, true);
    }

    public static void v(String tag, String msg, boolean withLine) {
        if (sLogEnable) {
            Log.v(tag, withLine ? getMsgWithLineNumber(msg) : msg);
        }
    }

    public static void d(String msg) {
        d(msg, true);
    }

    public static void d(String msg, boolean withLine) {
        if (sLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.d(content[0], withLine ? content[1] : msg);
        }
    }

    public static void e(String msg) {
        e(msg, true);
    }

    public static void e(String msg, boolean withLine) {
        if (sLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.e(content[0], withLine ? content[1] : msg);
        }
    }

    public static void i(String msg) {
        i(msg, true);
    }

    public static void i(String msg, boolean withLine) {
        if (sLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.i(content[0], withLine ? content[1] : msg);
        }
    }

    public static void i() {
        if (sLogEnable) {
            String[] content = getMsgAndTagWithLineNumber("");
            Log.i(content[0], content[1]);
        }
    }

    public static void w(String msg) {
        w(msg, true);
    }

    public static void w(String msg, boolean withLine) {
        if (sLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.w(content[0], withLine ? content[1] : msg);
        }
    }

    public static void v(String msg) {
        v(msg, true);
    }

    public static void v(String msg, boolean withLine) {
        if (sLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            Log.v(content[0], withLine ? content[1] : msg);
        }
    }

    public static String getMsgWithLineNumber(String msg) {
        try {
            StackTraceElement[] e = (new Throwable()).getStackTrace();
            for (StackTraceElement st : e) {
                if (!mClassName.equals(st.getClassName()) && !mMethods.contains(st.getMethodName())) {
                    int b = st.getClassName()
                              .lastIndexOf(".") + 1;
                    String TAG = st.getClassName()
                                   .substring(b);
                    return TAG + "->" + st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    public static String[] getMsgAndTagWithLineNumber(String msg) {
        try {
            StackTraceElement[] e = (new Throwable()).getStackTrace();
            for (StackTraceElement st : e) {
                if (!mClassName.equals(st.getClassName()) && !mMethods.contains(st.getMethodName())) {
                    int b = st.getClassName()
                              .lastIndexOf(".") + 1;
                    String TAG = st.getClassName()
                                   .substring(b);
                    String message = st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                    return new String[]{TAG, message};
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[]{"universal tag", msg};
    }
}
