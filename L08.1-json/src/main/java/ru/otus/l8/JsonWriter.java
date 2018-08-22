package ru.otus.l8;

import org.json.simple.JSONValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JsonWriter {

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
        } else return convertObjectToJson(obj);
    }

    private String convertObjectToJson(Object obj) {
        //not implemented
        return null;
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
