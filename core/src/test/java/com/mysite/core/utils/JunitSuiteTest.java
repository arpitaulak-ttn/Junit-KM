package com.mysite.core.utils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {       ExecutionTest.class,
                StringUtilBasicTest.class,
                StringUtilWithAnnotationTest.class
        }
)
public class JunitSuiteTest {
}
