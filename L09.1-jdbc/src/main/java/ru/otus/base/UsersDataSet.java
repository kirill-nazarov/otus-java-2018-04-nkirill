package ru.otus.base;

import java.util.Objects;

public class UsersDataSet extends DataSet {

    private String name;
    private Integer age;

    public UsersDataSet() {
    }

    public UsersDataSet(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersDataSet)) return false;
        UsersDataSet that = (UsersDataSet) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAge(), that.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", age=" + age +
                '}';
    }
}
