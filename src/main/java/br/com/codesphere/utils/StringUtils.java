package br.com.codesphere.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class StringUtils {

  public static String convertToBase64(String value) {
    return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
  }

  public static String removeAllBlankSpaces(String value) {
    return value.replaceAll("\\s+", "");
  }

}
