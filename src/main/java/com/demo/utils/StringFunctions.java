package com.demo.utils;

public class StringFunctions {
    public static String substringAfterLast(String str, String separator) {
        if (str == null || separator == null || !str.contains(separator)) {
            return str;
        }
        return str.substring(str.lastIndexOf(separator) + 1);
    }
}
