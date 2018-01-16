package com.framgia.service.impl;

import java.awt.Image;
import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.CartProduct;
import com.framgia.model.Category;
import com.framgia.model.Comment;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Promotion;
import com.framgia.model.Recent;
import com.framgia.model.User;
import com.framgia.service.ProductService;

public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

  @SuppressWarnings("unchecked")
  @Override
  public List<Order> getOrders(Integer productId) {
    List<OrderProduct> orderProducts = getProductDAO().getOrderProducts(productId);
    return (List<Order>) orderProducts.stream().map(OrderProduct::getOrder);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Cart> getCarts(Integer productId) {
    List<CartProduct> cartProducts = getProductDAO().getCartProducts(productId);
    return (List<Cart>) cartProducts.stream().map(CartProduct::getCart);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> getOrderedUser(Integer productId) {
    return (List<User>) getOrders(productId).stream().map(Order::getUser);
  }

  @Override
  public List<OrderProduct> getOrderProducts(Integer productId) {
    return getProductDAO().getOrderProducts(productId);
  }

  @Override
  public List<CartProduct> getCartProducts(Integer productId) {
    return getProductDAO().getCartProducts(productId);
  }

  @Override
  public List<Comment> getComments(Integer productId) {
    return getProductDAO().getComments(productId);
  }

  @Override
  public List<Image> getImages(Integer productId) {
    return getProductDAO().getImages(productId);
  }

  @Override
  public List<Product> getProducts(List<Integer> productIds) {
    return getProductDAO().getProducts(productIds);
  }

  @Override
  public Recent getRecent(Integer productId) {
    return getProductDAO().getRecent(productId);
  }

  @Override
  public Promotion getPromotion(Integer productId) {
    return getProductDAO().getPromotion(productId);
  }

  @Override
  public Category getCategory(Integer productId) {
    return getProductDAO().getCategory(productId);
  }
}