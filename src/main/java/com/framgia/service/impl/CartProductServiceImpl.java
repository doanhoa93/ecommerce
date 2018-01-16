package com.framgia.service.impl;

import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.CartProduct;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.CartProductService;

public class CartProductServiceImpl extends BaseServiceImpl implements CartProductService {

  @Override
  public User getUser(Integer cartProductId) {
    Cart cart = getCartProductDAO().getCart(cartProductId);
    if (cart != null)
      return getCartDAO().getUser(cart.getId());
    return null;
  }

  @Override
  public Cart getCart(Integer cartProductId) {
    return getCartProductDAO().getCart(cartProductId);
  }

  @Override
  public Product getProduct(Integer cartProductId) {
    return getCartProductDAO().getProduct(cartProductId);
  }

  @Override
  public List<CartProduct> getCartProducts(List<Integer> cartIds) {
    return getCartProductDAO().getCartProducts(cartIds);
  }
}
