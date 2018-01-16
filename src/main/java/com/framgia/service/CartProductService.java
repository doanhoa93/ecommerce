package com.framgia.service;

import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.CartProduct;
import com.framgia.model.Product;
import com.framgia.model.User;

public interface CartProductService extends BaseService {
  User getUser(Integer cartProductId);

  Cart getCart(Integer cartProductId);

  Product getProduct(Integer cartProductId);

  List<CartProduct> getCartProducts(List<Integer> cartIds);
}
