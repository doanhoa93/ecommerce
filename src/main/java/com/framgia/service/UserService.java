package com.framgia.service;

import java.util.List;

import com.framgia.model.Cart;
import com.framgia.model.Comment;
import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Profile;
import com.framgia.model.User;

public interface UserService extends BaseService<Integer, User> {
  User findByEmail(String email);

  Profile getProfile(Integer userId);
  
  Cart getCart(Integer userId, Integer productId);

  List<Order> getOrders(Integer userId);

  List<Cart> getCarts(Integer userId);

  List<Comment> getComments(Integer userId);

  List<OrderProduct> getOrderProducts(Integer userId);

  List<Product> getOrderedProducts(Integer userId);

  List<Product> getCartedProducts(Integer userId);
}
