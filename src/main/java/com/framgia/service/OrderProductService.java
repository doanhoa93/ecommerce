package com.framgia.service;

import com.framgia.model.Order;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.User;

public interface OrderProductService extends BaseService<Integer, OrderProduct> {
  User getUser(Integer orderProductId);

  Order getOrder(Integer orderProductId);

  Product getProduct(Integer orderProductId);
}
