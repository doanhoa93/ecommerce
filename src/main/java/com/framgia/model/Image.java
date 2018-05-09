package com.framgia.model;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
public class Image implements Serializable {
	private Integer id;

	@Field(type = FieldType.Object, ignoreFields = { "product" })
	@JsonIgnore
	private Product product;
	private String image;

	public Image() {
	}

	public Image(Integer id, Product product, String image) {
		this.id = id;
		this.product = product;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
