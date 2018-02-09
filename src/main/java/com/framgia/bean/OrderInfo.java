package com.framgia.bean;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	private Integer id;
	private Integer userId;
	private float totalPrice;
	private int status;
	private Date createdAt;
	private int productQuantity;
	private UserInfo user;
	private List<OrderProductInfo> orderProducts;

	public OrderInfo() {
		super();
	}

	public OrderInfo(Integer id) {
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
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public List<OrderProductInfo> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProductInfo> orderProducts) {
		this.orderProducts = orderProducts;
	}
}
