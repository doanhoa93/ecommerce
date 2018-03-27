package com.framgia.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Suggest implements Serializable {
	private Integer id;
	private User user;
	private String name;
	private String information;
	private String category;
	private String avatar;
	private float price;
	private Date createdAt;
	private int status;

	public Suggest() {
	}

	public Suggest(Integer id, User user, String name, String information, String category, String avatar, float price,
	        Date createdAt) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.information = information;
		this.category = category;
		this.avatar = avatar;
		this.price = price;
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
