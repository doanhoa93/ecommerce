package com.framgia.constant;

public interface Status {
	final int WAITING = 0;
	final int ACCEPT = 1;
	final int REJECT = 2;
	final int CANCEL = 3;
	final String[] statuses = { "WAITING", "ACCEPT", "REJECT", "CANCEL" };

	public static int getIntStatus(String status) {
		for (int i = 0; i < statuses.length; i++)
			if (statuses[i].equals(status))
				return i;

		return -1;
	}

	public static String getStrStatus(int status) {
		try {
			return statuses[status];
		} catch (Exception e) {
			return null;
		}
	}
}
