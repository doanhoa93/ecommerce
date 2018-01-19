package com.framgia.bean;

public class CartInfo {
	private Integer id;
	private Integer userId;
	private Integer productId;
	private int quantity;

	public CartInfo() {
		super();
	}

	public CartInfo(Integer id, Integer userId, Integer productId) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
