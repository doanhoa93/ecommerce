package com.framgia.service;

import java.util.List;

import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.User;

public interface OrderService extends BaseService {
  User getUser(Integer orderId);

  List<OrderProduct> getOrderProducts(Integer orderId);

  List<Order> getOrders(List<Integer> orderIds);

  List<Product> getProducts(Integer orderId);
}
