package cn.byk.pandora.libs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Byk on 2018/7/1.
 **/
public class ListUtil {

    public static boolean checkNull(List list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean checkMapNull(Map map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        return false;
    }

    public static List transToList(Map map) {
        List list = new ArrayList<>();
        Set<Map.Entry> set = map.entrySet();
        for (Map.Entry entry : set) {
            list.add(entry.getValue());
        }
        return list;
    }

    public static int size(List list) {
        if (checkNull(list)) {
            return 0;
        } else {
            return list.size();
        }
    }
}
