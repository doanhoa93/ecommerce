package com.framgia.model;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
public class OrderProduct implements Serializable {
	private Integer id;

	@Field(type = FieldType.Object, ignoreFields = { "order" })
	@JsonIgnore
	private Order order;

	@Field(type = FieldType.Object, ignoreFields = { "product" })
	@JsonIgnore
	private Product product;
	private float price;
	private int quantity;
	private int status;

	public OrderProduct() {
		this.quantity = 1;
	}

	public OrderProduct(Integer id, Order order, Product product, float price, int quantity,
	    int status) {
		this.id = id;
		this.order = order;
		this.product = product;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
