package cn.byk.pandora.libs.util;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Byk on 2018/7/1.
 **/
public class JsonUtil {

    public static String toString(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T toObject(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static JSONObject toJsonObj(String jsonStr) {
        return JSON.parseObject(jsonStr);
    }

    public static boolean isNull(JSONObject jsonObj, String key) {
        return TextUtils.isEmpty(jsonObj.getString(key));
    }

    public static int getIntValue(String jsonStr, String key) {
        if (TextUtils.isEmpty(jsonStr)) {
            return -1;
        }

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        if (jsonObject.containsKey(key)) {
            return jsonObject.getIntValue(key);
        } else {
            return -1;
        }
    }

    public static int getIntValue(JSONObject jsonObj, String key) {
        if (jsonObj.containsKey(key)) {
            return jsonObj.getIntValue(key);
        } else {
            return -1;
        }
    }

    public static String getJsonValue(String jsonStr, String key) {
        if (TextUtils.isEmpty(jsonStr)) {
            return null;
        }

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        return jsonObject.getString(key);
    }
}
