package com.framgia.service.impl;

import java.util.List;

import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.User;
import com.framgia.service.OrderProductService;

public class OrderProductServiceImpl extends BaseServiceImpl implements OrderProductService {

  @Override
  public User getUser(Integer orderProductId) {
    Order order = getOrderProductDAO().getOrder(orderProductId);
    if (order != null)
      return getOrderDAO().getUser(order.getId());
    return null;
  }

  @Override
  public Order getOrder(Integer orderProductId) {
    return getOrderProductDAO().getOrder(orderProductId);
  }

  @Override
  public Product getProduct(Integer orderProductId) {
    return getOrderProductDAO().getProduct(orderProductId);
  }

  @Override
  public List<OrderProduct> getOrderProducts(List<Integer> orderIds) {
    return getOrderProductDAO().getOrderProducts(orderIds);
  }
}
