package com.framgia.bean;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductInfo {
	private Integer id;
	private Integer categoryId;
	private Integer promotionId;
	private String name;
	private float price;
	private float rating;
	private String avatar;
	private MultipartFile avatarFile;
	private String information;
	private int number;
	private CategoryInfo category;
	private PromotionInfo promotion;
	private Date createdAt;
	private List<ImageInfo> images;
	private List<OrderProductInfo> orderProducts;
	private List<MultipartFile> imageFiles;
	private List<Integer> imagesStatus;
	private List<Integer> imageIds;

	public ProductInfo() {
	}

	public ProductInfo(Integer id, Integer categoryId, Integer promotionId, String name,
	    float price, float rating, String avatar, String information, int number) {
		this.id = id;
		this.categoryId = categoryId;
		this.promotionId = promotionId;
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
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

	public CategoryInfo getCategory() {
		return category;
	}

	public void setCategory(CategoryInfo category) {
		this.category = category;
	}

	public List<ImageInfo> getImages() {
		return images;
	}

	public void setImages(List<ImageInfo> images) {
		this.images = images;
	}

	public MultipartFile getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(MultipartFile avatarFile) {
		this.avatarFile = avatarFile;
	}

	public PromotionInfo getPromotion() {
		return promotion;
	}

	public void setPromotion(PromotionInfo promotion) {
		this.promotion = promotion;
	}

	public List<MultipartFile> getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(List<MultipartFile> imageFiles) {
		this.imageFiles = imageFiles;
	}

	public List<Integer> getImagesStatus() {
		return imagesStatus;
	}

	public void setImagesStatus(List<Integer> imagesStatus) {
		this.imagesStatus = imagesStatus;
	}

	public List<Integer> getImageIds() {
		return imageIds;
	}

	public void setImageIds(List<Integer> imageIds) {
		this.imageIds = imageIds;
	}

	public List<OrderProductInfo> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProductInfo> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
