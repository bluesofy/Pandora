package cn.byk.pandora.libs.util.log;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import androidx.annotation.NonNull;

import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;

import java.io.File;

import cn.byk.pandora.libs.util.BuildConfig;

/**
 * Created by Byk on 2018/7/12.
 **/
public class LogFileMan {

    private static final String LIB_STL_PORT_SHARED = "stlport_shared";
    private static final String LIB_MARS_X_LOG = "marsxlog";

    private static final String ROOT_DIR = "pandora" + File.separator + "log";
    private static final String CACHE_DIR = "CAT_CACHE";
    private static final String DEFAULT_LOG = "CAT";

    private static volatile LogFileMan sInstance;

    private File mRootFile;

    private boolean mIsLoadLib;
    private boolean mIsInit;

    private LogFileMan() {
        if (!mIsLoadLib) {
            System.loadLibrary(LIB_STL_PORT_SHARED);
            System.loadLibrary(LIB_MARS_X_LOG);
            mIsLoadLib = true;
        }
    }

    public static synchronized LogFileMan getInstance() {
        if (sInstance == null) {
            synchronized (LogFileMan.class) {
                if (sInstance == null) {
                    sInstance = new LogFileMan();
                }
            }
        }
        return sInstance;
    }

    public static LogFileMan write(String tag, String msg, boolean toConsole) {
        return getInstance().writeToFile(tag, msg, toConsole);
    }

    public static LogFileMan init(Context context) {
        return getInstance().build(context);
    }

    public static void close() {
        Log.appenderClose();
        getInstance().mIsInit = false;
    }

    private File getRootDir() {
        return mRootFile;
    }

    private LogFileMan build(Context context) {
        if (mIsInit) {
            return this;
        }

        String cachePath = "";
        if (context != null) {
            mRootFile = createRootDir(context);
            cachePath = context.getFilesDir() + File.separator + CACHE_DIR;
        }

        int level = BuildConfig.DEBUG ? Xlog.LEVEL_DEBUG : Xlog.LEVEL_INFO;
        Xlog.appenderOpen(level, Xlog.AppednerModeAsync, cachePath, mRootFile.getAbsolutePath(), DEFAULT_LOG, "");
        Xlog.setConsoleLogOpen(BuildConfig.DEBUG);

        Log.setLogImp(new Xlog());

        mIsInit = true;
        return this;
    }

    private LogFileMan writeToFile(String tag, String msg, boolean toConsole) {
        if (!mIsInit) {
            build(null);
        }

        Xlog.setConsoleLogOpen(toConsole);
        Log.d(tag, msg);

        return this;
    }

    public File createRootDir(Context context) {
        String root = ROOT_DIR + File.separator + context.getPackageName();
        if (Environment.getExternalStorageState()
                       .equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory(), root);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    return file;
                }
            } else {
                return file;
            }
        }

        // Get All External Dirs Include Inner SDCard Dir
        File[] extFilesDirs = getExternalFilesDirs(context);
        if (extFilesDirs != null) {
            for (File dir : extFilesDirs) {
                File file = new File(dir, root);
                if (!file.exists()) {
                    if (file.mkdirs()) {
                        return file;
                    }
                } else {
                    return file;
                }
            }
        }

        return null;
    }

    private File[] getExternalFilesDirs(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            return context.getExternalFilesDirs(null);
        } else {
            return new File[]{context.getExternalFilesDir(null)};
        }
    }
}
