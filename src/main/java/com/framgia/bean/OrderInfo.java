package com.framgia.bean;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	private Integer id;
	private Integer userId;
	private float totalPrice;
	private String status;
	private Date createdAt;
	private int productQuantity;
	private String phoneNumber;
	private String name;
	private String address;
	private String email;
	private String sessionId;
	private UserInfo user;
	private List<OrderProductInfo> orderProducts;
	private List<Integer> cartIds;

	public OrderInfo() {
	}

	public OrderInfo(Integer id) {
		this.id = id;
	}

	public OrderInfo(Integer id, Integer userId, float totalPrice, String status, Date createdAt) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getCartIds() {
		return cartIds;
	}

	public void setCartIds(List<Integer> cartIds) {
		this.cartIds = cartIds;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
