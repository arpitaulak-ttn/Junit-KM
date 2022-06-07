package com.mysite.core.utils;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {

    StringUtil stringUtil;

    @Before
    public void before(){
        stringUtil = new StringUtil();
    }

    @Test
    public void testGetShowNameMethod(){
        assertEquals("ram kumar gupta", stringUtil.getShownName("  ram   kumar   gupta  "));
    }
}
