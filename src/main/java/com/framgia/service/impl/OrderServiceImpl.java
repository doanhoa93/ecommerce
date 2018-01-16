package com.framgia.service.impl;

import java.util.List;

import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

  @Override
  public User getUser(Integer orderId) {
    return getOrderDAO().getUser(orderId);
  }

  @Override
  public List<OrderProduct> getOrderProducts(Integer orderId) {
    return getOrderDAO().getOrderProducts(orderId);
  }

  @Override
  public List<Order> getOrders(List<Integer> orderIds) {
    return getOrderDAO().getOrders(orderIds);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Product> getProducts(Integer orderId) {
    List<OrderProduct> orderProducts = getOrderDAO().getOrderProducts(orderId);
    return (List<Product>) orderProducts.stream().map(OrderProduct::getProduct);
  }
}
