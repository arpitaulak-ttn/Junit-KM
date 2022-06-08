package com.mysite.core.utils;

import org.junit.*;

public class ExecutionTest {

    @BeforeClass
    public static void before_class() {
        System.out.println("This method is executed before ExecutionTest class");
    }

    @Before
    public void before() {
        System.out.println("This method is executed before every test method");
    }

    @After
    public void after() {
        System.out.println("This method is executed after every test method");
    }

    @AfterClass
    public static void after_class() {
        System.out.println("This method is executed before ExecutionTest class");
    }

    @Test
    public void testcase1() {
        System.out.println("Test case1");
    }

    @Test
    public void testcase2() {
        System.out.println("Test case2");
    }
}
