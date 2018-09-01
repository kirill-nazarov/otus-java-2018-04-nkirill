package ru.otus.l8;

/*
ДЗ-08: JSON object writer
Напишите свой json object writer (object to JSON string) аналогичный gson на основе javax.json или simple-json и Reflection.
Поддержите массивы объектов и примитивных типов, и коллекции из стандартной библиотеки.
 */

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {

        Gson gson = new Gson();
        System.out.println(gson.toJson("Some text"));

        JsonObjectWriter jsonWriter = new JsonObjectWriter();
        System.out.println(jsonWriter.toJson("Some text"));

    }

}
