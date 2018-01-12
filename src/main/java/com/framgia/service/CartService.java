package com.framgia.service;

import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.CartProduct;
import com.framgia.model.Product;
import com.framgia.model.User;

public interface CartService extends BaseService {
  User getUser(Integer cartId);

  List<CartProduct> getCartProducts(Integer cartId);

  List<Cart> getCarts(List<Integer> cartIds);

  List<Product> getProducts(Integer cartId);
}
