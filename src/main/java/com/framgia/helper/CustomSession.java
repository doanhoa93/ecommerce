package com.framgia.helper;

import org.springframework.web.context.request.RequestContextHolder;

public class CustomSession {
	public static String current() {
		return RequestContextHolder.currentRequestAttributes().getSessionId();
	}
}
