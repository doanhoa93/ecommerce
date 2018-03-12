package com.framgia.model;

import java.util.Date;

public class Notification {
	private Integer id;
	private User user;
	private Order order;
	private String content;
	private boolean watched;
	private Date createdAt;

	public Notification() {
	}

	public Notification(Integer id, User user, Order order, String content, boolean watched) {
		this.id = id;
		this.user = user;
		this.order = order;
		this.content = content;
		this.watched = watched;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isWatched() {
		return watched;
	}

	public void setWatched(boolean watched) {
		this.watched = watched;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
