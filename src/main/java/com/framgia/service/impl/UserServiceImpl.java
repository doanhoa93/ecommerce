package com.framgia.service.impl;

import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.CartProduct;
import com.framgia.model.Comment;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Profile;
import com.framgia.model.User;
import com.framgia.service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {

  @Override
  public User findByEmail(String email) {
    return getUserDAO().findByEmail(email);
  }

  @Override
  public Profile getProfile(Integer userId) {
    return getUserDAO().getProfile(userId);
  }

  @Override
  public List<Order> getOrders(Integer userId) {
    return getUserDAO().getOrders(userId);
  }

  @Override
  public List<Cart> getCarts(Integer userId) {
    return getUserDAO().getCarts(userId);
  }

  @Override
  public List<Comment> getComments(Integer userId) {
    return getUserDAO().getComments(userId);
  }

  @Override
  public List<User> getUsers(List<Integer> userIds) {
    return getUserDAO().getUsers(userIds);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<OrderProduct> getOrderProducts(Integer userId) {
    List<Order> orders = getUserDAO().getOrders(userId);
    List<Integer> orderIds = (List<Integer>) orders.stream().map(Order::getId);
    return getOrderProductDAO().getOrderProducts(orderIds);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<CartProduct> getCartProducts(Integer userId) {
    List<Cart> carts = getUserDAO().getCarts(userId);
    List<Integer> cartIds = (List<Integer>) carts.stream().map(Cart::getId);
    return getCartProductDAO().getCartProducts(cartIds);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Product> getOrderedProducts(Integer userId) {
    return (List<Product>) getOrderProducts(userId).stream().map(OrderProduct::getProduct);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Product> getCartedProducts(Integer userId) {
    return (List<Product>) getCartProducts(userId).stream().map(CartProduct::getProduct);
  }
}
