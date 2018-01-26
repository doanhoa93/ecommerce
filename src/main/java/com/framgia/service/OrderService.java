package com.framgia.service;

import java.util.List;

import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.User;

public interface OrderService extends BaseService<Integer, Order> {
  User getUser(Integer orderId);

  List<OrderProduct> getOrderProducts(Integer orderId);

  List<Product> getProducts(Integer orderId);
  
  boolean createOrder(Integer userId, List<Integer> cartIds);
}
