package com.framgia.bean;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class SuggestInfo {
	private Integer id;
	private Integer userId;
	private String name;
	private String information;
	private String category;
	private String avatar;
	private float price;
	private Date createdAt;
	private int status;
	private MultipartFile avatarFile;

	public SuggestInfo(Integer id, Integer userId, String name, String information, String category, String avatar,
	        float price, Date createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.information = information;
		this.category = category;
		this.avatar = avatar;
		this.price = price;
		this.createdAt = createdAt;
	}

	public SuggestInfo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public MultipartFile getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(MultipartFile avatarFile) {
		this.avatarFile = avatarFile;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
