package com.doug.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
  public static String toHex(byte bytes[]) {
    StringBuffer buffer = new StringBuffer();
    for (byte b : bytes) {
      buffer.append(String.format("%X", b));
    }

    return buffer.toString();
  }

  public static boolean isAlphanumeric(String str) {
    Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
    Matcher matcher = pattern.matcher(str);
    return matcher.matches();
  }
}
