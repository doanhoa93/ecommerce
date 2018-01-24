package com.framgia.bean;

import java.util.Date;

public class OrderInfo {
	private Integer id;
	private Integer userId;
	private float totalPrice;
	private int status;
	private Date createdAt;
	private int getProductQuantity;
	
	public OrderInfo() {
		super();
	}

	public OrderInfo(Integer id) {
		super();
		this.id = id;
	}

	public OrderInfo(Integer id, Integer userId, float totalPrice, int status, Date createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.status = status;
		this.createdAt = createdAt;
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

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getProductQuantity() {
		return getProductQuantity;
	}

	public void setProductQuantity(int getProductQuantity) {
		this.getProductQuantity = getProductQuantity;
	}
}
