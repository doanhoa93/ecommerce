package com.framgia.bean;

import java.util.Date;

public class RecentInfo {
	private Integer id;
	private Integer productId;
	private Date createdAt;
	private ProductInfo product;

	public RecentInfo() {
	}

	public RecentInfo(Integer id, Integer productId) {
		this.id = id;
		this.productId = productId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public ProductInfo getProduct() {
		return product;
	}

	public void setProduct(ProductInfo product) {
		this.product = product;
	}
}
