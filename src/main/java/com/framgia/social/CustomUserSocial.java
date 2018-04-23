package com.framgia.social;

import com.restfb.types.User;

public class CustomUserSocial extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String password;

	public CustomUserSocial() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
