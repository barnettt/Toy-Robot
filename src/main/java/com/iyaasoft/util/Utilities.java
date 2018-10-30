package com.iyaasoft.util;

public class Utilities {

    public static final String END_OF_LINE = "\n";

    public static String removeEndLineCharFromString(String value) {
        StringBuilder buff = new StringBuilder(value);
        if (value.contains(END_OF_LINE)) {
            buff = new StringBuilder(value);
            buff.deleteCharAt(buff.indexOf(END_OF_LINE));
        }
        return buff.toString();
    }

}
