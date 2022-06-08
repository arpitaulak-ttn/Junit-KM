package com.mysite.core.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    public String getFormattedName(String name){
        return StringUtils.trim(name).replaceAll("\\s+"," ");
    }
}
