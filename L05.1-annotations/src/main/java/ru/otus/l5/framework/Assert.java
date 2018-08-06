package ru.otus.l5.framework;

public class Assert {

    public static void assertTrue(boolean condition) {
        if (!condition)
            throw new TestException("Assert True exception");
    }

    public static void assertFalse(boolean condition) {
        if (condition)
            throw new TestException("Assert False exception");
    }

    public static void assertEquals(final Object expected, final Object actual) {
        if (!actual.equals(expected)) {
            throw new TestException("Assert Equals Exception." + "Expected: " + expected + ", but was: " + actual);
        }
    }

}
