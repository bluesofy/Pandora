package cn.byk.pandora.libs.util.image;

import android.content.Context;

import com.bumptech.glide.Glide;

/**
 * Created by Byk on 2018/7/7.
 **/
public class GlideMgr {

    public static void cleanMemory(Context context) {
        Glide.get(context)
             .clearMemory();
    }

    public static void trimMemory(Context context, int level) {
        Glide.get(context)
             .onTrimMemory(level);
    }
}
