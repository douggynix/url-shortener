package com.doug.utils;

public class Utils {
    public static String toHex(byte bytes[]){
        StringBuffer buffer = new StringBuffer();
        for(byte b : bytes){
            buffer.append(String.format("%X",b));
        }

        return buffer.toString();
    }
}
