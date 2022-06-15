package com.mysite.core.utils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {       ExecutionTest.class,
                MySiteUtilityBasicTest.class,
                MySiteUtilityWithAnnotationTest.class
        }
)
public class JunitSuiteTest {
}
