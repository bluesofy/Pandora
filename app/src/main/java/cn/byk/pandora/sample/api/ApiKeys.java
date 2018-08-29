package cn.byk.pandora.sample.api;

import android.content.Context;

import cn.byk.pandora.sample.R;

/**
 * Created by Byk on 2018/8/27.
 **/
public class ApiKeys {

    public static final String AUTH_CONNECT_API_CENTER = "";
    public static final String AUTH_LOGIN = "";

    public static String getGlobalBaseUrl(Context context) {
        StringBuilder sBuilder = new StringBuilder(context.getString(R.string.txt_ic_address));
        sBuilder.append(":");
        sBuilder.append(context.getString(R.string.txt_ic_port));
        return sBuilder.toString();
    }
}
