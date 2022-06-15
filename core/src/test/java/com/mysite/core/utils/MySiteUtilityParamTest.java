package com.mysite.core.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MySiteUtilityParamTest {
    private final String inputNumber;
    private final String expectedResult;
    private MySiteUtility mySiteUtility;

    @Before
    public void initialize() {
        mySiteUtility = new MySiteUtility();
    }

    // Each parameter should be placed as an argument here
    // Every time runner triggers, it will pass the arguments
    // from parameters we defined in primeNumbers() method

    public MySiteUtilityParamTest(String inputNumber, String expectedResult) {
        this.inputNumber = inputNumber;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParamTestData() {
        return Arrays.asList(new Object[][] {
                { "  ram gupta  ", "ram gupta" },
                { "  ram gupta", "ram gupta" },
                { "ram     gupta", "ram gupta" },
                { "ram gupta  ", "ram gupta" },
                { "  ram    gupta", "ram gupta" },
                { "   ram gupta  ", "ram gupta" },
        });
    }

    @Test
    public void testGetShowNameMethod_Param() {
        Assert.assertEquals(expectedResult,
                mySiteUtility.getFormattedName(inputNumber));
    }
}
