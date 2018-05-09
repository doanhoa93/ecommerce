package com.framgia.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Document(indexName = "categories")
public class Category implements Serializable {
	private Integer id;
	private Integer parentId;
	private String name;
	private Date createdAt;

	@Field(type = FieldType.Nested, ignoreFields = { "products" })
	@JsonIgnore
	private List<Product> products;

	public Category() {
	}

	public Category(Integer id) {
		this.id = id;
	}

	public Category(Integer id, Integer parentId, String name) {
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\"}";
	}
}
