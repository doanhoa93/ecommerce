package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Rate implements Serializable {
	private Integer id;
	private User user;
	private Product product;
	private int rating;

	public Rate() {
	}

	public Rate(Integer id, User user, Product product, int rating) {
		this.id = id;
		this.user = user;
		this.product = product;
		this.rating = rating;
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
