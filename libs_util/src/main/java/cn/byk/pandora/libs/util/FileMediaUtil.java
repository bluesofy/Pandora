package cn.byk.pandora.libs.util;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;

import java.io.File;

/**
 * Created by Byk on 2018/7/1.
 **/
public class FileMediaUtil {

    public static String getSuffix(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        String fileName = file.getName();
        int lastPointIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastPointIndex + 1);
    }

    public static String getSuffix(File file) {
        String fileName = file.getName();
        int lastPointIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastPointIndex + 1);
    }

    public static Intent openFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        return openFile(file);
    }

    public static Intent openFile(File file) {
        String suffix = getSuffix(file);
        if (suffix.equals("m4a") || suffix.equals("mp3") || suffix.equals("mid") || suffix.equals("xmf") ||
                suffix.equals("ogg") || suffix.equals("wav") || suffix.equals("amr")) {
            return getAudioFileIntent(file);
        } else if (suffix.equals("3gp") || suffix.equals("mp4")) {
            return getVideoFileIntent(file);
        } else if (suffix.equals("jpg") || suffix.equals("gif") || suffix.equals("png") || suffix.equals("jpeg") ||
                suffix.equals("bmp")) {
            return getImageFileIntent(file);
        } else if (suffix.equals("apk")) {
            return getApkFileIntent(file);
        } else if (suffix.equals("ppt")) {
            return getPptFileIntent(file);
        } else if (suffix.equals("xls")) {
            return getExcelFileIntent(file);
        } else if (suffix.equals("doc")) {
            return getWordFileIntent(file);
        } else if (suffix.equals("pdf")) {
            return getPdfFileIntent(file);
        } else if (suffix.equals("chm")) {
            return getChmFileIntent(file);
        } else if (suffix.equals("txt")) {
            return getTextFileIntent(file);
        } else {
            return getAllIntent(file);
        }
    }

    public static boolean isApk(String path) {
        return getSuffix(path).equalsIgnoreCase("apk");
    }

    /**
     * 获取一个用于打开HTML文件的intent
     */
    public static Intent getHtmlFileIntent(File file) {
        Uri uri = Uri.parse(file.toString())
                     .buildUpon()
                     .encodedAuthority("com.android.htmlfileprovider")
                     .scheme("content")
                     .encodedPath(file.toString())
                     .build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    /**
     * 获取一个用于打开图片文件的intent
     */
    public static Intent getImageFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = fileToUri(file);
        intent.setDataAndType(uri, "image/*");

        return intent;
    }

    /**
     * 获取一个用于打开PDF文件的intent
     */
    public static Intent getPdfFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = fileToUri(file);
        intent.setDataAndType(uri, "application/pdf");

        return intent;
    }

    /**
     * 获取一个用于打开文本文件的intent
     */
    public static Intent getTextFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = fileToUri(file);
        intent.setDataAndType(uri, "text/plain");

        return intent;
    }

    /**
     * 获取一个用于打开音频文件的intent
     */
    public static Intent getAudioFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);

        Uri uri = fileToUri(file);
        intent.setDataAndType(uri, "audio/*");

        return intent;
    }

    /**
     * 获取一个用于打开视频文件的intent
     */
    public static Intent getVideoFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);

        Uri uri = fileToUri(file);
        intent.setDataAndType(uri, "video/*");

        return intent;
    }

    /**
     * 获取一个用于打开CHM文件的intent
     */
    public static Intent getChmFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = fileToUri(file);
        intent.setDataAndType(uri, "application/x-chm");

        return intent;
    }

    /**
     * 获取一个用于打开Word文件的intent
     */
    public static Intent getWordFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = fileToUri(file);
        intent.setDataAndType(uri, "application/msword");

        return intent;
    }

    /**
     * 获取一个用于打开Excel文件的intent
     */
    public static Intent getExcelFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = fileToUri(file);
        intent.setDataAndType(uri, "application/vnd.ms-excel");

        return intent;
    }

    /**
     * 获取一个用于打开PPT文件的intent
     */
    public static Intent getPptFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = fileToUri(file);
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");

        return intent;
    }

    /**
     * 获取一个用于打开apk文件的intent
     */
    public static Intent getApkFileIntent(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(fileToUri(file), "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * 通常情况下用于打开所有未知类型的文件
     */
    public static Intent getAllIntent(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        Uri uri = fileToUri(file);
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    public static Uri fileToUri(File file) {
        return fileToUri(null, file);
    }

    public static Uri fileToUri(Context context, File file) {
        return fileToUri(context, file, null);
    }

    public static Uri fileToUri(Context context, File file, String authority) {
        if (file == null) {
            return null;
        }

        if (context != null && authority != null && Build.VERSION.SDK_INT >= 24) {
            // Build.VERSION_CODES.N
            return FileProvider.getUriForFile(context, authority, file);
        } else {
            return Uri.fromFile(file);
        }
    }

    public static File uriToFile(Context context, final Uri uri, final String columnName) {
        if (uri == null) return null;
        CursorLoader cl = new CursorLoader(context);
        cl.setUri(uri);
        cl.setProjection(new String[]{columnName});
        Cursor cursor = cl.loadInBackground();
        int columnIndex = cursor.getColumnIndexOrThrow(columnName);
        cursor.moveToFirst();
        return new File(cursor.getString(columnIndex));
    }

}
