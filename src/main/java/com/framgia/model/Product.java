package com.framgia.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Product implements Serializable {
	private Integer id;
	private Category category;
	private boolean isPromotion;
	private Integer promotionId;
	private float saleOff;
	private String name;
	private float price;
	private float rating;
	private String avatar;
	private String information;
	private int number;
	private Date createdAt;
	private Recent recent;

	private List<Image> images;
	private List<OrderProduct> orderProducts;

	public Product() {
	}

	public Product(Integer id) {
		this.id = id;
	}

	public Product(Integer id, Category category, Boolean isPromotion, Integer promotionId, float saleOff, String name,
	        float price, float rating, String avatar, String information, int number) {
		this.id = id;
		this.category = category;
		this.isPromotion = isPromotion;
		this.promotionId = promotionId;
		this.saleOff = saleOff;
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

	public boolean getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(boolean isPromotion) {
		this.isPromotion = isPromotion;
	}

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public float getSaleOff() {
		return saleOff;
	}

	public void setSaleOff(float saleOff) {
		this.saleOff = saleOff;
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

	public Recent getRecent() {
		return recent;
	}

	public void setRecent(Recent recent) {
		this.recent = recent;
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
}
