package com.obid.jon;

public class Utils {
    public static boolean isNumber(char ch){
        return ch >= '0' && ch <= '9';
    }
    public static int toInt(char[] chars, int start, int end){
        return Integer.parseInt(String.valueOf(chars, start, end - start));
    }
}