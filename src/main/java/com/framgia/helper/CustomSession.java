package com.framgia.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.context.request.RequestContextHolder;

public class CustomSession {
	static final int TOKEN_LENGTH = 64;

	public static String current() {
		return RequestContextHolder.currentRequestAttributes().getSessionId();
	}

	public static String randomToken() {
		return RandomStringUtils.random(TOKEN_LENGTH, true, true);
	}
}
