package com.framgia.bean;

public class OrderProductInfo {
  private Integer id;
  private Integer orderId;
  private Integer productId;
  private float price;
  private int quantity;

  public OrderProductInfo() {
    super();
  }

  public OrderProductInfo(Integer id, Integer orderId, Integer productId, float price,
    int quantity) {
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

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
