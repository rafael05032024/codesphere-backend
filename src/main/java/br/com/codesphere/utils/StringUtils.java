package br.com.codesphere.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class StringUtils {

  public static String encodeBase64(String s) throws IllegalArgumentException {
    return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
  }

  public static String decodeBase64(String s) throws IllegalArgumentException {
    byte[] bytes = Base64.getDecoder().decode(s);

    return new String(bytes, StandardCharsets.UTF_8);
  }

  public static String removeAllNewLines(String s) {
    return s.replaceAll("\n", "");
  }

  public static boolean isBase64(String s) {
    if (Objects.isNull(s) || s.isBlank()) {
      return false;
    }

    try {
      decodeBase64(s);
    } catch (IllegalArgumentException ex) {
      return false;
    }

    return true;
  }

  public static int getSizeInBytes(String s) {
    return s.getBytes(StandardCharsets.UTF_8).length;
  }

}
