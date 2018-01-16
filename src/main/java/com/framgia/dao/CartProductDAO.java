package com.framgia.dao;

import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.CartProduct;
import com.framgia.model.Product;

public interface CartProductDAO {
  Cart getCart(Integer cartProductId);

  Product getProduct(Integer cartProductId);

  List<CartProduct> getCartProducts(List<Integer> cartIds);
}
