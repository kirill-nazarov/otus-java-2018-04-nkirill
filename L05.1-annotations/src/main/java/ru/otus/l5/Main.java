package ru.otus.l5;

import ru.otus.l5.framework.TestsRunner;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {

        // Run tests in  class
        TestsRunner.runTestsInClass("ru.otus.l5.tests.TestOne");
        // Run tests in package
        TestsRunner.runTestsInPackage("ru.otus.l5.tests");
    }

}
