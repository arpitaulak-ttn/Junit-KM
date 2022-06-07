package com.mysite.core.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    public String getShownName(String name){
        return StringUtils.trim(name).replaceAll("\\s+"," ");
    }
}
