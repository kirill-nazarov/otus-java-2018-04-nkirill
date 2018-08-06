package ru.otus.l5.tests;

import ru.otus.l5.framework.Assert;
import ru.otus.l5.framework.annotations.After;
import ru.otus.l5.framework.annotations.Before;
import ru.otus.l5.framework.annotations.Test;

public class TestOne {

    @Before
    public void startTest() {
        System.out.println("Test One Before annotation processed");
    }

    @Test
    public void assertTrueTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void assertFalseTest() {
        Assert.assertFalse(false);
    }

    @Test
    public void assertEqualsTest() {
        Assert.assertEquals("Test", "Test");
    }

    @After
    public void endTest() {
        System.out.println("Test One After annotation processed");
    }

}
