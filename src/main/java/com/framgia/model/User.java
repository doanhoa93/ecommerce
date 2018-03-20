package com.framgia.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class User implements Serializable {
	private Integer id;
	private String email;
	private String password;
	private String name;
	private String avatar;
	private String role;
	private Date createdAt;
	private String token;
	private Profile profile;
	private List<Order> orders;
	private List<Cart> carts;
	private List<Notification> notifications;
	private List<Chat> chats;
	private List<Chat> sendedChats;

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(Integer id, String email, String password, String name, String avatar, String role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.avatar = avatar;
		this.role = role;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Chat> getChats() {
		return chats;
	}

	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}

	public List<Chat> getSendedChats() {
		return sendedChats;
	}

	public void setSendedChats(List<Chat> sendedChats) {
		this.sendedChats = sendedChats;
	}
}
