package com.mysite.core.utils;

import junit.framework.TestCase;

public class MySiteUtilityBasicTest extends TestCase {

    //Class Under Test
    MySiteUtility stringUtil = new MySiteUtility();;

    public void testGetShowNameMethod_BlankSpaceAtBothEnd(){
        assertEquals("ram gupta", stringUtil.getFormattedName("  ram gupta  "));
    }

    public void testGetShowNameMethod_BlankSpaceAtStarting(){
        assertEquals("ram gupta", stringUtil.getFormattedName("  ram gupta"));
    }

    public void testGetShowNameMethod_BlankSpaceAtMiddle(){
        assertEquals("ram gupta", stringUtil.getFormattedName("ram     gupta"));
    }

    public void testGetShowNameMethod_BlankSpaceAtEnd(){
        assertEquals("ram gupta", stringUtil.getFormattedName("ram gupta  "));
    }

    public void testGetShowNameMethod_BlankSpaceAtStartAndMiddle(){
        assertEquals("ram gupta", stringUtil.getFormattedName("  ram    gupta"));
    }

    public void testGetShowNameMethod_BlankSpaceAtStartAndEnd(){
        assertEquals("ram gupta", stringUtil.getFormattedName("   ram gupta  "));
    }
}
