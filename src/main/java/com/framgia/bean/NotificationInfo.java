package com.framgia.bean;

import java.util.Date;

public class NotificationInfo {
	private Integer id;
	private UserInfo user;
	private OrderInfo order;
	private String content;
	private boolean watched;
	private Integer userId;
	private Integer OrderId;
	private Date createdAt;

	public NotificationInfo() {
	}

	public NotificationInfo(Integer id, UserInfo user, OrderInfo order, String content) {
		this.id = id;
		this.user = user;
		this.order = order;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrderId() {
		return OrderId;
	}

	public void setOrderId(Integer orderId) {
		OrderId = orderId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
