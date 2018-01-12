package com.framgia.service;

import java.util.List;

import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.User;

public interface OrderProductService extends BaseService {
  User getUser(Integer orderProductId);

  Order getOrder(Integer orderProductId);

  Product getProduct(Integer orderProductId);

  List<OrderProduct> getOrderProducts(List<Integer> orderIds);
}
