package com.framgia.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Profile implements Serializable {
	private Integer id;
	private User user;
	private int gender;
	private Date birthday;
	private String address;

	public Profile() {
		super();
	}

	public Profile(Integer id, User user) {
		super();
		this.id = id;
		this.user = user;
	}

	public Profile(Integer id, User user, int gender, Date birthday, String address) {
		super();
		this.id = id;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
}
