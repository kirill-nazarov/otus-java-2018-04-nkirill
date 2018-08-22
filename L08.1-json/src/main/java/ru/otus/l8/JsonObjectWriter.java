package ru.otus.l8;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public class JsonObjectWriter {

    public String toJson(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj.getClass().isArray()) {
            Object[] items = (Object[]) obj;
            List list = Arrays.asList(items);
            return convertToJson(list);
        }
        return convertToJson(obj);
    }

    private String convertToJson(Object obj) {
        if (isSimpleObject(obj)) {
            return JSONValue.toJSONString(obj);
        }
        return convertObjectToJson(obj);
    }

    private String convertObjectToJson(Object obj) {
        JSONObject jsonObject = new JSONObject();

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        LinkedHashMap map = new LinkedHashMap();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }

        return jsonObject.toJSONString(map);

    }


    private boolean isSimpleObject(Object obj) {
        if (obj instanceof String) {
            return true;
        } else if (obj instanceof Boolean) {
            return true;
        } else if (obj instanceof Character) {
            return true;
        } else if (obj instanceof Number) {
            return true;
        } else if (obj instanceof Collection) {
            return true;
        }
        return false;
    }


}
