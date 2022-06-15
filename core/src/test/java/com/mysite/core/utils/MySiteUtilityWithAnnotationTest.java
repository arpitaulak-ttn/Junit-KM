package com.mysite.core.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MySiteUtilityWithAnnotationTest {
    MySiteUtility siteUtility = new MySiteUtility();

    @Test
    public void testAssertEqualsMethod(){
        String expected = "Adobe Experience Manager";
        String actual = siteUtility.getFormattedName("Adobe Experience Manager ");
        Assert.assertEquals("These strings should be equal", expected, actual);
    }

    @Test
    public void testAssertNotEqualsMethod(){
        String expected = "Adobe Experience Manager123";
        String actual = siteUtility.getFormattedName("Adobe Experience Manager ");
        Assert.assertNotEquals("These strings should not be equal", expected, actual);
    }

    @Test
    public void testAssertSameMethod(){
        String expected = "JunitKM";
        String actual = "JunitKM";
        Assert.assertSame("These strings should point to the same object", expected, actual);
    }

    @Test
    public void testAssertNotSameMethod(){
        String expected = "JunitKM";
        String actual = new String("JunitKM");
        Assert.assertNotSame("These strings should point to the same object", expected, actual);
    }

    @Test
    public void testAssertArraysEquals() {
        char[] expected = {'J','u','n','i','t'};
        char[] actual = "Junit".toCharArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testAssertTrue(){
        Assert.assertTrue("This should be true as the number 86 is even", siteUtility.isEven(86));
    }

    @Test
    public void testAssertFalse(){
        Assert.assertFalse("This should be true as the number 85 is odd", siteUtility.isEven(85));
    }

    @Test
    public void testAssertNull() {
        MySiteUtility siteUtility = null;
        Assert.assertNull("should be null", siteUtility);
    }

    @Test
    public void testAssertNotNull() {
        Assert.assertNotNull("should be null", siteUtility);
    }

    @Test(timeout = 100)
    public void testPerformance() throws InterruptedException {
        List<Integer> range = IntStream.range(1, 501).boxed().collect(Collectors.toList());
        siteUtility.sort(range);
    }

    @Test(expected = NullPointerException.class)
    public void testException() {
        siteUtility.sort(null);
    }
}
