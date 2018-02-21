package com.framgia.bean;

public class RateInfo {
	private Integer id;
	private int rating;
	private int userId;
	private int productId;
	private UserInfo user;
	private ProductInfo product;

	public RateInfo() {
	}

	public RateInfo(Integer id, int rating, int userId, int productId, UserInfo user, ProductInfo product) {
		this.id = id;
		this.rating = rating;
		this.userId = userId;
		this.productId = productId;
		this.user = user;
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public ProductInfo getProduct() {
		return product;
	}

	public void setProduct(ProductInfo product) {
		this.product = product;
	}
}
