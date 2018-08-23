package ru.otus.l8;

import com.google.gson.Gson;
import org.junit.Test;
import ru.otus.l8.testObjects.A;
import ru.otus.l8.testObjects.CustomObject;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class JsonWriterTest {

    private Gson gson = new Gson();
    private JsonObjectWriter jsonWriter = new JsonObjectWriter();

    @Test
    public void writeNullObject() {
        assertEquals(gson.toJson(null), jsonWriter.toJson(null));
    }

    @Test
    public void writePrimitiveType() {
        assertEquals(gson.toJson(42), jsonWriter.toJson(42));
    }

    @Test
    public void writeBoolean() {
        assertEquals(gson.toJson(true), jsonWriter.toJson(true));
        assertEquals(gson.toJson(false), jsonWriter.toJson(false));
    }

    @Test
    public void writeString() {
        assertEquals(gson.toJson("Test String"), jsonWriter.toJson("Test String"));
    }

    @Test
    public void writeStringList() {
        List<String> strings = Arrays.asList("String 1", "String 2", "String 3");
        assertEquals(gson.toJson(strings), jsonWriter.toJson(strings));
    }

    @Test
    public void writeIntegerList() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        assertEquals(gson.toJson(integers), jsonWriter.toJson(integers));
    }

    @Test
    public void writeStringArray() {
        String[] array = {"String 1", "String 2", "String 3"};
        assertEquals(gson.toJson(array), jsonWriter.toJson(array));
    }

    @Test
    public void writeCustomObject() {
        String[] array = {"String 1", "String 2", "String 3"};
        A someClass = new A("abc", 123);
        CustomObject object = new CustomObject("New Name", 1995, array, someClass);
        System.out.println(gson.toJson(object));
        System.out.println(jsonWriter.toJson(object));
        assertEquals(gson.toJson(object), jsonWriter.toJson(object));
    }


}
