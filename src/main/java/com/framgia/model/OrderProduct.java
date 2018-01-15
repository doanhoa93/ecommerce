package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrderProduct implements Serializable {
  private Integer id;
  private Integer orderId;
  private Integer productId;
  private Float price;
  private Integer quantity;

  public OrderProduct() {
    super();
  }

  public OrderProduct(Integer id, Integer orderId, Integer productId, Float price,
    Integer quantity) {
    super();
    this.id = id;
    this.orderId = orderId;
    this.productId = productId;
    this.price = price;
    this.quantity = quantity;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
