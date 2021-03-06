package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cart implements Serializable {
	private Integer id;
	private User user;
	private String sessionId;
	private Product product;
	private int quantity;

	public Cart() {
		this.quantity = 1;
	}

	public Cart(Integer id, User user, Product product, int quantity) {
		this.id = id;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
