package com.framgia.constant;

public interface Status {
	final int WAITING = 0;
	final int ACCEPT = 1;
	final int REJECT = 2;
	final int CANCEL = 3;
	final String[] statuses = { "WAITING", "ACCEPT", "REJECT", "CANCEL" };
}
