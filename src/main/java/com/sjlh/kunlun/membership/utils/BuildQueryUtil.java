package com.sjlh.kunlun.membership.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Auther: HR
 * @Date 2022/3/24 12:05
 * @Description:
 */
public class BuildQueryUtil {

    public static String buildQuery(String target, Object param) {
        StringBuilder builder = new StringBuilder();
        if(!(target==null || target.trim().equals("")))builder.append(target);
        builder.append(buildQuery(param));
        return builder.toString();
    }

    private static StringBuilder buildQuery(Object param) {
        List<KeyValue>
                list = buildKeyValues(param);
        StringBuilder builder = new StringBuilder();
        list.stream().forEach((k)->{if(builder.length()>0)builder.append("&");builder.append(k.key).append("=").append(k.value);});
        if(builder.length()>0)builder.insert(0, "?");
        return builder;
    }

    private static List<KeyValue> buildKeyValues(Object param){
        List<KeyValue> list = new ArrayList<>();
        if(param==null)return list;
        if(Map.class.isAssignableFrom(param.getClass())) {
            Map<?, ?> map = (Map<?, ?>)param;
            Iterator<?>
                    ite = map.keySet().iterator();
            while(ite.hasNext()) {
                Object key = ite.next();
                Object v = map.get(key);
                if(v==null)continue;
                list.add(new KeyValue(key.toString(), v.toString()));
            }
        }else if(param.getClass().isArray())return list;
        else{
            Method[] methods = param.getClass().getDeclaredMethods();
            for(Method m: methods) {
                String methodName = m.getName();
                if(methodName.startsWith("get") && methodName.length()>3) {
                    String key = methodName.substring(3);
                    key = Character.toLowerCase(key.charAt(0))+key.substring(1);
                    String value = null;
                    try {
                        Object v = m.invoke(param, new Object[] {});
                        if(v!=null) {
                            if(v.getClass().isPrimitive()) {
                                value = String.valueOf(v);
                            }else {
                                value = v.toString();
                            }
                        }
                        if(value!=null) {
                            list.add(new KeyValue(key, value));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return list;
    }

    static class KeyValue{
        String key;
        String value;

        KeyValue(String key, String value){
            this.key = key;
            this.value = value;
        }
    }
}
