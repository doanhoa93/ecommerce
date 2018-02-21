package com.framgia.bean;

public class CommentInfo {
	private Integer id;
	private Integer userId;
	private Integer productId;
	private String content;
	private UserInfo user;
	private ProductInfo product;

	public CommentInfo() {
	}

	public CommentInfo(Integer id, Integer userId, Integer productId, String content) {
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
