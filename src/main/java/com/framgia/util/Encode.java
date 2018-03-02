package com.framgia.util;

import java.util.Base64;

import org.apache.commons.lang3.RandomStringUtils;

public class Encode {
	static final int TOKEN_LENGTH = 64;

	public static String encode(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes());
	}

	public static String decode(String str) {
		return new String(Base64.getDecoder().decode(str));
	}

	public static String generateToken() {
		return RandomStringUtils.random(TOKEN_LENGTH, true, true);
	}
}
