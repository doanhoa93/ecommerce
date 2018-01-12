package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Order implements Serializable {
  private Integer id;
  private Integer userId;
  private Float totalPrice;
  private StatusEnum status;

  public Order() {
    super();
  }

  public Order(Integer id) {
    super();
    this.id = id;
  }

  public Order(Integer id, Integer userId, Float totalPrice, StatusEnum status) {
    super();
    this.id = id;
    this.userId = userId;
    this.totalPrice = totalPrice;
    this.status = status;
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

  public Float getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Float totalPrice) {
    this.totalPrice = totalPrice;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }
}
