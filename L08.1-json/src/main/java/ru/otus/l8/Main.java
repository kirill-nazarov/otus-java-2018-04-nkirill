package ru.otus.l8;

/*
ДЗ-08: JSON object writer
Напишите свой json object writer (object to JSON string) аналогичный gson на основе javax.json или simple-json и Reflection.
Поддержите массивы объектов и примитивных типов, и коллекции из стандартной библиотеки.
 */

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {

        //TODO 1 Write JsonWriter
        //TODO 2 Write JsonWriter Tests

        Gson gson = new Gson();
        System.out.println(gson.toJson(null));

        JsonWriter jsonWriter = new JsonWriter();
        System.out.println(jsonWriter.toJson(null));

    }

}
