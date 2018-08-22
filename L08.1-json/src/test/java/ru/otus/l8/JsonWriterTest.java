package ru.otus.l8;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class JsonWriterTest {

    private Gson gson = new Gson();
    private JsonWriter jsonWriter = new JsonWriter();

    @Test
    public void writeNullObject() {
        assertEquals(gson.toJson(null), jsonWriter.toJson(null));
    }

}
