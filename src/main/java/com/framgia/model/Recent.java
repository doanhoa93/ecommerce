package com.framgia.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Recent implements Serializable {
	private Integer id;
	private Date createdAt;
	private Product product;

	public Recent() {
	}

	public Recent(Integer id, Product product) {
		this.id = id;
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
