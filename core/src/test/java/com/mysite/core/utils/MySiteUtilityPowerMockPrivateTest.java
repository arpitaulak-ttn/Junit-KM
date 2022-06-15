package com.mysite.core.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ MySiteUtility.class })
public class MySiteUtilityPowerMockPrivateTest {

    @Test
    public void testGetLuckyNumber() throws Exception {
        MySiteUtility siteUtility = PowerMockito.spy(new MySiteUtility());
        PowerMockito.doReturn(20).when(siteUtility, "getComputedLuckyNumber", 5);
        int number = siteUtility.getLuckyNumber("Arpit");
        Assert.assertEquals(20, number);
    }


}
