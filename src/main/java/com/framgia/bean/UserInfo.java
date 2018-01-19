package com.framgia.bean;

import com.framgia.model.Role;

public class UserInfo {
	private Integer id;
	private String email;
	private String password;
	private String name;
	private String avatar;
	private Role role;
	private ProfileInfo profileInfo;
	private boolean remember;

	public UserInfo() {
		super();
	}

	public UserInfo(Integer id, String email, String password, String name, String avatar, Role role,
	        ProfileInfo profileInfo) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.avatar = avatar;
		this.role = role;
		this.profileInfo = profileInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ProfileInfo getProfileInfo() {
		return profileInfo;
	}

	public void setProfileInfo(ProfileInfo profileInfo) {
		this.profileInfo = profileInfo;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}
}
