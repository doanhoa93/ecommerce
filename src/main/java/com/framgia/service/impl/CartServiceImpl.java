package com.framgia.service.impl;

import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.CartProduct;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.CartService;

public class CartServiceImpl extends BaseServiceImpl implements CartService {

  @Override
  public User getUser(Integer cartId) {
    return getCartDAO().getUser(cartId);
  }

  @Override
  public List<CartProduct> getCartProducts(Integer cartId) {
    return getCartDAO().getCartProducts(cartId);
  }

  @Override
  public List<Cart> getCarts(List<Integer> cartIds) {
    return getCartDAO().getCarts(cartIds);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Product> getProducts(Integer cartId) {
    List<CartProduct> cartProducts = getCartDAO().getCartProducts(cartId);
    List<Integer> productIds = (List<Integer>) cartProducts.stream()
      .map(CartProduct::getProduct);
    return getProductDAO().getProducts(productIds);
  }
}
