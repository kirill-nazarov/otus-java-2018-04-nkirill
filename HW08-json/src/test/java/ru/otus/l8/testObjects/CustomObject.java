package ru.otus.l8.testObjects;

public class CustomObject {

    private String name;

    private Integer number;

    private String[] array;

    private A clazzA;

    public CustomObject(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public CustomObject(String name, Integer number, String[] array) {
        this.name = name;
        this.number = number;
        this.array = array;
    }

    public CustomObject(String name, Integer number, String[] array, A clazzA) {
        this.name = name;
        this.number = number;
        this.array = array;
        this.clazzA = clazzA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }
}
