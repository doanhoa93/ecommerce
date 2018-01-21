package com.framgia.bean;

public class OrderInfo {
	private Integer id;
	private Integer userId;
	private float totalPrice;
	private int status;

	public OrderInfo() {
		super();
	}

	public OrderInfo(Integer id) {
		super();
		this.id = id;
	}

	public OrderInfo(Integer id, Integer userId, float totalPrice, int status) {
		super();
		this.id = id;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.status = status;
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
}
