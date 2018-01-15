package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CartProduct implements Serializable {
  private Integer id;
  private Integer cartId;
  private Integer productId;

  public CartProduct() {
    super();
  }

  public CartProduct(Integer id, Integer cartId, Integer productId) {
    super();
    this.id = id;
    this.cartId = cartId;
    this.productId = productId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCartId() {
    return cartId;
  }

  public void setCartId(Integer cartId) {
    this.cartId = cartId;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }
}