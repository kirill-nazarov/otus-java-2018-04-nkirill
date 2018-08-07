package ru.otus.l5.tests;

import ru.otus.l5.framework.Assert;
import ru.otus.l5.framework.TestException;
import ru.otus.l5.framework.annotations.After;
import ru.otus.l5.framework.annotations.Before;
import ru.otus.l5.framework.annotations.Test;

public class TestOne {

    @Before
    public void startTest() {
        System.out.println("Test One Before annotation processed");
    }

    @Test
    public void assertTrueTest() throws TestException {
        Assert.assertTrue(true);
    }

    @Test
    public void assertFalseTest() throws TestException {
        Assert.assertFalse(false);
    }

    @Test
    public void assertEqualsTest() throws TestException {
        Assert.assertEquals("Test", "Test");
    }

    @After
    public void endTest() {
        System.out.println("Test One After annotation processed");
    }

}
