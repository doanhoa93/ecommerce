package com.framgia.util;

import java.util.Base64;

public class Encode {
  public static String encode(String str) {
    return Base64.getEncoder().encodeToString(str.getBytes());
  }
}
