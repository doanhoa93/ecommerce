package com.framgia.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Order implements Serializable {
  private Integer id;
  private User user;
  private float totalPrice;
  private StatusEnum status;
  private List<OrderProduct> orderProducts;

  public Order() {
    super();
  }

  public Order(Integer id) {
    super();
    this.id = id;
  }

  public Order(Integer id, User user, float totalPrice, StatusEnum status) {
    super();
    this.id = id;
    this.user = user;
    this.totalPrice = totalPrice;
    this.status = status;
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

  public float getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(float totalPrice) {
    this.totalPrice = totalPrice;
  }

  public int getStatus() {
    return status.ordinal();
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public List<OrderProduct> getOrderProducts() {
    return orderProducts;
  }

  public void setOrderProducts(List<OrderProduct> orderProducts) {
    this.orderProducts = orderProducts;
  }
}
