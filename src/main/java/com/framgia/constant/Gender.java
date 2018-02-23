package com.framgia.constant;

public interface Gender {
	final String MALE = "MALE";
	final String FEMALE = "FEMALE";
	final String[] genders = { "MALE", "FEMALE" };

	static int getInt(String gender) {
		for (int i = 0; i < genders.length; i++)
			if (genders[i].equals(gender))
				return i;
		return -1;
	}

	static String getStr(int gender) {
		try {
			return genders[gender];
		} catch (Exception e) {
			return null;
		}
	}
}
