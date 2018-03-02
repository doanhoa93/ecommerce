package com.framgia.bean;

import java.util.List;

public class UserInfo {
	private Integer id;
	private String email;
	private String password;
	private String name;
	private String avatar;
	private String role;
	private String gender;
	private boolean remember;
	private ProfileInfo profile;
	private int unWatchedNotifications;
	private List<OrderInfo> orders;
	private List<CartInfo> carts;
	private List<NotificationInfo> notifications;

	public UserInfo() {
	}

	public UserInfo(Integer id, String email, String password, String name, String gender, String avatar, String role,
	        ProfileInfo profile) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.avatar = avatar;
		this.role = role;
		this.profile = profile;
		this.gender = gender;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ProfileInfo getProfile() {
		return profile;
	}

	public void setProfile(ProfileInfo profile) {
		this.profile = profile;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<OrderInfo> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderInfo> orders) {
		this.orders = orders;
	}

	public List<CartInfo> getCarts() {
		return carts;
	}

	public void setCarts(List<CartInfo> carts) {
		this.carts = carts;
	}

	public List<NotificationInfo> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationInfo> notifications) {
		this.notifications = notifications;
	}

	public int getUnWatchedNotifications() {
		return unWatchedNotifications;
	}

	public void setUnWatchedNotifications(int unWatchedNotifications) {
		this.unWatchedNotifications = unWatchedNotifications;
	}
}
