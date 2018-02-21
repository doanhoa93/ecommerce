package com.framgia.bean;

import java.util.Date;

public class ProfileInfo {
	private Integer id;
	private Integer userId;
	private int gender;
	private Date birthday;
	private String address;
	private UserInfo user;

	public ProfileInfo() {
	}

	public ProfileInfo(Integer id, Integer userId, int gender, Date birthday, String address) {
		this.id = id;
		this.userId = userId;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}
}
