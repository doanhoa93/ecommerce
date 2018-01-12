package com.framgia.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CartProduct implements Serializable {
  private Integer id;
  private Cart cart;
  private Product product;

  public CartProduct() {
    super();
  }

  public CartProduct(Integer id, Cart cart, Product product) {
    super();
    this.id = id;
    this.cart = cart;
    this.product = product;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
