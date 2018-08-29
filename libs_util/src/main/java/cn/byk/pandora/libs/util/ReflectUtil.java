package cn.byk.pandora.libs.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Byk on 2018/7/1.
 **/
public class ReflectUtil {

    public static <T> Type findNeedClass(Class<T> cls) {
        Type finalNeedType;
        Type genType = cls.getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            finalNeedType = params[0];
        } else {
            finalNeedType = genType;
        }
        return finalNeedType;
    }

    public static <T> Type findNeedType(Class<T> cls) {
        List<Type> typeList = getMethodTypes(cls);
        if (typeList != null && !typeList.isEmpty()) {
            return typeList.get(0);
        }
        return null;
    }

    public static <T> Type findRawType(Class<T> cls) {
        Type genType = cls.getGenericSuperclass();
        return getGenericType((ParameterizedType) genType, 0);
    }

    public static Type getGenericType(ParameterizedType parameterizedType, int i) {
        Type genericType = parameterizedType.getActualTypeArguments()[i];
        if (genericType instanceof ParameterizedType) {
            return ((ParameterizedType) genericType).getRawType();
        } else if (genericType instanceof GenericArrayType) {
            return ((GenericArrayType) genericType).getGenericComponentType();
        } else if (genericType instanceof TypeVariable) {
            return getClass(((TypeVariable) genericType).getBounds()[0], 0);
        } else {
            return genericType;
        }
    }

    public static Class getGenericClass(ParameterizedType parameterizedType, int i) {
        Type genericClass = parameterizedType.getActualTypeArguments()[i];
        if (genericClass instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) genericClass).getRawType();
        } else if (genericClass instanceof GenericArrayType) {
            return (Class) ((GenericArrayType) genericClass).getGenericComponentType();
        } else if (genericClass instanceof TypeVariable) {
            return getClass(((TypeVariable) genericClass).getBounds()[0], 0);
        } else {
            return (Class) genericClass;
        }
    }

    public static Class getClass(Type type, int i) {
        if (type instanceof ParameterizedType) {
            return getGenericClass((ParameterizedType) type, i);
        } else if (type instanceof TypeVariable) {
            return getClass(((TypeVariable) type).getBounds()[0], 0);
        } else {
            return (Class) type;
        }
    }

    public static <T> List<Type> getMethodTypes(Class<T> cls) {
        Type typeOri = cls.getGenericSuperclass();
        List<Type> needTypes = null;
        if (typeOri instanceof ParameterizedType) {
            needTypes = new ArrayList<>();
            Type[] parentTypes = ((ParameterizedType) typeOri).getActualTypeArguments();
            for (Type childType : parentTypes) {
                needTypes.add(childType);
                if (childType instanceof ParameterizedType) {
                    Type[] childTypes = ((ParameterizedType) childType).getActualTypeArguments();
                    Collections.addAll(needTypes, childTypes);
                }
            }
        }
        return needTypes;
    }
}
