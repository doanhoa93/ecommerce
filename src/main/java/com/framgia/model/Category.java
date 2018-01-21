package com.framgia.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Category implements Serializable {
	private Integer id;
	private Integer parentId;
	private String name;
	private List<Product> products;

	public Category() {
		super();
	}

	public Category(Integer id, Integer parentId, String name) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
