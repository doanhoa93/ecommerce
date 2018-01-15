package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Product implements Serializable {
  private Integer id;
  private Integer categoryId;
  private Boolean isPromotion;
  private Integer promotionId;
  private Float saleOf;
  private String name;
  private Float price;
  private Float rating;
  private String avatar;
  private String information;
  private Integer number;

  public Product() {
    super();
  }

  public Product(Integer id, Integer categoryId, Boolean isPromotion, Integer promotionId,
    Float saleOf, String name, Float price, Float rating, String avatar, String information,
    Integer number) {
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

  public Float getSaleOf() {
    return saleOf;
  }

  public void setSaleOf(Float saleOf) {
    this.saleOf = saleOf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Float getRating() {
    return rating;
  }

  public void setRating(Float rating) {
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

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }
}
