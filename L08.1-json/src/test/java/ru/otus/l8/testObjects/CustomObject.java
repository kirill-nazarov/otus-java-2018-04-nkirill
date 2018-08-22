package ru.otus.l8.testObjects;

public class CustomObject {

    private String name;

    private Integer number;

    public CustomObject() {
    }

    public CustomObject(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
