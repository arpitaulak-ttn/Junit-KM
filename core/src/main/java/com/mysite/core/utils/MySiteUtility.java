package com.mysite.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MySiteUtility {

    public String getFormattedName(String name){
        return StringUtils.trim(name).replaceAll("\\s+"," ");
    }

    public List<Integer> sort(List<Integer> values) {
        return values.stream().sorted().collect(Collectors.toList());
    }

    public int multiply(int a, int b){
        return a * b;
    }

    public float divide(int a, int b){
        return (float) a / b;
    }

    public boolean isOdd(int a){
        return a % 2 != 0;
    }

    public boolean isEven(int a){
        return a % 2 == 0;
    }

    public int getLuckyNumber(String name) {
        if (name == null) {
            return getDefaultLuckyNumber();
        }
        return getComputedLuckyNumber(name.length());
    }

    private int getDefaultLuckyNumber(){
        return 1;
    }

    private int getComputedLuckyNumber(int length){
        Random random = new Random();
        return random.nextInt(length);
    }

}
