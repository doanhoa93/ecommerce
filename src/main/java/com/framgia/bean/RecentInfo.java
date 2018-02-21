package com.framgia.bean;

public class RecentInfo {
	private Integer id;
	private Integer productId;
	private int viewed;
	private ProductInfo product;

	public RecentInfo() {
	}

	public RecentInfo(Integer id, Integer productId, int viewed) {
		this.id = id;
		this.productId = productId;
		this.viewed = viewed;
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

	public int getViewed() {
		return viewed;
	}

	public void setViewed(int viewed) {
		this.viewed = viewed;
	}

	public ProductInfo getProduct() {
		return product;
	}

	public void setProduct(ProductInfo product) {
		this.product = product;
	}
}
