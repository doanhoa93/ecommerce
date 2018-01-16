package com.framgia.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Cart implements Serializable {
  private Integer id;
  private User user;
  private List<CartProduct> cartProducts;

  public Cart() {
    super();
  }

  public Cart(Integer id, User user) {
    super();
    this.id = id;
    this.user = user;
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

  public List<CartProduct> getCartProducts() {
    return cartProducts;
  }

  public void setCartProducts(List<CartProduct> cartProducts) {
    this.cartProducts = cartProducts;
  }
}
