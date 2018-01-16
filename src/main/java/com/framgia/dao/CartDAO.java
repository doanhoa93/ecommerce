package com.framgia.dao;

import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.CartProduct;
import com.framgia.model.User;

public interface CartDAO {
  User getUser(Integer cartId);

  List<CartProduct> getCartProducts(Integer cartId);

  List<Cart> getCarts(List<Integer> cartIds);
}
