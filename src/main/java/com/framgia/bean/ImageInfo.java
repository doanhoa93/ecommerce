package com.framgia.bean;

public class ImageInfo {
	private Integer id;
	private Integer productId;
	private String image;
	private ProductInfo product;

	public ImageInfo() {
	}

	public ImageInfo(Integer id, Integer productId, String image) {
		this.id = id;
		this.productId = productId;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ProductInfo getProduct() {
		return product;
	}

	public void setProduct(ProductInfo product) {
		this.product = product;
	}
}
