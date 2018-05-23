package com.framgia.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Document(indexName = "products")
public class Product implements Serializable {
	private Integer id;

	@Field(type = FieldType.Object, ignoreFields = { "category" })
	@JsonIgnore
	private Category category;

	@Field(type = FieldType.Object, ignoreFields = { "promotion" })
	@JsonIgnore
	private Promotion promotion;
	
	private String name;
	private float price;
	private float rating;
	private String avatar;
	private String information;
	private int number;
	private Date createdAt;

	@Field(type = FieldType.Nested, ignoreFields = { "images" })
	@JsonIgnore
	private List<Image> images;

	@Field(type = FieldType.Nested, ignoreFields = { "orderProducts" })
	@JsonIgnore
	private List<OrderProduct> orderProducts;

	public Product() {
	}

	public Product(Integer id) {
		this.id = id;
	}

	public Product(Integer id, Category category, String name, float price, float rating,
	    String avatar, String information, int number) {
		this.id = id;
		this.category = category;
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.avatar = avatar;
		this.information = information;
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\",\"information\":\"" + information + "\"}";
	}
}
