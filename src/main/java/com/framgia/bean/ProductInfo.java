package com.framgia.bean;

public class ProductInfo {
  private Integer id;
  private Integer categoryId;
  private Boolean isPromotion;
  private Integer promotionId;
  private float saleOf;
  private String name;
  private float price;
  private float rating;
  private String avatar;
  private String information;
  private int number;

  public ProductInfo() {
    super();
  }

  public ProductInfo(Integer id, Integer categoryId, Boolean isPromotion, Integer promotionId,
    float saleOf, String name, float price, float rating, String avatar, String information,
    int number) {
    super();
    this.id = id;
    this.categoryId = categoryId;
    this.isPromotion = isPromotion;
    this.promotionId = promotionId;
    this.saleOf = saleOf;
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

  public Boolean getIsPromotion() {
    return isPromotion;
  }

  public void setIsPromotion(Boolean isPromotion) {
    this.isPromotion = isPromotion;
  }

  public Integer getPromotionId() {
    return promotionId;
  }

  public void setPromotionId(Integer promotionId) {
    this.promotionId = promotionId;
  }

  public float getSaleOf() {
    return saleOf;
  }

  public void setSaleOf(float saleOf) {
    this.saleOf = saleOf;
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
}
