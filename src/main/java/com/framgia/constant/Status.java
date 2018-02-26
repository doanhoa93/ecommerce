package com.framgia.constant;

public interface Status {
	final String WAITING = "WAITING";
	final String ACCEPT = "ACCEPT";
	final String REJECT = "REJECT";
	final String CANCEL = "CANCEL";
	final String[] statuses = { "WAITING", "ACCEPT", "REJECT", "CANCEL" };

	static int getIntStatus(String status) {
		for (int i = 0; i < statuses.length; i++)
			if (statuses[i].equals(status))
				return i;

		return -1;
	}

	static String getStrStatus(int status) {
		try {
			return statuses[status];
		} catch (Exception e) {
			return null;
		}
	}
}
